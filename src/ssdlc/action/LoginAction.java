package ssdlc.action;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.Cookie;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.struts2.ServletActionContext;

import com.mysql.cj.log.Log;
import com.opensymphony.xwork2.ActionContext;

import ssdlc.model.DBModel;
import ssdlc.model.LogModel;

public class LoginAction {
	
	static Logger log = Logger.getLogger(LoginAction.class);
	
	private String account;
	private String password;	
	
	public String login() {
		
		log.info(LogModel.log_sanitized("Call login method " + account + " " + password));
		
		boolean is_success = false;
		
		Connection conn = null;

		try {
			
			conn = new DBModel().getConnection();
						
			String sql = "select id, account, password,name from user where account like '%" + account + "%' and password='" + password + "'";
			log.debug(LogModel.log_sanitized("login sql:"+sql));
			
			//TODO Day2 使用 prepareStatement 預防 SQL Injection
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			
			ServletActionContext.getRequest().setAttribute("sql",sql);
			
			if(rs.next()) {
				
				Integer id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				
				
				//TODO Day3 將 cookie 設定為只允許使用 HTTP 存取
				Cookie cookie = new Cookie("account",account);
				ServletActionContext.getResponse().addCookie(cookie);
				
				cookie = new Cookie("password",password);
				ServletActionContext.getResponse().addCookie(cookie);
				
				
				Map<String, Object> session = ActionContext.getContext().getSession();				
				session.put("id",id);
				//TODO Day2 針對 account 內容作 HTML消毒
				session.put("account",account);
				session.put("name",name);
				
				is_success = true;

			}
			else {
				if(account!=null) {
					//TODO Day2 針對訊息內容作對應的消毒
					String msg = "帳號"+account+"不存在或是密碼錯誤";
					ServletActionContext.getRequest().setAttribute("msg",msg);
				}				
			}			
			
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			log.error("資料庫操作錯誤",ex);			
		}
		
		if(is_success)
			return "success";
		else 
			return "fail";
		
	}
	
	public String loginOut() {
		return "success";
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

}
