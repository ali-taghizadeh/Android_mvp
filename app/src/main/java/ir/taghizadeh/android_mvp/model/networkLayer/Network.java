package ir.taghizadeh.android_mvp.model.networkLayer;

import java.io.IOException;

import io.reactivex.functions.Consumer;
import ir.taghizadeh.android_mvp.utils.Threading;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Network implements NetworkLayer {

    @Override
    public void readJson(Consumer<String> finished) {
        Threading.async(this::request, finished, null);
    }

    private String request() {
        String result = "";
        try {
            result = response("http://localhost:8080/");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private String response(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
