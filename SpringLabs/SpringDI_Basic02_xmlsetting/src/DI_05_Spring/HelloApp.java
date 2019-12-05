package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/*
		MyBean mybean = new MyBean();
		MyBean mybean2 = new MyBean("hong");
		
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		
		System.out.println("mybean : " + mybean);
		System.out.println("mybean2 : " + mybean2);
		System.out.println("single : " + single);
		System.out.println("single2 : " + single2);
		
		mybean : DI_05_Spring.MyBean@15db9742
		mybean2 : DI_05_Spring.MyBean@6d06d69c
		single : DI_05_Spring.Singleton@7852e922
		single2 : DI_05_Spring.Singleton@7852e922
		*/
		
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml"); 
		/* 
			스프링 컨테이너가 구성되고 xml 파일 read 해서 파싱 .. 객체 생성 ..조립
			
		 >>컨테이너 객체 생성 ... 필요한 객체 사용
		 
		 getBean() return Object Type 객체 >> down casting
		 **주의 : getBean() 호출시에 새로운 객체를 만들지 않아요 
		 **스프링 컨테이너 가지는 객체의 타입은 : default Singleton
		 *****예외적으로 getBean()가 객체를 생성하게 할 수 있다 
		*/
		MyBean mybean = context.getBean("mybean",MyBean.class);
		MyBean mybean2 = context.getBean("mybean",MyBean.class);
		MyBean mybean3 = context.getBean("mybean",MyBean.class);
		System.out.println(mybean + " , " + mybean2 + " , " +mybean3);
		
		MyBean mybean4 = context.getBean("mybean2",MyBean.class);
		System.out.println(mybean4);
		
		Singleton single = context.getBean("single",Singleton.class);
		Singleton single3 = context.getBean("single",Singleton.class);
		System.out.println(single + " , " + single3);
	}

}


