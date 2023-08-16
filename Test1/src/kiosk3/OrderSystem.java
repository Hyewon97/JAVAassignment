package kiosk3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OrderSystem {
	private Scanner scanner; // 사용자한테 변수 입력받을 스캐너 선언
	private List<MenuItem> menuItems; // 메뉴 리스트
	private List<OrderItem> orderedItems; // 주문 리스트
	private int orderNum; // 주문 번호

	private Map<String, Integer> foodQuantities; // 음식 별 누적 주문 수량

	public OrderSystem() {
		scanner = new Scanner(System.in);
		menuItems = new ArrayList<>();
		orderedItems = new ArrayList<>();
		orderNum = 0;

		foodQuantities = new HashMap<>(); // 읍식 별 누적 주문 수량 담을 해시맵

		initializeMenu(); // 음식 메뉴, 가격, 최대 주문 수량 생성
	}

	// 전체 수량
	private int getTotalQuantity(String foodName) {
		return foodQuantities.getOrDefault(foodName, 0);
	}

	// 수량 업데이트
	private void updateFoodQuantity(String foodName, int quantity) {
		foodQuantities.put(foodName, getTotalQuantity(foodName) + quantity);
	}

	public void orderFood(MenuItem food, int quantity) {

		// 남은 수량 저장할 변수 선언
		int remainingCapacity = 0;

		// 돼지고기라면
		if (food.getName() == "돈까스" || food.getName() == "치즈 돈까스") {
			remainingCapacity = food.getMaxQuantity() - (getTotalQuantity("돈까스") + getTotalQuantity("치즈 돈까스"));
		} else // 돼지고기가 아니라면
			remainingCapacity = food.getMaxQuantity() - getTotalQuantity(food.getName());

		if (remainingCapacity >= quantity) {
			// 주문 가능한 최대 수량을 확인
			FoodOrderItem foodOrderItem = new FoodOrderItem(food, quantity);
			orderedItems.add(foodOrderItem);
			updateFoodQuantity(food.getName(), quantity);
			System.out.println(quantity + "개의 " + food.getName() + "를 주문하였습니다.");
		} else {
			System.out.println("주문 가능한 최대 수량을 초과하였습니다.");
		}
	}

	// 메뉴 선언. 메뉴이름, 가격, 수량 설정
	private void initializeMenu() {
		menuItems.add(new Food("돈까스", 8000, 5));
		menuItems.add(new Food("치즈 돈까스", 10000, 5));
		menuItems.add(new Food("치킨까스", 7000, 5));
		menuItems.add(new Food("콜라", 2000, 5));
	}

	public void start() {
		System.out.println("혜원식당에 오신 것을 환영합니다!\n");

		while (true) {
			displayMenu();

			System.out.print("번호를 선택하세요: ");
			int num = scanner.nextInt();

			if (num >= 1 && num <= 4) {
				MenuItem menuItem = menuItems.get(num - 1); // 인덱스 번호여서 -1 사용
				System.out.print("주문할 갯수를 입력하세요: ");
				int quantity = scanner.nextInt();

				// orderFood 메서드를 호출하여 주문 가능한 최대 수량을 체크하고 주문 처리
				orderFood(menuItem, quantity);

			} else if (num == 5) {
				displayOrderSummary();
			} else if (num == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			} else {
				System.out.println("잘못된 번호입니다. 다시 입력하세요.");
			}
		}

		scanner.close();
	}

	private void displayMenu() {
		System.out.println("\n**** 메뉴를 선택하세요 ****");
		for (int i = 0; i < menuItems.size(); i++) {
			MenuItem menuItem = menuItems.get(i);
			System.out.printf("%d. %s - %,d원\n", i + 1, menuItem.getName(), menuItem.getPrice());
		}
		System.out.println("5. 주문확인");
		System.out.println("0. 프로그램 종료");
	}

	private void displayOrderSummary() {
		System.out.println("============ 주문확인 ============");
		int totalCost = 0;

		for (OrderItem orderItem : orderedItems) {
			System.out.printf("%s %d개, ", orderItem.getMenuItemName(), orderItem.getQuantity());
			totalCost += orderItem.calculateTotalCost();
		}
		System.out.println("선택되었습니다.");
		System.out.printf("총 금액은 %,d원입니다.\n", totalCost);

		System.out.print("주문 하시겠습니까? (y/n): ");
		char choice = scanner.next().charAt(0);

		if (choice == 'y') {
			orderNum++;
			System.out.printf("주문 되었습니다. 주문번호는 %d번입니다.\n", orderNum);
			orderedItems.clear();
		} else if (choice == 'n') {
			System.out.print("주문을 취소하시겠습니까? (y/n): ");
			choice = scanner.next().charAt(0);
			if (choice == 'y') {
				orderedItems.clear();
			}
		} else {
			System.out.println("잘못된 입력입니다.");
		}
	}

	public static void main(String[] args) {
		OrderSystem orderSystem = new OrderSystem();
		orderSystem.start();
	}
}
