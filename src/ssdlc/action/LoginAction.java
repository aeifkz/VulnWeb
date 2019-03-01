package ssdlc.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.owasp.encoder.Encode;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.codecs.MySQLCodec;

import com.opensymphony.xwork2.ActionContext;

import ssdlc.model.DBModel;

public class LoginAction {

	private String account;
	private String password;	
	
	public String login() {

		System.out.println("Call login method " + account + " " + password);
		
		boolean is_success = false;
		
		Connection conn = null;

		try {
			
			conn = new DBModel().getConnection();
			
			String sql = "select id, account, name from user where account='" + account + "' and password='" + password + "'";			
			Statement stmt = conn.createStatement();			
			ResultSet rs = stmt.executeQuery(sql);
			
			System.out.println("sql:"+sql);
			
			if(rs.next()) {
				
				Integer id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				
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
