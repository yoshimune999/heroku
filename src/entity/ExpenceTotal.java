//費目合計に関する情報を持つEntity

package entity;

import java.io.Serializable;

public class ExpenceTotal implements Serializable {
	private int expenceId;
	private String expence;
	private int dAmountTotal;
	private int wAmountTotal;


	public ExpenceTotal () {}
	public ExpenceTotal (int expenceId, String expence, int dAmountTotal, int wAmountTotal) {
		this.expenceId = expenceId;
		this.expence = expence;
		this.dAmountTotal = dAmountTotal;
		this.wAmountTotal = wAmountTotal;
	}


	public int getExpenceId() { return this.expenceId;}
	public String getExpence() {return this.expence;}
	public int getDAmountTotal() {return this.dAmountTotal;}
	public int getWAmountTotal() {return this.wAmountTotal;}
}


