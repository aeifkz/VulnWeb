package ssdlc.action;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import javax.servlet.http.Cookie;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;

import ssdlc.model.DBModel;

public class LoginAction {
	
	static Logger log = Logger.getLogger(LoginAction.class);
	

	private String account;
	private String password;	
	
	public String login() {
		
		log.info("Call login method " + account + " " + password);
				
		
		boolean is_success = false;
		
		Connection conn = null;

		try {
			
			conn = new DBModel().getConnection();
						
			String sql = "select id, account, password,name from user where account like '%" + account + "%' and password='" + password + "'";
			log.debug("login sql:"+sql);
			
			
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			
			ServletActionContext.getRequest().setAttribute("sql",sql);
			
			if(rs.next()) {
				
				Integer id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");
				
				
				Cookie cookie = new Cookie("account",account);
				ServletActionContext.getResponse().addCookie(cookie);
				
				cookie = new Cookie("password",password);
				ServletActionContext.getResponse().addCookie(cookie);
				
				
				Map<String, Object> session = ActionContext.getContext().getSession();				
				session.put("id",id);
				session.put("account",account);
				session.put("name",name);
				
				is_success = true;

			}
			else {
				if(account!=null) {
					//account = Encode.forHtml(account);
					ServletActionContext.getRequest().setAttribute("msg","帳號"+account+"不存在或是密碼錯誤");
				}				
			}			
			
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();			
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
