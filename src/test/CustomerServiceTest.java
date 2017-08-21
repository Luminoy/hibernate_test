package test;

import java.util.HashSet;
import java.util.Set;

import service.CustomerService;
import service.CustomerServiceImpl;
import entity.Customer;
import entity.Order;

public class CustomerServiceTest {
	private static CustomerService service = new CustomerServiceImpl();

	// 测试添加新用户
	public static void addCustomerTest() {
		try {
			Customer customer = new Customer();
			customer.setName("Lumin");
			customer.setSex("male");
			customer.setPhone("18023456789");	
			
			service.addCustomer(customer);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	// 测试为用户添加订单
	public static void addOrdersTest() {
		int id = 22;
		Set<Order> setOrders = new HashSet<Order>();
		
		Order order1 = new Order();
		order1.setNumber("201708-TP-00001");
		order1.setCustomerId(id);
		setOrders.add(order1);
		
		Order order2 = new Order();
		order2.setNumber("201708-TP-00002");
		order2.setCustomerId(id);
		setOrders.add(order2);
		
		try {
			Customer customer = service.getCustomerById(id);
			customer.setSetOrders(setOrders);
			service.updateCustomer(customer, setOrders);
		} catch(Exception e) {
			e.getStackTrace();
		}
	}

	//测试获取用户订单
	public static void getCustomerOrdersTest() {
		Set<Order> orders = service.getOrdersByCustomerId(22);
		for (Order order : orders){
			System.out.println("ID " + order.getId() + ": " + order.getNumber());
		}
	}
	public static void main(String[] args) {
		//addOrdersTest();
		getCustomerOrdersTest();
	}
}
