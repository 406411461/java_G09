package bigggg;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawTable extends JPanel implements ActionListener{
	private RunTable runtable;
	private JPanel actionPanel;
	private JButton start;
	
	public DrawTable() {
		super();
		this.setLayout(new BorderLayout());
		runtable=new RunTable();
		add(runtable,BorderLayout.CENTER);
		
		actionPanel=new JPanel(new GridBagLayout());
		start =new JButton("start");
		start.addActionListener(this);
		actionPanel.add(start);
		
		add(actionPanel,BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
