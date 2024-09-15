package bacal.johnrey.expresslike.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sun.net.httpserver.HttpExchange;

/**
 * Wrapper for HttpExchange. Only contains members necessary for a Response
 */
public class Response {

    private final HttpExchange exchange;
    private boolean sent;

    public Response(HttpExchange exchange) {
        this.exchange = exchange;
        sent = false;
    }

    public Response setContentType(String[] contentType) {
        exchange.getResponseHeaders().put("Content-Type", Arrays.asList(contentType));

        return this;
    }

    public void send(int statusCode, Object data) throws IOException {
        if (sent) {
            return;
        }
        String response = data.toString();

        exchange.sendResponseHeaders(statusCode, response.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        sent = true;
    }

    public void sendJson(int statusCode, Object data) throws IOException {
        List<String> contentType = new ArrayList<>();
        contentType.add("application/json");
        exchange.getResponseHeaders().put("Content-Type", contentType);
        this.send(statusCode, data);
    }

    public HttpExchange getExchange() {
        return this.exchange;
    }
}
