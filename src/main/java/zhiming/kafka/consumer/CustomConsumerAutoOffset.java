package zhiming.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

public class CustomConsumerAutoOffset {
    public static void main(String[] args) {

        //0.配置
        Properties properties = new Properties();
        // 连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.176.77.142:9092,10.176.77.143:9092,10.176.77.144:9092");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "mqp-hzm-test-group");
        //自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);

        //1.创建一个消费者, "","hello"
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //定义主题mqp-hzm-test
        ArrayList<String> topics = new ArrayList<>();
        topics.add("mqp-hzm-test");
        kafkaConsumer.subscribe(topics);
        //消费数据
        boolean flag = false;
        while(true) {
            //每次拉取的间隔时间
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
            //if(flag == true) {        //可以在其他线程中设置这个值来停止消费kafka
            //    break;
            //}
        }

    }
}
