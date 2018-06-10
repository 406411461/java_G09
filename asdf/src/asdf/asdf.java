package asdf;

 import java.awt.Color;
 import java.awt.Graphics;
  
 import javax.swing.JFrame;
 
 public class asdf extends JFrame{
	
	//定?移??量
     int x = 100;
     int y = 50;
     double m = 1;
     double n = -1;
     int a=250;
     int b=100;
     double c=0;
     double d=0;
     int t=0;
     int j=0;
     float slope;
     float test;
     //主函?
     public static void main(String[] args) {
         new asdf();
     }
     //使用构造器?建窗体并?置
     public asdf(){
         this.setVisible(true);
         this.setSize(500,500);
         this.setDefaultCloseOperation(3);
//         this.setLocation(400, 100);
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
         g.fillOval(a, b, 50, 50);
     }
     //定?小球移???
     public void move(){
         while(true){
        	 /*t++;
        	 j++;
        	 if(j==100) {
        		 break;
        	 }*/
             x += m;
             y += n;
             a += c;
             b += d;
//             if(x==450&&y==425) {
//            	 x=10000;
//            	 y=10000;
////            	 break;
//             }
             if(x > 425){
            	 System.out.println("x=450");
                 m = -m; 
             }
             if(x < 25){
            	 System.out.println("x=0");
                 m = 1;
             }
             if(y > 425){
            	 System.out.println("y=425");
                 n = -n;
             }
             if(y < 25){
            	 System.out.println("y=0");
                 n = 1;
             }
             
             
             if(a > 425){
                 c = -c; 
             }
             if(a < 25){
                 c = 0;
             }
             if(b > 425){
                 d = -d;
             }
             if(b < 25){
                 d = 0;
             }
             if(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2))<50) {
            	 System.out.println(Math.sqrt(Math.pow(x-a,2)+Math.pow(y-b,2)));
            
            	 
            	 
            	
                     double degree = Math.atan((y - b) / (x - a)); //获取自己与发生碰撞的小球之间所形成的夹角，因为夹角只能在-pi/2-pi/2之间，所以还需判断两球的x坐标之间的关系  
                     if (x > a)      //如果自己的x坐标大于发生碰撞的小球的x坐标，由数学知识可知自己应该往正向运动  
                     {  
                         m = Math.cos(degree);    
                         n = Math.sin(degree);
                         
                         //c = -Math.cos(degree);  
                         //d = -Math.sin(degree); 
                         
                     }  
                     else   //如果自己的x坐标小于发生碰撞的小球的x坐标，由数学知识可知应该朝负向运动  
                     {  
                    	 System.out.println(x-a);
//                    	 System.out.println(a);
                         m = -Math.cos(degree);  
                         System.out.print(m);
                         n = -Math.sin(degree);  
                         System.out.print(m);
                         //c = Math.cos(degree);    
//                         d = Math.sin(degree);
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
 }