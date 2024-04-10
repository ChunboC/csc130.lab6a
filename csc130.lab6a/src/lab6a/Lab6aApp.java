package lab6a;

/**
 * <p>Title: Driver Class - Lab6aApp</p>
 *
 * <p>Copyright: Copyright (c) 2014, 2021</p>
 *
 * @author F. Graham, Chunbo Cheng
 * @version 1.2
 */
import java.sql.*;

public class Lab6aApp {
	public static void main(String[] args) {
		ArrayList<BestBuyProduct> list1 = new ArrayList<>();
		System.out.println(list1);
		ArrayList<BestBuyProduct> list2 = new ArrayList<>(1000);
		System.out.println(list2 + "\n");
		BestBuyProduct[] products = getData();
		for (BestBuyProduct product : products)
			list2.add(product);
		// System.out.println(list2);
		System.out.println(list2 + "\n");
		// System.out.println(list2.get(0));
		// System.out.println(list2.get(list2.getSize() / 2).getSku());

		// testing search
		testSearch(1088665, list2); // in the middle of the list
		testSearch(344097, list2); // not in the list
		testSearch(list2.get(0).getSku(), list2); // in the beginning
		testSearch(list2.get(list2.getSize() - 1).getSku(), list2); // at the end
		testSearch(43900, list1); // in an empty list
		// testing remove
		testRemove(1196144, list2); // in the middle
		testRemove(344197, list2); // not in the list
		testRemove(list2.get(0).getSku(), list2); // in the beginning
		testRemove(list2.get(list2.getSize() - 1).getSku(), list2); // at the end
		list1.add(list2.get(list2.getSize() / 2)); // add the middle product in list1
		testRemove(list2.get(list2.getSize() / 2).getSku(), list1); // remove the only item in list1
		testRemove(344197, list1); // empty list
		// testing find
		find("Apple", list2);
		/*
		 * Question 17: when I commented out equals() in BestBuyProduct class, I did not
		 * get an error message because there is an equals() method in the Object class.
		 * However, the output changed, and no matter what parameter I use, the method
		 * would always return false because the equals() method in object class
		 * requires two objects to be exactly the same to return true.
		 */
	}

	/**
	 * getData method -- gets the products from an SQLite database
	 * 
	 * @return the an array of products
	 */
	public static BestBuyProduct[] getData() {
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

			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.print(String.format("%-12s", rsmd.getColumnLabel(i)) + "\t");
				System.out.print(rsmd.getColumnTypeName(i) + "\t");
				System.out.println(rsmd.getPrecision(i));
			}

			rs = stmt.executeQuery("select count (*) AS totalRecords from products");
			int totalRecords = rs.getInt("totalRecords");
			System.out.println("Records: " + totalRecords + "\n");

			System.out.println("The first 5 items in the list:\n");
			rs = stmt.executeQuery("SELECT * FROM products;");
			if (rs != null) {
				products = new BestBuyProduct[totalRecords];
				while (rs.next()) {
					int sku = rs.getInt("sku");
					double price = rs.getFloat("price");
					String name = rs.getString("name");
					String upc = rs.getString("upc");
					String manufacturer = rs.getString("manufacturer");
					String model = rs.getString("model");

					// System.out.println(String.format("%3s %-6s %3d %6.2f",
					// records, prodId, quantity, price));
					products[records++] = new BestBuyProduct(sku, price, name, upc, manufacturer, model);
					;
					if (records < 6)
						System.out.println(records + " " + products[records - 1]);
				}
				System.out.println();
			}

			stmt.close();
			c.commit();
			c.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			System.err.println(se.getClass().getName() + ": " + se.getMessage());
		}
		return products;
	}

	/**
	 * testSearch - utilizes indexOf(), lastIndexOf(), and contains() methods from
	 * ArrayList class to test them
	 * 
	 * @param key   the product id to search for
	 * @param ulist the product list to search within
	 */
	public static void testSearch(long key, ArrayList<BestBuyProduct> ulist) {
		BestBuyProduct temp = new BestBuyProduct(key, 0, null, null, null, null);
		System.out.println("Searching for product " + key);
		if (ulist.contains(temp)) {
			temp = ulist.get(ulist.indexOf(temp));
			System.out.print("Product " + key + " located at index position: " + ulist.indexOf(temp));
			if (ulist.indexOf(temp) != ulist.lastIndexOf(temp)) {
				System.out.println(" and " + ulist.lastIndexOf(temp));
			} else {
				System.out.println("\n");
			}
		} else {
			System.out.println("Product " + key + " was not found in the list\n");
		}
	}

	/**
	 * testRemove - utilizes remove() method from ArrayList class to test it
	 * 
	 * @param key   the product id to be removed
	 * @param ulist the list to remove items from
	 */
	public static void testRemove(long key, ArrayList<BestBuyProduct> ulist) {
		BestBuyProduct temp = new BestBuyProduct(key, 0, null, null, null, null);
		System.out.println("Removing product " + key);
		if (ulist.contains(temp)) {
			temp = ulist.get(ulist.indexOf(temp));
			ulist.remove(temp);
			System.out.println("Product " + key + " removed from list");
			System.out.println(ulist + "\n");
		} else {
			System.out.println("Product " + key + " not found in the list\n");
		}
	}

	/**
	 * find - prints out all items containing certain words in the name
	 * 
	 * @param str   the keyword to look for
	 * @param ulist the list to look within
	 */
	public static void find(String str, ArrayList<BestBuyProduct> ulist) {
		String output = "";
		for (int i = 0; i < ulist.getSize(); i++) {
			if (ulist.get(i).toString().contains(str)) {
				output += ulist.get(i);
			}
		}
		if (output.length() > 0) {
			System.out.println(output);
		} else {
			System.out.println("No product contains " + str + " were found.");
		}
	}

}
