/**
 * This class is used to make an list of movies with their average rate with filters
 * @author Marek 
 * @version 2019-06-23
 */

import java.util.*;

public class ThirdRatings {
	private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
    	FirstRatings fr = new FirstRatings();
    	myRaters = fr.loadRaters(ratingsfile);
    }
        
    public int getRaterSize() {
    	return myRaters.size();
    }
    
    public String getTitle(String id) {
    	return MovieDatabase.getTitle(id);
    }
    
    public String getGenres(String id) {
    	return MovieDatabase.getGenres(id);
    }
    
    public int getTime(String id) {
    	return MovieDatabase.getMinutes(id);
    }

    public String getDirectors(String id) {
    	return MovieDatabase.getDirector(id);
    }
    
    public int getYear(String id) {
    	return MovieDatabase.getYear(id);
    }

    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
    	ArrayList<Rating> avgMovieRating = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	
    	for(int i = 0; i < movies.size(); i++) {
    		String currId = movies.get(i);
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
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {
     	ArrayList<String> filteredList = MovieDatabase.filterBy(filterCriteria);
     	ArrayList<Rating> avgFilteredList = new ArrayList<Rating>();

    	for(int i = 0; i < filteredList.size(); i++) {
    		String currId = filteredList.get(i);
    		Rating avgCurr = new Rating(currId, getAverageByID(currId, minimalRaters));
    		avgFilteredList.add(avgCurr);
    	}
     	
    	return avgFilteredList;
    }
    
}