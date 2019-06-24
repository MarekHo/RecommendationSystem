/**
 * This class is used to make an list of movies with their average rate
 * @author Marek 
 * @version 2019-06-23
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myMovies = fr.loadMovies(moviefile);
    	myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
    	return myMovies.size();
    }
    
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    public String getTitle(String id) {
    	for(int i = 0; i < myMovies.size(); i++) {
    		if(myMovies.get(i).getID().equals(id)) {
    			return myMovies.get(i).getTitle(); 
    		}
    	}
    	return "ID was not found";
    }
    
    public String getID(String title) {
    	
    	for(int i = 0; i < myMovies.size(); i++) {
    		
    		if(myMovies.get(i).getTitle().equals(title)) {
    			return myMovies.get(i).getID(); 
    		}
    	}
    	return "NO SUCH TITLE";
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<Rating> avgMovieRating = new ArrayList<Rating>();
    	
    	for(int i = 0; i < myMovies.size(); i++) {
    		String currId = myMovies.get(i).getID();
    		Rating avgCurr = new Rating(currId, getAverageByID(currId, minimalRaters));
    		avgMovieRating.add(avgCurr);
    	}
    	
    	return avgMovieRating;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
    	int nrRaters = 0;
    	double sumRate = 0.0;
    	
    	for(int i = 0; i < myRaters.size(); i++) {
    		
    		if(myRaters.get(i).hasRating(id)) {
    			nrRaters++;
    			sumRate += myRaters.get(i).getRating(id);
    		}
    	}
    	
    	if(nrRaters >= minimalRaters) {
    		return sumRate/nrRaters;
    	}
    	
    	return 0.0;
    }
    
}