package lab6a;

/**
 * <p>Title: Driver Class - Lab6App</p>
 *
 * <p>Copyright: Copyright (c) 2014, 2021</p>
 *
 * @author F. Graham
 * @version 1.2
 */
import java.sql.*;

public class Lab6aApp {
	public static void main(String[] args) {
		ArrayList list1;
		ArrayList<BestBuyProduct> list2 = new ArrayList<>(1000);
		BestBuyProduct[] products = getData();
		for(BestBuyProduct product : products)
			list2.add(product);
		System.out.println(list2);
		//testSearch(1088665, list2);
		System.out.println(list2.get(0));
		System.out.println(list2.get(list2.getSize() / 2));
	}
	/**
	 * getData method -- gets the products from an SQLite database
	 * @return the an array of products
	 */
		public static BestBuyProduct[] getData(){		
			Statement stmt = null;
			int records = 0;
			BestBuyProduct[] products = null; 
			try {
				Class.forName("org.sqlite.JDBC");
				Connection c = DriverManager.getConnection("jdbc:sqlite:BestBuyProducts.db");
				c.setAutoCommit(false);
				System.out.println("Opened database successfully");			
				
				stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM products;");
				ResultSetMetaData rsmd = rs.getMetaData();			
				
				for(int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					System.out.print(String.format("%-12s", rsmd.getColumnLabel(i)) + "\t");
					System.out.print(rsmd.getColumnTypeName(i) + "\t");
					System.out.println(rsmd.getPrecision(i));
				}
				
				rs = stmt.executeQuery("select count (*) AS totalRecords from products");
				int totalRecords = rs.getInt("totalRecords");
				System.out.println("Records: " + totalRecords);
				
				rs = stmt.executeQuery("SELECT * FROM products;");
				if(rs != null){
					products = new BestBuyProduct[totalRecords];
					while (rs.next()) {
						int sku = rs.getInt("sku");
						double price = rs.getFloat("price");
						String name = rs.getString("name");
						String upc = rs.getString("upc");
						String manufacturer = rs.getString("manufacturer");
						String model = rs.getString("model");

						//System.out.println(String.format("%3s %-6s %3d %6.2f",
						//		records, prodId, quantity, price));	
						products[records++] = new BestBuyProduct(sku, price, name, upc, manufacturer, model);;
						if(records < 6)
						System.out.println(records + " " + products[records-1]);
					}
					System.out.println();
				}
				
				stmt.close();
				c.commit();
				c.close();
			} 
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException se){
				System.err.println(se.getClass().getName() + ": " + se.getMessage());
			}
			return products; 
		}
}
