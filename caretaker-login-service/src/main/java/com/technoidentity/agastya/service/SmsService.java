package com.technoidentity.agastya.service;

import java.text.ParseException;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsService {

	private final String ACCOUNT_SID = "AC1272336dfaf920cf19319a0b06280b7f";

	private final String AUTH_TOKEN = "bf8dd1a35a4519d5394570793d06813f";

	private final String FROM_NUMBER = "+14043417638";

	public void send(String phoneNo, String msg) throws ParseException {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber(phoneNo), new PhoneNumber(FROM_NUMBER), msg).create();

		/*
		 * Date myDate=new Date(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date = sdf.
		 * parse(myDate.toString()); long millis = date. getTime();
		 * System.out.println(millis); OTPpojo.setOtp(number);
		 * System.out.println("here is my id:"+message.getSid());// Unique resource ID
		 * created to manage this transaction
		 */
	}

	public void receive(MultiValueMap<String, String> smscallback) {

	 }

}
