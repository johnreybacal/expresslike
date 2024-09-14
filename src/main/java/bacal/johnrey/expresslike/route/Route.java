package bacal.johnrey.expresslike.route;

import java.util.HashMap;
import java.util.Map;

import bacal.johnrey.expresslike.http.Method;
import bacal.johnrey.expresslike.http.Middleware;

public class Route {
    private final String url;
    private final Method method;
    private final Map<Integer, String> parameters;
    private final Middleware[] middlewares;

    public Route(String url,
            Method method,
            Middleware... middlewares) {
        this.url = url;
        this.method = method;
        this.parameters = new HashMap<>();
        this.middlewares = middlewares;
    }

    public Route(String url,
            Method method,
            Map<Integer, String> parameters,
            Middleware... middlewares) {
        this.url = url;
        this.method = method;
        this.parameters = parameters;
        this.middlewares = middlewares;
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method.toString();
    }

    public Map<Integer, String> getParameters() {
        return parameters;
    }

    public Middleware[] getMiddlewares() {
        return middlewares;
    }

}
