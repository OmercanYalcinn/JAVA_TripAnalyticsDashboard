package db;

import java.io.*;
import java.sql.*;
import java.nio.file.*;

/*
 * A CSVLoader typically reads a CSV (Comma Separated Values) file, 
 * parses it line by line, and loads the data into a database or Java objects.
 */
public class CSVLoader {
	
	// This whole full filled try and catch blocks method works with:
	// Going to connect with Database than
	// If trips table is empty
		// Read the CSV(excel) file
		// Adding data into the table(batch format)
	// Then commits the job
	// Basiclly it works for data importing from CSV file to Database
	public static void loadTripFromCVS(String cvsPath) {
		String url = "jdbc:sqlite:data/trips.db";

		System.out.println("Trying to read file: " + cvsPath);
		System.out.println("Absolute path: " + Paths.get(cvsPath).toAbsolutePath());
		
		try(Connection conn = DriverManager.getConnection(url)) {
			
			// If table is not empty, load it
			try(Statement stmt = conn.createStatement()){
				// Result Set is an all rows and columns of a SQL query
				ResultSet resultSet = stmt.executeQuery("SELECT COUNT(*) FROM trips;");
				if (resultSet.next() && resultSet.getInt(1) > 0) {
					System.out.println(" Database already has data,  skipping CSV import.");
					return;
				}
			}
			System.out.println("Loading data from CSV...");
			
			try (BufferedReader br = Files.newBufferedReader(Paths.get(cvsPath))){
				String line;
				br.readLine(); // skip header
				conn.setAutoCommit(false);
				
				String sql = """
						INSERT INTO raw_trajectories
						(trip_id, timestamp, velocity_kmh, acceleration_mss, latitude, longitude, bearing)
						VALUES (?,?,?,?,?,?,?)
						""";
				PreparedStatement  ps = conn.prepareStatement(sql);
				
				int count = 0;
				while ((line = br.readLine()) != null) {
					String[] fields = line.split(",");
					
					if(fields.length != 7) continue;
					
					try {
						ps.setInt(1, Integer.parseInt(fields[0]));
						ps.setString(2, fields[1]);
						ps.setString(3, fields[2]);
						ps.setInt(4, Integer.parseInt(fields[3]));
						ps.setInt(5, Integer.parseInt(fields[4]));
						ps.setString(6, fields[5]);
						ps.addBatch();
						count++;
						
					} catch(NumberFormatException  e) {
						System.err.println("Skipped invalid line: " + line);
					}
				}
				 ps.executeBatch();
	                conn.commit();
	                System.out.println("CSV data imported successfully. (" + count + " rows added)");
				
			} catch (IOException e) {
				System.err.println("Error reading CSV: " + e.getMessage());
			}
		} catch (SQLException e) {
            e.printStackTrace();
		}
	}
	
}
