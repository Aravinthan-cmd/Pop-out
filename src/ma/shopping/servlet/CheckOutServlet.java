package ma.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.shopping.connection.DBcon;
import ma.shopping.dao.OrderDao;
import ma.shopping.model.card;
import ma.shopping.model.order;
import ma.shopping.model.user;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/check-out")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date(0);
			
			ArrayList<card> cart_list = (ArrayList<card>) request.getSession().getAttribute("card-list");
			user user = (user) request.getSession().getAttribute("auth");
			
			if(cart_list != null && user != null){
				
				for(card c:cart_list){
					order order = new order();
					
					order.setId(c.getId());
					order.setUid(user.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(format.format(date));
					
					OrderDao dao = new OrderDao(DBcon.getConnection());
					boolean result = dao.insertOrder(order);
					if(!result){
						break;
					}
				}
				
				cart_list.clear();
				response.sendRedirect("order.jsp");
				
			}else{
				if(user == null){
					response.sendRedirect("login.jsp");
				}
				response.sendRedirect("card.jsp");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
