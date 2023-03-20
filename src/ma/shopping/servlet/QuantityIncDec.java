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
 * Servlet implementation class QuantityIncDec
 */
@WebServlet("/quantity-Inc-dec")
public class QuantityIncDec extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		try(PrintWriter pw = response.getWriter()){
			
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<card> cart_list = (ArrayList<card>) request.getSession().getAttribute("card-list");
			
			if(action != null && id>0){
				
				if(action.equals("inc")){
					for(card c:cart_list){
						if(c.getId() == id){
							int quantity = c.getQuantity();
							quantity++;
							c.setQuantity(quantity);
							response.sendRedirect("card.jsp");
						}
					}
				}
				
				if(action.equals("dec")){
					for(card c:cart_list){
						if(c.getId() == id && c.getQuantity() >1){
							int quantity = c.getQuantity();
							quantity--;
							c.setQuantity(quantity);
							response.sendRedirect("card.jsp");
						}else{
							response.sendRedirect("card.jsp");
						}
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
