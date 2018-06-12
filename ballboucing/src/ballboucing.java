

 import java.awt.Color;
 import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
 
 public class ballboucing extends JFrame implements MouseListener,MouseMotionListener{
	
	//定?移??量
	 int speed = 10;
	 static int width = 500;
	 static int height = 500;
     int x=100;
     int y=100;
//     double m = 1;
//     double n = -1;
     double vx = 4;   //小球在x轴的速度  
     double vy = 9; 
     int i=175;
     int j=350;
     double vi = 6;   //小球在x轴的速度  
     double vj = 10;
     int radius=50;
	 int stick_x=275;
	 int stick_y=425;
	 int momx=250;
	 int momy=400;
	 int momm=0;
	 int momvx=0;
	 int momvy=0;
     //主函?
     public static void main(String[] args) {
         new  ballboucing();
     }
     //使用构造器?建窗体并?置
     public  ballboucing(){
         this.setVisible(true);
         this.setSize(width,height);
         this.setDefaultCloseOperation(3);
         this.setResizable(false);
         this.setTitle("??框架");
         addMouseListener(this);
         addMouseMotionListener(this);
         
         move();

 		
 		
     }
     
     public int momx(){
    	 return momx;
     }
     public int momy(){
    	 return momy;
     }
     public int momvx(){
    	 return momy;
     }
     public int momvy(){
    	 return momy;
     }
     
     
     //重???方法
     @Override
     public void paint(Graphics g) {
         super.paint(g);
         g.setColor(Color.blue.darker());
         g.fillOval(x, y, radius, radius);
         g.setColor(Color.blue);
         g.fillOval(x+radius/4, y+radius/4, radius/2, radius/2);
         g.setColor(Color.RED.darker());
         g.fillOval(i, j, radius, radius);
         g.setColor(Color.RED);
         g.fillOval(i+radius/4, j+radius/4, radius/2, radius/2);
         g.setColor(Color.white);
         g.fillOval(momx,momy, radius, radius);
         g.setColor(Color.black);
         momx();
         momy();
         momvx();
         momvy();
         
         g.drawLine(momx+radius/2,momy+radius/2, stick_x,stick_y );
         stick_x=momx+radius/2;
   		 stick_y=momy+radius/2;
        
     }
     //定?小球移???
     public void move(){
         while(true){
        	 /*t++;
        	 j++;
        	 if(j==100) {
        		 break;
        	 }*/
//             if(x==450&&y==425) {
//            	 x=10000;
//            	 y=10000;
////            	 break;
//             }
        	 momx+=momvx;
        	 momy+=momvy;
        	 if(momx>425) {
        		 momvx=-momvx;
        	 }
        	 if(momx<25) {
        		 momvx=-momvx;
        	 }
        	 if(momy>425) {
        		 momvy=-momvy;
        		 
        	 }
        	 if(momy<25) {
        		 momvy=-momvy;
        	 }
            if(vx >0){
            	 //System.out.println("x=450");
            	vx = vx - 0.0001;
					if (vx<=0) {
						vx = 0;
					}
					else if (x+50 > width) {
						vx = -vx;
						x = width-50;
					}
					break;
			}
             if(vx < 0){
            	 //System.out.println("x=0");
            	 vx = vx + 0.0001;
					if (vx>=0) {
						vx = 0;
					}
					else if (x < 0) {
						 vx = -vx;
						 x = 0;
					}
			 }
             
             if(vy > 0){
            	 //System.out.println("y=425");
            	 vy = vy - 0.0001;
					if (vy<=0) {
						vy = 0;
					}
					else if (y+50 > height) {
						vy = -vy;
						y = height-50;
					}
			}
             if(vy < 0){
            	 //System.out.println("y=0");
            	 vy = vy + 0.0001;
					if (vy>=0) {
						vy = 0;
					}
					else if (y < 0) {
						 vy = -vy;
						 y = 0;
					}
             }
             
             if(vi >0){
            	 //System.out.println("x=450");
            	vi = vi - 0.0001;
					if (vi<=0) {
						vi = 0;
					}
					else if (i+50 > width) {
						vi = -vi;
						i = width-50;
					}
			}
             if(vi < 0){
            	 //System.out.println("x=0");
            	 vi = vi + 0.0001;
					if (vi>=0) {
						vi = 0;
					}
					else if (i < 0) {
						 vi = -vi;
						 i = 0;
					}
			 }
             
             if(vj > 0){
            	 //System.out.println("y=425");
            	 vj = vj - 0.0001;
					if (vj<=0) {
						vj = 0;
					}
					else if (j+50 > height) {
						vj = -vj;
						j = height-50;
					}
			}
             if(vj < 0){
            	 //System.out.println("y=0");
            	 vj = vj + 0.0001;
					if (vj>=0) {
						vj = 0;
					}
					else if (j < 0) {
						 vj = -vj;
						 j = 0;
					}
             }
             
             x += vx;      
             y += vy;
             i += vi;
             j += vj;
             
             if(Math.sqrt(Math.pow(x-i,2)+Math.pow(y-j,2))<50) {
            	 //System.out.println(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2)));
            	
            	 
                 double degree = Math.atan((x-i) / (y-j)); //获取自己与发生碰撞的小球之间所形成的夹角，因为夹角只能在-pi/2-pi/2之间，所以还需判断两球的x坐标之间的关系  
        
                 if (x > i)      //如果自己的x坐标大于发生碰撞的小球的x坐标，由数学知识可知自己应该往正向运动  
                 {  
                	    vx = vx*Math.cos(degree);    
                        vy = vy*Math.sin(degree);
                     
                        vi = vi*-Math.cos(degree);  
                        vj = vj*-Math.sin(degree); 
                 }  
                 else if(x < i)   //如果自己的x坐标小于发生碰撞的小球的x坐标，由数学知识可知应该朝负向运动  
                 {  
                 	    vx = vx*-Math.cos(degree);    
                         vy = vy*-Math.sin(degree);
                      
                         vi = vi*Math.cos(degree);  
                         vj = vj*Math.sin(degree); 
                  }  
                   
             }
             
             try {
                 Thread.sleep(10);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             if(vx ==0 && vy==0 && vi==0 && vj==0) {
            	 System.out.print("0");
            	 break;
             }
             repaint();
         }
     }
	@Override
	public void mouseDragged(MouseEvent e) {
		stick_x=e.getX();
		stick_y=e.getY();
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
//		stick_x=e.getX();
//		stick_y=e.getY();
//		System.out.printf("Move");
		
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
		// TODO Auto-generated method stub
		System.out.printf("Move");
		stick_x=e.getX();
		stick_y=e.getY();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		stick_x=e.getX();
		stick_y=e.getY();
		momm=(stick_y-momy)/(stick_x-momx);
//		momvx=(stick_x-momx)/momm;
//		momvy=(stick_y-momy)/momm;
		if(momx>stick_x) {
			if(momy>stick_y) {
				System.out.printf("x>y>");
				
				momvx=(int) (-(stick_x-momx)/Math.pow(momm, 20));
				momvy=(int) (-(stick_y-momy)/Math.pow(momm, 20));
				System.out.println(momvx);
				System.out.println(momvy);
			}
			else {
				System.out.printf("x>y<");
				momvx=(int) (-(stick_x-momx)/Math.pow(momm, 30));
				momvy=(int) ((stick_y-momy)/Math.pow(momm, 30));
			}
		}
		
		if(momx<stick_x) {
			if(momy>stick_y) {
				System.out.printf("x<y>");
				momvx=(int) ((stick_x-momx)/momm);
				momvy=(int) (-(stick_y-momy)/momm);
				System.out.print(momvx);
				System.out.print(momvy);
			}
			else {
				System.out.printf("x<y<");
				momvx=(int) (-(stick_x-momx)/Math.pow(momm, 30));
				momvy=(int) (-(stick_y-momy)/Math.pow(momm, 30));
				System.out.print(momvx);
				System.out.print(momvy);
			}
		}
		
//		System.out.println(momx);
//		System.out.println(momy);
//		System.out.println(stick_x);
//		System.out.println(stick_y);
		
		stick_x=momx+radius/2;
		stick_y=momy+radius/2;
	}
	
	
	
	
	
	
	
 }