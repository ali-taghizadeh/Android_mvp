package ir.taghizadeh.android_mvp.model.conversionLayer;

import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.dataLayer.Home;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;

public interface HomeConverter {
    HomeDTO convert (Home from);
    Home convert (HomeDTO dto, Realm realm);
}
