package ir.taghizadeh.android_mvp.model.modelLayer;

import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import ir.taghizadeh.android_mvp.model.conversionLayer.ConversionLayer;
import ir.taghizadeh.android_mvp.model.conversionLayer.HomeConverter;
import ir.taghizadeh.android_mvp.model.dataLayer.DataLayer;
import ir.taghizadeh.android_mvp.model.dataLayer.Home;
import ir.taghizadeh.android_mvp.model.dtos.HomeDTO;
import ir.taghizadeh.android_mvp.model.enums.Source;
import ir.taghizadeh.android_mvp.model.networkLayer.NetworkLayer;
import ir.taghizadeh.android_mvp.utils.Threading;

public class Model implements ModelLayer {

    private NetworkLayer networkLayer;
    private DataLayer dataLayer;
    private ConversionLayer conversionLayer;

    public Model(NetworkLayer networkLayer, DataLayer dataLayer, ConversionLayer conversionLayer) {
        this.networkLayer = networkLayer;
        this.dataLayer = dataLayer;
        this.conversionLayer = conversionLayer;
    }

    @Override
    public void loadData(Consumer<List<HomeDTO>> onNewResults, Consumer<Source> notifyDataReceived) {

        HomeConverter homeConverter = conversionLayer.convertFor(HomeDTO.dtoType);

        try {
            dataLayer.loadHomes_local(homeConverter::convert, onNewResults);
            notifyDataReceived.accept(Source.local);
        } catch (Exception e) {
            e.printStackTrace();
        }

        networkLayer.readJson(json -> {
            notifyDataReceived.accept(Source.network);
            saveJson(json, ()-> dataLayer.loadHomes_local(homeConverter::convert, onNewResults));
        });


    }

    @Override
    public HomeDTO homeById (int homeId) {
        Home home = dataLayer.homeById(homeId);
        return conversionLayer.convert(home);
    }

    private void saveJson (String json, Action finished) {
        List<HomeDTO> dtos = conversionLayer.convertJson(json);
        Threading.async(()->{
           dataLayer.removeAll(() -> {
               HomeConverter homeConverter = conversionLayer.convertFor(HomeDTO.dtoType);
               dataLayer.saveDTOs(dtos, homeConverter::convert);
               Threading.dispatchMain(finished::run);
           });
            return true;
        });
    }
}
