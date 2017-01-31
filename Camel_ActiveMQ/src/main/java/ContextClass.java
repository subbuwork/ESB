import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * 
 */

/**
 * @author subbu
 *
 */
public class ContextClass {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
   SampleRouteBuilder builder = new SampleRouteBuilder();
   
   CamelContext camelContext = new DefaultCamelContext();
   
   ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://0.0.0.0:61616");
   camelContext.addComponent("activemq",JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
   
   camelContext.addRoutes(builder);
   
   camelContext.start();
   Thread.sleep(5*60*1000);
   camelContext.stop();	
		
	}

}
