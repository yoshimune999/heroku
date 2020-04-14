//ログインに関する情報を持つEntity

package entity;

import java.io.Serializable;

public class Login implements Serializable {
	private String userName;
	private String pass;

	public Login() {}

	public Login(String userName, String pass) {
	this.userName = userName;
	this.pass = pass;
  }

	public String getUserName() { return this.userName;}
	public String getPass() { return this.pass;}
}
