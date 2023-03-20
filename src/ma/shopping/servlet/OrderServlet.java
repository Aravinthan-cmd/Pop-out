package ma.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
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

import org.apache.catalina.User;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order-now")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
			Date date = new Date(0);
			
			user user = (user) request.getSession().getAttribute("auth");
			
			if(user != null){
				
				String product_id = request.getParameter("id");
				int product_quantity = Integer.parseInt(request.getParameter("quantity"));
				if(product_quantity <=0){
					product_quantity = 1;
				}
				
				order order = new order();
				order.setId(Integer.parseInt(product_id));
				order.setUid(user.getId());
				order.setQuantity(product_quantity);
				order.setDate(format.format(date));
				
				OrderDao dao = new OrderDao(DBcon.getConnection());
				boolean result = dao.insertOrder(order);
				
				if(result){
					
					ArrayList<card> cart_list = (ArrayList<card>) request.getSession().getAttribute("card-list");
					if(cart_list != null){
						for(card c:cart_list){
							if(c.getId() == Integer.parseInt(product_id)){
								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					
					response.sendRedirect("order.jsp");
				}else{
					out.println("Order Failed");
				}
				
			}else{
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
