import com.Base.TestBase;
import com.Client.RestClient;
import com.Client.RestClientadvanced;
import com.Util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetAPITestAdvanced extends TestBase {
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
    public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
        RestClientadvanced restClient = new RestClientadvanced();
        closeableHttpsResponse = restClient.get(url);

        //        status code

        int statusCode =  closeableHttpsResponse.getStatusLine().getStatusCode();
        System.out.println("Status code : " +statusCode);

        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");

//        get the response

        String response = EntityUtils.toString(closeableHttpsResponse.getEntity());

//        System.out.println("Response : " +response);

//        Convert the string to json

        JSONObject jsonResponse = new JSONObject(response);

        System.out.println(" jsonResponse : " + jsonResponse);

        String totalValue = TestUtil.getValueByJpath(jsonResponse,"/total");

        System.out.println("page ->" +totalValue);

        Assert.assertEquals(Integer.parseInt(totalValue),12);

//        get the value from JSON array

        String lastname = TestUtil.getValueByJpath(jsonResponse,"/data[0]/last_name");
        String id = TestUtil.getValueByJpath(jsonResponse,"/data[0]/id");
        String avatar = TestUtil.getValueByJpath(jsonResponse,"/data[0]/avatar");
        String email = TestUtil.getValueByJpath(jsonResponse,"/data[0]/email");

        System.out.println("last name  : " +lastname + " id : " +id + " avatar : " +avatar + " email : " + email);


//        Headers

        Header[] arrayHeaders = closeableHttpsResponse.getAllHeaders();

        HashMap<String,String> allHeaders = new HashMap<>();

        for(Header header : arrayHeaders){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: " + allHeaders);

    }






    @Test
    public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
        RestClientadvanced restClient = new RestClientadvanced();

        HashMap<String,String> headerMap = new HashMap<>();
        headerMap.put("Content-Type","application/json");
//        headerMap.put("auth","hsdjksdl");
        closeableHttpsResponse = restClient.get(url,headerMap);

        //        status code

        int statusCode =  closeableHttpsResponse.getStatusLine().getStatusCode();
        System.out.println("Status code : " +statusCode);

        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200,"Status code is not 200");

//        get the response

        String response = EntityUtils.toString(closeableHttpsResponse.getEntity());

//        System.out.println("Response : " +response);

//        Convert the string to json

        JSONObject jsonResponse = new JSONObject(response);

        System.out.println(" jsonResponse : " + jsonResponse);

        String totalValue = TestUtil.getValueByJpath(jsonResponse,"/total");

        System.out.println("page ->" +totalValue);

        Assert.assertEquals(Integer.parseInt(totalValue),12);

//        get the value from JSON array

        String lastname = TestUtil.getValueByJpath(jsonResponse,"/data[0]/last_name");
        String id = TestUtil.getValueByJpath(jsonResponse,"/data[0]/id");
        String avatar = TestUtil.getValueByJpath(jsonResponse,"/data[0]/avatar");
        String email = TestUtil.getValueByJpath(jsonResponse,"/data[0]/email");

        System.out.println("last name  : " +lastname + " id : " +id + " avatar : " +avatar + " email : " + email);


//        Headers

        Header[] arrayHeaders = closeableHttpsResponse.getAllHeaders();

        HashMap<String,String> allHeaders = new HashMap<>();

        for(Header header : arrayHeaders){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: " + allHeaders);

    }



}

