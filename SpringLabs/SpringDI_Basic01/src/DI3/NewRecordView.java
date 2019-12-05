package DI3;

import java.util.Scanner;

public class NewRecordView implements RecordView {
	// 점수 출력 클래스
	private NewRecord record;

	// 1. [생성자]를 통해서 필요한 객체 생성 or 주입 >> DI1
	// 2. [함수(setter)]를 통해서 필요한 객체 주입 >> DI2
	// 3. [인터페이스] 활용 (다형성)
	public void setRecord(Record record) {
		this.record = (NewRecord) record;
	}

	@Override
	public void print() {
		System.out.println(record.total() + "/" + record.avg());
	}

	@Override
	public void input() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("국어 점수 :");
		record.setKor(scanner.nextInt());
		System.out.println("영어 점수 :");
		record.setEng(scanner.nextInt());
		System.out.println("수학 점수 :");
		record.setMath(scanner.nextInt());
	}
}
