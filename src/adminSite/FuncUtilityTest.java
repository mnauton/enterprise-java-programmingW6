package adminSite;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yourPrime.Action;
import yourPrime.Autobiography;
import yourPrime.Book;
import yourPrime.Comedy;
import yourPrime.Drama;
import yourPrime.Fantasy;
import yourPrime.Movie;
import yourPrime.MyMedia;
import yourPrime.Pop;
import yourPrime.Rap;
import yourPrime.Rock;
import yourPrime.Song;
import yourPrime.Subscriber;

public class FuncUtilityTest {

	public static void main(String[] args) {
		// dummy songs 
		Song s1 = new Song("Fight The Power", "Public Enemy", 4, new Rap());
		Song s2 = new Song("Smell Like Teen Spirit", "Nirvana", 4, new Rock());
		Song s3 = new Song("Blinding Lights", "The Weeknd", 2, new Pop());
		Song s4 = new Song("We Belong Together", "Mariah Carey", 3, new Pop());
		Song s5 = new Song("Hey Jude", "The Beatles", 3, new Rock());
				
		// dummy movies
		Movie m1 = new Movie("The Godfather", "Marlon Brando, Al Pacino", 175.0, 1972, 4, new Drama());
		Movie m2 = new Movie("Avangers: Endgame", "Ironman, Capt. America, Thor", 181.0, 2019, 2, new Action());
		Movie m3 = new Movie("Zoolander", "Ben Stiller, Owen Wilson", 90.0, 1998, 1, new Comedy());
		Movie m4 = new Movie("The Shawshank Redemption", "Tim Robbins, Morgan Freeman", 142.0, 1994, 5, new Drama());
		Movie m5 = new Movie("Forrest Gump", "Tom Hanks", 142.5, 1994, 3, new Comedy());
		
		// dummy books
		Book b1 = new Book("The Da Vinci Code", "Dan Brown", 2003, 1, 689, new Fantasy());
		Book b2 = new Book("Harry Potter and the Deathly Hallows", "J. K. Rowlings", 2007, 3, 607, new Fantasy());
		Book b3 = new Book("Show Dog", "Phil Knight", 2016, 5, 399, new Autobiography());
		Book b4 = new Book("The Fellowship of the Ring", "J. R. R. Tolkien", 1954, 2, 423, new Fantasy());
		Book b5 = new Book("Surely You're Jocking, Mr. Feynman", "Richard P. Feynman", 1985, 5, 356, new Autobiography());
		
		// create subscribers for testing purpose
		Map<Integer, List<Song>> mapLibSong = new HashMap<>();
		mapLibSong.put(0, Arrays.asList(s3, s2, s4));
		mapLibSong.put(1, Arrays.asList(s1, s2, s3, s4));
		mapLibSong.put(2, Arrays.asList(s2, s3, s4, s5));
		mapLibSong.put(3, Arrays.asList(s3, s5));
		mapLibSong.put(4, Arrays.asList(s1, s2, s4));
		
		Map<Integer, List<Movie>> mapLibMovie = new HashMap<>();
		mapLibMovie.put(0, Arrays.asList(m3, m2, m4));
		mapLibMovie.put(1, Arrays.asList(m1, m2, m3, m4));
		mapLibMovie.put(2, Arrays.asList(m2, m3, m4, m5));
		mapLibMovie.put(3, Arrays.asList(m3, m5));
		mapLibMovie.put(4, Arrays.asList(m1, m2, m4));
		
		Map<Integer, List<Book>> mapLibBook = new HashMap<>();
		mapLibBook.put(0, Arrays.asList(b3, b2, b4));
		mapLibBook.put(1, Arrays.asList(b1, b2, b3, b4));
		mapLibBook.put(2, Arrays.asList(b2, b3, b4, b5));
		mapLibBook.put(3, Arrays.asList(b3, b5));
		mapLibBook.put(4, Arrays.asList(b1, b2, b4));
		
		List<String> listUser = Arrays.asList("Arthur Shelby", "Tommy Shelby", "Ada Shelby", "John Shelby", "Finn Shelby");
		List<String> listId = Arrays.asList("001", "002", "003", "004", "005");
		Map<String, Subscriber> userDb = new HashMap<>();
		MyMedia myMedia;
		for (int i = 0; i < 5; i++) {
			myMedia = new MyMedia(mapLibMovie.get(i), mapLibBook.get(i), mapLibSong.get(i));
			userDb.put(listId.get(i), new Subscriber(listId.get(i), listUser.get(i), "password", myMedia));
		}
		FuncUtil testData = new FuncUtil(userDb);
		
		// Test addSubscriber not on the database
		Subscriber newUser = new Subscriber("", "Alfie Solomons", "password", null);
		System.out.println("Test addSubscriber:");
		testData.addSubscriber(newUser);
		testData.getUserDb().forEach((k, v) -> System.out.println(k + " for " + v.getName()));
		
		// Test for modifyPassword
		System.out.print("\nTest modifyPassword: ");
		System.out.println(testData.modifyPassword("002", "grace0123"));
		
		// Test for searchSubscriber
		System.out.print("\nTest searchSubscriber: ");
		List<Subscriber> results = testData.searchSubscriber("Alfie Solomons");
		results.forEach(p -> System.out.println(p.getUserID() + " " + p.getName()));
		
		// Test for deleteSubscriber
		System.out.print("\nTest deleteSubscriber: " + 
				testData.deleteSubscriber(results.get(0).getUserID()) + "\n");
		
		testData.getUserDb().forEach((k, v) -> System.out.println(k + " for " + v.getName()));
		
		// Test for calculateOverdueFees
		System.out.print("\nTest calculateOverdueFees: " + testData.calculateOverdueFees() + "\n");
		System.out.println("Outstanding amount: " +
				testData.getUserDb().values().stream().mapToDouble(p -> p.getFees()).sum());
		
		// Test for printAllSubscribers
		System.out.println("\nPrint all: ");
		testData.printAllSubscribers();
	}

}
