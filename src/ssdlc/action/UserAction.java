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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import ssdlc.model.DBModel;
import ssdlc.model.LogModel;

@WebServlet(name = "edit", urlPatterns = "/edit")
public class UserAction extends HttpServlet {

	private Integer id;
	private String password;
	private String name;

	static Logger log = Logger.getLogger(LoginAction.class.getName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// TODO Day3 實作 CSRF token 防禦措施
		
		
		id = req.getParameter("id")==null ? null : Integer.parseInt(req.getParameter("id"));
		password = req.getParameter("password");
		name = req.getParameter("name");
		

		log.info(LogModel.log_sanitized("Call edit method " + id + " " + password + " " + name));

		Connection conn = null;

		try {

			conn = new DBModel().getConnection();

			String sql = "update user set account=account ";

			if (password != null && !password.isEmpty()) {
				sql = sql + ", password='" + password + "'";
			}
			if (name != null && !name.isEmpty()) {
				sql = sql + ", name='" + name + "'";
			}

			sql = sql + " where id=" + id;

			log.debug(LogModel.log_sanitized("edit sql:" + sql));
			req.setAttribute("sql", sql);

			// TODO Day2 使用 prepareStatement 預防 SQL Injection
			Statement stmt = conn.createStatement();
			int rs = stmt.executeUpdate(sql);

			if (rs > 0) {
				
				req.setAttribute("msg", "修改資料成功");
				
				HttpSession session = req.getSession();
				if (name != null && !name.isEmpty()) {
					session.setAttribute("name", name);
				}

			} else {
				req.setAttribute("msg", "修改失敗");
			}

			stmt.close();
			conn.close();

		} catch (Exception ex) {
			log.error("資料庫操作錯誤", ex);
		}
		
		resp.sendRedirect("main.jsp");

	}

	

}
