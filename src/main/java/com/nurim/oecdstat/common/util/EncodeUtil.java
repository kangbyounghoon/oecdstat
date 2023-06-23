/*
 * @(#)BoardUtil.java    2004. 11. 4.
 *
 * Copyright (c) 2004 Nurimsoft co., KOREA
 * All rights reserved.
 *
 * This software is the confidential
 * and proprietary information of Nurimsoft co. ("Confidential Information").
 * You shall not disclose such Confidential Information
 * and shall use it only in accordance with
 * the terms of the license agreement you entered into
 * with Nurimsoft co.
 *
 * Created on 2004. 11. 4.
 */
package com.nurim.oecdstat.common.util;
import java.io.IOException;
import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 1.DES, TripleDES 암호화, 복호화
 * 2.생성될 키는 암호화할때와 복호화시 같은 키를 사용해야한다.
 * 3.화면에 출력할 수 없는 문자 표시를 위해 BASE64 사용
 */
public class EncodeUtil {

    public EncodeUtil() {
    }
    
    /**
     * 16byte : DES
     * 24byte : TripleDES
     * 
     * 문자열이 해당 byte의 크기만 되면 어떤 내용이든 상관 없음.
     * @return String 키값 
     */
    private static String key() {
    	return "ab_booktv_abcd09";   //DES  
    }
    
    /**
     * 24바이트인 경우 TripleDES 아니면 DES
     * @return Key
     * @throws Exception 
     */
    private static Key getKey() throws Exception {
     
     return (key().length() == 24) ? getKey2(key()) : getKey1(key());     
    }
    
    /**
     * Cipher의 instance 생성시 사용될 값
     * @return String DES, TripleDES 구분
     * @throws Exception 
     */
    private static String getInstance() throws Exception {
     
     return (key().length() == 24) ? "DESede/ECB/PKCS5Padding" : "DES/ECB/PKCS5Padding";
    }
    
    /**
     * 지정된 비밀키를 가지고 오는 메서드(DES) 
     * require Key Size : 16bytes
     * 
     * @param keyValue
     * @return Key
     * @throws Exception 
     */
    private static Key getKey1(String keyValue) throws Exception {
     
     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
     DESKeySpec desKeySpec = new DESKeySpec( keyValue.getBytes() );
     
     return keyFactory.generateSecret( desKeySpec );
    }
    
    /**
     * 지정된 비밀키를 가지고 오는 메서드(TripleDES) 
     * require Key Size : 24bytes
     * 
     * @param keyValue
     * @return Key
     * @throws Exception 
     */
    private static Key getKey2(String keyValue) throws Exception {
     
     SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
     DESKeySpec desKeySpec = new DESKeySpec( keyValue.getBytes() );
     
     return keyFactory.generateSecret( desKeySpec );
    }
    
    /**
     * 문자열 대칭 암호화
     * return String 암호화된 문자열
     */
    public static String encrypt(String input) throws Exception {
     
     if(input == null || input.length() == 0) return "";
     
     Cipher cipher = Cipher.getInstance( getInstance() );
        cipher.init( Cipher.ENCRYPT_MODE, getKey() );
        
        byte [] inputBytes = input.getBytes("UTF8");
        byte [] outputBytes = cipher.doFinal( inputBytes );


        return new BASE64Encoder().encode( outputBytes );
    }
    
    /**
     * 문자열 대칭 복호화
     * return String 복호화된 문자열 
     */
    public static String decrypt(String input) throws Exception {
        
     if(input == null || input.length() == 0) return "";
     
     Cipher cipher = Cipher.getInstance( getInstance() );
        cipher.init( Cipher.DECRYPT_MODE, getKey() );
     
     byte [] inputBytes = new BASE64Decoder().decodeBuffer( input );
     byte [] outputBytes = cipher.doFinal( inputBytes );
        
        return new String( outputBytes, "UTF8" );
    }    
    
    // ~ Static fields/initializers
 	// =============================================

 	private final static Log log = LogFactory.getLog(EncodeUtil.class);

 	// ~ Methods
 	// ================================================================

 	/**
 	 * Encode a string using algorithm specified in web.xml and return the
 	 * resulting encrypted password. If exception, the plain credentials string
 	 * is returned
 	 * 
 	 * @param password
 	 *            Password or other credentials to use in authenticating this
 	 *            username
 	 * @param algorithm
 	 *            Algorithm used to do the digest
 	 * 
 	 * @return encypted password based on the algorithm.
 	 */
 	public static String encodePassword(String password, String algorithm) {
 		byte[] unencodedPassword = password.getBytes();

 		MessageDigest md = null;

 		try {
 			// first create an instance, given the provider
 			md = MessageDigest.getInstance(algorithm);
 		} catch (Exception e) {
 			log.error("(encodePassword) Exception: " + e);
 			
 			return password;
 		}

 		md.reset();

 		// call the update method one or more times
 		// (useful when you don't know the size of your data, eg. stream)
 		md.update(unencodedPassword);

 		// now calculate the hash
 		byte[] encodedPassword = md.digest();

 		StringBuffer buf = new StringBuffer();

 		for (byte anEncodedPassword : encodedPassword) {
 			if ((anEncodedPassword & 0xff) < 0x10) {
 				buf.append("0");
 			}

 			buf.append(Long.toString(anEncodedPassword & 0xff, 16));
 		}

 		return buf.toString();
 	}

 	/**
 	 * Encode a string using Base64 encoding. Used when storing passwords as
 	 * cookies.
 	 * 
 	 * This is weak encoding in that anyone can use the decodeString routine to
 	 * reverse the encoding.
 	 * 
 	 * @param str
 	 * @return String
 	 */
 	public static String encodeString(String str) {
 		BASE64Encoder base64Encoder = new BASE64Encoder();
 		return base64Encoder.encode(str.getBytes());
 	}

 	/**
 	 * Decode a string using Base64 encoding.
 	 * 
 	 * @param str
 	 * @return String
 	 */
 	public static String decodeString(String str) {
 		BASE64Decoder base64Decoder = new BASE64Decoder();

 		try {
 			if(str == null || "".equals(str)) return str;
 			else return new String(base64Decoder.decodeBuffer(str));
 		} catch (IOException de) {
 			throw new RuntimeException(de.getMessage(), de.getCause());
 		}
 	}
 
}
