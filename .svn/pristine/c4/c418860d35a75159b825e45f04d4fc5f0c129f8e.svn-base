import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import jodd.http.HttpRequest;
import jodd.http.HttpResponse;



 



public class sssss {

	/*public static void main(String[] args) {
 		try {
			HttpURLConnection urlconnection = (HttpURLConnection) new URL("https://etherchain.org/api/account/0xece19e48a291efa5ccd026ea2e0c3950e86f8a21/tx/0").openConnection();
			urlconnection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			urlconnection.connect();
			InputStreamReader is = new InputStreamReader(urlconnection.getInputStream());
			char b[] = new char[1024];
			int len;
			StringBuffer sb = new StringBuffer();
			while((len = is.read(b)) != -1){
				sb.append(b,0,len);
			}
	 
 		JSONObject jo = JSONObject.fromObject(sb);
 		
 		System.out.println(jo.getInt("status"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}*/
	
	
	public static void main(String[] args) {
		HttpRequest request = HttpRequest.get("https://etherchain.org/api/account/0xece19e48a291efa5ccd026ea2e0c3950e86f8a21/tx/0");
		request.contentType("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		//System.err.println(request);
		HttpResponse response = request.send();
		String respJson = response.bodyText();
		
		Map<String, Object> respMap = JSON.parseObject(respJson, Map.class);
		System.out.println(respMap.get("status"));
		List<Map<String,Object>> dataList = (List<Map<String, Object>>) respMap.get("data");
	    for(Map<String,Object> m : dataList){
	    	String txid = m.get("hash").toString();
      		String amount = m.get("amount").toString();
      		String address= m.get("recipient").toString();
      		/*System.out.println("txid:"+txid+",amount:"+amount+",address:"+address);
      		*/
      		BigDecimal amountBig=new BigDecimal(amount);
      		BigDecimal wei=new BigDecimal("1000000000000000000");
      		BigDecimal res = amountBig.divide(wei);
      		System.out.println("res:"+res);
	    }
 
	}

}
