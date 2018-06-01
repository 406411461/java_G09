package D;

import java.awt.*;  
import java.awt.geom.*;  
import java.awt.event.*;  
import javax.swing.*;  
import javax.swing.plaf.ComponentUI;  
import java.util.*;  
  
/** 
 * BallPanel，一个可复用的小球碰撞面板 
 * @author zjf 
 * @version 1.0 2011-1-11 
 */  
@SuppressWarnings("serial")  
public class AA extends JPanel   
{  
    private ArrayList<Ball> balls = new ArrayList<Ball>();  //小球列表  
    private BallComponent component = new BallComponent();  //小球画板  
    private JButton btnAdd = new JButton("Add");    //Add按钮  
    private JButton btnStopAndContinue = new JButton("Stop");   //Stop按钮  
    private JButton btnClear = new JButton("Clear");    //Clear按钮  
    private JComboBox colorCombo = new JComboBox(); //颜色选择框  
    private JComboBox speedCombo = new JComboBox(); //速度选择框  
    private JComboBox placeCombo = new JComboBox(); //小球出现方位  
    private BallThread thread = new BallThread();   //小球运动线程  
    private int delay = 5;  //小球运动的延缓时间  
      
    /** 
     * 初始化小球面板 
     */  
    public AA()  
    {  
        setLayout(new BorderLayout());  //设置为BorderLayout的布局  
        add(component, BorderLayout.CENTER);    //将小球画板加到面板中央  
        component.setOpaque(true);              //设置画板不透明，以便能添加背景色  
        component.setBackground(Color.BLACK);   //设置背景色  
          
        JPanel panel = new JPanel();    //创建用来放各种按钮的面板  
        panel.add(btnAdd);              //将Add按钮放入该面板  
        panel.add(btnStopAndContinue);  //将Stop/Continue按钮放入该面板  
        panel.add(btnClear);            //将Clear按钮放入该面板  
        panel.setBackground(Color.LIGHT_GRAY);  
        add(panel, BorderLayout.SOUTH); //将按钮面板加到主面板南部  
          
        panel = new JPanel(new GridLayout(0, 1));   //创建用来放各种选择框的面板  
        panel.add(new JLabel(" Color: "));  //添加标签Color:  
        panel.add(colorCombo);              //添加颜色选择框  
        panel.add(new JLabel(" Speed: "));  //添加标签Speed:  
        panel.add(speedCombo);              //添加速度选择框  
        panel.add(new JLabel(" From: "));   //添加标签From:  
        panel.add(placeCombo);              //添加方位选择框  
        panel.setBackground(Color.LIGHT_GRAY);  
        add(panel, BorderLayout.EAST);      //将选择框面板加到主面板东部  
          
        //以下几句话用来向颜色选择框加入各种颜色选项  
        colorCombo.addItem("red");  
        colorCombo.addItem("orange");  
        colorCombo.addItem("yellow");  
        colorCombo.addItem("green");  
        colorCombo.addItem("cyan");  
        colorCombo.addItem("blue");  
        colorCombo.addItem("magenta");  
          
        //以下几句话用来向速度选择框加入各种速度选项  
        speedCombo.addItem("slow");  
        speedCombo.addItem("fast");  
        //速度选择框加入监听器，及时改变小球的速度  
        speedCombo.addActionListener(new ActionListener()  
            {  
                public void actionPerformed(ActionEvent event)  
                {  
                    String speed = (String)speedCombo.getSelectedItem();  
                    if (speed.equals("slow"))  
                    {  
                        delay = 5;  
                    }  
                    else  
                    {  
                        delay = 1;  
                    }  
                }  
            });  
          
        //以下几句话用来向方位选择框加入各种方位选项  
        placeCombo.addItem("Left-Top");  
        placeCombo.addItem("Left-Bottom");  
        placeCombo.addItem("Right-Top");  
        placeCombo.addItem("Right-Bottom");  
          
        //Add按钮加入监听器，当按下按钮时添加小球  
        btnAdd.addActionListener(new ActionListener()  
            {  
                public void actionPerformed(ActionEvent event)  
                {  
                    component.addBall();  
                }  
            });  
        //Stop/Continue按钮加入监听器，当按下按钮时暂停/继续动画  
        btnStopAndContinue.addActionListener(new ActionListener()  
            {  
                public void actionPerformed(ActionEvent event)  
                {  
                    if (btnStopAndContinue.getText().equals("Stop"))    //如果当前按钮的值为Stop  
                    {  
                        thread.setStop(true);   //将stop标志置为true  
                        btnStopAndContinue.setText("Continue"); //将按钮的标签变为Continue  
                        btnAdd.setEnabled(false);   //Add按钮不可用  
                    }  
                    else  
                    {  
                        thread.setStop(false);  //将stop标志置为false  
                        btnStopAndContinue.setText("Stop"); //将按钮的标签变为Stop  
                        btnAdd.setEnabled(true);    //Add按钮可用  
                    }  
                }  
            });  
        //Clear按钮加入监听器，当按下按钮时清空画板  
        btnClear.addActionListener(new ActionListener()  
            {  
                public void actionPerformed(ActionEvent event)  
                {  
                    balls = new ArrayList<Ball>();    //将球的列表清空  
                    component.repaint();            //重画画板  
                }  
            });  
          
        thread.start(); //画画板的线程开始  
    }  
      
