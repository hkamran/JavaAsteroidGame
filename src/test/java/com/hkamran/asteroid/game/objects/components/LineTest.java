package com.hkamran.asteroid.game.objects.components;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	@Test
	public void verticalCollidingWithNonVertical() {
		//vertical
		Line line1 = new Line(new Point(3, -10), new Point(3, 10));
		//non-vertical
		Line line2 = new Line(new Point(2, 0), new Point(10, 10));
		
		Assert.assertTrue(line1.isIntersecting(line2));		
	}
	
	@Test
	public void nonVerticalCollidingWithVertical() {
		//vertical
		Line line1 = new Line(new Point(3, -10), new Point(3, 10));
		//non-vertical
		Line line2 = new Line(new Point(2, 0), new Point(10, 10));
		
		Assert.assertTrue(line2.isIntersecting(line1));	
	}	
	
	@Test
	public void horizontalCollidingWithNonHorizontal() {
		//horizontal
		Line line1 = new Line(new Point(3, 0), new Point(10, 0));
		//non-horizontal
		Line line2 = new Line(new Point(5, -10), new Point(5, 10));
		
		Assert.assertTrue(line1.isIntersecting(line2));		
	}
	
	@Test
	public void nonHorizontalCollidingWithHorizontal() {
		//horizontal
		Line line1 = new Line(new Point(3, 0), new Point(10, 0));
		//non-horizontal
		Line line2 = new Line(new Point(5, -10), new Point(5, 10));
		
		Assert.assertTrue(line2.isIntersecting(line1));				
	}	
	
	@Test
	public void horizontalCollidingWithVertical() {
		//horizontal
		Line line1 = new Line(new Point(3, 0), new Point(10, 0));
		//vertical
		Line line2 = new Line(new Point(3, -10), new Point(3, 10));
		
		Assert.assertTrue(line1.isIntersecting(line2));	
	}	
	
	@Test
	public void verticalCollidingWithHorizontal() {
		//horizontal
		Line line1 = new Line(new Point(3, 0), new Point(10, 0));
		//vertical
		Line line2 = new Line(new Point(3, -10), new Point(3, 10));
		
		Assert.assertTrue(line2.isIntersecting(line1));		
	}	
	
	@Test
	public void slopeLineCollidingWithSlopeLine() {
		//sloped
		Line line1 = new Line(new Point(3, -10), new Point(10, 0));
		//sloped
		Line line2 = new Line(new Point(5, -20), new Point(3, 10));
		
		Assert.assertTrue(line2.isIntersecting(line1));	
	}	
	
	@Test
	public void slopeLineNotCollidingWithSlopeLine() {
		//sloped
		Line line1 = new Line(new Point(3, -10), new Point(4, 0));
		//sloped
		Line line2 = new Line(new Point(10, -20), new Point(20, 10));
		
		Assert.assertTrue(!line2.isIntersecting(line1));	
	}	
		
	
	@Test
	public void verticalNotCollidingWithVertical() {
		//vertical
		Line line1 = new Line(new Point(3, -10), new Point(3, 10));
		//vertical
		Line line2 = new Line(new Point(5, -10), new Point(5, 10));
		
		Assert.assertTrue(!line1.isIntersecting(line2));	
	}	
	
	@Test
	public void horizontalNotCollidingWithHorizontal() {
		//horizontal
		Line line1 = new Line(new Point(3, 0), new Point(10, 0));
		//horizontal
		Line line2 = new Line(new Point(3, 1), new Point(10, 1));
		
		
		Assert.assertTrue(!line1.isIntersecting(line2));	
	}	
	
}
