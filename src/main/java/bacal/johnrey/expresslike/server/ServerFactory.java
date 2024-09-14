package bacal.johnrey.expresslike.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class ServerFactory {
    public static HttpServer create(int port, int backlog) throws IOException {
        return HttpServer.create(new InetSocketAddress(port), 0);
    }

    public static HttpServer create(int port) throws IOException {
        return ServerFactory.create(port);
    }
}
