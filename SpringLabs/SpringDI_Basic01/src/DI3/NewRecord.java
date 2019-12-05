package DI3;

//vo, dto, domain
public class NewRecord implements Record {
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

	@Override
	public int total() {
		return kor + eng + math;
	}
	
	@Override
	public float avg() {
		return total() / 3.0f;
	}

	public int getKor() {
		return kor;
	}

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
