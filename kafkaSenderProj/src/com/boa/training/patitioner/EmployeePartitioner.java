package com.boa.training.patitioner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

import domain.Employee;

public class EmployeePartitioner implements Partitioner {
	private Properties design = new Properties();
	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fi = new FileInputStream("designation.properties");
			 design.load(fi);
	            fi.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		int partition=4;
		Employee emp = (Employee)value;
		String designation=emp.getDesignation();
		String partitionNo=design.getProperty(designation);
		if(partitionNo!=null) {
			partition=Integer.parseInt(partitionNo);
		}
		
		System.out.println("Designation:"+designation+"PartitionNo:"+partitionNo+"\temployeeid"+emp.getId());
		return partition;
	}

	@Override
	public void close() {
		design=null;
		// TODO Auto-generated method stub
		
	}

}
