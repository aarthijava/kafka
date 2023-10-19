package com.boa.training.streams;

import java.util.Properties;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import com.boa.training.domain.Employee;
import com.boa.training.serde.EmployeeSerde;
import com.boa.training.serde.EmployeeSerializer;

public class EmployeeStreamingApp {
	public static void main(String[] args) {
		Properties props=new Properties();
	    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
	    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EmployeeSerde.class.getName());
	    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "mysql-cassandra-etl-app");
	    
	    KafkaStreams streams=new KafkaStreams(createTopology(), props);
        streams.start();
        System.out.println("streaming started");
        try {
            Thread.sleep(10*60*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
        	streams.close();
            System.out.println("streaming stopped");
        }
  
	}
	
	static Topology createTopology() {
		StreamsBuilder builder=new StreamsBuilder();
		String srcTopic = "mysql-topic-employee";
		String targetTopic = "cassandra-topic-employee";
		KStream<String, Employee> inputStream=builder.stream(srcTopic);
		KStream<String, Employee> transformedStream=inputStream.mapValues(v->{
			
			System.out.println("processing employee with Id:"+v.getId());
			Employee e1=new Employee();
			e1.setId(v.getId());
			e1.setName(v.getName());
			String designation = v.getDesignation();
			double salary = 30000;
			if(designation.equalsIgnoreCase("Developer")) {
				salary =40000;
			}else if(designation.equalsIgnoreCase("Accountant")) {
				salary =25000;
			}else if(designation.equalsIgnoreCase("Architect")) {
				salary =80000;
			}else {
				salary =10000;
			}
			e1.setDesignation(designation);
			e1.setSalary(salary);
			return e1;
			
		});
		transformedStream.to(targetTopic);
        return builder.build();
		
	}
}
