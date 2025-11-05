package db;

import java.sql.*;

public class DatabaseInitializer {
	
	// Connect to Database and create table (or if it exist do not touch is the main job that class do
	public static void initializeDatabase() {
		String url = "jdbc:sqlite:data/trips.db";
		// The table for holding data => raw_trajectories
		// Amd the table for summary table => trips
		String createTableQuery = """ 
					CREATE TABLE IF NOT EXISTS raw_trajectories (
						trip_id INTEGER,
						timestamp TEXT,
						velocity_kmh REAL,
						latitude REAL,
						longitude REAL,
						bearing REAL
					);
					
					CREATE TABLE IF NOT EXISTS trips (
						trip_id INTEGER PRIMARY KEY,
		                driver_id TEXT,
		                date TEXT,
		                duration_seconds INTEGER,
		                distance_meters INTEGER,
		                city TEXT
					);
				""";
		// After the job is done, sources should be closed and here is the needed try catch block to do that
		// try with resources structure, different than normal java try:
		// we are creating an Connection object that is named a conn and connect to database
		try (Connection conn = DriverManager.getConnection(url);
				// to work with SQL commands properly we created Statement obejct named as stmt
				Statement stmt = conn.createStatement()){
				// We are able to send out createTableQuerry command  to database
				stmt.execute(createTableQuery);
				System.out.println("Database initialized successfully.");
			}
		catch (SQLException  e) {
			e.printStackTrace();
		}
	} 
}
