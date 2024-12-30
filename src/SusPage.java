import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

public class SusPage extends JFrame implements ActionListener{
	private JPanel panel = new JPanel(); //γενικό panel του Suspect Page
	private JPanel geninfo = new JPanel(); //panel με όνομα και κωδικό όνομα
	private JPanel smsSearch = new JPanel(); //panel με την αναζήτηση sms
	private JPanel possPartners = new JPanel(); //panel με τους πιθανούς συνεργάτες
	private JPanel suggPartners = new JPanel(); //panel με τους προτεινόμενους συνεργάτες
	private JTextField name = new JTextField(10);
	private JTextField codename = new JTextField(10);
	private JList<String> nums = new JList<String>();
	private JTextField numSearch = new JTextField(10);
	private JTextArea messages = new JTextArea(10, 20);
	private JButton findbutton = new JButton("Find SMS");
	private JLabel partners = new JLabel("Partners");
	private JTextArea pPartners = new JTextArea(10, 20);
	private JLabel suggested = new JLabel("Suggested Partners ----->");
	private JTextArea sPartners = new JTextArea(5, 20);
	private DefaultListModel<String> model = new DefaultListModel<String>();
	private JButton backbutton = new JButton("Back to Search Screen");
	private ArrayList<Suspect> temp = new ArrayList<Suspect>(); // temp array list για την ταξινόμιση 
	private Suspect sus;
	private Registry reg;
	private boolean flag; // flag για αν υπάρχουν SMS
	
	public SusPage(Suspect sus, Registry reg)
	{
		this.sus = sus;
		this.reg = reg;
		this.createGeninfo(sus);
		this.createSmsSearch(sus, reg);
		this.createPossPartners(sus);
		this.createSuggPartners(sus);
		this.createBackButton(reg);
		
		panel.add(geninfo);
		panel.add(smsSearch);
		panel.add(possPartners);
		panel.add(suggPartners);
		panel.add(backbutton);
		
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setTitle("Suspect Page");
		this.setSize(470, 610);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createGeninfo(Suspect sus)
	{
		name.setText(sus.getName());
		codename.setText(sus.getCodeName());
		nums.setModel(model);
		
		geninfo.setBorder(BorderFactory.createLineBorder(Color.black));
		geninfo.add(name);
		geninfo.add(codename);
		for(String num : sus.getNums()) //πέρασμα αριθμών του υπόπτου στο model
		{
			model.addElement(num);
		}
		geninfo.add(nums);
	}
	
	private void createSmsSearch(Suspect sus, Registry reg)
	{
		smsSearch.setBorder(BorderFactory.createLineBorder(Color.black));
		smsSearch.add(numSearch);
		smsSearch.add(messages);
		smsSearch.add(findbutton);
		
		findbutton.addActionListener(this);
	}
	
	private void createPossPartners(Suspect sus)
	{
		temp.clear();
		for(Suspect susp : sus.getPossible())
		{
			temp.add(susp);
		}
		temp.sort(null);
		possPartners.setBorder(BorderFactory.createLineBorder(Color.black));
		possPartners.add(partners);
		
		for(Suspect susp : temp)
		{
			pPartners.append(susp.getName() + ", " + susp.getCodeName() + "\n");
		}
		possPartners.add(pPartners);
	}
	
	private void createSuggPartners(Suspect sus)
	{
		temp.clear();
		for(Suspect susp : sus.suggestedSuspects())
		{
			temp.add(susp);
		}
		temp.sort(null);
		suggPartners.setBorder(BorderFactory.createLineBorder(Color.black));
		suggPartners.add(suggested);
		for(Suspect susp : temp)
		{
			sPartners.append(susp.getName() + ", " + susp.getCodeName() + "\n");
		}
		suggPartners.add(sPartners);
		
	}
	
	private void createBackButton(Registry reg)
	{
		backbutton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) { //υλοποίηση της actionPerformed για όλα τα κουμπιά του Suspect Page
		if(e.getSource() == findbutton)
		{
			flag = false;
			messages.setText("");
			for(String num : sus.getNums())
			{
				for(SMS message : reg.getMessagesBetween(num, numSearch.getText()))
				{
					flag = true;
					messages.append(message.getMessage() + "\n");
				}
			}
			
			if(!flag)
			{
				JOptionPane.showMessageDialog(null, "Messages Between this suspect and " + numSearch.getText() + " Not Found");
			}
		}else if(e.getSource() == backbutton)
		{
			new FindSuspect(reg);
			this.dispose(); //κλείσιμο του παραθύρου suspect page κατά την επιστροφή στην αναζήτηση υπόπτου
		}
		
	}
}
