package test;

/*
 * 测试乐观锁
 */
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entity.Customer;

import utils.HibernateUtils;

public class OptimizationLockTest {
	public static void main(String[] args) {
		// 创建sessionFactory对象
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
		if (sessionFactory == null) {
			return;
		}

		Session session1 = null;
		Session session2 = null;
		try {
			// 创建两个session对象
			session1 = sessionFactory.openSession();
			session2 = sessionFactory.openSession();

			// 分别进行两个事务
			Transaction trs1 = session1.getTransaction();
			Transaction trs2 = session2.getTransaction();

			// 获取同一个记录，customer_id=1
			Customer customer1 = (Customer) session1.load(Customer.class, 1);
			Customer customer2 = (Customer) session2.load(Customer.class, 1);

			trs1.begin();
			customer1.setPhone("111");

			trs2.begin();
			customer2.setPhone("222");

			// 对同一条记录进行修改，并将提交顺序反过来
			session2.save(customer2);
			trs2.commit();

			session1.save(customer1);
			trs1.commit();

			session1.close();
			session2.close();
			System.out.println("成功");
		} catch (Exception e) {
			System.out.println("失败" + e.getLocalizedMessage());
			session1.getTransaction().rollback();
		} finally {
			if (session1 != null && session1.isOpen()) {
				session1.close();
			}
			if (session2 != null && session2.isOpen()) {
				session2.close();
			}
		}
	}
}
// 错误消息：Row was updated or deleted by another transaction (or unsaved-value
// mapping was incorrect) : [entity.Customer#1]
