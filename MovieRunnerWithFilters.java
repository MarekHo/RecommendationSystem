import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters{
	
	public void printAverageRatings() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");
		
		System.out.println("Number of raters " + tr.getRaterSize());
		
		
		int ratings = 35;
		int nrMoviesWithRatings = 0;
		ArrayList<Rating> avgRatings = tr.getAverageRatings(ratings);
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			
			System.out.println(curr.getValue() + " " + tr.getTitle(curr.getItem()));
		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);

	}
	
	public void printAverageRatingsByYear() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 20;
		int nrMoviesWithRatings = 0;
		int filteredYear = 2000;
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, new YearAfterFilter(filteredYear));
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(curr.getValue() + " " + tr.getYear(curr.getItem()) + " " + tr.getTitle(curr.getItem()));
			}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);

	}
	
	public void printAverageGenre() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 20;
		int nrMoviesWithRatings = 0;
		String filteredGenre = "Comedy";
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, new GenreFilter(filteredGenre));
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(curr.getValue() + " " + tr.getTitle(curr.getItem()));
			System.out.println("\t" + tr.getGenres(curr.getItem()));
		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}
	
	public void printAverageRatingsByMinutes() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 5;
		int nrMoviesWithRatings = 0;
		int minMinutes = 105;
		int maxMinutes = 135;
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, new MinutesFilter(minMinutes, maxMinutes));
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(curr.getValue() + " Time: " + tr.getTime(curr.getItem()) + " " + tr.getTitle(curr.getItem()));

		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}
	
	public void printAverageRatingsByDirectors() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 4;
		int nrMoviesWithRatings = 0;
		String filteredDirectors = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, new DirectorsFilter(filteredDirectors));
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			
				System.out.println(curr.getValue() + " " + tr.getTitle(curr.getItem()));
				System.out.println("\t" + tr.getDirectors(curr.getItem()));
			}
		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 8;
		int nrMoviesWithRatings = 0;
		String filteredGenre = "Drama";
		int filteredYear = 1990;
		
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new GenreFilter(filteredGenre));
		filterCriteria.addFilter(new YearAfterFilter(filteredYear));
		
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, filterCriteria);
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			
				System.out.println(curr.getValue() + " " + tr.getYear(curr.getItem()) + " " + tr.getTitle(curr.getItem()));
				System.out.println("\t" + tr.getGenres(curr.getItem()));
			}
		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}

	public void printAverageRatingsByDirectorsAndMinutes() {
		ThirdRatings tr = new ThirdRatings("ratings.csv");

		System.out.println("Number of raters " + tr.getRaterSize());
		
		int ratings = 3;
		int nrMoviesWithRatings = 0;
		int minMinutes = 90;
		int maxMinutes = 180;
		String filteredDirectors = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";

		
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
		filterCriteria.addFilter(new DirectorsFilter(filteredDirectors));
		
		ArrayList<Rating> avgRatings = tr.getAverageRatingsByFilter(ratings, filterCriteria);
				Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			
				System.out.println(curr.getValue() + " Time: " + tr.getTime(curr.getItem()) + " " + tr.getTitle(curr.getItem()));
				System.out.println("\t" + tr.getDirectors(curr.getItem()));
			}
		}
		
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}
	
	
}