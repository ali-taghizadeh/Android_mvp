package ir.taghizadeh.android_mvp.model.conversionLayer;

import java.util.List;

import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.dataLayer.Home;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.DTOType;

public interface ConversionLayer {

    List<HomeDTO> convertJson (String json);

    HomeConverter convertFor (DTOType type);

    HomeDTO convert (Home home);

    Home convert (HomeDTO dto, Realm realm);
}
