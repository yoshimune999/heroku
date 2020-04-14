//DBから返されるuserに関する情報を持つEntity

package entity;

public class Account {
	private String userName;
	private String pass;
	private int id;

	public Account(String userName, String pass, int id) {
		this.userName = userName;
		this.pass = pass;
		this.id = id;
	}

	public String getUserName() { return this.userName;}
	public String getPass() { return this.pass;}
	public int getId() { return this.id;}
}
