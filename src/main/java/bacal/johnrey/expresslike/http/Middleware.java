package bacal.johnrey.expresslike.http;

import bacal.johnrey.expresslike.exception.ServerException;

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
    public void resolve(Request request, Response response) throws ServerException;
}
