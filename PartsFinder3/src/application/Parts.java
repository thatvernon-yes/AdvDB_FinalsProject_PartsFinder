package application;

import java.io.InputStream;


public class Parts {

	private InputStream image;
	private String name;
	private int srp;
	//private String retailer;
	private int stock;
	private String location;
	private int id;



	public Parts( InputStream image, String name, int stock, int srp, String location) {
		
		//information na naka display
		this.image = image;
		this.name = name;
		this.srp = srp;
		this.stock = stock;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Parts() {
		// TODO Auto-generated constructor stub
	}

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String retailer) {
		this.location = retailer;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

		
}
	
