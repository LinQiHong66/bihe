package com.inesv.digiccy.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.inesv.digiccy.common.ResponseCode;
import com.inesv.digiccy.util.FourTest;
import com.inesv.digiccy.util.QiniuUploadUtil;
import com.inesv.digiccy.validata.UserVoucherValidate;
import com.inesv.digiccy.validata.user.InesvUserValidata;

/**
 * 实名认证接口
 * 
 * @author Liukeling
 *
 */
@RequestMapping("voucher")
@Controller
public class UserVoucherController {

	@Autowired
	UserVoucherValidate userVoucherValidate;
	@Autowired
	InesvUserValidata inesvUserValidata;
	
//	@RequestMapping(value = "verifyuser", method = RequestMethod.POST)
//	@ResponseBody
//	public Map verifyUser(MultipartFile files, int voucherType, String realName, String voucherId, int userNo) {
//		String imgUrl1 = "";
//		String imgUrl2 = "";
//		String imgUrl3 = "";
//		System.out.println(files);
//			if (files != null && !files.isEmpty()) {
//				String url = saveMultipartFile(files);
//				if (url != null && !"".equals(url)) {
//					if ("".equals(imgUrl1) || imgUrl1 == null) {
//						imgUrl1 = url;
//					} else if ("".equals(imgUrl2) || imgUrl2 == null) {
//						imgUrl2 = url;
//					} else if ("".equals(imgUrl3) || imgUrl3 == null) {
//						imgUrl3 = url;
//					}
//				}
//			}
//
//		System.out.println(voucherType+"----"+realName+"----"+voucherId+"---"+userNo+"=---"+imgUrl1+"----"+imgUrl2+"----"+imgUrl3);
//		return userVoucherValidate.startVoucher(voucherId, voucherType, imgUrl1, imgUrl2, imgUrl3, userNo, realName,
//				"");
//	}
	@RequestMapping(method=RequestMethod.POST, value="fourVoucher")
	@ResponseBody
	//四要素验证
	public Map<String, Object> verifyUserForBank(String bankCard, String idCard, String mobile, String realName, int userNo){
		try {
			realName = new String(realName.getBytes("iso-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inesvUserValidata.FourVoucher(bankCard, idCard, mobile, realName, userNo);
	}
	// 保存图片的方法
//	public String saveMultipartFile(MultipartFile file) {
//		String originalName = file.getOriginalFilename();
//		String imgUrl = "";
//		try {
//			String startS = QiniuUploadUtil.getStartStaff();
//			imgUrl = startS + QiniuUploadUtil.upLoadImage(file.getInputStream(), originalName);
//			if(startS.equals(imgUrl)){
//				imgUrl = "";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return imgUrl;
//	}
}
