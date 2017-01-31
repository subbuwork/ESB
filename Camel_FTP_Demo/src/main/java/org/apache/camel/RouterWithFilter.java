/**
 * 
 */
package org.apache.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author subbu
 *
 */
public class RouterWithFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		// Creating camel context.
		CamelContext context = new DefaultCamelContext();
		
		
		// connect to embedded ActiveMQ JMS broker
		
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
		
		// Add component to the camel context..
		context.addComponent("activemq",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
		
		// Add route to the camel context
		
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure()  {

				// load file orders from src/data into the JMS queue
				from("file:src/data?noop=true").to("activemq:queue:incomingOrders1");
				
				// content-based router
				from("activemq:queue:incomingOrders1").choice()
				.when(header("CamelFileName").endsWith(".xml")).to("activemq:xmlOrders1")
				.when(header("CamelFileName").regex("^.*(csv|csl)$")).to("activemq:csvOrders1")
				.otherwise().to("activemq:badOrders1");
			}
		});
		
		
		context.start();
		Thread.sleep(5*60*100);
		context.stop();
		
	}

}
