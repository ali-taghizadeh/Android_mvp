package ir.taghizadeh.android_mvp.activities;

import java.util.List;
import io.reactivex.functions.Consumer;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.Source;

public interface HomeListPresenter {
    void loadData(Consumer<List<HomeDTO>> onNewResults, Consumer<Source> notifyDataReceived);
}
