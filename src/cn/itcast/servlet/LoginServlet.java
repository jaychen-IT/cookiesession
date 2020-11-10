package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.dao.UsersDao;
import cn.itcast.domain.Users;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String,String[]> map=request.getParameterMap();
		Map<String,String[]>map2 = new HashMap<>();
		Set<String> keyset=map.keySet();
		for(String key:keyset)
		{
			String[] values=map.get(key);
			for(String value:values) {
				System.out.println(key+"-->"+value);
			}
			
		}
		map2.putAll(map);
		map2.remove("checkCode");
		Set<String> keyset2=map2.keySet();
		for(String key:keyset2)
		{
			String[] values=map2.get(key);
			for(String value:values) {
				System.out.println(key+"-->"+value);
			}
			
		}
		String checkcode= request.getParameter("checkCode");
		HttpSession session=request.getSession();
		String checkcode_session=(String)session.getAttribute("checkcode_session");
		//刪除session中的驗證碼
		session.removeAttribute("checkcode_session");
		if(checkcode_session!=null&&checkcode_session.equalsIgnoreCase(checkcode))
		{
			Users loginuser=new Users();
			try {
				BeanUtils.populate(loginuser, map2);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			UsersDao usersDao=new UsersDao();
			Users user=usersDao.login(loginuser);
			if(user==null)
			{
				request.setAttribute("login_error", "用戶名或密碼錯誤");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else
			{
				session.setAttribute("user", user.getUsername());
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}
			
		}
		else
		{
			request.setAttribute("cc_error", "驗證碼錯誤");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
