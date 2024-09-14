package bacal.johnrey;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import bacal.johnrey.expresslike.http.Method;
import bacal.johnrey.expresslike.http.Middleware;
import bacal.johnrey.expresslike.route.Handler;
import bacal.johnrey.expresslike.route.Registry;
import bacal.johnrey.expresslike.route.Route;
import bacal.johnrey.expresslike.route.Router;

public class App extends Router {
    private HttpServer server;
    private Registry registry;

    public App() throws IOException {
        super("");
        server = HttpServer.create();
        registry = new Registry();

        Handler handler = new Handler(registry);
        server.createContext("/", handler);
    }

    public void listen(int port, int backlog) throws IOException {
        server.bind(new InetSocketAddress(port), backlog);
        server.start();
    }

    public void listen(int port) throws IOException {
        listen(port, 0);
    }

    public void use(Route route) {
        registry.register(route);
    }

    public void use(Router router) {
        registry.register(router.getRoutes().toArray(new Route[0]));
    }

    @Override
    protected void on(Method method, String url, Middleware... middlewares) {
        Route route = new Route(url, method, middlewares);
        this.registry.register(route);
    }

}
