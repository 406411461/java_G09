package a0526;

import java.awt.*;  
import java.awt.geom.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.plaf.ComponentUI;

import java.util.*;
import java.util.Timer;  
  
/** 
 * BallPanel，一个可复用的小球碰撞面板 
 * @author zjf 
 * @version 1.0 2011-1-11 
 */  
@SuppressWarnings("serial")  
public class setBALL extends JPanel implements MouseListener,MouseMotionListener
{  
    private ArrayList<Ball> balls = new ArrayList<Ball>();  //小球列表  

    private BallComponent component = new BallComponent();  //小球画板   
    private BallThread thread = new BallThread();   //小球运动线程  
    private int delay ;  //小球运动的延缓时间  

    private boolean move=true;
    NumBall nball ;
	private int x[]= {100,200};
	private int y[]= {100,300};
	private int x1, y1;
	private int x2, y2;
	final int PLAYER_NUM = 2;// draw shapes with Java 2D API
	
	Timer ballTimer;// ��s�y��m���p�ɾ�
	
	int[] playerScore = new int[PLAYER_NUM];// ���a����
	
	private Share currentShape;


    /** 
     * 初始化小球面板 
     */  
    public setBALL()  
    {  
    	addMouseListener(this);
		addMouseMotionListener(this);
        setLayout(new BorderLayout());  //设置为BorderLayout的布局  
        add(component, BorderLayout.CENTER);    //将小球画板加到面板中央  

        delay = 5; 
        
        for(int i=0;i<2;i++)
        component.addBall(100,100); 
        

         
        thread.start(); //画画板的线程开始  
    }  
      

      
    /** 
     * 小球运动线程 
     * @author zjf 
     */  
    private class BallThread extends Thread  
    {  
        private boolean isStop = false; //停止标记  
          
        /** 
         * 线程体 
         */  
        public void run()  
        {  
            while (move)    //让它一直执行  
            {  
                if (!isStop)    //当没有停止的时候  
                {  
                    for (int i = 0; i <1; i++)  
                    {  
                        balls.get(i).move(component.getBounds());   //每个小球都移动一遍  
                    }  
                    component.repaint();    //重画画板  
                }  
                try {  
                    Thread.sleep(delay);    //线程延缓delay毫秒  
                } catch (InterruptedException e) {  //捕获异常  
                    e.printStackTrace();    //处理异常  
                }  
            }  
        }  
    }  
      
    /** 
     * 小球的画板 
     * @author zjf 
     */  
    private class BallComponent extends JComponent  
    {  

		public void addBall(double x,double y) {
			// TODO Auto-generated method stub

            Color color = Color.BLUE;  //小球开始的颜色 

            
        	balls.add(new Ball(x, y, color));
		}

		public void paintComponent(Graphics g)  
        {  
			
            super.paintComponent(g);  
            Graphics2D g2d = (Graphics2D)g;
        	g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           	g2d.setRenderingHint (RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            g2d.fillArc(-25, -25, 50, 50, 270, 90);
            g2d.fillArc(310, -25, 50, 50, 180, 90);
            g2d.fillArc(-25, 320, 50, 50, 270, 180);
            g2d.fillArc(310, 320, 50, 50, 90, 180);
            g2d.fillArc(-25, 635, 50, 50, 360, 90);
            g2d.fillArc(310, 635, 50, 50, 90, 90);
            
            
     	    g2d.setColor(Color.BLACK);
     	    g2d.drawString("Player1:"+ 0,80, 50);
     	    g2d.drawString("Player2:"+ 0, 200, 50);
     	
            for (int i = 0; i < balls.size(); i++)   //将小球列表中的小球都画到画板上  
            {  
                Ball ball = balls.get(i);  
                g2d.setColor(ball.getColor());   //设置画布中小球的颜色  
                g2d.fill(ball.getShape());       //画出小球的形状  
            }
            if(currentShape != null){
    			currentShape.draw(g);
    			 
    		}
        }  
    }  
      
    /** 
     * 小球类 
     * @author zjf 
     */  
    class Ball  
    {  
    	BouncingBallSimple bounce = new BouncingBallSimple();
        private static final double SIZE = 20;  //小球的直径  
        private int speed=2;
        private double x;   //小球所在的x坐标  
        private double y;   //小球所在的y坐标  
        private double vx;   //小球在x轴的速度  
        private double vy;   //小球在y轴的速度  
        private Color color = Color.RED;      //小球的颜色  
          
        /** 
         * 小球的构造函数 
         * @param x 小球所在的x坐标 
         * @param y 小球所在的y坐标 
         * @param color 小球的颜色 
         * @return 
         * @return 
         */  
       
