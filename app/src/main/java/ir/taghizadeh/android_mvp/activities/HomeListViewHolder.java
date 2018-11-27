package ir.taghizadeh.android_mvp.activities;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import ir.taghizadeh.android_mvp.R;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;

public class HomeListViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView image_item_avatar;
    private TextView text_item_district;
    private TextView text_item_building_type;
    private TextView text_item_ad_type;
    private TextView text_item_badge_bedroom;
    private TextView text_item_badge_floor;
    private TextView text_item_area;

    public HomeListViewHolder(View itemView) {
        super(itemView);

        this.context = itemView.getContext();
        this.image_item_avatar = itemView.findViewById(R.id.image_item_avatar);
        this.text_item_district = itemView.findViewById(R.id.text_item_district);
        this.text_item_building_type = itemView.findViewById(R.id.text_item_building_type);
        this.text_item_ad_type = itemView.findViewById(R.id.text_item_ad_type);
        this.text_item_badge_bedroom = itemView.findViewById(R.id.text_item_badge_bedroom);
        this.text_item_badge_floor = itemView.findViewById(R.id.text_item_badge_floor);
        this.text_item_area = itemView.findViewById(R.id.text_item_area);
    }

    public void configureWith(HomeDTO homeDTO) {
        Resources res = context.getResources();
        text_item_district.setText(homeDTO.district);
        text_item_building_type.setText(homeDTO.buildingType);
        text_item_ad_type.setText(String.format("FOR %s", homeDTO.adType.toUpperCase()));
        text_item_badge_bedroom.setText(String.valueOf(homeDTO.bedroomsCount));
        text_item_badge_floor.setText(String.valueOf(homeDTO.floor));
        text_item_area.setText(String.format("%s %s", String.valueOf(homeDTO.area), context.getString(R.string.m2)));

        String imageName = String.format(context.getString(R.string.image), homeDTO.id).toLowerCase();
        int resourceId = res.getIdentifier(imageName, "drawable", context.getPackageName());
        image_item_avatar.setImageResource(resourceId);
    }

}
