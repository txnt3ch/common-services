package txn.tech.commonservices.redis;
import redis.clients.jedis.Jedis;

import org.springframework.context.annotation.Configuration;
import txn.tech.commonservices.config.RedisConfig;

@Configuration
public class RedisClient {


    private Jedis jedis;


    public RedisClient(RedisConfig redisConfig)
    {

        System.out.println(String.format("Connect to Redis %s:%s",redisConfig.getHost(),redisConfig.getPort()));
        jedis = new Jedis(redisConfig.getHost(), redisConfig.getPort());
        //jedis.auth("password");

        System.out.println("Connected to Redis");
    }

    public void addValue(String key, String value)
    {
        jedis.set(key, value);
    }


    public String getValue(String key)
    {
        return jedis.get(key);
    }





}
