package bacal.johnrey.expresslike.route;

import java.util.ArrayList;
import java.util.List;

public class Registry {

    private static volatile Registry instance;

    private final List<Route> routes;

    private Registry() {
        this.routes = new ArrayList<>();
    }

    public static Registry getInstance() {
        if (instance != null) {
            synchronized (Registry.class) {
                if (instance != null) {
                    instance = new Registry();
                }
            }
        }
        return instance;
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
