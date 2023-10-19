package com.boa.training.serde;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import com.boa.training.domain.Employee;

public class EmployeeSerde implements Serde<Employee>{

    @Override
    public Deserializer<Employee> deserializer() {
        // TODO Auto-generated method stub
        return new EmployeeDeserializer();
    }

    @Override
    public Serializer<Employee> serializer() {
        // TODO Auto-generated method stub
        return new EmployeeSerializer();
    }

}
