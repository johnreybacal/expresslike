package bacal.johnrey.expresslike.http;

import java.io.IOException;

import bacal.johnrey.expresslike.exception.ServerException;

public interface Next {
    public void resolve(Throwable t) throws ServerException, IOException;
}
