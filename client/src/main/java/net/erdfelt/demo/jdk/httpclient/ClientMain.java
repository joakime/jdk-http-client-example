package net.erdfelt.demo.jdk.httpclient;

import java.io.IOException;
import java.net.URI;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

public class ClientMain
{
    public static void main(String[] args)
    {
        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create("http://localhost:9090/hello");
    
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri).GET().build();
    
        try
        {
            HttpResponse<String> response = client.send(getRequest,
                    HttpResponse.BodyHandler.asString());
            System.out.println("response to get: " + response.body());
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println("Unable to send request");
            e.printStackTrace();
        }
        
    }
}
