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

        System.out.println("Regular request, server determines Content-Length");
        issueGET(client, URI.create("http://localhost:9090/hello"));
    
        System.out.println("Server sends BAD Content-Length (under-send)");
        issueGET(client, URI.create("http://localhost:9090/hello?forced-len=40"));
    
        System.out.println("Server sends BAD Content-Length (over-send)");
        issueGET(client, URI.create("http://localhost:9090/hello?forced-len=70"));
    }
    
    public static void issueGET(HttpClient client, URI uri)
    {
        System.out.println("request: " + uri);
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(uri).GET().build();
    
        try
        {
            HttpResponse<String> response = client.send(getRequest,
                    HttpResponse.BodyHandler.asString());
            System.out.println("response headers: " + response.headers().toString());
            System.out.println("response to get: " + response.body());
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println("Unable to send request");
            e.printStackTrace();
        }
        
    }
}
