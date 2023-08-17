package kiosk_java;

public class Food implements MenuItem {
	private String name;
	private int price;
	private int maxQuantity; 

	public Food(String name, int price, int maxQuantity) {
		this.name = name;
		this.price = price;
		this.maxQuantity = maxQuantity;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getPrice() {
		return price;
	}

	@Override
	public int getMaxQuantity() {
		return maxQuantity;
	}

}
