package kiosk3;

public interface OrderItem {
    String getMenuItemName(); // 아이템 이름을 저장하는 변수
    int getQuantity(); // 수량을 저장하는 변수
    int calculateTotalCost(); // 전체 가격을 저장하는 변수
}
