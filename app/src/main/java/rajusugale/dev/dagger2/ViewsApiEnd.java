package rajusugale.dev.dagger2;


import java.util.ArrayList;

import rajusugale.dev.dagger2.models.Repository;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface ViewsApiEnd {

    @GET("/users/{user}/repos")
    Call<ArrayList<Repository>> getRepository(@Path("user") String userName);

}