package com.atb.grpc.ninja.srv;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.atb.grpc.ninja.dao.BlockDataDao;


@Component
public class EncriptSrv{
	
	@Autowired
	private BlockDataDao dao;
	
	public String generateHash(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException{		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
		byte[] hash = digest.digest(data.getBytes("UTF-8"));	        
		StringBuffer hexString = new StringBuffer(); 
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
	
	public String dummyLinkHashDecode() {
		return dao.getLinkHash();
	}

}
