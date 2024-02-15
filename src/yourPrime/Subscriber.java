package yourPrime;

import java.io.*;
import java.util.List;

public class Subscriber {
	
	// TODO complete the subscriber class, use the UML diagram in the task document
	private String userID;
	private String name;
	private String password;
	private MyMedia myMedia;
	
	public Subscriber(String userID, String name, String password, MyMedia myMedia) {
		// TODO assign all arguments to the instance variables
		this.userID = userID;
		this.name = name;
		this.password = password;
		this.myMedia = myMedia;
	}
	
	public void setUserID(String userId) {
		this.userID = userId;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public double getFees() {
		// TODO return fees for all subscribed items
		return this.myMedia.calculateFees();
		
	}
	
	public List<?> sort(String orderType) {
		// TODO return sorted list based on orderType argument
		return myMedia.sort(orderType);
	}
	
	@Override
	public String toString() {
		// TODO return a string with the following message:
		// name-of-subscriber Acc no: user-id total charge is the fees for show-all-subscribed-media
		return (this.name + " Acc. No: " + this.userID + " total charge is " + String.format("%.2f", getFees()) + " for:\n\n" + this.myMedia.toString());
	}
	
	public MyMedia getMyMedia() {
		return this.myMedia;
	}
	
	// TODO write a method to save all media belonging to the subscriber, use the combination of
	// default path and user name to store the myMedia object
	//
	public boolean saveMedia() {
		String path = "/home/margaux/java-workspace/EnterpriseJavaProgrammingW6/src/" + this.name + "/";
        try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
			out.writeObject(this.myMedia);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
        }
		return false;
	}
	
	// TODO write a method to deserialize the myMedia object from the path, return myMedia object
	//
	public MyMedia getMedia() {
		String path = "/home/margaux/java-workspace/EnterpriseJavaProgrammingW6/src/" + this.name + "/";
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
			this.myMedia = (MyMedia) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
        return null;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
}
