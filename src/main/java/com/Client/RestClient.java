package com.Client;

import com.Base.TestBase;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class RestClient {
    public void get(String url) throws ClientProtocolException, IOException{

//Create instance of CloseableHttpClient using helper class HttpClients.
//Create HttpGet or HttpPost instance based on the HTTP request type.
//Use addHeader method to add required headers such as User-Agent, Accept-Encoding etc.
//For POST, create list of NameValuePair and add all the form parameters. Then set it to the HttpPost entity.
//Get CloseableHttpResponse by executing the HttpGet or HttpPost request.
//Get required details such as status code, error information, response html etc from the response.
//Finally close the apache HttpClient resource.

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse closeableHttpsResponse = httpClient.execute(httpget);

//        status code

        int statusCode =  closeableHttpsResponse.getStatusLine().getStatusCode();
        System.out.println("Status code : " +statusCode);

//        get the response

        String response = EntityUtils.toString(closeableHttpsResponse.getEntity());

        System.out.println("Response : " +response);

//        Convert the string to json

        JSONObject jsonResponse = new JSONObject(response);

        System.out.println(" jsonResponse : " + jsonResponse);

//        Headers

        Header[] arrayHeaders = closeableHttpsResponse.getAllHeaders();

        HashMap<String,String> allHeaders = new HashMap<>();

        for(Header header : arrayHeaders){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers: " + allHeaders);


    }
}
