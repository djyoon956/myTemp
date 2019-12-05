package DI_06_Spring;

public class ArticleService {
	
	//사용자 요청에 따라서 
	
	//글쓰기 ...
	
	//목록보기 ...
	
	//수정하기 ...
	
	//서비스를 하기 위해서는  DB작업 ... DAO단
	private ArticleDao articledao; //변수가 실 객체 주소를 가지면 되요 ...
	public ArticleService(ArticleDao articledao) {
		this.articledao = articledao; //주소값 할당
		System.out.println("ArticleService 생성자 함수 호출");
	}
	
	//글쓰기 서비스
	public void write(Article article) {
		this.articledao.insert(article); //
	}
	
}





