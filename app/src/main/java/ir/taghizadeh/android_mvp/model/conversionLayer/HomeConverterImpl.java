package ir.taghizadeh.android_mvp.model.conversionLayer;

import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.dataLayer.Home;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;

public class HomeConverterImpl implements HomeConverter {

    @Override
    public HomeDTO convert (Home from) {

        if (from == null) return null;
        return new HomeDTO(from.id,
                from.district,
                from.adType,
                from.buildingType,
                from.area,
                from.bedroomsCount,
                from.floor);
    }

    @Override
    public Home convert (HomeDTO dto, Realm realm) {

        if (dto == null) return null;
        realm.executeTransaction(realm1 -> {
            Home home = realm1.createObject(Home.class);
            home.id              = dto.id;
            home.district        = dto.district;
            home.adType          = dto.adType;
            home.buildingType    = dto.buildingType;
            home.area            = dto.area;
            home.bedroomsCount   = dto.bedroomsCount;
            home.floor           = dto.floor;
        });
        return realm.where(Home.class).equalTo("id", dto.id).findFirst();
    }

}
