import com.Base.TestBase;
import com.Client.RestClientadvanced;
import com.PayLoad.Users;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
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
        Users users = new Users("Prativa","Leader");  //Expected user object

//        Object to JSON - Marshalling

        mapper.writeValue(new File("/Users/prativarout/Documents/Intellij/APITesting/src/main/java/com/PayLoad/user.json"),users);

//        Object to json in string
//        The methods writeValueAsString and writeValueAsBytes of ObjectMapper class generate a JSON from a Java object and return the generated JSON as a string or as a byte array:

        String userJsonString = mapper.writeValueAsString(users);
        System.out.println(userJsonString);

        closeableHttpsResponse = restClient.post(url,hashmap,userJsonString);

//        status code

        int statusCode = closeableHttpsResponse.getStatusLine().getStatusCode();
        Assert.assertEquals(statusCode,testbase.RESPONSE_STATUS_CODE_201,"record not created");

//        json string
        String response = EntityUtils.toString(closeableHttpsResponse.getEntity());

        JSONObject responseJson = new JSONObject(response);

        System.out.println("Response Json : " +responseJson);


// JSON to java object - UnMarshalling

        Users userRespObj =  mapper.readValue(response,Users.class); //actual Users object
        System.out.println(userRespObj);

        Assert.assertTrue(users.getName().equals(userRespObj.getName()));

        Assert.assertTrue(users.getJob().equals(userRespObj.getJob()));

        System.out.println(userRespObj.getId());
        System.out.println(userRespObj.getCreatedAt());

    }



}
