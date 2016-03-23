package com.ziyidai.www.myretrofitdemo;
/**
 * 
 * @author liaopenghui
 *
 */
public class RegisterVerifyCode {
	
	public Data data;
	
	public class Data{
	public String code;  //返回码
	public String message;  //返回描述信息
	public String mobileVerifyCode;//服务器返回得到的验证码
	}
}
