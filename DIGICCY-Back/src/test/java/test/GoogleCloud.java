package test;
import java.io.FileOutputStream;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//Imports the Google Cloud client library
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleCloud {
public static void main(String... args) throws Exception {
 // Instantiates a client
 Storage storage = StorageOptions.getDefaultInstance().getService();

 // The name for the new bucket
 String bucketName ="btc_008";  // "my-new-bucket";
 
 
 // Creates the new bucket
 Bucket bucket = storage.create(BucketInfo.of(bucketName));

 System.out.printf("Bucket %s created.%n", bucket.getName());
  }
}





 