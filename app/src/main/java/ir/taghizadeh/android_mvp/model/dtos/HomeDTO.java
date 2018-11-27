package ir.taghizadeh.android_mvp.model.dtos;

import ir.taghizadeh.android_mvp.model.enums.DTOType;

public class HomeDTO {

    public static DTOType dtoType = DTOType.home;

    public int id;
    public String district;
    public String adType;
    public String buildingType;
    public int area;
    public int bedroomsCount;
    public int floor;

    public HomeDTO (int id, String district, String adType, String buildingType, int area, int bedroomsCount, int floor) {
        this.id = id;
        this.district = district;
        this.adType = adType;
        this.buildingType = buildingType;
        this.area = area;
        this.bedroomsCount = bedroomsCount;
        this.floor = floor;
    }

}
