package bigggg;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class DrawTable extends JPanel implements ActionListener{
	private RunTable runtable;
	private JButton start;
	
	public DrawTable() {
		super();

		JButton start =new JButton("start");
		start.addActionListener(this);
		add(start,BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start) {
			runtable.go();
		}
		
	}

}
