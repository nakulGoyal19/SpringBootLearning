package io.learning.spring;

import java.util.List;

public class PointTriangleUsingList {
	private List<Point> points;
	
	public List<Point> getPoints() {
		return points;
	}


	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public void draw() {
		System.out.println("Triangle Drawn Using Class PointTriangleUsingList");
		for(Point point: points)
		System.out.println("Point A : ("+point.getX()+","+point.getY()+")");
		

	}

}
