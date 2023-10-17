package kafkaSenderProj;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import com.boa.training.serializer.EmployeeSerializer;

import domain.Employee;

public class EmployeeSender {
	public static void main(String[] args) {
	    Properties props=new Properties();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
	    
	    KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
	    
	    //kafka-topics --create --topic emp-topic --partitions 5 --replication-factor 1 --zookeeper localhost:2181
	   // Created topic emp-topic.
	    String topic="emp-topic";
	    
	    
	    Employee emp1=new Employee(1001, "Tanvi", "Dev");
	    Employee emp2=new Employee(1002, "Yugan", "tester");
	    ProducerRecord<String, Employee> record1=new ProducerRecord<>(topic, "key-1", emp1);
	    ProducerRecord<String, Employee> record2=new ProducerRecord<>(topic, "key-2", emp2);
	    
	    producer.send(record1);
	    producer.send(record2);
	   
	    System.out.println("messages sent");
	    producer.close();
	}
	}

