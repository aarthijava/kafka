package com.boa.training.receiver;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import com.boa.training.deserializer.EmployeeDeserializer;
import com.boa.training.domain.Employee;

public class EmployeeConsumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EmployeeDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-1");

		KafkaConsumer<String, Employee> consumer = new KafkaConsumer<>(props);

		List<String> topics = Collections.singletonList("emp-topic");
		consumer.subscribe(topics);
		System.out.println("Waiting for messages");

		while (true) {
			ConsumerRecords<String, Employee> records = consumer.poll(Duration.ofSeconds(20));
			records.forEach(record -> {
				System.out.println("key:" + record.key());
				Employee emp = record.value();
				System.out.println("value: " + emp.getId() + "\t" + emp.getName() + "\t" + emp.getDesignation());
				
				System.out.println(emp);
			});
		}
	}
}
