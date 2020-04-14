package entity;

public class AddNewExpence {
	private int userId;
	private int balanceOPId;
	private String newExpence;

	public AddNewExpence(int userId, int balanceOPId, String newExpence) {
		this.userId = userId;
		this.balanceOPId = balanceOPId;
		this.newExpence = newExpence;
	}

	public int getUserId() { return this.userId;}
	public int getBalanceOPId() { return this.balanceOPId;}
	public String getNewExpence() { return this.newExpence;}
}
