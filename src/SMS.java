
public class SMS extends Communication{
	
	private String message;

	public SMS(String num1, String num2, int day, int month, int year, String message) {
		super(num1, num2, day, month, year);
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void printInfo()
	{
		System.out.println("This SMS has the following info");
		System.out.println("Between " + super.getNum1() + " --- " + super.getNum2());
		System.out.println("on " + super.getYear() + "/" + super.getMonth() + "/" + super.getDay());
		System.out.println("Text: " + message);
	}
}
