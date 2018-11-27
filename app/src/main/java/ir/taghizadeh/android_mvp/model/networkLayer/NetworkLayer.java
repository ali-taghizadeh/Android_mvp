package ir.taghizadeh.android_mvp.model.networkLayer;

import io.reactivex.functions.Consumer;

public interface NetworkLayer {
    void readJson(Consumer<String> finished);
}
