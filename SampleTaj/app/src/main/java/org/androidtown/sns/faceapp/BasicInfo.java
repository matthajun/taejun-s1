package org.androidtown.sns.faceapp;

import java.text.SimpleDateFormat;

import android.graphics.Bitmap;

import com.facebook.android.Facebook;

public class BasicInfo {

	public static final int REQ_CODE_FACEBOOK_LOGIN = 1001;

	public static boolean FacebookLogin = false;

	public static Facebook FacebookInstance = null;

	public static String[] FACEBOOK_PERMISSIONS = {"user_about_me", "publish_actions", "read_insights", "read_page_mailboxes", "public_profile",
			"user_friends", "user_photos", "email", "manage_pages", "publish_pages", "user_posts", "user_status", "user_website"};

	public static String FACEBOOK_ACCESS_TOKEN = "";
	public static String FACEBOOK_APP_ID = "470149279837209";
	public static String FACEBOOK_API_KEY = "224a8cee1a78a9fbb040bdad7153674a";
	public static String FACEBOOK_APP_SECRET = "9698ddca19e93c83a69fea6bed2a40e2";

	public static String FACEBOOK_NAME = "";

	public static String language = "";



	//========== 액티비티 요청 코드  ==========//
	public static final int REQ_NAVI_DISPLAY_ACTIVITY = 1001;



	public static String base_url = "http://map.naver.com:80/";
}
