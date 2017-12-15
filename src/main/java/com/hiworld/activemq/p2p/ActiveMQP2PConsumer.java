package com.hiworld.activemq.p2p;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQP2PConsumer {

	private static final String brokerURL = "tcp://10.249.73.144:61616";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		P2PConsumer p2pConsumer = new P2PConsumer(brokerURL);
		
		try {
			p2pConsumer.initConnection();
		} catch (JMSException e1) {
			e1.printStackTrace();
		}
		
		try {
			p2pConsumer.receiveMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		p2pConsumer.close();
	}

}

class P2PConsumer {
	
	private String username;
	private String password;
	private String brokerURL;
	
	private ActiveMQConnectionFactory connectionFactory;
	private Connection connection;
	
	public P2PConsumer(String brokerURL) {
		this(ActiveMQConnection.DEFAULT_USER ,ActiveMQConnection.DEFAULT_PASSWORD ,brokerURL);
	}
		
	public P2PConsumer(String username ,String password ,String brokerURL) {
		this.username = username;
		this.password = password;
		this.brokerURL = brokerURL;
	}
	
	/**
	 * 初始化连接
	 * @throws JMSException
	 */
	public void initConnection() throws JMSException {
		connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
		//关闭权限检查,信任所有的classes
		connectionFactory.setTrustAllPackages(true);
		connection = connectionFactory.createConnection();
		connection.start();
	}
	
	public void receiveMessage() throws JMSException {
		
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("MessageQueue");
		MessageConsumer messageConsumer = session.createConsumer(destination);
		while(true)
		{
			Message message = messageConsumer.receive();
			if(null!=message) {
				if(message instanceof ObjectMessage) {
					dealObjectMessage((ObjectMessage)message);
				} else if(message instanceof TextMessage) {
					dealTextMessage((TextMessage)message);
				} else if(message instanceof MapMessage) {
					dealMapMessage((MapMessage)message);
				}
			} else {
				break;
			}
		}
	}
	
	public void dealObjectMessage(ObjectMessage objectMessage) throws JMSException {
		ObjMessage message = (ObjMessage)objectMessage.getObject();
		System.out.println(message.getMessage());
	}
	
	public void dealTextMessage(TextMessage textMessage) throws JMSException {
		String text = textMessage.getText();
		System.out.println(text);
	}
	
	public void dealMapMessage(MapMessage mapMessage) throws JMSException {
		String name = mapMessage.getString("name");
		int age = mapMessage.getInt("age");
		System.out.println("name : " + name + " ,age : " + age);
	}
	
	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if(null!=connection)
			{
				connection.close();
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}