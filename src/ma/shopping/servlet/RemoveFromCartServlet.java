package ma.shopping.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.shopping.model.card;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/remove-from-cart")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out = response.getWriter()){
			
			response.setContentType("text/html");
			String id = request.getParameter("id");
			
			if(id != null){
				
				ArrayList<card> cart_list = (ArrayList<card>) request.getSession().getAttribute("card-list");
				if(cart_list != null){
					for(card c:cart_list){
						if(c.getId() == Integer.parseInt(id)){
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
					response.sendRedirect("card.jsp");
				}
				
			}else{
				response.sendRedirect("card.jsp");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
