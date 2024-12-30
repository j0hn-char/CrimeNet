import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FindSuspect extends JFrame implements ActionListener{
	private JPanel panel = new JPanel();
	private JButton findbutton = new JButton("Find");
	private JTextField text = new JTextField("Please enter suspect's name");
	private boolean flag = false;
	private Registry reg;

	public FindSuspect(Registry reg)
	{
		this.reg = reg;
		panel.add(text);
		panel.add(findbutton);
		
		this.setContentPane(panel);
		
		findbutton.addActionListener(this);
		
		this.setVisible(true);
		this.setSize(300, 100);
		this.setTitle("Find Suspect");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for(Suspect sus : reg.getSuspects())
		{
			if(sus.getName().equals(text.getText()))
			{
				flag = true;
				new SusPage(sus, reg);
				this.dispose();
			}
		}
		if(!flag)
		{
			JOptionPane.showMessageDialog(null, "Suspect " + text.getText() + " Not Found");
		}
	}
}
