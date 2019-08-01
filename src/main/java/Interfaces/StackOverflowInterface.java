package Interfaces;

import model.Search;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface StackOverflowInterface {
    @GET("2.2/search")
    Call<Search> listSearch(@QueryMap  Map<String, String> options);

    @GET("2.2/search")
    Call<ResponseBody> SearchStackOverflow(@QueryMap  Map<String, String> options);
}
