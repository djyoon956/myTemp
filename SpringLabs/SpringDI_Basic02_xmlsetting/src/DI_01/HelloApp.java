package DI_01;

public class HelloApp {

	public static void main(String[] args) {
		MessaageBean bean = new MessaageBean();
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