        public Ball(double x, double y, Color color)  
        {  
        	
            this.x = x;  
            this.y = y;  
            this.color = color;  
            sp(speed);
        }  
        public void sp(int speed) {
        	vx = Math.sqrt(speed) / 2;
        	vy = Math.sqrt(speed) / 2;
        }
        /** 
         * 小球在一个矩形边框中移动 
         * @param bounds 矩形边框 
         */  
        public void move(Rectangle2D bounds)  
        {  
        	
            x += vx;    //小球在x轴上的位移  
            y += vy;    //小球在y轴上的位移  
            double minX = bounds.getMinX(); //矩形边界的最小x坐标  
            double minY = bounds.getMinY(); //矩形边界的最小y坐标  
            double maxX = bounds.getMaxX(); //矩形边界的最大x坐标  
            double maxY = bounds.getMaxY(); //矩形边界的最大y坐标  
            if (x <= minX)   //如果小球越过左边界  
            {  
                x = minX;   //小球的x坐标变为矩形边界的最小x坐标  
                vx = -vx;   //小球在x轴方向的速度反向  
            }  
            if (y <= minY)   //如果小球越过上边界  
            {  
                y = minY;   //小球的y坐标变为矩形边界的最小y坐标  
                vy = -vy;   //小球在y轴方向的速度反向  
            }  
            if (x + SIZE >= maxX)    //如果小球越过右边界  
            {  
                x = maxX - SIZE;    //小球的x坐标变为矩形边界的最大x坐标减去小球的直径  
                vx = -vx;           //小球在x轴方向的速度反向  
            }  
            if (y + SIZE >= maxY)    //如果小球越过下边界  
            {  
                y = maxY - SIZE;    //小球的y坐标变为矩形边界的最大y坐标减去小球的直径  
                vy = -vy;           //小球在y轴方向的速度反向  
            }  
            for (int i = 0; i < balls.size(); i++)   //判断小球间是否发生碰撞  
            {  
                Ball ball = balls.get(i);  
                if (this.equals(ball))  //自己和自己不碰撞  
                    continue;  
                if ((ball.x - x) * (ball.x - x) + (ball.y - y) * (ball.y - y) <= SIZE * SIZE)    //当两球间的距离小于直径时，可认为两小球发生了碰撞  
                {  
                    double degree = Math.atan((y - ball.y) / (x - ball.x)); //获取自己与发生碰撞的小球之间所形成的夹角，因为夹角只能在-pi/2-pi/2之间，所以还需判断两球的x坐标之间的关系  
                    if (x > ball.x)      //如果自己的x坐标大于发生碰撞的小球的x坐标，由数学知识可知自己应该往正向运动  
                    {  
                        vx = Math.cos(degree);    
                        vy = Math.sin(degree);  
                    }  
                    else    //如果自己的x坐标小于发生碰撞的小球的x坐标，由数学知识可知应该朝负向运动  
                    {  
                        vx = -Math.cos(degree);  
                        vy = -Math.sin(degree);  
                    }  
                }  
            }  
        }  
          
        /** 
         * 获取小球的形状 
         * @return 形状 
         */  
        public Ellipse2D getShape()  
        {  

            return new Ellipse2D.Double(x, y, SIZE, SIZE);  
        }  
          
        /** 
         * 获取小球的颜色 
         * @return 颜色 
         */  
        public Color getColor()  
        {  
            return color;  
        }  
          
        /** 
         * 判断两个小球是否相同 
         */  
        public boolean equals(Object object)  
        {  
            if (this == object) return true;    //如果所指的对象相同，即两小球的确相同  
            if (object == null) return false;   //如果要比较的小球不存在，则两小球不同  
            if (getClass() != object.getClass()) return false;  //如果自己的类名与另一个对象的类名不同，则两小球不同  
            Ball ball = (Ball)object;           //将另一个对象强制转化为小球  
            return x == ball.x && y == ball.y && color.equals(ball.color);  //通过方位，颜色判断是否相同  
        }  
    }
    @Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		x2 = x1;
		y2 = y1;
;
		currentShape = new Setpower(x1,y1,x2,y2);
		System.out.println("mouseReleased: "+x1+" "+y1);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		currentShape.x2 = e.getX();
		currentShape.y2 = e.getY();
		repaint();
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		int sum;
		x2 = e.getX();
		y2 = e.getY();
		
		
		currentShape=null;
		repaint();
		System.out.println("mouseReleased: "+x2+" "+y2);
		sum=((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2))/10000;
		
		
		System.out.println("sum:"+sum);          //sum�ȥi�ΨөI�s����
		
		
	}
	
	
	
	
	
	
	
	
	
	

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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

} 