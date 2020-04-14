package entity;

public class AddBudget {
	private int userId;
	private int expenceId;
	private int amount;
	private String expenceName;

	public AddBudget(int userId, int expenceId, int amount) {
		this.userId = userId;
		this.expenceId = expenceId;
		this.amount = amount;
	}
	public AddBudget(int userId, int expenceId, String expenceName, int amount) {
		this.userId = userId;
		this.expenceId = expenceId;
		this.expenceName = expenceName;
		this.amount = amount;
	}

	public int getUserId() { return this.userId;}
	public int getExpenceId() { return this.expenceId;}
	public int getAmount() { return this.amount;}
	public String getExpenceName() { return this.expenceName;}
}
