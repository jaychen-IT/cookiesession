package cn.itcast.servlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.Request;

/**
 * Servlet implementation class ResponseDemo1
 */
@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       int width=100;
       int height=50;
       //1.創建一個對象，在內存中的圖片（驗證碼圖片對象）
       BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         //2.美化圖片
        //2.1 填充背景色
       Graphics g=image.getGraphics();//填充背景色
       g.setColor(Color.PINK);//設置畫筆顏色
       g.fillRect(0, 0, width, height);      
       //2.2畫邊框
       g.setColor(Color.BLUE);
       g.drawRect(0, 0, width-1, height-1);
       //2.3寫字母
       String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
       StringBuilder sb=new StringBuilder();
       Random rm=new Random();
       for(int i=0;i<4;i++) {
    	   int index =rm.nextInt(str.length());
    	   char ch=str.charAt(index);
    	   sb.append(ch);
    	   g.drawString(ch+"", width/4*i, height/2);
       }
       String checkcode_session=sb.toString();
       request.getSession().setAttribute("checkcode_session", checkcode_session);
       //2.4畫干擾線
       g.setColor(Color.GREEN);
       for(int i=0;i<10;i++)
       {
    	   int x1=rm.nextInt(width);
    	   int x2=rm.nextInt(width);
    	   int y1=rm.nextInt(height);
    	   int y2=rm.nextInt(height);
    	   g.drawLine(x1, y1, x2, y2);
       }
       //將圖片展示到頁面
       ImageIO.write(image, "jpg", response.getOutputStream());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