    /** 
     * 主函数，主要用于测试 
     * @param args 
     */  
    public static void main(String[] args)  
    {  
        EventQueue.invokeLater(new Runnable()  
            {  
                public void run()  
                {  
                    JFrame frame = new JFrame("Hit Balls"); //设置测试框架的标题  
                    frame.add(new AA());     //将小球碰撞动画面板放上去  
                    frame.setSize(400, 300);        //设置框架大小  
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //设置框架的默认关闭方式  
                    frame.setLocationByPlatform(true);  //将框架的定位交给系统实现  
                    frame.setVisible(true);         //设置框架可见  
                }  
            });  
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
            while (true)    //让它一直执行  
            {  
                if (!isStop)    //当没有停止的时候  
                {  
                    for (int i = 0; i < balls.size(); i++)  
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
          
        /** 
         * 设置stop标志 
         * @param isStop    是否停止 
         */  
        public void setStop(boolean isStop)  
        {  
            this.isStop = isStop;  
        }  
    }  
      
    /** 
     * 小球的画板 
     * @author zjf 
     */  
    private class BallComponent extends JComponent  
    {  
        public BallComponent()  
        {  
            //说实话，我不是很明白这段代码是干什么的，但是要用到背景色必须用到这段代码  
            setUI(new ComponentUI()       
            {  
                public void installUI(JComponent c)  
                {  
                    super.installUI(c);  
                    LookAndFeel.installColors(c, "Panel.background",  
                            "Panel.foreground");  
                }  
            });  
        }  
        /** 
         * 添加小球 
         */  
        public void addBall()  
        {  
            double x = 0;   //小球开始的x坐标  
            double y = 0;   //小球开始的y坐标  
            String tmp = (String)placeCombo.getSelectedItem();  //得到方位的选择项  
            if (tmp.equals("Left-Top")) //如果为左上  
            {  
                x = component.getBounds().getMinX();    //x设为画板的最左边的值  
                y = component.getBounds().getMinY();    //y设为画板的最上边的值  
            }  
            if (tmp.equals("Left-Bottom"))  //下同  
            {  
                x = component.getBounds().getMinX();  
                y = component.getBounds().getMaxY();  
            }  
            if (tmp.equals("Right-Top"))  
            {  
                x = component.getBounds().getMaxX();  
                y = component.getBounds().getMinY();  
            }  
            if (tmp.equals("Right-Bottom"))  
            {  
                x = component.getBounds().getMaxX();  
                y = component.getBounds().getMaxY();  
            }  
              
            Color color = Color.BLACK;  //小球开始的颜色  
            tmp = (String)colorCombo.getSelectedItem(); //得到颜色的选择项  
            if (tmp.equals("red"))  //如果为red  
            {  
                color = Color.RED;  //颜色设为red  
            }  
            if (tmp.equals("orange"))   //下同  
            {  
                color = Color.ORANGE;  
            }  
            if (tmp.equals("yellow"))  
            {  
                color = Color.YELLOW;  
            }  
            if (tmp.equals("green"))  
            {  
                color = Color.GREEN;  
            }  
            if (tmp.equals("cyan"))  
            {  
                color = Color.CYAN;  
            }  
            if (tmp.equals("blue"))  
            {  
                color = Color.BLUE;  
            }  
            if (tmp.equals("magenta"))  
            {  
                color = Color.MAGENTA;  
            }  
            balls.add(new Ball(x, y, color));   //在小球的列表中加入新球，球的初始方位和颜色为前面的值  
        }  
          
        /** 
         * 绘制画板 
         */  
        public void paintComponent(Graphics g)  
        {  
            super.paintComponent(g);  
            Graphics2D g2 = (Graphics2D)g;  
            for (int i = 0; i < balls.size(); i++)   //将小球列表中的小球都画到画板上  
            {  
                Ball ball = balls.get(i);  
                g2.setColor(ball.getColor());   //设置画布中小球的颜色  
                g2.fill(ball.getShape());       //画出小球的形状  
            }  
        }  
    }  
      
    /** 
     * 小球类 
     * @author zjf 
     */  
    private class Ball  
    {  
        private static final double SIZE = 20;  //小球的直径  
        private double x = 0;   //小球所在的x坐标  
        private double y = 0;   //小球所在的y坐标  
        private double vx = Math.sqrt(2) / 2;   //小球在x轴的速度  
        private double vy = Math.sqrt(2) / 2;   //小球在y轴的速度  
        private Color color = Color.BLACK;      //小球的颜色  
          
        /** 
         * 小球的构造函数 
         * @param x 小球所在的x坐标 
         * @param y 小球所在的y坐标 
         * @param color 小球的颜色 
         */  
        public Ball(double x, double y, Color color)  
        {  
            this.x = x;  
            this.y = y;  
            this.color = color;  
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
} 