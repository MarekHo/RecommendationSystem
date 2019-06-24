import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
//		FirstRatings fr = new FirstRatings();
//		fr.testLoadMovies();
//		fr.testloadRaters();
//		
//		MovieRunnerAverage mra = new MovieRunnerAverage();
//		mra.printAverageRatings();
//		mra.getAverageRatingOneMovie();
		
//		MovieRunnerWithFilters mrwf = new MovieRunnerWithFilters();
//		mrwf.printAverageRatings();
//		mrwf.printAverageRatingsByYear();
//		mrwf.printAverageGenre();
//		mrwf.printAverageRatingsByMinutes();
//		mrwf.printAverageRatingsByDirectors();
//		mrwf.printAverageRatingsByYearAfterAndGenre();
//		mrwf.printAverageRatingsByDirectorsAndMinutes();
		
		MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
//		mrsr.printAverageRatings();
//		mrsr.printAverageRatingsByYearAfterAndGenre();
//		mrsr.printSimilarRatings();
//		mrsr.printSimilarRatingsByGenre();
//		mrsr.printSimilarRatingsByDirector();
//		mrsr.printSimilarRatingsByGenreAndMinutes();
//		mrsr.printSimilarRatingsByYearAndMinutes();
		
		RecommendationRunner rr = new RecommendationRunner();
		ArrayList<String> test = rr.getItemsToRate();
//		for(String t : test) {
//			System.out.println(t);
//		}
		rr.printRecommendationsFor("65");

	}
}
