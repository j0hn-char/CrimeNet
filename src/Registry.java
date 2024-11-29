import java.util.ArrayList;

public class Registry {
	private ArrayList<Communication> comms = new ArrayList<>();
	private ArrayList<Suspect> suspects = new ArrayList<>();
	
	public void addSuspect(Suspect sus)
	{
		this.suspects.add(sus);
	}
	
	public void addCommunication(Communication comm) //προσθέτη μία επικοινωνία στην λίστα επικοινωνιών ενημερώνοντας τις λίστες πιθανών συνεργατών των εμπλεκόμενων υπόπτων
	{
		this.comms.add(comm);
		Suspect susp1 = null;
		Suspect susp2 = null;
		
		for(Suspect sus1 : this.suspects)
		{
			if(sus1.getNums().contains(comm.getNum1()))
			{
				susp1 = sus1;
				break;
			}
		}
		
		for(Suspect sus2 : this.suspects)
		{
			if(sus2.getNums().contains(comm.getNum2()))
			{
				susp2 = sus2;
				break;
			}
		}
		susp1.addPossiblePartner(susp2);
		susp2.addPossiblePartner(susp1);
	}
	
	public Suspect getSuspectWithMostPartners() //εππιστρέφει τον ύποπτο με τους περισσότερους πιθανούς συνεργάτες
	{
		int max = -1;
		Suspect maxsus = null;
		
		for(Suspect sus : this.suspects)
		{
			if(sus.getPossible().size() > max)
			{
				max = sus.getPossible().size();
				maxsus = sus;
			}
		}
		return maxsus;
	}
	
	public PhoneCall getLongestPhoneCallBetween(String num1, String num2) //επιστρέφει την τηλεγωνική επικοινωνία με την μεγαλύτερη διάρκεια ανάμεσα σε δύο αριθμούς
	{
		int max = -1;
		PhoneCall maxCall = null;
		
		for(Communication com : this.comms)
		{
			if(com instanceof PhoneCall)
			{
				if((num1 == com.getNum1() && num2 == com.getNum2()) || (num1 == com.getNum2() && num2 == com.getNum1()))
				{
					if(((PhoneCall) com).getSecs() > max)
					{
						maxCall = (PhoneCall) com;
						max = ((PhoneCall) com).getSecs();
					}
				}
			}
		}
		return maxCall;
	}
	
	public ArrayList<SMS> getMessagesBetween(String num1, String num2) //επιστρέφει λίστα με τα μηνύματα των αριθμών στις παραμέτρους που περιέχουν τις λέξεις "Bomb", "Attack", "Explosives", ή/και "Gun"
	{
		ArrayList<SMS> messages = new ArrayList<>();
		
		for(Communication com : this.comms)
		{
			if(com instanceof SMS)
			{
				if((num1 == com.getNum1() && num2 == com.getNum2()) || (num1 == com.getNum2() && num2 == com.getNum1()))
				{
					if(((SMS) com).getMessage().contains("Bomb") || ((SMS) com).getMessage().contains("Attack") || ((SMS) com).getMessage().contains("Explosives") || ((SMS) com).getMessage().contains("Gun"))
					{
						messages.add((SMS) com);
					}
				}
			}
		}
		return messages;
	}
}
