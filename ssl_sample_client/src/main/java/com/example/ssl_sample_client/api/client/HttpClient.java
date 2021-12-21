package com.example.ssl_sample_client.api.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpClient {

  @GetMapping(value = "/api/client/ssl/test")
  public void httpCall(@RequestParam String clientValue) {
    
    CloseableHttpClient httpClient = HttpClients.createDefault();

    try {
      //요청 주소 세팅
      String url = "https://mylocal.test.com:8080/api/server/ssl/test?echoParam=" + clientValue;
      System.out.println("=== url ===\n" + url);
      HttpGet request = new HttpGet(url);

      //Header 값 세팅
      request.addHeader("custom-key","wplee");
      request.addHeader(HttpHeaders.USER_AGENT,"Googlebot");

      //Http 요청
      CloseableHttpResponse response = httpClient.execute(request);

      System.out.println(response.toString());

      try {

        System.out.println("Protocol Verstion   : " + response.getProtocolVersion());                // Protocol Version : HTTP/1.1
        System.out.println("Status Code         : " + response.getStatusLine().getStatusCode());     // Status Code : 200
        System.out.println("Status Message      : " + response.getStatusLine().getReasonPhrase());   // Status Message : OK
        System.out.println("Status              : " + response.getStatusLine().toString());           // HTTP/1.1 200 OK

        for (Header header : response.getAllHeaders()) {
          System.out.println("Http_Header_Name    : " + header.getName());
          System.out.println("Http_Header_Value   : " + header.getValue());
        }

        HttpEntity httpEntity = response.getEntity();
        if (httpEntity != null) {
          String result = EntityUtils.toString(httpEntity);
          System.out.println("=== result === \n" + result);
        }

      } catch (Exception e) {
        System.out.println("Exception #1");
        e.printStackTrace();
      }
      
    } catch (Exception e) {
      System.out.println("Exception #2");
      e.printStackTrace();
    }

  }

}
