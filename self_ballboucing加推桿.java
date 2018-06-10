package drawpan;



 import java.awt.Color;
 import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
 
 public class self_ballboucing extends JFrame implements MouseMotionListener,MouseListener{
	
	//定?移??量
	 int speed = 10;
	 int stick_x;
	 int stick_y;
	 int stick_speed;
	 
	 int gapx;
	 int gapy;
	 int gapm;
	 int momcenter_x=250;
	 int momcenter_y=400;
	 int valuex;
	 int valuey;
	 
     int x = 1000;
     int y = 100;
//     double m = 1;
//     double n = -1;
     double vx = Math.sqrt(speed) / 2;   //小球在x轴的速度  
     double vy = -Math.sqrt(speed) / 2; 
     int a=250;
     int b=100;
     double ax = -Math.sqrt(speed) / 2;   //小球在x轴的速度  
     double by = Math.sqrt(speed) / 2;
     int t=0;
     int j=0;
     float slope;
     float test;
     //主函?
     public static void main(String[] args) {
         new self_ballboucing();
     }
     //使用构造器?建窗体并?置
     public self_ballboucing(){
         this.setVisible(true);
         this.setSize(500,500);
         this.setDefaultCloseOperation(3);
//         this.setLocation(400, 100);
         this.setResizable(false);
         this.setTitle("??框架");
         addMouseListener(this);
         addMouseMotionListener(this);
         move();
     }
     //重???方法
     @Override
     public void paint(Graphics g) {
         super.paint(g);
         g.setColor(Color.BLACK);
         g.fillOval(x, y, 50, 50);
         g.setColor(Color.RED);
         g.fillOval(a, b, 50, 50);
         g.setColor(Color.WHITE);
         g.fillOval(225,375,50,50);
         g.setColor(Color.BLACK);
         g.drawLine(momcenter_x,momcenter_y,stick_x,stick_y);
     }
     //定?小球移???
     public void move(){
         while(true){
        	 /*t++;
        	 j++;
        	 if(j==100) {
        		 break;
        	 }*/
        	 x += vx;      
             y += vy;
             a += ax;
             b += by;
//             if(x==450&&y==425) {
//            	 x=10000;
//            	 y=10000;
////            	 break;
//             }
             if(x >= 425){
            	 //System.out.println("x=450");
            	 vx = -Math.sqrt(speed) / 2;
             }
             if(x <= 25){
            	 //System.out.println("x=0");
            	 vx = Math.sqrt(speed) / 2;
             }
             if(y >= 425){
            	 //System.out.println("y=425");
            	 vy = -Math.sqrt(speed) / 2;
             }
             if(y <= 25){
            	 //System.out.println("y=0");
            	 vy = Math.sqrt(speed) / 2;
             }
             
             
             if(a > 425){
                 ax =  -Math.sqrt(speed) / 2;; 
             }
             if(a < 25){
                 ax =  Math.sqrt(speed) / 2;;
             }
             if(b > 425){
                 by =  -Math.sqrt(speed) / 2;;
             }
             if(b < 25){
                 by =  Math.sqrt(speed) / 2;;
             }
             if(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2))<50) {
            	 //System.out.println(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2)));
                 //System.out.printf("y-b==%d ,x-a ==%d",y-b,x-a);
                 //System.out.println();
                 //System.out.printf("%",(y-b)/(x-a));
                 double temp1=(y-b)/(x-a);
 //           	 System.out.printf("temp1==%f",temp1);
            	
            	 double degree2 = Math.atan((b-y) / (a-x));
            	 
                 double degree = Math.atan((y - b) / (x - a)); //获取自己与发生碰撞的小球之间所形成的夹角，因为夹角只能在-pi/2-pi/2之间，所以还需判断两球的x坐标之间的关系  
                 /*if(degree==0){
                	 System.out.printf("degree==0");
                	 degree++;
                	 degree2++;
                 }*/
                 //System.out.print(degree);
                 //System.out.println();
                 if (x > a)      //如果自己的x坐标大于发生碰撞的小球的x坐标，由数学知识可知自己应该往正向运动  
                 {  
                	 
                	 if(degree==0){
  //                  	 System.out.printf("x>a&&degree==0");
  //                  	 System.out.println();
                    	 degree++;
                    	 degree2++;
                    	 vx = Math.cos(degree);    
                         vy = Math.sin(degree);
                         
                         ax = -Math.cos(degree2);  
                         by = -Math.sin(degree2);
                     }
                     vx = Math.cos(degree);    
                     vy = Math.sin(degree);
                     
                     ax = -Math.cos(degree2);  
                     by = -Math.sin(degree2); 
                     
                 }  
                 else if(x<a)   //如果自己的x坐标小于发生碰撞的小球的x坐标，由数学知识可知应该朝负向运动  
                 {  
                	 if(degree==0){
 //                   	 System.out.printf("x<a&&degree==0");
 //                   	 System.out.println();

                    	 degree--;
                    	 degree2--;
                    	 vx = -Math.cos(degree);  
                         //System.out.print(m);
                         vy = -Math.sin(degree);  
                         //System.out.print(m);
                         ax = Math.cos(degree2);    
                         by = Math.sin(degree2);
                     }
                	 //System.out.println(x-a);
//                	 System.out.println(a);
                     vx = -Math.cos(degree);  
                     //System.out.print(m);
                     vy = -Math.sin(degree);  
                     //System.out.print(m);
                     ax = Math.cos(degree2);    
                     by = Math.sin(degree2);
                     }  
                   
            	 
            	 
            	 
             }
//             else if(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2))>50) {
//            	//System.out.printf("stop");
//            	 
//            	 
//            	 
//             }
             /*else if(Math.sqrt(Math.pow(x-300,2)+Math.pow(y-300,2))==50) {
            	 System.out.printf("COLLISE");
            	 a=a+1;
            	 b=b+1;
             }*/
             try {
                 Thread.sleep(5);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             
             
             
             
             repaint();
         }
     }
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		stick_x=e.getX();
		stick_y=e.getY();
