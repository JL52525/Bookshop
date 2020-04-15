package cn.itcast.bookstore.admin.admin.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.admin.admin.domain.Admin;
import cn.itcast.bookstore.admin.admin.service.AdminService;
import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;

public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();
	
	/**
	 * 登录功能
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form.getAdminname(), form.getAdminpwd());
		if(admin != null) {
			req.getSession().setAttribute("admin", admin);
			return "r:/adminjsps/admin/index.jsp";
		} else {
			req.setAttribute("msg", "用户名或密码错误！");
			req.setAttribute("form", form);
			return "f:/adminjsps/login.jsp";
		}
	}
	
	/**
	 * 管理员退出
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String quit(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getSession().removeAttribute("admin");
		return "r:/adminjsps/login.jsp";
	}
}
