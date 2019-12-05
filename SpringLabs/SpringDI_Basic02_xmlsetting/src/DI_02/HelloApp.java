package DI_02;

public class HelloApp {

	public static void main(String[] args) {
		MessaageBean_en messaageBean_en = new MessaageBean_en();
		messaageBean_en.sayHello("hong");
		
		MessaageBean_kr messaageBean_kr = new MessaageBean_kr();
		messaageBean_kr.sayHello("hong");
		
		MessageBean bean =  new MessaageBean_en();
		bean.sayHello("hong");
		bean =   new MessaageBean_kr();
		bean.sayHello("hong");
	}
}

/*
 요구사항
 영문 버전 (hong) >> Hello hong
 한글 버전 (hong) >> 안녕 hong
 
 MessageBean_kr > 함수 > sayHello
 MessageBean_en > 함수 > sayHello
 
 >>인터페이스 : MessageBean 인터페이스 설계 
 
 */
