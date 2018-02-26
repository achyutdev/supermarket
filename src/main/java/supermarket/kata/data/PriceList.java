package supermarket.kata.data;

public enum PriceList {
	A (50),
	B (30),
	C (20),
	D (15);
	
	private final double price; 
	
	PriceList(double price){
		this.price = price;
	}
	
	public double  getPrice(){
		return price;
	}

}
