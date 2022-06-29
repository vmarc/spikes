package vmarc.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class TaskProcessor {

    final Logger logger = LoggerFactory.getLogger(TaskProcessor.class);

    @Scheduled(fixedDelay = 1000)
    public void process() {
        log("1");
    }

    @Scheduled(initialDelay = 5500, fixedDelay = 5000)
    public void task2() throws InterruptedException {
        log("2 start");
        TimeUnit.SECONDS.sleep(10);
        log("2 end");
    }

    private void log(String message) {
        String timestampedMessage = String.format("%s %s", time(), message);
        logger.info(timestampedMessage);
    }

    private String time() {
        return "" + (System.currentTimeMillis() / 1000) % 1000;
    }
}
