package com.inesv.digiccy.query;

import com.inesv.digiccy.dto.ApiDepthDto;
import com.inesv.digiccy.dto.EntrustDto;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 鏌ヨ鐢ㄦ埛鎷ユ湁鐨勮祫婧�
 * Created by JimJim on 2016/11/4 0004.
 */
@Component
public class QueryEntrustInfo {
	private static Logger log = LoggerFactory.getLogger(QueryEntrustInfo.class);
	
    @Autowired
    QueryRunner queryRunner;

    /**
     * 鍒嗛〉鏌ヨ濮旀墭璁板綍
     */
    public List<EntrustDto> queryPagingEntrust(int pageNum, int itemCount, int userNo, int coinType){
    	List<EntrustDto> entrustList = new ArrayList<EntrustDto>();
    	String last = " order by a.id limit ?,?";
    	String sql = "SELECT a.user_no, a.entrust_coin,a.id, a.entrust_type, a.entrust_price, a.entrust_num, a.deal_num, a.piundatge, a.state,a.date, b.username AS attr2, c.coin_name AS attr3  FROM t_inesv_entrust a, t_inesv_user b, t_inesv_coin_type c WHERE a.user_no=b.user_no AND a.entrust_coin=c.coin_no and a.user_no=?";
    	ArrayList<Object> paraArr = new ArrayList<>();
    	paraArr.add(userNo);
    	if(coinType != 0){
    		sql += " and a.entrust_coin=?";
    		paraArr.add(coinType);
    	}
    	sql += last;
    	int startItem = itemCount*(pageNum-1);
    	paraArr.add(startItem);
    	paraArr.add(itemCount);
    	try {
			entrustList = queryRunner.query(sql, new BeanListHandler<EntrustDto>(EntrustDto.class), paraArr.toArray(new Object[]{}));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("鏌ヨ鍒嗛〉濮旀墭璁板綍澶辫触");
			return null;
		}
    	return entrustList;
    }
    /**
     * 鏍规嵁鐢ㄦ埛鏌ヨ濮旀墭璁板綍
     * @param userNo
     * @return
     * @throws SQLException 
     */
	public List<EntrustDto> queryEntrustInfoByUserNo(String userNo){
    	List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select e.*,c.coin_name as attr1 from t_inesv_entrust e " +
                "join t_inesv_coin_type c on c.coin_no = e.entrust_coin where user_no=? ";
        Object params[] = {userNo};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("鏍规嵁鐢ㄦ埛鏌ヨ濮旀墭璁板綍澶辫触");
		}
        return EntrustList;
    }
	
	/**
     * 鏍规嵁鐢ㄦ埛鏌ヨ濮旀墭璁板綍
     * @param userNo
     * @return
     * @throws SQLException 
     */
	public List<EntrustDto> queryEntrustInfoByUserNo(Integer userNo) throws Exception{
    	List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "SELECT t1.id AS id , " + 
        		"	t1.user_no AS user_no ," + 
        		"	t1.entrust_coin AS entrust_coin ," + 
        		"	t1.entrust_type AS entrust_type ," + 
        		"	t1.entrust_price AS entrust_price ," + 
        		"	t1.entrust_num AS entrust_num ," + 
        		"	t1.deal_num AS deal_num ," + 
        		"	t1.piundatge AS piundatge ," + 
        		"	t1.state AS state ," + 
        		"   t1.date AS date ," +
        		"	t2.coin_name AS attr2 " + 
        		"	FROM t_inesv_entrust t1 " + 
        		"        JOIN t_inesv_coin_type t2 ON t2.coin_no = t1.entrust_coin WHERE user_no=?";
        Object params[] = {userNo};
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        return EntrustList;
    }

	/**
	 * 寰楀埌璇ョ敤鎴峰墠10鏉℃湭浜ゆ槗鐨勫鎵樿褰�
	 * @param userNo
	 * @return
	 */
	public List<EntrustDto> getNotTradeEntrustOfTradeCenter(Integer userNo,Integer state,Integer number) {
		List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select * from t_inesv_entrust  where user_no=? and state=? order by date desc limit ? ";
        Object params[] = {userNo,state,number};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("寰楀埌璇ョ敤鎴峰墠10鏉℃湭浜ゆ槗鐨勫鎵樿褰曪紒锛侊紒");
		}
        return EntrustList;
	}

	/**
	 * 寰楀埌鏌愮璐у竵鍓�15鏉＄殑涔板崠濮旀墭璁板綍
	 * @param entrustCoin
	 * @param entrustType
	 * @return
	 */
	public List<EntrustDto> queryEntrustInfoOfTradeCenter(Integer entrustCoin,Integer entrustType, Integer number) {
		List<EntrustDto> EntrustList = new ArrayList<>();
        String querySql = "select * from t_inesv_entrust  where entrust_coin=? and entrust_type=? order by date desc limit ?";
        Object params[] = {entrustCoin,entrustType,number};
        try {
			EntrustList=(List<EntrustDto>)queryRunner.query(querySql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("寰楀埌鏌愮璐у竵鍓�15鏉＄殑涔板崠濮旀墭璁板綍澶辫触锛侊紒锛�");
		}
        return EntrustList;
	}

    /**
     * 鏍规嵁濮旀墭浠锋牸锛岃揣甯佺被鍨嬶紝浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰�
     * @param entrustPrice
     * @param entrustCoin
     * @param entrustType
     * @param state
     * @return
     */
    public List<EntrustDto> queryEntrustByEntrustPriceEntrustCoinAndEntrustTypeAndState(BigDecimal entrustPrice, Integer entrustCoin, Integer entrustType, Integer state){
        String sql="select * from t_inesv_entrust where entrust_price=? entrust_coin =? and entrust_type=? and state=? and entrust_num!=deal_num order by date asc";
        Object params[] = {entrustPrice,entrustCoin,entrustType,state};
        List<EntrustDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            log.debug("鏍规嵁濮旀墭浠锋牸,璐у竵绫诲瀷锛屼氦鏄撶被鍨嬶紝濮旀墭鐘舵�佹煡鎵惧鎵樿褰曞け璐�");
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 鏍规嵁浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰�
     * @param entrustType
     * @param state
     * @return
     */
    public List<EntrustDto> queryEntrustByEntrustTypeAndState(Integer entrustType,Integer state){
        String sql="select * from t_inesv_entrust where entrust_type=? and state=? and entrust_num!=deal_num order by date asc";
        Object params[] = {entrustType,state};
        List<EntrustDto> list = null;
        try {
            list = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            log.debug("鏍规嵁浜ゆ槗绫诲瀷锛屽鎵樼姸鎬佹煡鎵惧鎵樿褰曞け璐�");
            e.printStackTrace();
        }
        return list;
    }

    public List<EntrustDto> queryEntrustInfoAll(){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
        String sql = "select * from t_inesv_entrust";
        try {
            entrustDtoList = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  entrustDtoList;
    }
    
    public List<EntrustDto> queryEntrustInfoAllByDay(){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
        String sql = "SELECT * FROM t_inesv_entrust WHERE TO_DAYS(DATE) = TO_DAYS(NOW())";
        try {
            entrustDtoList = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class));
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
        }
        return  entrustDtoList;
    }
    
    public List<EntrustDto> queryEntrustInfoAllPaging(Integer pageSize,Integer lineSize){
        List<EntrustDto> entrustDtoList = new ArrayList<>();
        String sql = "select * from t_inesv_entrust limit ?,?";
        Object params[] = {(pageSize * lineSize),lineSize};
        try {
            entrustDtoList = queryRunner.query(sql,new BeanListHandler<EntrustDto>(EntrustDto.class),params);
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
        }
        return  entrustDtoList;
    }
    
    /**
     * 鏍规嵁濮旀墭id 鏌ヨ濮旀墭璁板綍
     */
    public EntrustDto queryEntrustInfoByID(Long id){
        String querySql = "select * FROM t_inesv_entrust  WHERE id=? ";
        Object params[] = {id};
    	EntrustDto ddd=null;
		try {
			ddd = queryRunner.query(querySql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("鏍规嵁濮旀墭id鍜岀敤鎴风紪鍙锋煡璇㈠鎵樿褰曞け璐�");
		}
        return ddd;
    }
    
    public EntrustDto queryEntrustInfoByAttr1(Long attr1){
    	EntrustDto entrustDto = new EntrustDto();
    	String sql = "select * from t_inesv_entrust where attr1 = ?";
    	Object params[] = {attr1};
    	try {
    		entrustDto = queryRunner.query(sql,new BeanHandler<EntrustDto>(EntrustDto.class),params);
    	} catch (SQLException e) {
    		e.printStackTrace();
    		log.error("鏌ヨ鎵�鏈夊鎵樿褰曞け璐�");
    	}
    	return  entrustDto;
    }
    
    /**
     * 委托买单
     * */
    public List<ApiDepthDto> queryBuyEntrustByEntrustCoin(Integer entrust_coin) throws Exception{
    	String sql = "SELECt entrust_coin,entrust_price,count(*) AS entrust_count FROM t_inesv_entrust WHERE entrust_coin = ? and entrust_type = 0 GROUP BY entrust_price DESC";
    	Object param[] = {entrust_coin};
    	List<ApiDepthDto> list = new ArrayList<ApiDepthDto>();
			list = queryRunner.query(sql, new BeanListHandler<ApiDepthDto>(ApiDepthDto.class),param);
    	return list;
    }
    
    /*
     * 委托卖单
     */
    public List<ApiDepthDto> querySellEntrustByEntrustCoin(Integer entrust_coin) throws Exception{
    	String sql = "SELECt entrust_coin,entrust_price,count(*) AS entrust_count FROM t_inesv_entrust WHERE entrust_coin = ? and entrust_type = 1 GROUP BY entrust_price DESC";
    	Object param[] = {entrust_coin};
    	List<ApiDepthDto> list = new ArrayList<ApiDepthDto>();
			list = queryRunner.query(sql, new BeanListHandler<ApiDepthDto>(ApiDepthDto.class),param);
    	return list;
    }
}
