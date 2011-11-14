package com.langda.util;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class VerifyTwitterCredentials {
	private String consumerKey;
	private String consumerSecret;
	private String pin;
	
	private Twitter twitter;
	private RequestToken requestToken;
	private AccessToken accessToken;
	
	
	public VerifyTwitterCredentials(String consumerKey, String consumerSecret)  throws TwitterException {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
		setRequestToken();
	}
		
	private void setRequestToken() throws TwitterException {
		twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(this.consumerKey,this.consumerSecret);
        this.requestToken = twitter.getOAuthRequestToken();
	}

	public void setPin() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Open the following URL and grant access to your account:");
        System.out.println(requestToken.getAuthorizationURL());
        try {
            Desktop.getDesktop().browse(new URI(requestToken.getAuthorizationURL()));
            System.out.println(requestToken.getAuthenticationURL());
            System.out.println(requestToken.getAuthorizationURL());
        } catch (IOException ignore) {
        } catch (URISyntaxException e) {
            throw new AssertionError(e);
        }
        System.out.print("Enter the PIN(if available) and hit enter after you granted access.[PIN]:");
        pin = br.readLine();
	}
	
	public void setAccessToken() {
		try {
//            if (pin.length() > 0) {
//                accessToken = twitter.getOAuthAccessToken(requestToken, pin);
//            } else {
                accessToken = twitter.getOAuthAccessToken(requestToken);
//            }
        } catch (TwitterException te) {
            if (401 == te.getStatusCode()) {
                System.out.println("Unable to get the access token.");
            } else {
                te.printStackTrace();
            }
        }
	}
	
	public void getTimeline() throws TwitterException {
		User user = twitter.verifyCredentials();
		List<Status> statii = twitter.getHomeTimeline();
		for(Status status:statii) {
			System.out.println("@"+status.getUser().getScreenName() + "-" + status.getText());
		}
	}
	
}
