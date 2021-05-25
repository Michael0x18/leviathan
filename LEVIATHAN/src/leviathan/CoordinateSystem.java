//Michael

package leviathan;

// Coordinate System written using AffineTransform.
// Inspiration by SKYLIT CoordinateSystem, but mine is better, by far.
// It allows you to get coordinates from it.

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

//public class CoordinateSystem {
//	private Image picture;
//	private AffineTransform coordTransform;
//	private int[][] coords = {{0,0},{0,0},{0,0},{0,0}};          //goes clockwise, starts from up left.
//
//	// 0 degrees points east
//	public CoordinateSystem(int x, int y, Image pic) {
//		picture = pic;
//		coordTransform = new AffineTransform();
//
//		int w = picture.getWidth(null);
//		int h = picture.getHeight(null);
//		this.coords[0][0] = x;
//		this.coords[0][1] = y;
//		this.coords[1][0] = x+w;
//		this.coords[1][1] = y;
//		this.coords[2][0] = x+w;
//		this.coords[2][1] = y+h;
//		this.coords[3][0] = x;
//		this.coords[3][1] = y+h;
//		
//		//coordTransform.translate(x - w, y - h);
//	}
//
//	public void shift(double dx, double dy) {
//		coordTransform.translate(dx, dy);
//		for(int[]item : this.coords) {
//			item[0]+= dx;
//			item[1]+= dy;
//		}
//	}
//	public int[][] get(){
//		return this.coords;
//	}
//	
//	
//	/*
//	public void rotate(double radians) {
//		int w = picture.getWidth(null);
//		int h = picture.getHeight(null);/
//		coordTransform.rotate(radians, w / 2, h / 2);
//	}
//	*/
//	
//	public void drawImage(Graphics g, Image picture) {
//		System.out.println(((Graphics2D) g).drawImage(picture, coordTransform, null));
//	}
//}


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CoordinateSystem
{
  private Image picture;
  private AffineTransform coordTransform;
  private double x=0,y=0;

   //  0 degrees points east
  public CoordinateSystem(double x1, double y1, Image pic)
  {
    picture = pic;
    coordTransform = new AffineTransform();
    this.x=(int) x1;
    this.y=(int) y1;

    int w = picture.getWidth(null) / 2;
    int h = picture.getHeight(null) / 2;
    coordTransform.translate(x - w, y - h);
  }

  public void shift(double dx, double dy)
  {
	//System.out.println(dx+ " " + dy);
    coordTransform.translate(dx, dy);
    this.x+=dx;
    this.y+=dy;
    //System.out.println(this.x);
    //System.out.println(this.y+"\n");
  }
  
  public void rotate(double radians)
  {
    int w = picture.getWidth(null);
    int h = picture.getHeight(null);
    coordTransform.rotate(radians, w/2, h/2);
  }
  
  public int[] get() {
	  int[] xy = {(int)this.x,(int)this.y};
	  //System.out.println(""+this.x+" "+this.y);
	  return xy;
  }

  public void drawImage(Graphics g, Image picture)
  {
	//System.out.println(this.x);
	((Graphics2D)g).drawImage(picture, (int)this.x*2,(int)this.y*2, null);
    //g.drawRect((int)this.x, (int)this.y, 16, 16);
    //System.out.println(this.x+" "+this.y);
  }

public void drawImage(Graphics g, Image sprite, boolean b) {
	// TODO Auto-generated method stub
	((Graphics2D)g).drawImage(picture, (int)this.x*2,(int)this.y*2, null);
}
}

