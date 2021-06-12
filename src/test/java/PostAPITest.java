import com.Base.TestBase;
import com.Client.RestClientadvanced;
import com.PayLoad.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostAPITest extends TestBase {
    TestBase testbase;
    String serviceUrl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpsResponse;


    @BeforeMethod
    public void setup() {
        testbase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        url = serviceUrl + apiUrl ;
        System.out.println(url);
    }

    @Test
    public void postTest() throws IOException {
        RestClientadvanced restClient = new RestClientadvanced();
        HashMap<String,String> hashmap = new HashMap<>();
        hashmap.put("Content-Type","application/json");

//        Jackson API

        ObjectMapper mapper = new ObjectMapper();
        Users users = new Users("Prativa","Leader");

//        Object to JSON

        mapper.writeValue(new File("/Users/prativarout/Documents/Intellij/APITesting/src/main/java/com/PayLoad/user.json"),users);

//        Object to json in string

        String userJsonString = mapper.writeValueAsString(users);
        System.out.println(userJsonString);

        closeableHttpsResponse = restClient.post(url,hashmap,userJsonString);

//        status code

        int statusCode = closeableHttpsResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,testbase.RESPONSE_STATUS_CODE_201,"record not created");

    }



}
