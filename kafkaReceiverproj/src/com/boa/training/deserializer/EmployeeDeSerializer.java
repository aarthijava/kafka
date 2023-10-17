package com.boa.training.deserializer;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;

import com.boa.training.domain.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public Employee deserialize(String topic, byte[] array) {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		//e.setId(id);
		try {
			e=mapper.readValue(array, Employee.class);
		} catch (JsonParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}

}
