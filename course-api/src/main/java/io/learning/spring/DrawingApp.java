package io.learning.spring;

//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.core.io.FileSystemResource;

public class DrawingApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Triangle t=new Triangle();
		//BeanFactory factory=new XmlBeanFactory(new FileSystemResource("spring.xml"));
		ApplicationContext context=new ClassPathXmlApplicationContext("spring.xml");
		Triangle t=(Triangle) context.getBean("triangle");
		t.draw();
	}

}
