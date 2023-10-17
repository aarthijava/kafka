package kafkaSenderProj;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.boa.training.patitioner.EmployeePartitioner;
import com.boa.training.serializer.EmployeeSerializer;

import domain.Employee;

public class EmployeeSenderPartition {
	public static void main(String[] args) {
	    Properties props=new Properties();
	    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
	    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EmployeePartitioner.class.getName());
	    KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
	    
	    //kafka-topics --create --topic emp-topic --partitions 5 --replication-factor 1 --zookeeper localhost:2181
	   // Created topic emp-topic.
	    String topic="emp-topic";
	    
	    
	  /*  Employee emp1=new Employee(1001, "Tanvi", "Developer");
	    Employee emp2=new Employee(1002, "Yugan", "Accountant");
	    Employee emp3=new Employee(1003, "Tanvi", "Architect");
	    Employee emp4=new Employee(1004, "Yugan", "IT-Admin");
	    Employee emp5=new Employee(1005, "Tanvi", "Developer");
	    Employee emp6=new Employee(1006, "Yugan", "Accountant");
	    
	    
	    ProducerRecord<String, Employee> record1=new ProducerRecord<>(topic, emp1);
	    ProducerRecord<String, Employee> record2=new ProducerRecord<>(topic, emp2);*/
	    
	    for(int i=1001; i<1010; i++) {
	    	 ProducerRecord<String, Employee> record=new ProducerRecord<>(topic, new Employee(i,"Name"+i,"Developer"));
	    	 producer.send(record,new Callback() {
	 	        
	 	        @Override
	 	        public void onCompletion(RecordMetadata rmd, Exception e) {
	 	            // TODO Auto-generated method stub
	 	            if(e==null) {
	 	                //System.out.println("message successfully published to partition "+rmd.partition());
	 	                //System.out.println(record.value());
	 	            }
	 	            else {
	 	                System.out.println("error in publishing");
	 	                e.printStackTrace();
	 	            }
	 	        }
	 	    });
	    }
	    
	    for(int i=1011; i<1020; i++) {
	    	 ProducerRecord<String, Employee> record=new ProducerRecord<>(topic, new Employee(i,"Name"+i,"Accountant"));
	    	 producer.send(record,new Callback() {
		 	        
		 	        @Override
		 	        public void onCompletion(RecordMetadata rmd, Exception e) {
		 	            // TODO Auto-generated method stub
		 	            if(e==null) {
		 	                System.out.println("message successfully published to partition "+rmd.partition());
		 	                System.out.println(rmd.topic());
		 	            }
		 	            else {
		 	                System.out.println("error in publishing");
		 	                e.printStackTrace();
		 	            }
		 	        }
		 	    });
	    }
	    
	    
	    for(int i=1021; i<1030; i++) {
	    	 ProducerRecord<String, Employee> record=new ProducerRecord<>(topic, new Employee(i,"Name"+i,"Architect"));
	    	 producer.send(record,new Callback() {
		 	        
		 	        @Override
		 	        public void onCompletion(RecordMetadata rmd, Exception e) {
		 	            // TODO Auto-generated method stub
		 	            if(e==null) {
		 	                System.out.println("message successfully published to partition "+rmd.partition());
		 	                System.out.println(rmd.topic());
		 	            }
		 	            else {
		 	                System.out.println("error in publishing");
		 	                e.printStackTrace();
		 	            }
		 	        }
		 	    });
	    }
	    
	    for(int i=1031; i<1040; i++) {
	    	 ProducerRecord<String, Employee> record=new ProducerRecord<>(topic, new Employee(i,"Name"+i,"IT-Admin"));
	    	 producer.send(record,new Callback() {
		 	        
		 	        @Override
		 	        public void onCompletion(RecordMetadata rmd, Exception e) {
		 	            // TODO Auto-generated method stub
		 	            if(e==null) {
		 	                System.out.println("message successfully published to partition "+rmd.partition());
		 	                System.out.println(rmd.topic());
		 	            }
		 	            else {
		 	                System.out.println("error in publishing");
		 	                e.printStackTrace();
		 	            }
		 	        }
		 	    });
	    }
	    
	    for(int i=1041; i<1050; i++) {
	    	 ProducerRecord<String, Employee> record=new ProducerRecord<>(topic, new Employee(i,"Name"+i,"Manager"));
	    	 producer.send(record,new Callback() {
		 	        
		 	        @Override
		 	        public void onCompletion(RecordMetadata rmd, Exception e) {
		 	            // TODO Auto-generated method stub
		 	            if(e==null) {
		 	                System.out.println("message successfully published to partition "+rmd.partition());
		 	                System.out.println(rmd.topic());
		 	            }
		 	            else {
		 	                System.out.println("error in publishing");
		 	                e.printStackTrace();
		 	            }
		 	        }
		 	    });
	    }
	    
	    
	    //producer.send(record2);
	   
	    System.out.println("messages sent");
	    producer.close();
	}
	}

