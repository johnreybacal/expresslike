package bacal.johnrey.expresslike;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import com.sun.net.httpserver.HttpServer;

import bacal.johnrey.expresslike.http.Method;
import bacal.johnrey.expresslike.http.Middleware;
import bacal.johnrey.expresslike.route.Handler;
import bacal.johnrey.expresslike.route.Registry;
import bacal.johnrey.expresslike.route.Route;
import bacal.johnrey.expresslike.route.Router;

/**
 * An express-like server
 */
public class App extends Router {
    private HttpServer server;
    private Registry registry;

    /**
     * Creates a new server
     *
     * @throws IOException
     */
    public App() throws IOException {
        super("");
        server = HttpServer.create();
        registry = new Registry();

        Handler handler = new Handler(registry);
        server.createContext("/", handler);
    }

    /**
     * Start the server
     */
    public void listen(int port, int backlog) throws IOException {
        server.bind(new InetSocketAddress(port), backlog);
        server.start();
    }

    /**
     * Start the server
     */
    public void listen(int port) throws IOException {
        listen(port, 0);
    }

    /**
     * Register a router to the app
     *
     * @param router
     */
    public void use(Router router) {
        registry.register(router.getRoutes().toArray(new Route[0]));
    }

    @Override
    protected void on(Method method, String url, Middleware... middlewares) {
        Route route = new Route(url, method, middlewares);
        this.registry.register(route);
    }

    @Override
    public List<Route> getRoutes() {
        return registry.getRoutes();
    }

}
