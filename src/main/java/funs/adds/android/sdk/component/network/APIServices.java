package funs.adds.android.sdk.component.network;

import funs.adds.android.sdk.component.model.networkmodel.AdsFunModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIServices {

    @GET("v1/inventory/{inventoryId}/fetch-ads")
    Call<AdsFunModel> fetchAds(@Path("inventoryId") String inventory);
}
