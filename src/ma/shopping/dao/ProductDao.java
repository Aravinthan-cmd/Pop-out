package ma.shopping.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ma.shopping.model.card;
import ma.shopping.model.product;

public class ProductDao {
	
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDao(Connection con) {
		this.con = con;
	}
	
	public List<product> getAllProducts(){
		List<product> products = new ArrayList<product>();
		
		try{
			
			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			
			while(rs.next()){
				product row = new product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				
				products.add(row);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return products;
	}
	
	public product getSingleProduct(int id) {
		 product row = null;
	        try {
	            query = "select * from products where id=? ";

	            pst = this.con.prepareStatement(query);
	            pst.setInt(1, id);
	            ResultSet rs = pst.executeQuery();

	            while (rs.next()) {
	            	row = new product();
	                row.setId(rs.getInt("id"));
	                row.setName(rs.getString("name"));
	                row.setCategory(rs.getString("category"));
	                row.setPrice(rs.getDouble("price"));
	                row.setImage(rs.getString("image"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println(e.getMessage());
	        }

	        return row;
	    }
	

	public List<card> getCardProducts(ArrayList<card> cardlist){
		List<card> products = new ArrayList<>();
		
		try{
			
			if(cardlist.size()>0){
				
				for(card item:cardlist){
					query ="select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()){
						card row = new card();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price")*item.getQuantity());
						row.setQuantity(item.getQuantity());
						products.add(row);
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return products;
	}
	
	public double getTotalCartPrice(ArrayList<card> cardlist){
		
		double sum=0;
		
		try{
			if(cardlist.size() > 0){
				
				for(card item:cardlist){
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while(rs.next()){
						sum+= rs.getDouble("price")*item.getQuantity();
					}
							
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return sum;
	}

}
