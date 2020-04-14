package entity;

import java.io.Serializable;

public class ThisMonthTotal implements Serializable {
	private int total;



	public ThisMonthTotal () {}
	public ThisMonthTotal (int total) {
		this.total = total;

	}

	public int getTotal() {return this.total;}
}