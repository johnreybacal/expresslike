package bacal.johnrey.expresslike.route;

import bacal.johnrey.expresslike.http.Middleware;

public interface RegistryWriter extends RegistryReader {
    /**
     * Register a route
     *
     * @param route
     */
    public void register(Route... route);

    /**
     * Register a middleware before route
     *
     * @param middleware
     */
    public void addBeforeRoute(Middleware... middleware);

    /**
     * Register a middleware after route
     *
     * @param middleware
     */
    public void addAfterRoute(Middleware... middleware);
}
