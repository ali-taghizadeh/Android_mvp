package ir.taghizadeh.android_mvp.model.conversionLayer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.dataLayer.Home;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.DTOType;

public class Conversion implements ConversionLayer {

    private Gson gson;

    private HomeConverter homeConverter;

    public Conversion(Gson gson, HomeConverter homeConverter) {
        this.gson = gson;
        this.homeConverter = homeConverter;
    }

    @Override
    public List<HomeDTO> convertJson (String json) {
        TypeToken<List<HomeDTO>> token = new TypeToken<List<HomeDTO>>(){};
        return gson.fromJson(json, token.getType());
    }

    @Override
    public HomeConverter convertFor (DTOType type){
        switch (type) {
            case home:
                return homeConverter;
            default:
                return homeConverter;
        }
    }

    @Override
    public HomeDTO convert (Home home) {
        return homeConverter.convert(home);
    }

    @Override
    public Home convert (HomeDTO dto, Realm realm) {
        return homeConverter.convert(dto, realm);
    }
}
