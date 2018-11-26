package ir.taghizadeh.android_mvp.model.database;

import java.util.List;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.realm.Realm;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;

public interface DataLayer {
    void loadHomes_local(Function<Home, HomeDTO> conversionBlock, Consumer<List<HomeDTO>> onNewData) throws Exception;

    void saveDTOs(List<HomeDTO> dtos, BiFunction<HomeDTO, Realm, Home> conversionBlock);

    void removeAll(Action finished) throws Exception;

    Home homeById(int homeId);
}
