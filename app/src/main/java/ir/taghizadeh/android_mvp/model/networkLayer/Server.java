package ir.taghizadeh.android_mvp.model.networkLayer;

import java.io.IOException;
import fi.iki.elonen.NanoHTTPD;

public class Server extends NanoHTTPD {

    public Server() throws IOException {
        super(8080);
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, false);
    }

    @Override
    public Response serve(IHTTPSession session) {
        String json = LocalJsonLoader.loader.load("res/raw/homes.json");
        return newFixedLengthResponse(Response.Status.OK, "application/json", json);
    }
}
