import model.Search;
import org.junit.Test;
import service.StackOverflowService;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class StackOverflow_Search_Test {
    @Test
    public void SearchStackOverflow(){
        StackOverflowService stackOverflow = new StackOverflowService();
        Map<String, String> QueryParams = new LinkedHashMap<>();
        QueryParams.put("order","desc");
        QueryParams.put("sort","activity");
        QueryParams.put("intitle","Android");
        QueryParams.put("site","webapps");


        try {
            Search searchOutput = stackOverflow.getSearchOutput(QueryParams);


            searchOutput.getItems().forEach(
                    item -> System.out.println(item.getTitle())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
