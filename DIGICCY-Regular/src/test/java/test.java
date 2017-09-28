import java.math.BigDecimal;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.utils.Numeric;

 


public class test {

@Autowired
static
 RedisTemplate<String, Object> redisTemplate;
	public static void main(String[] args) {
/*		BigDecimal wei=new BigDecimal("10000000000000");
		BigDecimal numbig=new BigDecimal(1);
		BigDecimal  res = wei.multiply(numbig);
        System.out.println(Long.toHexString(res.longValue()));*/
		 //添加一个 key 
        ValueOperations<String, Object> value = redisTemplate.opsForValue();
        value.set("lp", "hello word");
        //获取 这个 key 的值
        System.out.println(value.get("lp"));
		
       
	}

}
