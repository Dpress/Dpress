
import java.awt.Point;

import lejos.hardware.lcd.LCD;
// YKSI RUUTU ON 4mm x 4mm alue (TUSSIN JÄLKI)
public class Main {
	
	private double servoDistance = 3.25;
	private static double SarmLen = 4.75;
	private static double LarmLen = 6.35;
	public static void main(String[] args){
		Point motoraPoint = new Point(21,0);
		Point motorbPoint = new Point(29,0);
		Point jointPointA = new Point(0,0);
		Point jointPointB = new Point(0,0);
		calculateTmpPoint(motoraPoint,jointPointA, -90);
		calculateTmpPoint(motorbPoint,jointPointB, 0);
		
		System.out.println("Nivel A: "+jointPointA);
		System.out.println("Nivel B: "+jointPointB);/*
		double base=calculateDifference(jointPointA, jointPointB);
		System.out.println("Nivelten välinen etäisyys: "+base);
		double height=calculateUpTriHeight(base);
		System.out.println("Ylemmän kolmion korkeus:"+height);
		double angle=calculateHalfwpAngle(base,jointPointA,jointPointB);
		Point finalpoint = calculateDrawPoint(jointPointB,angle,height);
		System.out.println("Final p: X:"+finalpoint.x+" Y:"+finalpoint.y);*/
		System.out.println(Loppupiste(jointPointA, jointPointB));
	}

	public static void calculateTmpPoint(Point point,Point tmpPoint, double angle){
		angle = (Math.PI/180)*(90-(angle));
		double y = Math.sin(angle)*SarmLen;
		double x = Math.cos(angle)*SarmLen;
		System.out.println("x muutos: "+x+ " y muutos: "+y);
		tmpPoint.x = (int) Math.round((point.getX()+(x/0.4)));
		tmpPoint.y = (int)Math.round((point.getY()+(y/0.4)));
	}
	
	/*
	public static double calculateDifference(Point pointa, Point pointb){
		double x = Math.abs(pointa.x-pointb.x)*0.4;
		double y = Math.abs(pointa.y-pointb.y)*0.4;
		
		return Math.sqrt((x*x)+(y*y));
	}
	
	
	public static double calculateUpTriHeight(double base){
		double c = base/2;
	double height = Math.sqrt((LarmLen*LarmLen)-(c*c));
	return height;
	}
	
	
	public static Point calculateDrawPoint(Point tmpPointb, double angle, double height)
	{// TÄSSÄ VIRHE, MUUT KOHDAT TARKISTETTU JA NÄYTTÄÄ OIKEETA ARVO! TTÄÄL VIRHE TÄSSÄ ON JOTAIN HÄIKKÄÄ ELI JOKU VIRHE KOODISSA ELI VIRHE PITÄÄ KORJATA KOSKA TÄÄ VIRHE KUSEE KAIKEN KOSKA KOODISSA VIRHE
		System.out.println("angle:"+angle*(180/Math.PI)+"  height:"+height);
		double angleB= Math.asin(height/LarmLen);
		
		double angleA= Math.abs(0.5-(angleB+angle));// Kuseee
		System.out.println("A kulma:"+angleA*(180/Math.PI)+" B kulma:"+angleB*(180/Math.PI));
		int x= (int) (Math.round((Math.sin(angleA)*LarmLen)/0.4));
		int y=(int)(Math.round((Math.cos(angleA)*LarmLen)/0.4));
		System.out.println("x:"+x+" y:"+y);
		
		Point endingPoint = new Point();
		endingPoint.x=tmpPointb.x+x;
		endingPoint.y=tmpPointb.y+y;
		return endingPoint;
		
	}
	
	
	
	public static double calculateHalfwpAngle(double c, Point a, Point b){
		double angle;
		double width;
		width = (b.x - a.x)*0.4;
		
		angle = Math.acos(width/c);
		return angle;
	}
	
	public static Point calculateBaseHlfPoint(double c, Point a, Point b){//turhhhha
		int height, width;
		
		Point halfwayP = new Point();
		width = b.x - a.x;
		height= a.y-b.y;
		
		width= Math.round(width/2);
		height = Math.round(height/2);
		
		halfwayP.x = a.x+width;
		halfwayP.y = a.y+height;
		return halfwayP;
		}
	//laskee ylemmän kolmion kannan puolvälin koordinaatin
	*/
	
	public static Point Loppupiste(Point A, Point B){
		double y, x;
		int xPoint, yPoint;
		Point[] pisteet = new Point[181];
		Point loppupiste =null;
		
		for(int i = 0; i < 181; i++){
			y = Math.sin(i*(Math.PI/180))*LarmLen;
			x = Math.cos(i*(Math.PI/180))*LarmLen;
			xPoint = (int) Math.round(x/0.4) +A.x;
			yPoint = (int) Math.round(y/0.4) + A.y;
			pisteet[i] = new Point(xPoint, yPoint);
		}
		for(int i = 0; i<181;i++){
			y = Math.sin(i*(Math.PI/180))*LarmLen;
			x = Math.cos(i*(Math.PI/180))*LarmLen;
			xPoint = (int) Math.round(x/0.4) +B.x;
			yPoint = (int) Math.round(y/0.4) + B.y;
			for(int j = 0; j < 181; j++){
			if(pisteet[j].x == xPoint && pisteet[j].y== yPoint){
				loppupiste = pisteet[j];
				break;
			}}
		}
		return loppupiste;
	}
	
	
	
	
	
	

		
		
		
		
	}
	 
	
	

