package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
		MessageBeanImpl messagebean2 = context.getBean("m1", MessageBeanImpl.class);
		messagebean2.sayHello();
	}
}
