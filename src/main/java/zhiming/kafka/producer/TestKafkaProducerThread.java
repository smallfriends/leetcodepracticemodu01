package zhiming.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;

public class TestKafkaProducerThread {

    //实例连接池大小
    public static final int producerNum = 20;
    //阻塞队列实现生产者实例池,获取连接作出队操作,归还连接作入队操作
    public static BlockingDeque<KafkaProducer<String, String>> queue = new LinkedBlockingDeque<>(producerNum);

    //static代码块,初始化producer实例池
    static {
        //kafka生产者配置文件
        Map<String, Object> config = new HashMap<>();
        //测试环境
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.90.73.176:9092,10.90.73.177:9092,10.90.73.185:9092");
        //线上环境
        //config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.61.67.193:9092,10.61.67.194:9092,10.61.67.195:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        config.put(ProducerConfig.ACKS_CONFIG, "1");
        //config.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required\n" +
        //        "username=\"jcfwzt_zhiming_test\" \n" +
        //        "password=\"I29tTBEsLZn51uWA\";");
        //config.put("sasl.mechanism", "PLAIN");
        //config.put("security.protocol", "SASL_PLAINTEXT");

        for (int i = 0; i < producerNum; i++) {
            KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(config);
            //将每一个kafka producer实例添加到阻塞队列中
            queue.add(kafkaProducer);
        }
    }

    //生产者发送线程
    static class SendTread extends Thread {
        String msg;
        public SendTread(String msg) {
            this.msg = msg;
        }
        public void run() {
            ProducerRecord record = new ProducerRecord("mqp-hzm-test", msg);
            try {
                //从实例池获取连接,没有空闲连接则阻塞等待
                KafkaProducer<String, String> kafkaProducer = queue.take();
                for (int i = 0; i < 20000; i++) {
                    Future future = kafkaProducer.send(record);
                    System.out.println(future.get());
                }
                //归还kafka producer连接实例到连接池队列
                queue.put(kafkaProducer);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //kafka生产者配置文件
        //Map<String, Object> config = new HashMap<>();
        //config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.90.73.93:9092,10.90.73.49:9092,10.90.73.212:9092");
        //config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        //config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, org.apache.kafka.common.serialization.StringSerializer.class);
        //config.put(ProducerConfig.ACKS_CONFIG, "1");
        //config.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required\n" +
        //        "username=\"jcfwzt_zhiming_test\" \n" +
        //        "password=\"I29tTBEsLZn51uWA\";");
        //config.put("sasl.mechanism", "PLAIN");
        //config.put("security.protocol", "SASL_PLAINTEXT");
        //
        //KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(config);
        //ProducerRecord record = new ProducerRecord("mqp-hzm-test", "single thread producer!");
        //Future future = kafkaProducer.send(record);
        //System.out.println(future.get());

        for (int i = 0; i < 50; i++) {
            SendTread sendTread = new SendTread("test multi-thread producer!");
            sendTread.start();
        }

    }

}