//		System.out.println(e.getX());
//	    System.out.println(e.getY());
	    
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		/*
		gapx=stick_x-250;
		gapy=stick_y-400;
		gapm=gapy/gapx;
		//g.drawLine(momcenter_x,momcenter_y,stick_x,stick_y);
		if(gapm>0&&stick_x>momcenter_x){
			valuex=-1;
		}
		else if(gapm>0&&stick_x<momcenter_x){
			valuex=1;
		}
		else if(gapm<0&&stick_x>momcenter_x){
			valuey=-1;
		}
		else if(gapm<0&&stick_x<momcenter_x){
			valuey=1;
		}
		momcenter_x=momcenter_x-1*valuex;
		momcenter_y=momcenter_y-gapm*valuey;
		stick_x=stick_x-1*valuex;
		stick_y=stick_y-gapm*valuey;
		System.out.printf("Pressed");
		*/
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		/*gapx=stick_x-250;
		gapy=stick_y-400;
		gapm=gapy/gapx;
		//g.drawLine(momcenter_x,momcenter_y,stick_x,stick_y);
		momcenter_x=momcenter_x-1;
		momcenter_y=momcenter_y-gapm;
		//stick_x=stick_x+1;
		//stick_y=stick_y+gapm;
		System.out.printf("Drag");*/
		
	    //System.out.print(e.getX());
	    //System.out.print(e.getY());
		gapx=stick_x-250;
		gapy=stick_y-400;
		gapm=gapy/gapx;
		//g.drawLine(momcenter_x,momcenter_y,stick_x,stick_y);
		if(gapm>0&&stick_x>momcenter_x){
			valuex=-1;
		}
		else if(gapm>0&&stick_x<momcenter_x){
			valuex=1;
		}
		else if(gapm<0&&stick_x>momcenter_x){
			valuey=-1;
		}
		else if(gapm<0&&stick_x<momcenter_x){
			valuey=1;
		}
		momcenter_x=(int) (momcenter_x-1*valuex*0.1);
		momcenter_y=(int) (momcenter_y-gapm*valuey*0.1);
		stick_x=(int) (stick_x-1*valuex*0.1);
		stick_y=(int) (stick_y-gapm*valuey*0.1);
		System.out.printf("Drag");
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		/*System.out.printf("t==%d",t);
		gapx=stick_x-250;
		gapy=stick_y-400;
		gapm=gapy/gapx;
		//g.drawLine(momcenter_x,momcenter_y,stick_x,stick_y);
		if(gapm>0&&stick_x>momcenter_x){
			valuex=-1;
		}
		else if(gapm>0&&stick_x<momcenter_x){
			valuex=1;
		}
		else if(gapm<0&&stick_x>momcenter_x){
			valuey=-1;
		}
		else if(gapm<0&&stick_x<momcenter_x){
			valuey=1;
		}
		momcenter_x=momcenter_x-1*valuex*t;
		momcenter_y=momcenter_y-gapm*valuey*t;
		stick_x=stick_x-1*valuex*t;
		stick_y=stick_y-gapm*valuey*t;
		System.out.printf("Release");
		*/
	}
 }