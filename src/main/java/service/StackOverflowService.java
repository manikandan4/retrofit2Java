package service;

import Interfaces.APIInterface;
import Interfaces.StackOverflowInterface;
import model.Search;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import java.io.IOException;
import java.util.Map;

public class StackOverflowService implements APIInterface {
    private StackOverflowInterface stackService;

    public StackOverflowService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        stackService = retrofit.create(StackOverflowInterface.class);
    }

    public Search getSearchOutput(Map<String, String> options) throws IOException {
        Call<Search> listCall = stackService.listSearch(options);
        Search returnObj = null;
        //Async call
        /*listCall.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                System.out.println(response.toString());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable throwable) {
                System.out.println(throwable.getMessage());

            }
        });*/

        Response<Search> response = listCall.execute();

        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null
                    ? response.errorBody().string() : "Unknown error");
        }
        return response.body();
    }
}
