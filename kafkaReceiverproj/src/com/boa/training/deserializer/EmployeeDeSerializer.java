package com.boa.training.deserializer;

import org.apache.kafka.common.serialization.Deserializer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.Employee;

public class EmployeeDeSerializer implements Deserializer<Employee>{
	private ObjectMapper mapper=new ObjectMapper();
/*	@Override
	public byte[] serialize(String topic, Employee e) {
		
        byte[] array=null;
        try {
            array=mapper.writeValueAsBytes(e);
            System.out.println("serialized to "+new String(array));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return array;
	}*/
	@Override
	public Employee deserialize(String topic, byte[] arg1) {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		//e.setId(id);
		
		return e;
	}

}
