package kafkaReceiverproj;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

public class ReceiverFromSpecificPartition {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Properties props=new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, 
                StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, 
                StringDeserializer.class.getName());
        
        
        KafkaConsumer<String, String> consumer=new KafkaConsumer<>(props);
        
        int partitionNo=Integer.parseInt(args[0]);
        TopicPartition partition=new TopicPartition("first-topic", partitionNo);
        
        consumer.assign(Collections.singletonList(partition));
        System.out.println("waiting for messages");
        while(true) {
            ConsumerRecords<String, String> records=consumer.poll(Duration.ofSeconds(20));
            records.forEach(record->System.out.println(
                    "key:"+record.key()+"\tvalue:"+record.value()+"\tpartition:"+record.partition()));
        }

    }

}

