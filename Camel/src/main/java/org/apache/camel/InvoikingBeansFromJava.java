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
public class InvoikingBeansFromJava extends RouteBuilder{

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CamelContext context = new DefaultCamelContext();
		
		
		
		
	}

	@Override
	public void configure() throws Exception {
		from("").process(new Processor() {
			
			public void process(Exchange arg0) throws Exception {
				String msg = arg0.getIn().getBody(String.class);
				HelloBean name = new HelloBean();
				
				String ans = name.hello(msg);
				
				arg0.getOut().setBody(ans);
				
				
			}
		});
		
	}

	
	

}
