/**
 * 
 */
package org.apache.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * @author subbu
 *
 */
public class CamelDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			
			@Override
			public void configure() throws Exception {

				from("file:E://input?noop=true").to("file:E://output");
				
				
			}
		});
		
		context.start();
		Thread.sleep(5*100*1000);
		
		context.stop();

	}

}
