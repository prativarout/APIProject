import com.Base.TestBase;
import com.Client.RestClient;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAPITest extends TestBase {

    TestBase testbase;
    String serviceUrl;
    String apiUrl;
    String url;

    @BeforeMethod
    public void setup() {
        testbase = new TestBase();
         serviceUrl = prop.getProperty("URL");
         apiUrl = prop.getProperty("serviceURL");

         url = serviceUrl + apiUrl ;
        System.out.println(url);
    }

    @Test
    public void getTest() throws ClientProtocolException,IOException {
        RestClient restClient = new RestClient();
        restClient.get(url);
    }



}
