
import java.util.*;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings;


    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        
        return false;
    }
    
     public String getID() {
        return myID;
    }

     public double getRating(String item) {
         return myRatings.get(item).getValue();
     }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
    	return new ArrayList<String>(myRatings.keySet());
    }
    
}
