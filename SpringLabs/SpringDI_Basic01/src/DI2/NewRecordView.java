package DI2;

public class NewRecordView {
	// 점수 출력 클래스
	private NewRecord record;

	// 1. [생성자]를 통해서 필요한 객체 생성 or 주입 >> DI1
	// 2. [함수(setter)]를 통해서 필요한 객체 주입 >> DI2
	

	public void setRecord(NewRecord record) {
		this.record = record;
	}
	
	public void print() {
		System.out.println(record.total() + "/" + record.avg());

	}
}
