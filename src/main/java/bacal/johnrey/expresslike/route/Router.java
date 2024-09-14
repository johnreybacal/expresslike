package bacal.johnrey.expresslike.route;

import java.util.ArrayList;
import java.util.List;

import bacal.johnrey.expresslike.http.Method;
import bacal.johnrey.expresslike.http.Middleware;

/**
 * Associate routes to a base URL
 */
public class Router {

    private String url;
    private final List<Route> routes;

    public Router(String url) {
        this.url = url;
        routes = new ArrayList<>();
    }

    protected Router() {
        routes = new ArrayList<>();
    }

    protected void on(Method method, String url, Middleware... middlewares) {
        Route route = new Route(this.url + url, method, middlewares);
        this.routes.add(route);
    }

    /**
     * Map middlewares to a GET request on url
     *
     * @param url
     * @param middlewares
     */
    public void get(String url, Middleware... middlewares) {
        on(Method.GET, url, middlewares);
    }

    /**
     * Map middlewares to a POST request on url
     *
     * @param url
     * @param middlewares
     */
    public void post(String url, Middleware... middlewares) {
        on(Method.POST, url, middlewares);
    }

    /**
     * Map middlewares to a PUT request on url
     *
     * @param url
     * @param middlewares
     */
    public void put(String url, Middleware... middlewares) {
        on(Method.PUT, url, middlewares);
    }

    /**
     * Map middlewares to a PATCH request on url
     *
     * @param url
     * @param middlewares
     */
    public void patch(String url, Middleware... middlewares) {
        on(Method.PATCH, url, middlewares);
    }

    /**
     * Map middlewares to a DELETE request on url
     *
     * @param url
     * @param middlewares
     */
    public void delete(String url, Middleware... middlewares) {
        on(Method.DELETE, url, middlewares);
    }

    public List<Route> getRoutes() {
        return routes;
    }
}
