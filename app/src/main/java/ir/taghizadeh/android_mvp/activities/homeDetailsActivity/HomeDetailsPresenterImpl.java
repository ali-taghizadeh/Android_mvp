package ir.taghizadeh.android_mvp.activities.homeDetailsActivity;

import android.content.Context;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.modelLayer.Model;
import ir.taghizadeh.android_mvp.model.modelLayer.ModelLayer;

public class HomeDetailsPresenterImpl implements HomeDetailsPresenter {

    private int id;
    private String district;
    private String adType;
    private String buildingType;
    private int area;
    private int bedroomsCount;
    private int floor;
    private Context context;
    private ModelLayer modelLayer;

    public HomeDetailsPresenterImpl(int homeId, Context context, ModelLayer modelLayer) {
        this.id = homeId;
        this.context = context;
        this.modelLayer = modelLayer;
        configureData();
    }

    private void configureData() {
        HomeDTO homeDTO = modelLayer.homeById(id);
        district = homeDTO.district;
        adType = homeDTO.adType;
        buildingType = homeDTO.buildingType;
        area = homeDTO.area;
        bedroomsCount = homeDTO.bedroomsCount;
        floor = homeDTO.floor;
    }

    @Override
    public int getHomeId() {
        return id;
    }

    @Override
    public String getDistrict() {
        return district;
    }

    @Override
    public String getAdType() {
        return adType;
    }

    @Override
    public String getBuildingType() {
        return buildingType;
    }

    @Override
    public String getArea() {
        return String.valueOf(area);
    }

    @Override
    public String getBedroomsCount() {
        return String.valueOf(bedroomsCount);
    }

    @Override
    public String getFloor() {
        return String.valueOf(floor);
    }
}
