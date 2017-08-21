package service;

import java.util.List;
import java.util.Set;

import entity.Customer;
import entity.Order;

/*
 * Customer各种操作的接口
 */
public interface CustomerService {
	/*
	 * 根据Id获取用户
	 */
	Customer getCustomerById(int id) throws Exception;
	/*
	 * 添加新用户
	 */
	boolean addCustomer(Customer customer);
	/*
	 * 根据Id删除用户
	 */
	void deleteCustomerById(int id);
	/*
	 * 更新用户信息
	 */
	boolean updateCustomer(Customer customer);
	boolean updateCustomer(Customer customer, Set<Order> orders);
	/*
	 * 获取某用户的订单
	 */
	Set<Order> getOrdersByCustomerId(int id);
}
