package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		NewRecordView view = new NewRecordView();
		
		NewRecord record = new NewRecord();
		view.setRecord(record);
		*/
		// view.input();
		// view.print();
		
		// **Spring Framework이 제공하는 메모리 생성 (IOC container)**
		// 컨테이너를 만들고 >>  그 공간에 필요한 객체를 올려 놓기 작업
		
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		// context 컨테이너의 주소를 가지고있다.
		RecordView view = (RecordView) context.getBean("view");
		view.input();
		view.print();
	}
}
