package entity;

import java.io.Serializable;

public class AddItem implements Serializable {
	private int userId;
	private String date;
	private int balanceOPId;
	private int expenceId;
	private int dAmount;
	private int wAmount;
	private String memo;
	private String expenceName;


	public AddItem () {}
	public AddItem (int expenceId, int balanceOPId, int userId, String expenceName) {
		this.expenceId = expenceId;
		this.userId = userId;
		this.balanceOPId = balanceOPId;
		this.expenceName = expenceName;
	}
	public AddItem (int userId, String date, int balanceOPId, int expenceId, int dAmount, int wAmount, String memo) {
		this.userId = userId;
		this.date = date;
		this.balanceOPId = balanceOPId;
		this.expenceId = expenceId;
		this.dAmount = dAmount;
		this.wAmount = wAmount;
		this.memo = memo;
	}


	public int getUserId() {return this.userId;}
	public String getDate() {return this.date;}
	public int getBalanceOPId() {return this.balanceOPId;}
	public int getExpenceId() {return this.expenceId;}
	public int getDAmount() {return this.dAmount;}
	public int getWAmount() {return this.wAmount;}
	public String getMemo() {return this.memo;}
	public String getExpenceName() {return this.expenceName;}

	public void setUserId(int userId) {this.userId = userId;}
	public void setDate(String date) {this.date = date;}
	public void setBalanceOPId(int balanceOPId) {this.balanceOPId = balanceOPId;}
	public void setExpenceId(int expenceId) {this.expenceId = expenceId;}
	public void setDAmount(int dAmount) {this.dAmount = dAmount;}
	public void setWAmount(int wAmount) {this.wAmount = wAmount;}
	public void setMemo(String memo) {this.memo = memo;}
	public void setExpenceName(String expenceName) {this.expenceName = expenceName;}
}
