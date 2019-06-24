
public class DirectorsFilter implements Filter {
	private String[] myDirectors;
	
	public DirectorsFilter(String directors) {
		myDirectors = directors.split(",");
	}
	
	@Override
	public boolean satisfies(String id) {
		String movieDirectors = MovieDatabase.getDirector(id);
		for(String s : myDirectors) {
			if(movieDirectors.contains(s)) {
				return true;
			}
		}
		return false;
	}

}
