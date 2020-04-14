//signInするUserの情報を持つEntity

package entity;

import java.io.Serializable;

public class SignIn implements Serializable {
	private String userName;
	private String pass;


	public SignIn() {}

	public SignIn(String userName, String pass) {
		this.userName = userName;
		this.pass = pass;
	}

	public String getUserName() { return this.userName;}
	public String getPass() { return this.pass;}
}
