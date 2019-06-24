import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/** This class loads the csv files with information about a movie
 * and the list of raters and their movie ratings
 * @author Marek
 * @version 2019-06-23
 */

public class FirstRatings {
	
	/**
	 * This method loads the csv with movie information
	 * @param filename path to the csv file
	 * @return ArrayList with all the movies in the csv file
	 */
	public ArrayList<Movie> loadMovies(String filename) {
	
		FileResource fr = new FileResource("data/" + filename);
		ArrayList<Movie> movieList = new ArrayList<Movie>();
	
		for (CSVRecord rec : fr.getCSVParser(true)) {
				int minutes = Integer.parseInt(rec.get("minutes"));
				Movie curr = new Movie(rec.get("id"), rec.get("title"), rec.get("year"), rec.get("genre"), rec.get("director"), rec.get("country"), rec.get("poster"), minutes);
				movieList.add(curr);
			}
		
		return movieList;
	}
	
	public void testLoadMovies() {
		HashMap<String,ArrayList<Movie>> directors = new HashMap<String,ArrayList<Movie>>();
		ArrayList<Movie> movieList = loadMovies("ratedmoviesfull.csv");
		System.out.println("Number of movies is " + movieList.size());
//		movieList.forEach((m) -> System.out.println(m)); 
		int countComedy = 0;
		int countMinutes = 0;
		for(int i = 0; i < movieList.size(); i++) {
			if(movieList.get(i).getGenres().contains("Comedy")) {
				countComedy++;
			}
			if(movieList.get(i).getMinutes() > 150) {
				countMinutes++;
			}
			String[] currDirector = movieList.get(i).getDirector().split(", ");
			for(int j = 0; j < currDirector.length; j++) {
				if(!directors.containsKey(currDirector[j])){
					ArrayList<Movie> list = new ArrayList<Movie>();
					list.add(movieList.get(i));
					directors.put(currDirector[j], list);
				}else{
					ArrayList<Movie> list = directors.get(currDirector[j]);
					list.add(movieList.get(i));
					directors.put(currDirector[j], list);
				}
			}
		}
		
		int maxDirected = 0;
		for(String dir : directors.keySet()) {
			if(directors.get(dir).size() > maxDirected) {
				maxDirected = directors.get(dir).size();
			}
		}
		System.out.println("\nMaximum number of movies by any director is " + maxDirected);
		System.out.println("These directors are: ");
		
		for(String dir : directors.keySet()) {
			if(directors.get(dir).size() == maxDirected) {
				System.out.println( dir);
			}
		}
		System.out.println("\nMovie list contains " + countComedy + " movies with genre Comedy");
		System.out.println("\nMovie list contains " + countMinutes + " movies with lenght is more than 150 minutes");
		
	}
	
	/**
	 * This method load a csv file with raters and thier movie ratings
	 * @param filename csv file with the raters and ratings
	 * @return ArrayList with raters
	 */
	public ArrayList<Rater> loadRaters(String filename) {
		
		FileResource fr = new FileResource("data/" + filename);
		ArrayList<String> raterId = new ArrayList<String>();
		ArrayList<Rater> raterList = new ArrayList<Rater>();
	
		for (CSVRecord rec : fr.getCSVParser(true)) {
			String currId = rec.get("rater_id");
			if(!raterId.contains(currId) && currId != null) {
				raterId.add(currId);
			}
		}
		raterId.forEach(id -> raterList.add(new EfficientRater(id))); 

		
		for (CSVRecord rec : fr.getCSVParser(true)) {
			for(int i = 0; i < raterList.size(); i++) {
				if(raterList.get(i).getID().equals(rec.get("rater_id"))) {
					Rater temp = raterList.get(i);
					temp.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
					raterList.set(i, temp);
					break;
				}
			}
		}
		
		return raterList;
	}
	
	public void testloadRaters() {
		ArrayList<Rater> raterList = loadRaters("ratings.csv");
		
		System.out.println("\nNumber of raters is " + raterList.size());
		ArrayList<String> ratedMovies = new ArrayList<String>();
		int maxRated = 0;
		
		for(int i = 0; i < raterList.size(); i++) {
			Rater curr = raterList.get(i);
//			System.out.println("\nRater " + curr.getID() + " had rated " + curr.getItemsRated().size() + " movies");
			ArrayList<String> currRatings = curr.getItemsRated();
			
			if(currRatings.size() > maxRated) {
				maxRated = currRatings.size();
			}
			
			for(int j = 0; j < currRatings.size(); j++) {
				String movieId = currRatings.get(j);
				if(!ratedMovies.contains(movieId)) {
					ratedMovies.add(movieId);
				}
				Double movieRating = curr.getRating(movieId);
//				System.out.println("Movie " + movieId + " has been rated " + movieRating);
			}
		}	
			
		System.out.println("\nMaximum number of ratings by any rater is " + maxRated + ". The raters are ");
		for(int i = 0; i < raterList.size(); i++) {
			Rater curr = raterList.get(i);
			if(curr.getItemsRated().size() ==  maxRated) {
				System.out.println(curr.getID());
			}
		}
		
		System.out.println("\n" + ratedMovies.size() + " different movies have been rated.");
		
		String particularRater = "193";
		for(int i = 0; i < raterList.size(); i++) {
			if(raterList.get(i).getID().equals(particularRater)) {
				System.out.println("\n" + particularRater + " rated " + raterList.get(i).getItemsRated().size() + " movies");
				break;
			}
		}
		
		String particularMovie = "1798709";
		int countMovieRates = 0;
		for(int i = 0; i < raterList.size(); i++) {
			if(raterList.get(i).hasRating(particularMovie)) {
				countMovieRates++;
			}
		}
		System.out.println("\n" + particularMovie + " has " + countMovieRates + " rates");
		
	}
}


