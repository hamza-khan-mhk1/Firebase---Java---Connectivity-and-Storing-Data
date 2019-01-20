package com.example;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SpringBootApplication
public class FirebaseTestApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FirebaseTestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// replace ".json" file, available in firebase project console.
		FileInputStream serviceAccount =
				new FileInputStream("fir-notificationtest-fae54-firebase-adminsdk-rcxif-326f0e834f.json");
		
		// replace Database url with your firebase database url
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://fir-notificationtest-fae54.firebaseio.com")
				.build();

		FirebaseApp.initializeApp(options);

		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference ref = database.getReference().child("notifications");
		DatabaseReference node_key = ref.push();
		
				
		Map<String, String> users = new HashMap<>();		
		users.put("name", "hamza khan");
		// save data
		node_key.setValueAsync(users);

	}
	
	
	
}
