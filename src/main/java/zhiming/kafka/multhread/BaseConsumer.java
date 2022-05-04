package zhiming.kafka.multhread;

import org.apache.kafka.common.utils.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.event.ConsumerStoppedEvent;
import org.springframework.kafka.support.Acknowledgment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class BaseConsumer implements ApplicationListener<ConsumerStoppedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(BaseConsumer.class);

    @Value("${kafka.consumer.thread.min}")
    private int consumerThreadMin;

    @Value("${kafka.consumer.thread.max}")
    private int consumerThreadMax;

    private ThreadPoolExecutor consumeExecutor;

    private volatile boolean isClosePoolExecutor = false;

    private int getConsumeThreadMax() {
        return consumerThreadMax;
    }

    private int getConsumeThreadMin() {
        return consumerThreadMin;
    }

    public void setConsumerThreadMax(int consumerThreadMax) {
        this.consumerThreadMax = consumerThreadMax;
    }

    public void setConsumerThreadMin(int consumerThreadMin) {
        this.consumerThreadMin = consumerThreadMin;
    }


    @PostConstruct
    public void init() {
        setConsumerThreadMax(consumerThreadMax);
        setConsumerThreadMin(consumerThreadMin);
        this.consumeExecutor = new ThreadPoolExecutor(
                getConsumeThreadMin(),
                getConsumeThreadMax(),
                //此处最大最小不一样没啥大的意义,因为消息队列需要达到 Integer.MAX_VALUE才有点作用,
                //矛盾来了,我每次批量拉下来不可能设置Integer.MAX_VALUE这么多,
                //个人觉得每次批量下拉的原则,觉得消费可控就行,
                //不然,如果出现异常情况下,整个服务示例突然挂了,拉下来太多,这些消息会被重复消费一次.
                1000 * 60,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>()
        );
    }

    private void closeConsumeExecutorService() {
        if (!consumeExecutor.isShutdown()) {
            //consumeExecutor.shutdown();
            //线程优雅关闭
            MultiThreadUtil.shutdownGracefully(consumeExecutor, 120, TimeUnit.SECONDS);
            LOG.info("consumeExecutor stopped");
        }
    }

    @PreDestroy
    public void doClose() {
        if(!isClosePoolExecutor) {
            closeConsumeExecutorService();
        }
    }


    /**
     * 收到spring-kafka 关闭Consumer的通知
     * @param event 关闭Consumer 事件
     */
    @Override
    public void onApplicationEvent(ConsumerStoppedEvent event) {
        isClosePoolExecutor = true;
        closeConsumeExecutorService();
    }

    /**
     * 子类实现该抽象方法处理具体消息的业务逻辑
     * @param message kafka的消息
     */
    protected abstract void onDealMessage(String message);

    private void submitConsumerTask(String message, CountDownLatch countDownLatch) {
        consumeExecutor.submit(() -> {
            try {
                onDealMessage(message);
            } catch (Exception ex) {
                LOG.error("on DealMessage exception:", ex);
            } finally {
                countDownLatch.countDown();
            }
        });
    }

    @KafkaListener(topics = "${msg.consumer.topic}", containerFactory = "kafkaListenerContainerFactory")
    public void onMessage(List<String> msgList, Acknowledgment ack) {
        CountDownLatch countDownLatch = new CountDownLatch(msgList.size());
        for(String message : msgList) {
            submitConsumerTask(message, countDownLatch);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            LOG.error("countDownLatch exception ", e);
        }

        // 本次批量消费完,手动提交
        ack.acknowledge();
        LOG.info("finish commit offset");

    }






}
