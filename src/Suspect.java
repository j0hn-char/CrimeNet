import java.util.ArrayList;

public class Suspect {

	private String name;
	private String codeName;
	private String location;
	private ArrayList<String> nums; //τηλεφωνικοί αριθμοί υπόπτου
	private ArrayList<Suspect> possible; //πιθανοί συνεργάτες υπόπτου
	
	public Suspect(String name, String codeName, String location) {
		this.name = name;
		this.codeName = codeName;
		this.location = location;
		this.nums = new ArrayList<>();
		this.possible = new ArrayList<>();
	}
	

	public String getName()
	{
		return name;
	}

	public ArrayList<String> getNums()
	{
		return nums;
	}

	public ArrayList<Suspect> getPossible() {
		return possible;
	}

	public String getCodeName()
	{
		return codeName;
	}

	
	public void addNumber(String num)
	{
		nums.add(num);
	}
	
	public void addPossiblePartner(Suspect sus)
	{
		if(!possible.contains(sus))
		{
			possible.add(sus);
		}
	}
	
	public boolean isConnectedTo(Suspect sus) //ελέγχει για σύνδεση μεταξύ δύο υπόπτων
	{
		for(Suspect sus1 : sus.possible)
		{
			if(this.possible.contains(sus1))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect sus) //επιστρέφει λίστα με τους κοινούς συνεργάτες
	{
		ArrayList<Suspect> common = new ArrayList<>();
		
		for(Suspect sus1 : sus.possible)
		{
			if(this.possible.contains(sus1))
			{
				common.add(sus1);
			}
		}
		return common;
	}
	
	public void printPossiblePartners()
	{
		for(Suspect sus : this.possible)
		{
			System.out.println(sus.name + ", " + sus.codeName);
		}
	}
}
