package ir.taghizadeh.android_mvp.model.dataLayer;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.util.List;
import java.util.concurrent.Callable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmResults;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;

public class Data implements DataLayer {

    private static final String TAG = Data.class.getName();
    private Realm realm;
    private Callable<Realm> realmCallable;

    public Data(Realm realm, Callable<Realm> realmCallable) {
        this.realm = realm;
        this.realmCallable = realmCallable;
    }

    @Override
    public void loadHomes_local(Function<Home, HomeDTO> conversionBlock, Consumer<List<HomeDTO>> onNewData) throws Exception {
        Log.d(TAG, "Loading from DB");
        loadHomesFromRealm(homes -> {
            List<HomeDTO> homeDTOS = convert(homes, conversionBlock);
            onNewData.accept(homeDTOS);
        });
    }

    private void loadHomesFromRealm(Consumer<List<Home>> finished) {
        RealmResults<Home> realmResults = realm.where(Home.class).findAll();
        List<Home> homes = realm.copyFromRealm(realmResults);
        try {
            finished.accept(homes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<HomeDTO> convert(List<Home> homes, Function<Home, HomeDTO> conversionBlock) {
        List<HomeDTO> homeDTOS = Observable.fromArray(homes)
                .flatMapIterable(list -> list)
                .map(conversionBlock)
                .toList()
                .blockingGet();
        return homeDTOS;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void saveDTOs(List<HomeDTO> dtos, BiFunction<HomeDTO, Realm, Home> conversionBlock) {
        Log.d(TAG, "saving data to DB");
        try {
            Realm backgroundRealm = realmCallable.call();
            dtos.forEach(dto -> convertToHome(conversionBlock, backgroundRealm, dto));
            backgroundRealm.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void convertToHome(BiFunction<HomeDTO, Realm, Home> conversionBlock, Realm backgroundRealm, HomeDTO dto) {
        try {
            conversionBlock.apply(dto, backgroundRealm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeAll(Action finished) throws Exception {
        Log.d(TAG, "clear DB");
        Realm backgroundRealm = realmCallable.call();
        backgroundRealm.executeTransaction(r -> r.delete(Home.class));
        backgroundRealm.close();
        finished.run();
    }

    @Override
    public Home homeById(int homeId) {
        Home home = realm.where(Home.class).equalTo("id", homeId).findFirst();
        assert home != null;
        return realm.copyFromRealm(home);
    }
}
