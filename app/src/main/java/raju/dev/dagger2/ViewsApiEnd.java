package raju.dev.dagger2;


import java.util.ArrayList;

import raju.dev.dagger2.models.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ViewsApiEnd {

    @GET("/users/{user}/repos")
    Call<ArrayList<Repository>> getRepository(@Path("user") String userName);

}