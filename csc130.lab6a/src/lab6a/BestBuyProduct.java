package lab6a;

/**
 * <p>
 * Title: BestBuyProduct
 * </p>
 * 
 * <p>
 * Description: This class implements Comparable class, it creates best buy
 * product objects and stores each object's information
 * </p>
 * 
 * @author Chunbo Cheng
 */
class BestBuyProduct implements Comparable<BestBuyProduct> {
	long sku;
	double price;
	String name, upc, manufacturer, model;

	public BestBuyProduct(long sku, double price, String name, String upc, String manufacturer, String model) {
		this.sku = sku;
		this.price = price;
		this.name = name;
		this.upc = upc;
		this.manufacturer = manufacturer;
		this.model = model;
	}

	public long getSku() {
		return sku;
	}

	public double getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}

	public String getUPC() {
		return upc;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getModel() {
		return model;
	}

	/**
	 * toString method -- creates and returns a String which represents the state of
	 * the object
	 * 
	 * @return a String containing the current values of the product
	 */
	public String toString() {
		return sku + "," + price + ",\"" + name + "\"," + upc + ",\"" + manufacturer + "\"," + model + "\n";
	}

	/**
	 * equals method -- determines if two Products have the same sku
	 * 
	 * @param otherItem is a reference to a Product object
	 * @return true if the two objects contain the same sku false otherwise
	 */
	public boolean equals(Object otherItem) {
		if (otherItem instanceof BestBuyProduct) {
			BestBuyProduct temp = (BestBuyProduct) otherItem;
			return (this.sku == temp.sku);
		}
		return false;
	}

	@Override
	public int compareTo(BestBuyProduct otherProduct) {
		return (int) (sku - otherProduct.sku);
	}

}