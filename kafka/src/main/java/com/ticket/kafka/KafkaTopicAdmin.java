package com.ticket.kafka;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.GenericWebApplicationContext;

import com.ticket.kafka.KafkaTopicConfig.Topic;

@Configuration
public class KafkaTopicAdmin {
	
	private final KafkaTopicConfig topicConfig;
	private final GenericWebApplicationContext context;
	
	public KafkaTopicAdmin(KafkaTopicConfig config, GenericWebApplicationContext context) {
		this.topicConfig = config;
		this.context = context;
	}
	
	@PostConstruct
	public void createTopics() {
		if(!topicConfig.getTopics().isEmpty()) {
			this.initTopic(topicConfig.getTopics());
		}
	}
	
	private void initTopic(List<Topic> topics) {
		topics.forEach(topic -> {
			context.registerBean(topic.getName(), NewTopic.class, topic::createTopic);
		});
	}
}
