package kiosk3;

public class FoodOrderItem implements OrderItem {
	private MenuItem menuItem; // 메뉴 아이템
	private int quantity; // 수량

	public FoodOrderItem(MenuItem menuItem, int quantity) {
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
