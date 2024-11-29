
public class Communication {

	private String num1;
	private String num2;
	
	private int day, month, year;

	public Communication(String num1, String num2, int day, int month, int year) {
		this.num1 = num1;
		this.num2 = num2;
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public String getNum1() {
		return num1;
	}
	
	public String getNum2() {
		return num2;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}
	
}
