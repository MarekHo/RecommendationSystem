import java.util.ArrayList;
import java.util.Random;

public class RecommendationRunner implements Recommender {

	@Override
	public ArrayList<String> getItemsToRate() {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		Random index = new Random();
		ArrayList<String> itemsToRate = new ArrayList<String>();
		ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
		for(int i = 0; i < 11; i++) {
			itemsToRate.add(movies.get(index.nextInt(MovieDatabase.size())));
		}
		return itemsToRate;
	}

	@Override
	public void printRecommendationsFor(String webRaterID) {
		MovieDatabase.initialize("ratedmoviesfull.csv");
		RaterDatabase.initialize("data/ratings.csv");
		FourthRatings fr = new FourthRatings();
		ArrayList<Rating> list = fr.getSimilarRatings(webRaterID, 4, 3);
		
		if(list.size() < 1) {
			 System.out.println("Recommendation List: Not found");
		}else {
			StringBuilder buf = new StringBuilder();
			buf.append("<html>" +
			           "<body>" +
			           "<table>" +
			           "<tr>" +
			           "<th>Title</th>" +
			           "<th>Average weight rate</th>" +
			           "</tr>");
			for (int i = 0; i < list.size(); i++) {
			    buf.append("<tr><td>")
			       .append(MovieDatabase.getTitle(list.get(i).getItem()))
			       .append("</td><td>")
			       .append(list.get(i).getValue())
			       .append("</td><td>");
			    if(i > 11) {
			    	break;
			    }
			}
			
			buf.append("</table>" +
			           "</body>" +
			           "</html>");
			String html = buf.toString();
			System.out.println(html);
		}
	}

}
