package yourPrime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyMedia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// TODO complete the MyMedia class - use the UML diagram in the task document to guide you
	private List<Movie> movieList = new ArrayList<>();
	private List<Book> bookList = new ArrayList<>();
	private List<Song> songList = new ArrayList<>();
	
	public MyMedia(List<Movie> listM, List<Book> listB, List<Song> listS) {
		// TODO use argument to assign instance variables in the class
		this.movieList = listM;
		this.bookList = listB;
		this.songList = listS;
	}
	
	public double calculateFees() {
		// TODO calculate the fees for all media - use the getPrice method from the media sub-types
		double fees = movieList.stream().reduce(0d, (subSum, m) -> subSum + m.getPrice(), Double::sum);
		fees = bookList.stream().reduce(fees, (subSum, m) -> subSum + m.getPrice(), Double::sum);
		fees = songList.stream().reduce(fees, (subSum, m) -> subSum + m.getPrice(), Double::sum);
		return fees;
	}
	
	@Override
	public String toString() {
		// TODO return a string, call the toString method from the media sub-types
		String strAll = movieList.stream().reduce("", (subStr, m) -> subStr + m.toString() + "\n", String::concat);
		strAll = songList.stream().reduce(strAll, (subStr, m) -> subStr + m.toString() + "\n", String::concat);
		strAll = bookList.stream().reduce(strAll, (subStr, m) -> subStr + m.toString() + "\n", String::concat);
		return strAll;
	}
	
	public List<?> sort(String orderType) {
		// TODO the sort method will generate a sorted list (ascending order) following the orderType argument
		// this method will be used by the subsciber objects
		// return the sorted list
		
		List<Media> masterList = new ArrayList<>();
		masterList.addAll(movieList);
		masterList.addAll(songList);
		masterList.addAll(bookList);
		
		Comparator<Media> compareByPrice = Comparator.comparing(Media::getPrice);
		Comparator<Media> compareByRating = Comparator.comparing(Media::getRating);
		Comparator<Media> compareByTitle = Comparator.comparing(Media::getTitle);
		Comparator<Movie> compareByDuration = Comparator.comparing(Movie::getDuration);
		Comparator<Book> compareByPages = Comparator.comparing(Book::getNoPages);
		
		if (orderType.equalsIgnoreCase("Price")) 
			masterList.sort(compareByPrice);
		else if (orderType.equalsIgnoreCase("Rating"))
			masterList.sort(compareByRating);
		else if (orderType.equalsIgnoreCase("Title"))
			masterList.sort(compareByTitle);
		else if (orderType.equalsIgnoreCase("Duration")) {
			movieList.sort(compareByDuration);
			return movieList;
		} else if (orderType.equalsIgnoreCase("Pages")) {
			bookList.sort(compareByPages);
			return bookList;
		}
		return masterList;
	}
}
