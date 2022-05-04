package zhiming.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

public class CustomConsumerByHandSync {
    public static void main(String[] args) {

        //0.配置
        Properties properties = new Properties();
        // 连接bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.176.77.142:9092,10.176.77.143:9092,10.176.77.144:9092");
        //反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "mqp-hzm-test-group");

        //手动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        //1.创建一个消费者, "","hello"
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
        //定义主题mqp-hzm-test
        ArrayList<String> topics = new ArrayList<>();
        topics.add("mqp-hzm-test");
        kafkaConsumer.subscribe(topics);

        ////指定offset进行消费
        //Set<TopicPartition> assignment = kafkaConsumer.assignment();
        ////保证分区分配方案已经制定完毕
        //while(assignment.size() == 0) {
        //    kafkaConsumer.poll(Duration.ofSeconds(1));
        //    assignment = kafkaConsumer.assignment();
        //}
        ////开始重定位消费者组的offset
        //for (TopicPartition topicPartition : assignment) {
        //    kafkaConsumer.seek(topicPartition, 1);
        //}

        //希望把时间转换为对应的offset
        Set<TopicPartition> assignment = kafkaConsumer.assignment();
        HashMap<TopicPartition, Long> topicPartitionLongHashMap = new HashMap<>();
        //封装对应的集合
        for (TopicPartition topicPartition : assignment) {
            topicPartitionLongHashMap.put(topicPartition, System.currentTimeMillis() - 1 * 24 * 3600 * 1000);
        }
        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = kafkaConsumer.offsetsForTimes(topicPartitionLongHashMap);
        for (TopicPartition topicPartition : assignment) {
            OffsetAndTimestamp offsetAndTimestamp = topicPartitionOffsetAndTimestampMap.get(topicPartition);
            kafkaConsumer.seek(topicPartition, offsetAndTimestamp.offset());

        }

        //消费数据
        boolean flag = false;
        while(true) {
            //每次拉取的间隔时间
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
            //手动提交offset,同步提交
            //kafkaConsumer.commitSync();
            //手动提交offset,异步提交
            kafkaConsumer.commitAsync();
            //if(flag == true) {        //可以在其他线程中设置这个值来停止消费kafka
            //    break;
            //}
        }

    }
}
