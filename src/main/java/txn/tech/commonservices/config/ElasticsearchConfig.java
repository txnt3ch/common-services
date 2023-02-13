package txn.tech.commonservices.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;




@Component
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private int port;

    public String getHost()
    {
        return host;
    }
    public int getPort()
    {
        return port;
    }




}
