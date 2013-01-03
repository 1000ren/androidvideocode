package com.fangwei.domain;

public class Person {
	public Integer id;
	public String name;
	public String phone;
	
	public Person(Integer id,String name, String phone) {
		this.id = id ;
		this.name = name;
		this.phone = phone ;
	}
	
	public Person(String name, String phone) {
		this.name = name;
		this.phone = phone ;
	}
	
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
}
