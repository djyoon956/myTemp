package DI2;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NewRecordView view = new NewRecordView();
		
		NewRecord record = new NewRecord(100, 50, 60);
		
		view.setRecord(record); // 필요한 객체의 주소 주입
		view.print();
	}
}
