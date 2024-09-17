package bacal.johnrey.expresslike.route;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

/**
 * Handler for registered route in the Registry
 */
public class Handler implements HttpHandler {
    private Registry registry;

    public Handler(Registry registry) {
        this.registry = registry;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Thread handler = new Thread(new HandlerThread(exchange, registry));
        handler.start();
    }
}
