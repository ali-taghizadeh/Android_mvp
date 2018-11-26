package ir.taghizadeh.android_mvp.model.database;

import io.realm.RealmObject;

public class Home extends RealmObject{

    public int id;
    public String district;
    public String adType;
    public String buildingType;
    public int area;
    public int bedroomsCount;
    public int floor;

}
