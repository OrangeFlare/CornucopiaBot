package me.orangeflare.cornucopia.googleFirebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class firebase {
    public static Firestore init(String projectId) throws java.io.IOException, java.io.FileNotFoundException {
        try {
            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
        } catch (FileNotFoundException e1) {
            System.err.println("-----------------[CRITICAL ERROR]-----------------");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.out.println("  SYSTEM COULD NOT FIND 'serviceAccountKey.json'  ");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.out.println("                 SHUTTING DOWN!!!                 ");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            System.err.println("-----------------[CRITICAL ERROR]-----------------");
            try { Thread.sleep(2500); } catch (InterruptedException e2) { System.exit(0); }
            System.exit(0);
        }
        System.out.println("[firebase] Found 'serviceAccountKey.json' Successfully!");
        FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://" + projectId + ".firebaseio.com/")
                .setProjectId(projectId)
                .build();
        FirebaseApp.initializeApp(options);
        Firestore db = FirestoreClient.getFirestore();
        return db;
    }
}
