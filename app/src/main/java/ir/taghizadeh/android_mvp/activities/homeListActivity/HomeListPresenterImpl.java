package ir.taghizadeh.android_mvp.activities.homeListActivity;

import java.util.List;

import io.reactivex.functions.Consumer;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.Source;
import ir.taghizadeh.android_mvp.model.modelLayer.ModelLayer;

public class HomeListPresenterImpl implements HomeListPresenter {

    private ModelLayer modelLayer;

    public HomeListPresenterImpl(ModelLayer modelLayer) {
        this.modelLayer = modelLayer;
    }

    @Override
    public void loadData(Consumer<List<HomeDTO>> onNewResults, Consumer<Source> notifyDataReceived) {
        modelLayer.loadData(onNewResults, notifyDataReceived);
    }
}
