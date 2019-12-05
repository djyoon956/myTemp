package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import Spring.Client;

//xml > java 파일 > prototype 설정

@Configuration
public class JavaConfigPrototype {
	
/*	
  <bean id="client" class="Spring.Client" scope="prototype">
	<property name="host" value="webserver"></property> setter 주입...
	</bean>
*/
	@Bean
	@Scope("prototype") // 호출 시 마다 새로운 객체 return
	public Client client(){
		Client client = new Client(); // Client 생성
		client.setHost("webserver"); // setter에 접근

		return client;
	}
}