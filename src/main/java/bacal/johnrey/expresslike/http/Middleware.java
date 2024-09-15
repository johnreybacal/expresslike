package bacal.johnrey.expresslike.http;

import bacal.johnrey.expresslike.exception.ServerException;

import java.io.IOException;

/**
 * Functional interface for middlewares
 */
public interface Middleware {
    /**
     * Callback for resolving middlewares
     *
     * @param request
     * @param response
     * @throws ServerException
     */
    public void resolve(Throwable error, Request request, Response response, Next next) throws ServerException, IOException;
}
