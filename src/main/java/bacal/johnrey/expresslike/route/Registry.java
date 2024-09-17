package bacal.johnrey.expresslike.route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bacal.johnrey.expresslike.http.Middleware;

/**
 * Registry of routes
 */
public class Registry {

    private final List<Middleware> preRouteMiddlewares;
    private final List<Middleware> postRouteMiddlewares;
    private final List<Route> routes;

    public Registry() {
        this.preRouteMiddlewares = new ArrayList<>();
        this.postRouteMiddlewares = new ArrayList<>();
        this.routes = new ArrayList<>();
    }

    /**
     * Register a route
     *
     * @param route
     */
    public void register(Route... route) {
        this.routes.addAll(Arrays.asList(route));
    }

    /**
     * Register a middleware before route
     *
     * @param middleware
     */
    public void addBeforeRoute(Middleware... middleware) {
        this.preRouteMiddlewares.addAll(Arrays.asList(middleware));
    }

    /**
     * Register a middleware after route
     *
     * @param middleware
     */
    public void addAfterRoute(Middleware... middleware) {
        this.postRouteMiddlewares.addAll(Arrays.asList(middleware));
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Middleware> getPreRouteMiddlewares() {
        return preRouteMiddlewares;
    }

    public List<Middleware> getPostRouteMiddlewares() {
        return postRouteMiddlewares;
    }

    public Middleware[] wrapRouteMiddlewares(Route route) {
        List<Middleware> middlewaresList = new ArrayList<>();
        middlewaresList.addAll(preRouteMiddlewares);
        middlewaresList.addAll(Arrays.asList(route.getMiddlewares()));
        middlewaresList.addAll(postRouteMiddlewares);

        return middlewaresList.toArray(new Middleware[0]);
    }
}
