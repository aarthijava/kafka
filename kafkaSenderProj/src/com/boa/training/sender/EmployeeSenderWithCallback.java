package com.boa.training.sender;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import com.boa.training.domain.Employee;
import com.boa.training.partitioner.EmployeePartitioner;
import com.boa.training.serializer.EmployeeSerializer;


class EmpTopicCallback implements Callback{
    
    private int id;
    

    public EmpTopicCallback(int id) {
        super();
        this.id = id;
    }


    @Override
    public void onCompletion(RecordMetadata rmd, Exception e) {
        // TODO Auto-generated method stub
        if(e==null) {
            System.out.println("employee with id "+id+" is published to "+rmd.partition()
            +" at offset: "+rmd.offset());
        }
    }
    
}
public class EmployeeSenderWithCallback {
public static void main(String[] args) {
    Properties props=new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EmployeeSerializer.class.getName());
    props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, EmployeePartitioner.class.getName());
    KafkaProducer<String, Employee> producer=new KafkaProducer<>(props);
    String topic="emp-topic";
    for(int i=1001;i<=1010;i++) {
    ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
            new Employee(i, "Name "+i, "Developer"));
    producer.send(record,new EmpTopicCallback(i));
    }
    for(int i=2001;i<=2010;i++) {
        ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
                new Employee(i, "Name "+i, "Accountant"));
        producer.send(record,new EmpTopicCallback(i));
        }
    for(int i=3001;i<=3010;i++) {
        ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
                new Employee(i, "Name "+i, "Architect"));
        producer.send(record,new EmpTopicCallback(i));
        }
    for(int i=4001;i<=4010;i++) {
        ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
                new Employee(i, "Name "+i, "IT-Admin"));
        producer.send(record,new EmpTopicCallback(i));
        }
    for(int i=5001;i<=5010;i++) {
        ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
                new Employee(i, "Name "+i, "Project Manager"));
        producer.send(record,new EmpTopicCallback(i));
        }
    for(int i=6001;i<=6010;i++) {
        ProducerRecord<String, Employee> record=new ProducerRecord<>(topic,
                new Employee(i, "Name "+i, "HR Executive"));
        producer.send(record,new EmpTopicCallback(i));
        }
    
    
    System.out.println("messages sent");
    producer.close();
}
}
