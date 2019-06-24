import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage{
	
	public void printAverageRatings() {
		SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
		System.out.println("Number of movies " + sr.getMovieSize());
		System.out.println("Number of raters " + sr.getRaterSize());
		
		int ratings = 20;
		int nrMoviesWithRatings = 0;
		ArrayList<Rating> avgRatings = sr.getAverageRatings(ratings);
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(curr.getValue() + " " + sr.getTitle(curr.getItem()));
			}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);

	}
	
	
	public void getAverageRatingOneMovie() {
		
		SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
		
		String title = "Vacation";
		String id = sr.getID(title);
		if(id.equals("NO SUCH TITLE")) {
			System.out.println(id);
			return;
		}
		
		ArrayList<Rating> avgRatings = sr.getAverageRatings(1);
		
		for(Rating curr: avgRatings){
			if(curr.getItem().equals(id)){
				System.out.println(curr.getValue() + " " + title);
				break;
			}
		}
	}
		
	
}