package kafkaSenderProj;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class MessagePartitioner implements Partitioner{

	@Override
	public void configure(Map<String, ?> arg0) {
		System.out.println("*********Configuring");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		System.out.println("*********Closing");
		// TODO Auto-generated method stub
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster arg5) {
		// TODO Auto-generated method stub
		int partition=3;
		if(key.equals("first-key")) {
			partition=0;
		}
		else if(key.equals("second-key")) {
			partition=1;
		}
		else if(key.equals("third-key")) {
			partition=2;
		}
		else {
			partition=3;
		}
		return partition;
	}

}
