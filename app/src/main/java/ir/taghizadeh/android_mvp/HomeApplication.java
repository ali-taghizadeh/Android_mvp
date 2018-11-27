package ir.taghizadeh.android_mvp;

import android.app.Application;

import java.io.IOException;

import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.networkLayer.Server;

public class HomeApplication extends Application {

    Server server;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
