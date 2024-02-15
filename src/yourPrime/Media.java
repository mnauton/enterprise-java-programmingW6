package yourPrime;

import java.io.Serializable;

public class Media implements Serializable {
	
	// superclass for the media types available in YourPrime streaming  platform.
	// TODO you need to complete all the methods in this class, please refer to 
	// each method for details
	
	protected String title = "";
	protected int year = 0;
	protected int rating = 0;
	protected double price = 0.0;
	protected Genre genre;
	
	public double getPrice() {
		// TODO return the price for each media sub-type
		this.price = genre.setPrice(rating);
		return price;
	}
	
	public String getTitle() {
		// TODO return title
		return title;
	}
	
	public int getRating() {
		// TODO return rating
		return rating;
	}
}
