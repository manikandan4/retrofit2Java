package service;

import Interfaces.APIInterface;
import Interfaces.StackOverflowInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Search;
import okhttp3.ResponseBody;
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


    public void SearchStackOverflow(Map<String, String> options) throws IOException {
        Call<ResponseBody> responseBodyCall = stackService.SearchStackOverflow(options);
        Response<ResponseBody> execute = responseBodyCall.execute();

        System.out.println(toPrettyFormat(execute.body().string()));
    }

    public static String toPrettyFormat(String jsonString)
    {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(jsonString).getAsJsonObject();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(json);

        return prettyJson;
    }
}
