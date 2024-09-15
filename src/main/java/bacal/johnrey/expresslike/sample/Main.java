package bacal.johnrey.expresslike.sample;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;

import bacal.johnrey.expresslike.App;
import bacal.johnrey.expresslike.exception.ServerException;

public class Main {
    public static void main(String[] args) throws IOException {
        App app = new App();

        app.get("/hello", (err, req, res, next) -> {
            System.out.println("Middleware 1");
            next.resolve(null);
        }, (err, req, res, next) -> {
            System.out.println("Middleware 1.2");
            next.resolve(null);
        }, (err, req, res, next) -> {
            System.out.println("Middleware 2");
            res.send(200, "OK");
        }, (err, req, res, next) -> {
            HttpExchange exchange = res.getExchange();
            System.out.println(exchange.getRequestMethod() + " "
                    + exchange.getRequestURI().getPath() + " "
                    + exchange.getResponseCode() + " - "
                    + exchange.getResponseHeaders().getFirst("Content-length"));
        });
        app.get("/exception", (err, req, res, next) -> {
            System.out.println("Middleware 1");
            next.resolve(null);
        }, (err, req, res, next) -> {
            System.out.println("Middleware 1.2");
            next.resolve(new ServerException(500, "Oh noes"));
        }, (err, req, res, next) -> {
            if (err != null) {
                System.out.println(err);
                res.send(500, "NG");
            } else {
                System.out.println("Middleware 2");
                res.send(200, "OK");
            }
        }, (err, req, res, next) -> {
            HttpExchange exchange = res.getExchange();
            System.out.println(exchange.getRequestMethod() + " "
                    + exchange.getRequestURI().getPath() + " "
                    + exchange.getResponseCode() + " - "
                    + exchange.getResponseHeaders().getFirst("Content-length"));
        });
        System.out.println(app.getRoutes());

        app.listen(8080);
    }
}
