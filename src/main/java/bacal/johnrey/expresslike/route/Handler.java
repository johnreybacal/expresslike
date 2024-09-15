package bacal.johnrey.expresslike.route;

import java.io.IOException;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import bacal.johnrey.expresslike.exception.ServerException;
import bacal.johnrey.expresslike.http.Middleware;
import bacal.johnrey.expresslike.http.Next;
import bacal.johnrey.expresslike.http.Request;
import bacal.johnrey.expresslike.http.Response;

/**
 * Handler for registered route in the Registry
 */
public class Handler implements HttpHandler {
    private Registry registry;

    public Handler(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            List<Route> routes = registry.getRoutes();

            for (Route route : routes) {
                if (Helper.isRouteMatch(route, exchange.getRequestMethod(), exchange.getRequestURI().getPath())) {
                    Request request = new Request(exchange);
                    Response response = new Response(exchange);
                    Helper.setRequestParameters(request, route.getParameters());

                    final Middleware[] middlewares = route.getMiddlewares().clone();
                    int middlewaresLength = middlewares.length;
                    Next next = null;
                    final Next[] nexts = new Next[middlewaresLength];

                    // Assign the next for each middleware
                    // From last element
                    for (int i = middlewaresLength - 1; i > 0; i--) {
                        final int index = i;
                        // Create a next call
                        next = (t) -> {
                            // if the next is invoked in the prior middleware
                            Middleware middleware = middlewares[index];
                            // set the index to null so that it won't be invoked in the next invocation
                            // iteration
                            middlewares[index] = null;

                            // Resolve this middleware
                            middleware.resolve(t, request, response, nexts[index]);
                        };
                        // Assign the next to be used by the middleware that comes first
                        nexts[i - 1] = next;
                    }

                    // For each middleware
                    for (int i = 0; i < middlewaresLength; i++) {
                        // Skip if null
                        if (middlewares[i] != null) {
                            middlewares[i].resolve(null, request, response, nexts[i]);
                        }
                    }

                    return;
                }
            }
            throw ServerException.notFound();
        } catch (ServerException e) {
            exchange.sendResponseHeaders(e.getStatusCode(), e.getMessage().length());
            exchange.getResponseBody().write(e.getMessage().getBytes());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            exchange.close();
        }
    }
}
