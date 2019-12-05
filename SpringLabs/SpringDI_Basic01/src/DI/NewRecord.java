package DI;

//vo, dto, domain
public class NewRecord {
	private int kor;
	private int eng;
	private int math;

	public NewRecord() {
	}

	public NewRecord(int kor, int eng, int math) {
		super();
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}

	// 필요시 별도의 함수 추가 가능
	public int total() {
		return kor + eng + math;
	}

	public float avg() {
		return total() / 3.0f;
	}

	public int getKor() {
		return kor;
	}
	//////////////////////////////
	
	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

}
