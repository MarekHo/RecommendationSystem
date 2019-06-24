/**
 * This class is used to make an list of movies with their average rate with filters
 * @author Marek 
 * @version 2019-06-23
 */

import java.util.*;

public class FourthRatings {
    
    public FourthRatings() {
         // default constructorx
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {
    	ArrayList<Rating> similarities = getSimilarities(id);   
    	ArrayList<Rating> avgWeightMovieRating = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    	
    	if(similarities.size() == 0 || numSimilarRaters >= similarities.size()) {
    		return avgWeightMovieRating;
    	}
    	
		for(String movie : movies) {
		    double rate = 0.0;
		    int n = 0;
		    for(int k = 0; k < numSimilarRaters; k++){
		        Rating rater = similarities.get(k);
		        String raterID = rater.getItem();
		        double weight_rate = rater.getValue();
		        double ory_rate = 0;
		        try {
		            ory_rate = RaterDatabase.getRater(raterID).getRating(movie);
		        }
		        catch(NullPointerException e) {
		            continue;
		        }
		        rate += weight_rate * ory_rate;
		        n++;
		    }
		    if(n >= minimalRaters) avgWeightMovieRating.add(new Rating(movie, (rate/n)));
		}
		Collections.sort(avgWeightMovieRating, Collections.reverseOrder());
		return avgWeightMovieRating;
		    	
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters,Filter filterCriteria) {
    	ArrayList<Rating> similarities = getSimilarities(id);   
    	ArrayList<Rating> avgWeightMovieRating = new ArrayList<Rating>();
    	ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
    	
    	if(similarities.size() == 0 || numSimilarRaters >= similarities.size()) {
    		return avgWeightMovieRating;
    	}
    	
		for(String movie : movies) {
		    double rate = 0.0;
		    int n = 0;
		    for(int k = 0; k < numSimilarRaters; k++){
		    	
		        Rating rater = similarities.get(k);
		        String raterID = rater.getItem();
		        double weight_rate = rater.getValue();
		        double ory_rate = 0;
		        try {
		            ory_rate = RaterDatabase.getRater(raterID).getRating(movie);
		        }
		        catch(NullPointerException e) {
		            continue;
		        }
		        rate += weight_rate * ory_rate;
		        n++;
		    }
		    if(n >= minimalRaters) avgWeightMovieRating.add(new Rating(movie, (rate/n)));
		}
		Collections.sort(avgWeightMovieRating, Collections.reverseOrder());
		return avgWeightMovieRating;
		    	
    }
       
    private double dotProduct(Rater me, Rater r) {
    	double dot = 0;
    	for(String movie : me.getItemsRated()) {
    		if(r.hasRating(movie)) {
    			double meRate = me.getRating(movie) - 5;
    			double rRate = r.getRating(movie) - 5;
				dot += meRate * rRate;
    		}
    	}
    	return dot;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
    	ArrayList<Rating> list = new ArrayList<Rating>();
    	Rater me = RaterDatabase.getRater(id);
    	
    	for(Rater r : RaterDatabase.getRaters()) {
    		if(!r.getID().equals(id)) {
    			double dot = dotProduct(me, r);
    			if(dot > 0) {
    				list.add(new Rating(r.getID(), dot));
    			}
    		}
    	}
    	Collections.sort(list, Collections.reverseOrder());
    	
    	return list;
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
      	
    	for(Rater r : RaterDatabase.getRaters()) {  		
    		if(r.hasRating(id)) {
    			nrRaters++;
    			sumRate += r.getRating(id);
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