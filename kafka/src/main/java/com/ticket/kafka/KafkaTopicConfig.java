package com.ticket.kafka;

import java.util.List;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix="spring.kafka")
@Data
public class KafkaTopicConfig {
	
	private List<Topic> topics;
	
	@Data
	public static class Topic {
		private String name;
		private Integer numPartitions;
		private Short replicationFactor;
		
		public NewTopic createTopic() {
			return TopicBuilder
				.name(this.name)
				.partitions(this.numPartitions)
				.replicas(this.replicationFactor)
				.build();
		}
	}
}
