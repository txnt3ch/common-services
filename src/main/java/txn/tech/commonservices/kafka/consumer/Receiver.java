package txn.tech.commonservices.kafka.consumer;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);



    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "notification")
    public void receive(String payload) {
        LOGGER.info("received payload='{}'", payload);
        latch.countDown();
        try
        {

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }



    }
}