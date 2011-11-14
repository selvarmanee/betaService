package com.langda.ws;

import java.io.IOException;

import twitter4j.TwitterException;

import com.langda.util.VerifyTwitterCredentials;

public class twitterOAuth {
	public static void main(String[] args) {
		
//	    Properties prop = new Properties();
//	    
		String consumerKey = "lMuuwuhqK0eRcqwhSySA";
		String consumerSecret = "fGqw1GKapQW6dtPGrz6zXuzbI4QtozRTnnYYORlI";
//	    prop.setProperty("oauth.consumerKey", consumerKey);
//        prop.setProperty("oauth.consumerSecret", consumerSecret);
        
		try {
		
		VerifyTwitterCredentials verifyTwitterCred = new VerifyTwitterCredentials(consumerKey, consumerSecret);
		verifyTwitterCred.setPin();
		verifyTwitterCred.setAccessToken();
		verifyTwitterCred.getTimeline();
		
    	
            

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get accessToken: " + te.getMessage());
            System.exit(-1);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Failed to read the system input.");
            System.exit(-1);
        }
	}
}
