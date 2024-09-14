package bacal.johnrey.expresslike.route;

import java.util.ArrayList;
import java.util.List;

/**
 * Registry of routes
 */
public class Registry {

    private final List<Route> routes;

    public Registry() {
        this.routes = new ArrayList<>();
    }

    /**
     * Register a route
     *
     * @param route
     */
    public void register(Route... route) {
        this.routes.addAll(routes);
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
