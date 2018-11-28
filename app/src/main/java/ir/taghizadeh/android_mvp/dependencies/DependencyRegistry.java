package ir.taghizadeh.android_mvp.dependencies;

import android.os.Bundle;

import com.google.gson.Gson;

import java.util.NoSuchElementException;

import io.realm.Realm;
import ir.taghizadeh.android_mvp.activities.homeDetailsActivity.HomeDetailsActivity;
import ir.taghizadeh.android_mvp.activities.homeDetailsActivity.HomeDetailsPresenter;
import ir.taghizadeh.android_mvp.activities.homeDetailsActivity.HomeDetailsPresenterImpl;
import ir.taghizadeh.android_mvp.activities.homeListActivity.HomeListActivity;
import ir.taghizadeh.android_mvp.activities.homeListActivity.HomeListPresenter;
import ir.taghizadeh.android_mvp.activities.homeListActivity.HomeListPresenterImpl;
import ir.taghizadeh.android_mvp.model.conversionLayer.Conversion;
import ir.taghizadeh.android_mvp.model.conversionLayer.ConversionLayer;
import ir.taghizadeh.android_mvp.model.conversionLayer.HomeConverter;
import ir.taghizadeh.android_mvp.model.conversionLayer.HomeConverterImpl;
import ir.taghizadeh.android_mvp.model.dataLayer.Data;
import ir.taghizadeh.android_mvp.model.dataLayer.DataLayer;
import ir.taghizadeh.android_mvp.model.modelLayer.Model;
import ir.taghizadeh.android_mvp.model.modelLayer.ModelLayer;
import ir.taghizadeh.android_mvp.model.networkLayer.Network;
import ir.taghizadeh.android_mvp.model.networkLayer.NetworkLayer;

public class DependencyRegistry {

    public static  DependencyRegistry register = new DependencyRegistry();

    private Gson gson = new Gson();
    private Realm realm = Realm.getDefaultInstance();
    private Realm newRealmInstanceOnCurrentThread() {
        return Realm.getInstance(realm.getConfiguration());
    }

    private HomeConverter homeConverter = new HomeConverterImpl();

    private ConversionLayer conversionLayer = createConversionLayer();
    private ConversionLayer createConversionLayer() {
        return new Conversion(gson, homeConverter);
    }

    private DataLayer dataLayer = createDataLayer();
    private DataLayer createDataLayer() {
        return new Data(realm, this::newRealmInstanceOnCurrentThread);
    }

    private NetworkLayer networkLayer = new Network();

    private ModelLayer modelLayer = createModelLayer();
    private ModelLayer createModelLayer() {
        return new Model(networkLayer, dataLayer, conversionLayer);
    }

    public void inject(HomeListActivity activity) {
        HomeListPresenter presenter = new HomeListPresenterImpl(modelLayer);
        activity.configureWith(presenter);
    }

    public void inject(HomeDetailsActivity activity, Bundle bundle) throws NoSuchElementException {
        int homeId = idFromBundle(bundle);
        HomeDetailsPresenter presenter = new HomeDetailsPresenterImpl(homeId, activity, modelLayer);
        activity.configureWith(presenter);
    }

    private int idFromBundle(Bundle bundle) {
        if(bundle == null) throw new NoSuchElementException("Unable to get id from bundle");
        int homeId = bundle.getInt("id");
        if(homeId == 0) throw new NoSuchElementException("Unable to get id from bundle");
        return homeId;
    }

}
