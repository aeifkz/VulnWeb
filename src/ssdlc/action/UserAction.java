package ssdlc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import ssdlc.model.DBModel;

public class UserAction {
	
	private Integer id;
	private String password;
	private String name;
	
	
	public String edit() {
		
		System.out.println("Call edit method " + id + " " + password + " " + name);

		Connection conn = null;

		try {
			
			conn = new DBModel().getConnection();			
			
			String sql = "update user set account=account ";
			
			if(password!=null && !password.isEmpty()) {
				sql = sql + ", password='" + password + "'";
			}
			
			if(name!=null && !name.isEmpty()) {
				sql = sql + ", name='" + name + "'";
			}
			
			sql = sql + " where id=" + id;
			
			System.out.println("sql:"+sql);
			ServletActionContext.getRequest().setAttribute("sql",sql);
			
			Statement stmt = conn.createStatement();			
			int rs = stmt.executeUpdate(sql);
			
			System.out.println("sql:"+sql);
			
			if(rs>0) {
				
				ServletActionContext.getRequest().setAttribute("msg","修改資料成功");
				
				Map<String, Object> session = ActionContext.getContext().getSession();
				
				if(name!=null && !name.isEmpty()) {
					session.put("name",name);
				}
				
			}
			else {				
				ServletActionContext.getRequest().setAttribute("msg","修改失敗");				
			}			
						
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "info";
	}
	
	
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
		
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
