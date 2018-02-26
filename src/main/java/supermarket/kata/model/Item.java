package supermarket.kata.model;

public class Item {

	private String name;
	private double price;

	public Item(){
		
	}
	
	public Item(String name, double price) {
		super();
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [name=" + name + ", price=" + price + "]";
	}

	@Override
	public int hashCode() {
		final int offset = 31;
		int result = 1;
		result = offset * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		boolean flag = false;
		Item item = (Item) obj;
		if (item.name == name)
			flag = true;
		return flag;
	}

}
