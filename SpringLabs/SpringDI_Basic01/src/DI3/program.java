package DI3;

public class program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NewRecordView view = new NewRecordView();
		
		NewRecord record = new NewRecord();
		view.setRecord(record);
		view.input();
		view.print();
	}
}
