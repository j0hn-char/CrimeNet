
public class PhoneCall extends Communication {

	private int secs;

	public PhoneCall(String num1, String num2, int day, int month, int year, int secs)
	{
		super(num1, num2, day, month, year);
		this.secs = secs;
	}

	public int getSecs() {
		return secs;
	}
	
	public void printInfo()
	{
		System.out.println("This phone call has the following info");
		System.out.println("Between " + super.getNum1() + " --- " + super.getNum2());
		System.out.println("on " + super.getYear() + "/" + super.getMonth() + "/" + super.getDay());
		System.out.println("Duration: " + secs);
	}
}
