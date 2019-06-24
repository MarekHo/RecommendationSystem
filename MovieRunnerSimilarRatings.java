import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings{
	
	public void printSimilarRatings() {
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		
		int ratings = 5;
		ArrayList<Rating> list = fr.getSimilarRatings("71", 20, ratings);
		
		int nrMoviesWithRatings = 0;
		for(Rating curr: list){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(MovieDatabase.getTitle(curr.getItem()) + " " + curr.getValue());
			
		}
		
		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());

		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
		
	}
	
	public void printSimilarRatingsByGenre() {
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		
		int ratings = 5;
		int nrMoviesWithRatings = 0;
		String filteredGenre = "Mystery";
		
		ArrayList<Rating> list = fr.getSimilarRatings("964", 20, ratings, new GenreFilter(filteredGenre));
		
		for(Rating curr: list){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(MovieDatabase.getTitle(curr.getItem()) + " " + curr.getValue());
			System.out.println(MovieDatabase.getGenres(curr.getItem()));
		}
		
		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
		
	}

	public void printSimilarRatingsByDirector() {
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		
		int ratings = 2;
		int nrMoviesWithRatings = 0;
		String filteredDirectors = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh";
		
		ArrayList<Rating> list = fr.getSimilarRatings("120", 10, ratings, new DirectorsFilter(filteredDirectors));
		
		for(Rating curr: list){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(MovieDatabase.getTitle(curr.getItem()) + " " + curr.getValue());
			System.out.println(MovieDatabase.getGenres(curr.getItem()));
		}
		
		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
		
	}
	
	public void printSimilarRatingsByGenreAndMinutes() {
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		
		int ratings = 5;
		int nrMoviesWithRatings = 0;
		String filteredGenre = "Adventure";
		int minMinutes = 100;
		int maxMinutes = 200;
		
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new GenreFilter(filteredGenre));
		filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
		
		ArrayList<Rating> list = fr.getSimilarRatings("65", 10, ratings, filterCriteria);
		
		for(Rating curr: list){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(MovieDatabase.getTitle(curr.getItem()) + " " + curr.getValue());
			System.out.println(MovieDatabase.getGenres(curr.getItem()));
		}
		
		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
		
	}
	
	public void printSimilarRatingsByYearAndMinutes() {
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		
		int ratings = 5;
		int nrMoviesWithRatings = 0;
		int year = 1975;
		int minMinutes = 70;
		int maxMinutes = 200;
		
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new YearAfterFilter(year));
		filterCriteria.addFilter(new MinutesFilter(minMinutes, maxMinutes));
		
		ArrayList<Rating> list = fr.getSimilarRatings("314", 10, ratings, filterCriteria);
		
		for(Rating curr: list){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(MovieDatabase.getTitle(curr.getItem()) + " " + curr.getValue());
			System.out.println(MovieDatabase.getGenres(curr.getItem()));
		}
		
		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());
		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
		
	}

	
	public void printAverageRatings() {
		FourthRatings fr = new FourthRatings();
		
		int ratings = 35;
		int nrMoviesWithRatings = 0;
		ArrayList<Rating> avgRatings = fr.getAverageRatings(ratings);
		Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			}
			System.out.println(curr.getValue() + " " + MovieDatabase.getTitle(curr.getItem()));
			}

		System.out.println("\nNumber of raters is " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());

		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);

	}
	
	public void printAverageRatingsByYearAfterAndGenre() {
		FourthRatings fr = new FourthRatings();
		
		int ratings = 8;
		int nrMoviesWithRatings = 0;
		String filteredGenre = "Drama";
		int filteredYear = 1990;
		
		AllFilters filterCriteria = new AllFilters();
		filterCriteria.addFilter(new GenreFilter(filteredGenre));
		filterCriteria.addFilter(new YearAfterFilter(filteredYear));
		
		ArrayList<Rating> avgRatings = fr.getAverageRatingsByFilter(ratings, filterCriteria);
		Collections.sort(avgRatings);
		System.out.println("\nAfter Sorting:");
		
		for(Rating curr: avgRatings){
			if(curr.getValue() != 0) {
				nrMoviesWithRatings++;
			
				System.out.println(curr.getValue() + " " + MovieDatabase.getYear(curr.getItem()) + " " + MovieDatabase.getTitle(curr.getItem()));
				System.out.println("\t" + MovieDatabase.getGenres(curr.getItem()));
			}
		}
		
		System.out.println("\nNumber of raters " + RaterDatabase.size());
		System.out.println("Number of movies is " + MovieDatabase.size());

		System.out.println("\nNumber of movies with ratings more than " + ratings + " is " + nrMoviesWithRatings);
	}	
	
}