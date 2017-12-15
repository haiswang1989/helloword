package com.hiworld.activemq.p2p;

import java.io.Serializable;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * activeMQ点对点模式的生产者
 * @author haiswang
 *
 */
public class ActiveMQP2PProducer {

	private static final String brokerURL = "tcp://10.249.73.144:61616";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ObjMessage objectMessage = new ObjMessage("Hello.");
		P2PProducer p2pProducer = new P2PProducer(brokerURL);
		
		try {
			p2pProducer.initConnection();
		} catch (JMSException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			p2pProducer.sendObjectMessage(objectMessage);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			p2pProducer.sendTextMessage("Text");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		try {
			p2pProducer.sendMapMessage();
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
		System.out.println("Send success.");
		p2pProducer.close();
	}
}

/**
 * 生产者
 * @author haiswang
 *
 */
class P2PProducer {
	
	private String username;
	private String password;
	private String brokerURL;
	
	private ConnectionFactory connectionFactory;
	private Connection connection;
	
	public P2PProducer(String brokerURL)
	{
		this(ActiveMQConnection.DEFAULT_USER ,ActiveMQConnection.DEFAULT_PASSWORD ,brokerURL);
	}
	
	public P2PProducer(String username ,String password ,String brokerURL) {
		this.username = username;
		this.password = password;
		this.brokerURL = brokerURL;
	}
	
	/**
	 * 初始化连接
	 * @throws JMSException
	 */
	public void initConnection() throws JMSException
	{
		connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
		connection = connectionFactory.createConnection();
		connection.start();
	}
	
	/**
	 * 发送对象(必须实现Serializable)
	 * @param serializable
	 * @throws JMSException
	 */
	public void sendObjectMessage(Serializable serializable) throws JMSException
	{
		 Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		 Destination destination = session.createQueue("MessageQueue");
		 MessageProducer messageProducer = session.createProducer(destination);
		 messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		 Message message = session.createObjectMessage(serializable);
		 messageProducer.send(message);
		 session.commit();
	}
	
	/**
	 * 发送String对象
	 * @param text
	 * @throws JMSException
	 */
	public void sendTextMessage(String text) throws JMSException
	{
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("MessageQueue");
		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		Message message = session.createTextMessage(text);
		messageProducer.send(message);
		session.commit();
	}
	
	/**
	 * 发送Map对象
	 * @throws JMSException
	 */
	public void sendMapMessage() throws JMSException
	{
		Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("MessageQueue");
		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		MapMessage mapMessage = session.createMapMessage();
		mapMessage.setString("name", "wanghaisheng");
		mapMessage.setInt("age", 27);
		messageProducer.send(mapMessage);
		session.commit();
	}
	
	/**
	 * 关闭连接
	 */
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
