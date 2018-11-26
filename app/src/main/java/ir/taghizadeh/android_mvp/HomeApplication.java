package ir.taghizadeh.android_mvp;

import android.app.Application;
import io.realm.Realm;

public class HomeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
