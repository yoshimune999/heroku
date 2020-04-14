//履歴に関する情報を持つEntity

package entity;

import java.io.Serializable;

public class History implements Serializable {
	private String date;
	private String expence;
	private int dAmount;
	private int wAmount;
	private String memo;


	public History () {}
	public History (String date, String expence, int dAmount, int wAmount, String memo) {
		this.date = date;
		this.expence = expence;
		this.dAmount = dAmount;
		this.wAmount = wAmount;
		this.memo = memo;
	}


	public String getDate() {return this.date;}
	public String getExpence() {return this.expence;}
	public int getDAmount() {return this.dAmount;}
	public int getWAmount() {return this.wAmount;}
	public String getMemo() {return this.memo;}
}
