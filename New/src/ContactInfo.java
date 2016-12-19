
public class ContactInfo {
	String First_name, Last_name;
	int Phone_number, Work_number;

	public ContactInfo(String First_name, String Last_name, int Phone_number, int Work_number) {
		super();
		this.First_name = First_name;
		this.Last_name = Last_name;
		this.Phone_number = Phone_number;
		this.Work_number = Work_number;
	}

	public String getFirst_name() {
		return First_name;
	}

	public void setFirst_name(String First_name) {
		this.First_name = First_name;
	}

	public String getLast_name() {
		return Last_name;
	}

	public void setLast_name(String Last_name) {
		this.Last_name = Last_name;
	}

	public int getPhone_number() {
		return Phone_number;
	}

	public void setPhone_number(int Phone_number) {
		this.Phone_number = Phone_number;
	}

	public int getWork_number() {
		return Work_number;
	}

	public void setWork_number(int Work_number) {
		this.Work_number = Work_number;
	}
	
}
