package DI_06_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		
		//insert  작업
		//MySqlArticleDao articledao = new MySqlArticleDao();
		
		/*
		OracleArticleDao articledao = new OracleArticleDao();
		ArticleService articleservice = new ArticleService(articledao);
		
		Article article = new Article();
		articleservice.write(article);
		*/
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_06_Spring/DI_06.xml");
		
		Article article = context.getBean("article", Article.class);
		ArticleService articleservice = context.getBean("articleservice", ArticleService.class);
		
		articleservice.write(article);
		
		
	}

}


