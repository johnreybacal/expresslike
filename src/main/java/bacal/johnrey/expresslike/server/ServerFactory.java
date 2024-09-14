package bacal.johnrey.expresslike.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import bacal.johnrey.expresslike.route.Handler;

public class ServerFactory {
    public static HttpServer create(int port, int backlog) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        Handler handler = new Handler();
        server.createContext("/", handler);

        return server;
    }

    public static HttpServer create(int port) throws IOException {
        return ServerFactory.create(port);
    }
}
