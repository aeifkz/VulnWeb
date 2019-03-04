package ssdlc.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import ssdlc.model.DBModel;
import ssdlc.model.LogModel;


@WebServlet(name="register",urlPatterns="/register")
public class RegisterAction extends HttpServlet {
	
	static Logger log = Logger.getLogger(RegisterAction.class);
	
	private String account;
	private String password; 
	private String name;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		account = req.getParameter("account");
		password = req.getParameter("password");
		name = req.getParameter("name");
		
		log.info(LogModel.log_sanitized("Call register method " + account + " " + password + " " + name));
		
		
		//TODO Day2 針對帳號做格式驗證, 規則 ^[A-Za-z]{4,20}$		
		
		
		Connection conn = null;
		
		try {
			
			conn = new DBModel().getConnection();			
			
			String sql = "insert into user (account,password,name)  values ('" + account + "','"+password+"','"+name+"') ;";
			log.debug(LogModel.log_sanitized("register sql:"+sql));
			
			//TODO Day2 使用 prepareStatement 預防 SQL Injection
			Statement stmt = conn.createStatement();			
			int rs = stmt.executeUpdate(sql);
						
			req.setAttribute("sql",sql);
			
			if(rs>0) {
				req.setAttribute("msg","註冊成功");				
			}
			else {
				if(account!=null) {				
					req.setAttribute("msg","帳號"+account+"註冊失敗");
				}
				else {
					req.setAttribute("msg","註冊失敗");
				}
			}			
						
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			log.error("資料庫操作錯誤",ex);			
		}
		
		RequestDispatcher view = req.getRequestDispatcher("index.jsp");
		view.forward(req,resp);
		
	}
		
	
	
}
