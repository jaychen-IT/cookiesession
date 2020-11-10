package cn.itcast.cookie;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

/**
 * Servlet implementation class CookieTest
 */
@WebServlet("/cookieTest")
public class CookieTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //設置響應的消息體的格式以及編碼
		response.setContentType("text/html;charset=utf-8");
		//1.獲取所有Cookie
		Cookie[] cookies=request.getCookies();
		boolean flag=false;
		//2.遍歷所有數組
		if(cookies!=null && cookies.length>0 )
		{
			for(Cookie cookie:cookies) {
				//3.獲取Cookie名稱
				String name=cookie.getName();
				//判斷是否為lasttime
				if("lasttime".equals(name))
				{
					flag=true;
					Date date=new Date();
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
					String value=cookie.getValue();
					System.out.println(value);
					value= URLDecoder.decode(value, "utf-8");
					System.out.println(value);
					response.getWriter().write("<h1>歡迎回來，您上次訪問時間為:"+value+"!</h1>");	
					//設定訪問時間
					String str_date=sdf.format(date);
					System.out.println(str_date);
					str_date=URLEncoder.encode(str_date, "utf-8");
					System.out.println(str_date);
					cookie.setValue(str_date);
					cookie.setMaxAge(60*60*24*30);
					response.addCookie(cookie);
				}
			}	
		}
		if(cookies==null || cookies.length==0||flag==false )
		{
						Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
			String str_date=sdf.format(date);
			str_date= URLEncoder.encode(str_date, "utf-8");
			Cookie cookie=new Cookie("lasttime",str_date);
			cookie.setMaxAge(60*60*24*30);
			response.addCookie(cookie);
			response.getWriter().write("<h1>歡迎您首次訪問!</h1>");	
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
