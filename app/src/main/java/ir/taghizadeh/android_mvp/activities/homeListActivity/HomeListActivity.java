package ir.taghizadeh.android_mvp.activities.homeListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.taghizadeh.android_mvp.R;
import ir.taghizadeh.android_mvp.activities.homeDetailsActivity.HomeDetailsActivity;
import ir.taghizadeh.android_mvp.dependencies.DependencyRegistry;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.Source;

public class HomeListActivity extends AppCompatActivity {

    private HomeListPresenter presenter;
    private List<HomeDTO> homeDTOS = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);
        setupUI();
        DependencyRegistry.register.inject(this);
    }

    private void setupUI() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        initializeListView();
    }

    private void initializeListView() {
        HomeListViewAdapter adapter = new HomeListViewAdapter(homeDTOS, (v, position) -> rowTapped(v, position));
        recyclerView.setAdapter(adapter);
    }

    private void rowTapped(View view, int position) {
        HomeDTO homeDTO = homeDTOS.get(position);
        View avatar = view.findViewById(R.id.image_item_avatar);
        changeActivity(homeDTO.id, avatar);
    }

    private void changeActivity(int id, View avatar) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Intent intent = new Intent(this, HomeDetailsActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, avatar, ViewCompat.getTransitionName(avatar));
        intent.putExtras(bundle);
        this.startActivity(intent, options.toBundle());
    }

    public void configureWith(HomeListPresenter presenter) {
        this.presenter = presenter;
        loadData();
    }

    private void loadData() {
        presenter.loadData(this::updateList, this::onDataReceived);
    }

    private void updateList(List<HomeDTO> homeDTOS) {
        this.homeDTOS = homeDTOS;
        HomeListViewAdapter adapter = (HomeListViewAdapter) recyclerView.getAdapter();
        assert adapter != null;
        adapter.homeDTOS  = this.homeDTOS;
        adapter.notifyDataSetChanged();
    }

    private void onDataReceived(Source source) {
        String message = String.format("Updated from %s", source.name());
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
