package txn.tech.commonservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import txn.tech.commonservices.redis.RedisClient;
import txn.tech.commonservices.config.RedisConfig;

// Configure Kafka in application.yaml
// Create consumer and producer config and bean classes (Consumer, ConsumerConfig, Receiver, ReceiverConfig)
// Create KafkaController for sample of sending event
// The consumer is autowired, and will save a notification to database (Receiver.java)
//




@RestController
public class RedisController {

    @Autowired
    private RedisConfig redisConfig;


    @GetMapping("/redis/add")
    public String addValue(@RequestParam(value = "key", defaultValue = "key1") String key, @RequestParam(value = "value", defaultValue = "value1") String value) {
        RedisClient redisClient = new RedisClient(redisConfig);
        redisClient.addValue(key, value);
        return String.format("Redis add %s: %s", key, value);
    }

    @GetMapping("/redis/read")
    public String readValue(@RequestParam(value = "key", defaultValue = "key1") String key) {
        RedisClient redisClient = new RedisClient(redisConfig);
        return String.format("Redis read %s: %s", key, redisClient.getValue(key));
    }




}
