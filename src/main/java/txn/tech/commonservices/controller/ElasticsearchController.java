package txn.tech.commonservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import txn.tech.commonservices.config.ElasticsearchConfig;
import txn.tech.commonservices.elasticsearch.ElasticsearchClientWrapper;
import txn.tech.commonservices.kafka.producer.Sender;

import java.util.concurrent.atomic.AtomicLong;

// Configure Kafka in application.yaml
// Create consumer and producer config and bean classes (Consumer, ConsumerConfig, Receiver, ReceiverConfig)
// Create KafkaController for sample of sending event
// The consumer is autowired, and will save a notification to database (Receiver.java)
//




@RestController
public class ElasticsearchController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private ElasticsearchConfig elasticsearchConfig;

    @GetMapping("/elasticsearch/add")
    public String addPerson(@RequestParam(value = "age", defaultValue = "10") int age, @RequestParam(value = "name", defaultValue = "name") String name ) {
        ElasticsearchClientWrapper elasticsearchClient = new ElasticsearchClientWrapper(elasticsearchConfig);
        return elasticsearchClient.addPerson(age,name);
    }

    @GetMapping("/elasticsearch/get")
    public String sendEvent(@RequestParam(value = "name", defaultValue = "name") String name) {
        ElasticsearchClientWrapper elasticsearchClient = new ElasticsearchClientWrapper(elasticsearchConfig);
        return elasticsearchClient.getPerson(name);
    }



}
