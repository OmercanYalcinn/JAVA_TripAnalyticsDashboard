import db.DatabaseInitializer;
import db.CSVLoader;

public class Main {

	public static void main(String[] args) {		
		DatabaseInitializer.initializeDatabase(); 		// Creating Table Here
		CSVLoader.loadTripFromCVS("dataset/trajectories_to_publish.csv");	 // Adds Data Here
		System.out.println("TripAnalyticsDashboard setup complete!");
	}

}
