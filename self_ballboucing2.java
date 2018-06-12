 import java.awt.Color;
 import java.awt.Graphics;
  
 import javax.swing.JFrame;
 
 public class self_ballboucing extends JFrame{
	
	//定?移??量
	 int speed = 10;
	 static int width = 500;
	 static int height = 500;
     int x=55;
     int y=85;
//     double m = 1;
//     double n = -1;
     double vx = 4;   //小球在x轴的速度  
     double vy = 9; 
     int i=175;
     int j=350;
     double vi = 6;   //小球在x轴的速度  
     double vj = 10;
     //主函?
     public static void main(String[] args) {
         new self_ballboucing();
     }
     //使用构造器?建窗体并?置
     public self_ballboucing(){
         this.setVisible(true);
         this.setSize(width,height);
         this.setDefaultCloseOperation(3);
         this.setResizable(false);
         this.setTitle("??框架");
         move();
     }
     //重???方法
     @Override
     public void paint(Graphics g) {
         super.paint(g);
         g.setColor(Color.BLACK);
         g.fillOval(x, y, 50, 50);
         g.setColor(Color.RED);
         g.fillOval(i, j, 50, 50);
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
 }