package com.hiworld.activemq.pubsub;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.hiworld.activemq.p2p.ObjMessage;


/**
 * 订阅者
 * @author haiswang
 *
 */
public class ActiveMQPubSubSubScribe {
	
	private static final String brokerURL = "tcp://10.249.73.144:61616";
	
	public static void main(String[] args) {
		PubSubSubScribe pubSubSubScribe = new PubSubSubScribe(brokerURL);
		try {
			pubSubSubScribe.initConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			pubSubSubScribe.receiveMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
//		pubSubSubScribe.close();
	}
	
}


class PubSubSubScribe {
	
	private String username;
	private String password;
	private String brokerURL;
	
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	
	public PubSubSubScribe(String brokerURL) {
		this(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, brokerURL);
	}
	
	public PubSubSubScribe(String username ,String password ,String brokerURL) {
		this.username = username;
		this.password = password;
		this.brokerURL = brokerURL;
	}
	
	public void initConnection() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(username ,password ,brokerURL);
		connectionFactory.setTrustAllPackages(true);
		connection = connectionFactory.createConnection();
		connection.start();
	}
	
	public void receiveMessage() throws JMSException {
		
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE); 
		Topic topic = session.createTopic("MessageTopic");
		MessageConsumer messageConsumer = session.createConsumer(topic);
		messageConsumer.setMessageListener(new MessageListener() {
			
			public void onMessage(Message message) {
				if(message instanceof ObjectMessage) {
					dealObjectMessage((ObjectMessage)message);
				} else if(message instanceof TextMessage) {
					dealTextMessage((TextMessage)message);
				} else if(message instanceof MapMessage) {
					dealMapMessage((MapMessage)message);
				}
			}
		});
	}
	
	public void dealObjectMessage(ObjectMessage message) {
		try {
			ObjMessage objMessage = (ObjMessage)message.getObject();
			System.out.println(objMessage.getMessage());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void dealTextMessage(TextMessage message) {
		try {
			String text = message.getText();
			System.out.println(text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
	public void dealMapMessage(MapMessage message) {
		try {
			String name = message.getString("name");
			int age = message.getInt("age");
			System.out.println("name : " + name + " ,age : " + age);
		} catch (JMSException e) {
			e.printStackTrace();
		}
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