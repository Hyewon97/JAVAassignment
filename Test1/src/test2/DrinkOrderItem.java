package test2;

public class DrinkOrderItem implements OrderItem {
	private MenuItem menuItem;
	private int quantity;

	public DrinkOrderItem(MenuItem menuItem, int quantity) {
		this.menuItem = menuItem;
		this.quantity = quantity;
	}

	@Override
	public String getMenuItemName() {
		return menuItem.getName();
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public int calculateTotalCost() {
		return menuItem.getPrice() * quantity;
	}
}
