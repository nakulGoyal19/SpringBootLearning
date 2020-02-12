package io.learning.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class PointTriangle implements ApplicationContextAware, BeanNameAware{
	private Point pointA;
	private Point pointB;
	private Point pointC;
	private ApplicationContext context;
	private String beanName;
	
	public Point getPointA() {
		return pointA;
	}
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}
	public Point getPointB() {
		return pointB;
	}
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	public Point getPointC() {
		return pointC;
	}
	public void setPointC(Point pointC) {
		this.pointC = pointC;
	}
	public void draw() {
		System.out.println("Triangle Drawn Using Class PointTriangle");
		System.out.println("Context : "+this.context);
		System.out.println("BeanName: "+this.beanName);
		System.out.println("Point A : ("+getPointA().getX()+","+getPointA().getY()+")");
		System.out.println("Point B : ("+getPointB().getX()+","+getPointB().getY()+")");
		System.out.println("Point C : ("+getPointC().getX()+","+getPointC().getY()+")");

	}
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context=context;
		
	}
	@Override
	public void setBeanName(String name) {
		this.beanName=name;
		
	}

}
