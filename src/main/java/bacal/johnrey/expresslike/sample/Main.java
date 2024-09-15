package bacal.johnrey.expresslike.sample;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import bacal.johnrey.expresslike.App;
import bacal.johnrey.expresslike.exception.ServerException;
import bacal.johnrey.expresslike.http.Middleware;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();

        Middleware authMiddleware = (err, req, res, next) -> {
            System.out.println("User is authorized!");
            next.resolve(null);
        };

        Middleware errorMiddleware = (err, req, res, next) -> {
            if (err != null) {
                System.out.println(err);
                if (err instanceof ServerException) {
                    res.send(((ServerException) err).getStatusCode(), "Handled: " + err.getMessage());
                } else {
                    res.send(500, "Unhandled: " + err.getMessage());
                }
            }
        };

        Middleware logMiddleware = (err, req, res, next) -> {
            HttpExchange exchange = res.getExchange();
            System.out.println(exchange.getRequestMethod() + " "
                    + exchange.getRequestURI().getPath() + " "
                    + exchange.getResponseCode() + " - "
                    + exchange.getResponseHeaders().getFirst("Content-length"));
        };

        app.get("/hello",
                authMiddleware,
                (err, req, res, next) -> {
                    System.out.println("Middleware 2");
                    String name = req.getQuery().get("name");
                    String response = "Hello";
                    if (name != null) {
                        response += ", " + name;
                    }
                    res.send(200, response);
                },
                errorMiddleware,
                logMiddleware);
        app.get("/exception",
                authMiddleware,
                (err, req, res, next) -> {
                    next.resolve(new ServerException(400, "Oh noes"));
                    res.send(200, "OK");
                },
                errorMiddleware,
                logMiddleware);
        app.get("/exception/unhandled",
                authMiddleware,
                (err, req, res, next) -> {
                    next.resolve(new Exception("Oh noes"));
                    res.send(200, "OK");
                },
                errorMiddleware,
                logMiddleware);
        System.out.println(app.getRoutes());

        app.listen(8080);
    }
}
