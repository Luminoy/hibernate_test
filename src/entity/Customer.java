package entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Customer { 
	private int   id;           //主键	number(10)
	private String name;        //姓名	varchar2(50)
	private String phone;	    //电话	varchar2(20)
	private String address;	    //地址	varchar2(100)
	private String sex;	        //性别	varchar2(5)
	private Date   startDate;   //生效时间	date
	private Date   endDate;	    //失效时间	date
	private int   version;      //乐观锁字段
	private Set<Order> setOrders = new HashSet<Order>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public Set<Order> getSetOrders() {
		return setOrders;
	}
	public void setSetOrders(Set<Order> setOrders) {
		this.setOrders = setOrders;
	}
	
}
