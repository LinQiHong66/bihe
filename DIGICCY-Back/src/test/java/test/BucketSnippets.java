 
package test;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// [START example]
@SuppressWarnings("serial")
public class BucketSnippets extends HttpServlet {
  static final String bucket = "btc_001";

  // [START gcs]
  private static final GcsService gcsService = GcsServiceFactory.createGcsService(new RetryParams.Builder()
      .initialRetryDelayMillis(10)
      .retryMaxAttempts(10)
      .totalRetryPeriodMillis(15000)
      .build());
  // [END gcs]

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

  }
  
  public static void main(String[] args) throws IOException {
	    //[START original_image]
	    // 将image.jpg资源读入ByteBuffer.
	    FileInputStream fileInputStream = new FileInputStream(new File("C://image.jpg"));
	     
	    FileChannel fileChannel = fileInputStream.getChannel();
	    ByteBuffer byteBuffer = ByteBuffer.allocate((int)fileChannel.size());
	    fileChannel.read(byteBuffer);

	    byte[] imageBytes = byteBuffer.array();

	    // 将原始图像写入云端存储
	    gcsService.createOrReplace(
	        new GcsFilename(bucket, "image.jpeg"),
	        new GcsFileOptions.Builder().mimeType("image/jpeg").build(),
	        ByteBuffer.wrap(imageBytes));
	    //[END original_image]

	    //[START resize]
	    // 获取我们可以用来转换图像的imagesService的一个实例.
	    ImagesService imagesService = ImagesServiceFactory.getImagesService();

	    //直接从一个字节数组中创建一个图像，然后进行变换。
	    Image image = ImagesServiceFactory.makeImage(imageBytes);
	    Transform resize = ImagesServiceFactory.makeResize(100, 50);
	    Image resizedImage = imagesService.applyTransform(resize, image);

	    // 将转换后的图像写回Cloud Storage对象。
	    gcsService.createOrReplace(
	        new GcsFilename(bucket, "resizedImage.jpeg"),
	        new GcsFileOptions.Builder().mimeType("image/jpeg").build(),
	        ByteBuffer.wrap(resizedImage.getImageData()));
	    //[END resize]

	    //[START rotate]
	    // 从Cloud Storage对象制作图像，然后进行变换。
	    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	    BlobKey blobKey = blobstoreService.createGsBlobKey("/gs/" + bucket + "/image.jpeg");
	    Image blobImage = ImagesServiceFactory.makeImageFromBlob(blobKey);
	    Transform rotate = ImagesServiceFactory.makeRotate(90);
	    Image rotatedImage = imagesService.applyTransform(rotate, blobImage);

	    // 将转换后的图像写回Cloud Storage对象
	    gcsService.createOrReplace(
	        new GcsFilename(bucket, "rotatedImage.jpeg"),
	        new GcsFileOptions.Builder().mimeType("image/jpeg").build(),
	        ByteBuffer.wrap(rotatedImage.getImageData()));
	    //[END rotate]

 
	    
	    System.out.println("storage.cloud.google.com/"+bucket+"/image.jpeg");   
	   
	    
   }
  
}
