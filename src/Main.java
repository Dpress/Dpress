
import java.awt.Point;

// YKSI RUUTU ON 4mm x 4mm alue (TUSSIN J�LKI)
public class Main {

	private double servoDistance = 3.25;
	private static double SarmLen = 4.75;
	private static double LarmLen = 6.35;

	private static Point [] jointPointsA= new Point[181];
	private static Point [] jointPointsB= new Point[181];

	public static void main(String[] args){
		Point motoraPoint = new Point(21,0);
		Point motorbPoint = new Point(29,0);
		Point jointPointA = new Point(0,0);
		Point jointPointB = new Point(0,0);
		jointPoints(motoraPoint, motorbPoint);
		int i = 1;
		for(Point point : jointPointsA){
			System.out.println(i+" "+point);
			i++;
		}
		calculateTmpPoint(motoraPoint,jointPointA, 0);
		calculateTmpPoint(motorbPoint,jointPointB, 45);

		System.out.println("Nivel A: "+jointPointA);
		System.out.println("Nivel B: "+jointPointB);/*
		double base=calculateDifference(jointPointA, jointPointB);
		System.out.println("Nivelten v�linen et�isyys: "+base);
		double height=calculateUpTriHeight(base);
		System.out.println("Ylemm�n kolmion korkeus:"+height);
		double angle=calculateHalfwpAngle(base,jointPointA,jointPointB);
		Point finalpoint = calculateDrawPoint(jointPointB,angle,height);
		System.out.println("Final p: X:"+finalpoint.x+" Y:"+finalpoint.y);*/
		motorAngle(Loppupiste(jointPointA, jointPointB));
	}
	public static void jointPoints(Point A, Point B){//T��LL� VIRHE
		double x,y;
		int xPoint, yPoint;
		int j = 0;
		for (int i = 0; i < 181; i++){
			x = Math.cos(i*(Math.PI/180))*SarmLen;
			y = Math.sin(i*(Math.PI/180))*SarmLen;
			xPoint = (int) Math.round(x/0.4) +A.x;
			yPoint = (int) Math.round(y/0.4);
			jointPointsA[j] = new Point(xPoint, yPoint);

			x = Math.cos(i*(Math.PI/180))*SarmLen;
			y = Math.sin(i*(Math.PI/180))*SarmLen;
			xPoint = (int) Math.round(x/0.4) +B.x;
			yPoint = (int) Math.round(y/0.4);
			jointPointsB[j] = new Point(xPoint, yPoint);
			j++;
		}
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
	{// T�SS� VIRHE, MUUT KOHDAT TARKISTETTU JA N�YTT�� OIKEETA ARVO! TT��L VIRHE T�SS� ON JOTAIN H�IKK�� ELI JOKU VIRHE KOODISSA ELI VIRHE PIT�� KORJATA KOSKA T�� VIRHE KUSEE KAIKEN KOSKA KOODISSA VIRHE
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
	//laskee ylemm�n kolmion kannan puolv�lin koordinaatin
	*/

	public static Point Loppupiste(Point A, Point B){//PIT�� TARKISTAA VIEL� KUN SAA JOINTPONTIT TOIMIMAAN OIKEIN
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
	public static void motorAngle (Point drawPoint){
		double y,x;
		int xPoint, yPoint;
		Point jointPointA = null, jointPointB = null;
		for(int i = 180; i > -1; i--){
			x = Math.cos(i*(Math.PI/180))*LarmLen;
			y = Math.sin(i*(Math.PI/180))*LarmLen;

			xPoint = drawPoint.x + (int) Math.round(x/0.4);
			yPoint = drawPoint.y - (int) Math.round(y/0.4);

			for(int j = 0; j < 181; j++){
				if (xPoint == jointPointsA[j].x && yPoint == jointPointsA[j].y){
					jointPointA = jointPointsA[j];
					//System.out.println("L�YTY"+jointPointsA[j]);
					break;

				}
			}
			if (jointPointA != null){
				break;
			}
		}
		for(int i = 0; i < 181; i++){
			x = Math.cos(i*(Math.PI/180))*LarmLen;
			y = Math.sin(i*(Math.PI/180))*LarmLen;

			xPoint = drawPoint.x + (int) Math.round(x/0.4);
			yPoint = drawPoint.y - (int) Math.round(y/0.4);

			for(int j = 0; j < 181; j++){
				if (xPoint == jointPointsB[j].x && yPoint == jointPointsB[j].y){
					jointPointB = jointPointsB[j];
					//System.out.println("L�YTY"+jointPointsB[j]);
					break;

				}
			}
			if (jointPointB != null){
				break;
			}
		}
	double xA = (21 - jointPointA.x)*0.4;
	double angleA = (Math.acos(xA/SarmLen))*(180/Math.PI);
	double xB = (jointPointB.x - 29)*0.4;
	double angleB = (Math.acos(xB/SarmLen))*(180/Math.PI);
		System.out.println("A: "+jointPointA+"  B:"+jointPointB);
		System.out.println("AngleA: "+angleA+"  AngleB: "+angleB);
	}










	}