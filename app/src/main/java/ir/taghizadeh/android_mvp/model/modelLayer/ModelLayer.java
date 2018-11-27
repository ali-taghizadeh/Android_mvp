package ir.taghizadeh.android_mvp.model.modelLayer;

import java.util.List;

import io.reactivex.functions.Consumer;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.Source;

public interface ModelLayer {

    void loadData(Consumer<List<HomeDTO>> listConsumer, Consumer<Source> notifyDataReceived);
    HomeDTO homeById (int homeId);

}
