package ir.taghizadeh.android_mvp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import ir.taghizadeh.android_mvp.R;
import ir.taghizadeh.android_mvp.model.networkLayer.Network;

public class HomeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
    }

}
