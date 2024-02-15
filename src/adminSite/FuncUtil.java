package adminSite;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import yourPrime.Subscriber;

public class FuncUtil {

	private Map<String, Subscriber> userDb = new HashMap<>();

	public FuncUtil(Map<String, Subscriber> userDb) {
		this.userDb = userDb;
	}

	public Map<String, Subscriber> getUserDb() {
		return userDb;
	}

	// TODO check if the subscriber user id exists in the system. If not, create a new user id using supplier interface, and
	// add user into the map userDb. You should also use the built-in interface in map to check for existing user.
	//
	public void addSubscriber(Subscriber subscriber) {
		Supplier<String> userId = () -> generateId().toString();
		String id = userId.get();
		subscriber.setUserID(id);
		userDb.putIfAbsent(id, subscriber);
	}

	public Integer generateId() {
		return new Random().nextInt(80000);
	}

	// TODO update the subscriber password, you need to modify the userDb map accordingly.
	//
	public boolean modifyPassword(String userId, String newPassword) {
		Subscriber subs = userDb.get(userId);
		subs.setPassword(newPassword);
		userDb.replace(userId, subs);
		return (userDb.get(userId).getPassword().equals(newPassword));
	}

	// TODO delete subscriber given the userId, to ensure deletion, return the boolean value
	// to indicate that the user no longer exists
	//
	public boolean deleteSubscriber(String userId) {
		userDb.remove(userId);
		return userDb.get(userId) == null;
	}

		// TODO search user using the predicate interface - you can use the userId or password as keyword
		// return the list of subscriber matching any of the keyword.
		//
	public List<Subscriber> searchSubscriber (String keyword) {
		Predicate<String> searchByUserId = userId -> userId.equals(keyword);
		Predicate<String> searchName = name -> name.equals(keyword);

		List<Subscriber> results = new ArrayList<>();
		userDb.forEach((k, v) -> {
			if (searchByUserId.test(v.getUserID()) || searchName.test(v.getName())) results.add(v);
		});
		return results;

	}

	// TODO Calculate the total overdue fees for all subscribers (i.e., overdue fees are
	// any positive fees in the subscriber account - use the functional interface to filter
	// the values
	//
	public double calculateOverdueFees() {
		Predicate<Double> outstanding = n -> n > 0d;
		double total = 0;
		for (Subscriber s : userDb.values()) {
			if (outstanding.test(s.getFees())) {
				total += s.getFees();
			}
		}
		return total;
	}

	// TODO Use the predicate and function interfaces to display the subscribers with outstanding fees
	// the message should display: John with outstanding amount of X
	//
	public void printAllSubscribers() {
		Predicate<Double> outstanding = n -> n > 0d;
		Function<Subscriber, String> output = p -> p.getName() + " with outstanding fees of " + p.getFees();
		userDb.forEach((k, v) -> {
			if (outstanding.test(v.getFees()))
				System.out.println(output.apply(v));
		});
	}
}
