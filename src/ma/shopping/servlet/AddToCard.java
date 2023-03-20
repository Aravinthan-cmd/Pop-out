package ma.shopping.servlet;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.shopping.model.card;

/**
 * Servlet implementation class AddToCard
 */
@WebServlet("/add-to-cart")
public class AddToCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		try(PrintWriter out = response.getWriter()){
		ArrayList<card> cardlist = new ArrayList<>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		card cm = new card();
		cm.setId(id);
		cm.setQuantity(1);
		
		HttpSession session = request.getSession();
		ArrayList<card> card_list = (ArrayList<card>) session.getAttribute("card-list");
		
		if(card_list == null){
			cardlist.add(cm);
			session.setAttribute("card-list", cardlist);
			response.sendRedirect("index.jsp");
			
		}else{
			cardlist = card_list;
			boolean exiest = false;
			
			for(card c:card_list){
				if(c.getId() == id){
					exiest = true;
					out.println("<h3 style='color:crimson; text-align:center'>Item already exist in Cart <a href='card.jsp'> Go to Cart </a></h3>");
				}
			}
			
			if(!exiest){
				cardlist.add(cm);
				response.sendRedirect("index.jsp");
			}
		}
		
		
		
		}
	}
}
