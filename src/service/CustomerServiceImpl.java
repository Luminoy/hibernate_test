package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import utils.HibernateUtils;

import entity.Customer;
import entity.Order;

public class CustomerServiceImpl implements CustomerService {

	@Override
	public Customer getCustomerById(int id) throws Exception {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = sessionFactory.openSession();
		// 获取持久对象
		Customer customer = (Customer) session.get(Customer.class, id);
		session.close();
		return customer;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		Transaction trs = null;
		try {
			session = sessionFactory.openSession();
			trs = session.beginTransaction();
			session.save(customer);
			trs.commit();
			session.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trs != null) {
				trs.rollback();
			}
			if (session != null && session.isOpen()) {
				session.close();
			}
			return false;
		}
		return true;
	}

	@Override
	public void deleteCustomerById(int id) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		Transaction trs = null;
		try {
			session = sessionFactory.openSession();
			trs = session.beginTransaction();
			Customer customer = (Customer) session.get(Customer.class, id);
			session.delete(customer);
			trs.commit();
			session.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trs != null) {
				trs.rollback();
			}
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		Transaction trs = null;
		try {
			session = sessionFactory.openSession();
			trs = session.beginTransaction();
			session.update(customer);
			trs.commit();
			session.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trs != null) {
				trs.rollback();
			}
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return false;
	}

	public boolean updateCustomer(Customer customer, Set<Order> orders) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Session session = null;
		Transaction trs = null;
		try {
			session = sessionFactory.openSession();
			trs = session.beginTransaction();
			//为用户设置订单，用cascade级联插入
			customer.setSetOrders(orders);
			session.update(customer);
			trs.commit();
			session.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trs != null) {
				trs.rollback();
			}
			if (session != null && session.isOpen()) {
				session.close();
			}
			return false;
		}
		return true;
	}

	@Override
	public Set<Order> getOrdersByCustomerId(int id) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		Set<Order> orders = new HashSet<Order>();
		Session session = null;
		Transaction trs = null;
		try {
			session = sessionFactory.openSession();
			trs = session.beginTransaction();
			//用sql的方式查订单
			String sql = "select * from  setOrders o where o.customer_id =" + id;
			List<Order> listOrders = session.createSQLQuery(sql).addEntity(Order.class).list();
			for(Order order: listOrders) {
				orders.add(order);
			}
			trs.commit();
			session.close();
		} catch (RuntimeException e) {
			e.printStackTrace();
			if (trs != null) {
				trs.rollback();
			}
			if (session != null && session.isOpen()) {
				session.close();
			}
			return null;
		}
		return orders;
	}
}
