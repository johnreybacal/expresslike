package bacal.johnrey.expresslike.http;

import bacal.johnrey.expresslike.exception.ServerException;

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
