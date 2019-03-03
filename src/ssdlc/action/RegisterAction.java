package ssdlc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import ssdlc.model.DBModel;


public class RegisterAction {
	
	static Logger log = Logger.getLogger(RegisterAction.class);
	
	private String account;
	private String password; 
	private String name;	
	
	public String register() {
		
		log.info("Call register method " + account + " " + password + " " + name);
		
		Connection conn = null;
		
		try {
			
			conn = new DBModel().getConnection();			
			
			String sql = "insert into user (account,password,name)  values ('" + account + "','"+password+"','"+name+"') ;";
			log.debug("register sql:"+sql);
			
			Statement stmt = conn.createStatement();			
			int rs = stmt.executeUpdate(sql);
						
			ServletActionContext.getRequest().setAttribute("sql",sql);
			
			if(rs>0) {
				ServletActionContext.getRequest().setAttribute("msg","註冊成功");				
			}
			else {
				if(account!=null) {				
					ServletActionContext.getRequest().setAttribute("msg","帳號"+account+"註冊失敗");
				}
				else {
					ServletActionContext.getRequest().setAttribute("msg","註冊失敗");
				}
			}			
						
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();			
		}
		
		return "info";
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
