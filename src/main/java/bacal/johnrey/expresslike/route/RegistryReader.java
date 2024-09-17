package bacal.johnrey.expresslike.route;

import java.util.List;

import bacal.johnrey.expresslike.http.Middleware;

public interface RegistryReader {
    /**
     * Get routes
     *
     * @return
     */
    public List<Route> getRoutes();

    /**
     * Get pre-route middlewares
     *
     * @return
     */
    public List<Middleware> getPreRouteMiddlewares();

    /**
     * Get post-route middlewares
     */
    public List<Middleware> getPostRouteMiddlewares();

    /**
     * Generate list of middlewares wrapping the route middlewares
     *
     * @param route
     * @return
     */
    public Middleware[] wrapRouteMiddlewares(Route route);
}
