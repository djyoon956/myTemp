package DI_02;

public class MessaageBean_en implements MessageBean {
	@Override
	public void sayHello(String name) {
		System.out.println("Hello " + name + "!!");
	}
}
