package bacal.johnrey.expresslike.route;

import java.io.IOException;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import bacal.johnrey.expresslike.exception.ServerException;
import bacal.johnrey.expresslike.http.Middleware;
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

                    Middleware[] middlewares = route.getMiddlewares();
                    for (int i = 0; i < middlewares.length; i++) {
                        middlewares[i].resolve(request, response);
                    }

                    return;
                }
            }
            throw ServerException.notFound();
        } catch (ServerException e) {
            exchange.sendResponseHeaders(e.getStatusCode(), e.getMessage().length());
            exchange.getResponseBody().write(e.getMessage().getBytes());
        } finally {
            exchange.close();
        }
    }
}
