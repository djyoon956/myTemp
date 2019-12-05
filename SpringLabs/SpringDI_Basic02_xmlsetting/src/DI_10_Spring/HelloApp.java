package DI_10_Spring;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		// BookClient client = new BookClient();

		// Properties config = new Properties();
		// config.put("server", "192.168.0.12");
		// config.put("timeout", "20");
		// client.setConfig(config);

		// client.connect();

		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_10_Spring/DI_10.xml");
		BookClient client = context.getBean("bookClient", BookClient.class);
		client.connect();
	}
}
