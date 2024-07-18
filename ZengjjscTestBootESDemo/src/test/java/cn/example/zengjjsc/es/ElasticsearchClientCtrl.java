package cn.example.zengjjsc.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import java.util.ArrayList;
import java.util.List;

public interface ElasticsearchClientCtrl {

    default ElasticsearchClient getElasticsearchClient() {
        RestClient client = RestClient.builder(getHttpHosts()).build();
        ElasticsearchTransport transport = new RestClientTransport(client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }

    private HttpHost[] getHttpHosts() {
        //String hostsString = "10.132.21.221:19200,10.132.21.222:19200,10.132.21.223:19200";
        String hostsString = "127.0.0.1:9200";

        List<HttpHost> hosts = new ArrayList<>();
        String[] hostEntries = hostsString.split(",");

        for (String entry : hostEntries) {
            String[] hostAndPort = entry.split(":");
            if (hostAndPort.length != 2) {
                throw new IllegalArgumentException("Invalid host entry: " + entry);
            }

            String host = hostAndPort[0].trim();
            int port = Integer.parseInt(hostAndPort[1]);

            hosts.add(new HttpHost(host, port, "http"));
        }

        return hosts.toArray(new HttpHost[0]);
    }
    
}
