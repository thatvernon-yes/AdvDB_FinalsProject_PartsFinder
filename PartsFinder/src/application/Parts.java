package application;

import java.sql.Blob;

public class Parts {

	private Blob image;
	private String name;
	private int srp;
	private String retailer;
	private int stock;

	public Parts( Blob image, String name, int stock, int srp, String retailer) {
		
		//information na naka display
		this.image = image;
		this.name = name;
		this.srp = srp;
		this.stock = stock;
		this.retailer = retailer;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSrp() {
		return srp;
	}

	public void setSrp(int srp) {
		this.srp = srp;
	}

	public String getRetailer() {
		return retailer;
	}

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	
	
	
	
	//from result set ; gagawa ng separate object kada parts_ID; kukunin yung attributes galing sa result set
}
