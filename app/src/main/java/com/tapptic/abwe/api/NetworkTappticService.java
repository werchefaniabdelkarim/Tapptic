package com.tapptic.abwe.api;


import com.tapptic.abwe.datamodels.ItemList;
import com.tapptic.abwe.datamodels.Item;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface NetworkTappticService {

    String BASE_URL = "http://dev.tapptic.com/";

    @GET("test/json.php")
    Observable<List<ItemList>> getAllData();

    @GET("test/json.php")
    Observable<Item> getOneItem(@Query("name") String name);

}
