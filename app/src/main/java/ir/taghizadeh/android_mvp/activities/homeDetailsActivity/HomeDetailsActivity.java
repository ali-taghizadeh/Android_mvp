package ir.taghizadeh.android_mvp.activities.homeDetailsActivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ir.taghizadeh.android_mvp.R;
import ir.taghizadeh.android_mvp.dependencies.DependencyRegistry;

public class HomeDetailsActivity extends AppCompatActivity{

    private HomeDetailsPresenter presenter;
    private ImageView image_details_avatar;
    private TextView text_details_district;
    private TextView text_details_building_type;
    private TextView text_details_ad_type;
    private TextView text_details_badge_bedroom;
    private TextView text_details_badge_floor;
    private TextView text_details_area;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_details);
        setupUI();
        Bundle bundle = getIntent().getExtras();
        DependencyRegistry.register.inject(this, bundle);
    }

    private void setupUI() {
        image_details_avatar = findViewById(R.id.image_details_avatar);
        text_details_district = findViewById(R.id.text_details_district);
        text_details_building_type = findViewById(R.id.text_details_building_type);
        text_details_ad_type = findViewById(R.id.text_details_ad_type);
        text_details_badge_bedroom = findViewById(R.id.text_details_badge_bedroom);
        text_details_badge_floor = findViewById(R.id.text_details_badge_floor);
        text_details_area = findViewById(R.id.text_details_area);
    }

    public void configureWith(HomeDetailsPresenter presenter) {
        this.presenter = presenter;
        text_details_district.setText(presenter.getDistrict());
        text_details_building_type.setText(presenter.getBuildingType());
        text_details_ad_type.setText(String.format("FOR %s", presenter.getAdType().toUpperCase()));
        text_details_badge_bedroom.setText(presenter.getBedroomsCount());
        text_details_badge_floor.setText(presenter.getFloor());
        text_details_area.setText(String.format("%s %s", presenter.getArea(), getString(R.string.m2)));
        String imageName = String.format(getString(R.string.image), presenter.getHomeId()).toLowerCase();
        int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
        image_details_avatar.setImageResource(resourceId);
    }
}
