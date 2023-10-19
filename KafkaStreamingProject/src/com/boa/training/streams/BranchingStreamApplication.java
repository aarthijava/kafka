                                                                     
package com.boa.training.streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes.StringSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import com.boa.training.domain.Employee;
import com.boa.training.serde.EmployeeSerde;

public class BranchingStreamApplication {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Properties props=new Properties();
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, StringSerde.class.getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, EmployeeSerde.class.getName());
        props.put(StreamsConfig.APPLICATION_ID_CONFIG,"branching-streaming-app");
        
        KafkaStreams streams=new KafkaStreams(createTopology(), props);
        streams.start();
        System.out.println("streaming started");
        try {
            Thread.sleep(20*60*1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally {
        streams.close();
        }
        System.out.println("streaming stopped");
    }
    
    static Topology createTopology()
    {
        StreamsBuilder builder=new StreamsBuilder();
        String srcTopic="myex-topic-employee";
        String targetTopicDev="dev-topic";
        String targetTopicAcct="acct-topic";
        String targetTopicOthers="others-topic";
        KStream<String, Employee> inputStream= builder.stream(srcTopic);
        inputStream.filter((key,emp)->emp.getDesignation().equals("Developer"))
        .to(targetTopicDev);
        inputStream.filter((key,emp)->emp.getDesignation().equals("Accountant"))
        .to(targetTopicAcct);
        inputStream.filter((key,emp)-> !(emp.getDesignation().equals("Developer")
                ||emp.getDesignation().equals("Accountant")))
        .to(targetTopicOthers);
        
        return builder.build();
    }


}


