package com.boa.training.serializer;

import org.apache.kafka.common.serialization.Serializer;

import domain.Employee;

public class EmployeeSerializer implements Serializer<Employee>{

	@Override
	public byte[] serialize(String topic, Employee e) {
		// TODO Auto-generated method stub
		
		
		return null;
	}

}
