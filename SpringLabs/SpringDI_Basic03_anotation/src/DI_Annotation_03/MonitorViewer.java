package DI_Annotation_03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Qualifier;

public class MonitorViewer {

	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}

	@Autowired(required = false) // 기본 설정(required = true) 무조건 injection
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
		System.out.println("setter 주입 성공 : " + recorder);
	}
}
