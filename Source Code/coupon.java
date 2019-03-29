package system;

import java.util.Arrays;

/*
 * This class holds details for each coupon
 */
public class coupon {

	protected String Provider = "";// Coupon Provider- Eg:Amazon, Ebay, Nike, Dominos,...
	protected String product = "";// Coupon Product- Eg:Electronics, Shoes, Garments, Groceries,...
	protected double price ;// price of the product
	protected int dis_rate ;// Discount rate - normally between 5-90 %
	protected int exp_period ;// Between 1 to 365 days
	protected boolean Used ; // 1- used/redeemed ; 0- unused
	protected double final_price ;// Price of product after discount is applied
	
	public coupon (){
		/*
		 * Constructor for a coupon object 
		 */
		String Provider = null;
		String product = null;
		double price = 0;
		int dis_rate = 0;
		int exp_period = 0;
		boolean Used = false;
		double final_price ;
	}
	
	public String getProvider() {
		return Provider;
	}
	public void setProvider(String provider) {
		Provider = provider;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getDis_rate() {
		return dis_rate;
	}
	public void setDis_rate(int dis_rate) {
		this.dis_rate = dis_rate;
	}
	public int getExp_period() {
		return exp_period;
	}
	public void setExp_period(int exp_period) {
		this.exp_period = exp_period;
	}
	public boolean isUsed() {
		return Used;
	}
	public void setUsed(boolean used) {
		Used = used;
	}

	public double getFinal_price() {
		return  final_price;
	}

	public void setFinal_price(double final_price) {
		this.final_price = final_price;
	}

	@Override
	public String toString() {
		return "coupon [Provider=" + Provider + ", product=" + product + ", price=$" + price + ", dis_rate=" + dis_rate
				+ "%, exp_period=" + exp_period + "days, Used=" + Used + ", Final Price =$" + final_price + "]";
	}

	
	
	
}
