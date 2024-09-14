package bacal.johnrey;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class App {
    private HttpServer server;

    public App() throws IOException {
        this.server = HttpServer.create();
    }

    public void listen(int port, int backlog) throws IOException {
        this.server.bind(new InetSocketAddress(port), backlog);
    }

    public void listen(int port) throws IOException {
        listen(port, 0);
    }

}
