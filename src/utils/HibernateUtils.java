package utils;
/*
 * Hibernate工具类，如用于获取SessionFactory对象等
 */
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	
	//获取SessionFactory对象
	public static SessionFactory getSessionFactory() {
		SessionFactory sessionFactory = null;
		try {
		Configuration cfg = new Configuration().configure();
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).build();
		sessionFactory = cfg.buildSessionFactory(standardRegistry);
		}catch(Exception e) {
			System.out.println("无法获取SessionFactory对象！");
			System.out.println("错误信息：" + e.getLocalizedMessage());
		}
		return sessionFactory;
	}
}
