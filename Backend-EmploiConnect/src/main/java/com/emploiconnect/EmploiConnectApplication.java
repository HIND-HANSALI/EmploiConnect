package com.emploiconnect;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

@SpringBootApplication
public class EmploiConnectApplication {
	/*public static void initialize() {
		try {
			// Specify the path to the service account key JSON file
			String serviceAccountPath = "src/main/resources/serviceAccountKey.json";

			// Read the service account key JSON file
			InputStream serviceAccount = new FileInputStream(serviceAccountPath);

			// Initialize Firebase options
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.build();

			// Initialize Firebase app
			FirebaseApp.initializeApp(options);

			// Firebase is now initialized and ready to use
			System.out.println("Firebase initialized successfully!");
		} catch (IOException e) {
			System.err.println("Error initializing Firebase: " + e.getMessage());
		}
	}*/
	public static void main(String[] args) {
		//initialize();
		SpringApplication.run(EmploiConnectApplication.class, args);
	}

}
