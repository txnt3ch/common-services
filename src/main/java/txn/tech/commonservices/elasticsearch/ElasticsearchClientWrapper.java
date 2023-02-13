package txn.tech.commonservices.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;

import org.springframework.context.annotation.Configuration;
import txn.tech.commonservices.config.ElasticsearchConfig;
import txn.tech.commonservices.entity.Person;



@Configuration
public class ElasticsearchClientWrapper {

    ElasticsearchClient client;
    public ElasticsearchClientWrapper(ElasticsearchConfig elasticsearchConfig) {

        System.out.println(String.format("Connect to Elasticsearch %s:%s", elasticsearchConfig.getHost(), elasticsearchConfig.getPort()));

        // Create the low-level client
        RestClient restClient = RestClient.builder(new HttpHost(elasticsearchConfig.getHost(), elasticsearchConfig.getPort())).build();

        // Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());

        // And create the API client
        client = new ElasticsearchClient(transport);



        System.out.println("Connected to Elasticsearch");
    }

    public String addPerson(int age, String name)
    {
        Person person = new Person(age, name);
        try {
            IndexResponse response = client.index(i -> i
                    .index("people")
                    .id(person.getFullName())
                    .document(person)
            );
            return "added:"+ response.result().jsonValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public String getPerson(String name)
    {
        try{
            GetResponse<Person> response = client.get(g -> g
                            .index("people")
                            .id(name),
                    Person.class
            );

            if (response.found()) {
                Person person = response.source();
                return person.getFullName()+":" + String.valueOf(person.getAge());

            } else {
                return null;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }


    }


}
