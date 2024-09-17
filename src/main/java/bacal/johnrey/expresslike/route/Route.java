package bacal.johnrey.expresslike.route;

import java.util.List;
import java.util.Map;

import bacal.johnrey.expresslike.http.Method;
import bacal.johnrey.expresslike.http.Middleware;

/**
 * Mapping of middlewares to a request
 */
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

        List<String> urlSegments = Helper.getSegments(url);
        this.parameters = Helper.getParameters(urlSegments);

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

    @Override
    public String toString() {
        return method.name() + " " + url;
    }

}
