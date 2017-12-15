package com.hiworld.activemq.pubsub;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.hiworld.activemq.p2p.ObjMessage;


/**
 * 发布者
 * @author haiswang
 *
 */
public class ActiveMQPubSubPublish {
	
	private static final String brokerURL = "tcp://10.249.73.144:61616";
	
	public static void main(String[] args) {
		
		PubSubPublisher pubSubPublisher = new PubSubPublisher(brokerURL);
		try {
			pubSubPublisher.initConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			pubSubPublisher.sendObjectMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			pubSubPublisher.sendTextMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			pubSubPublisher.sendMapMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		System.out.println("Send success.");
		pubSubPublisher.close();
	}
	
}

/**
 * 发布者
 * @author haiswang
 *
 */
class PubSubPublisher {
	
	private String username;
	private String password;
	private String brokerURL;
	
	ActiveMQConnectionFactory connectionFactory = null;
	Connection connection = null;
	
	public PubSubPublisher(String brokerURL) {
		this(ActiveMQConnection.DEFAULT_USER,ActiveMQConnection.DEFAULT_PASSWORD,brokerURL);
	}
	
	public PubSubPublisher(String username ,String password ,String brokerURL) {
		this.username = username;
		this.password = password;
		this.brokerURL = brokerURL;
	}
	
	public void initConnection() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
		connection = connectionFactory.createConnection();
		connection.start();
	}
	
	
	public void sendObjectMessage() throws JMSException {
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("MessageTopic");
		MessageProducer messageProducer = session.createProducer(topic);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		Message message = session.createObjectMessage(new ObjMessage("topic-obj-message"));
		messageProducer.send(message);
		session.commit();
	}
	
	public void sendTextMessage() throws JMSException {
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("MessageTopic");
		MessageProducer messageProducer = session.createProducer(topic);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		Message message = session.createTextMessage("topic-text-message");
		messageProducer.send(message);
		session.commit();
		
	}
	
	public void sendMapMessage() throws JMSException {
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("MessageTopic");
		MessageProducer messageProducer = session.createProducer(topic);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		MapMessage message = session.createMapMessage();
		message.setString("name", "wanghaisheng");
		message.setInt("age", 27);
		messageProducer.send(message);
		session.commit();
	}
	
	public void close() {
		try {
			if(null!=connection) {
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
