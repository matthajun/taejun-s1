package org.androidtown.sns.faceapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.AdapterView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
//import com.facebook.android.Util;


import org.json.JSONObject;

public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    ImageView imege;
    Button connectBtn;
    ImageView imegeBtn;

    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imege = (ImageView) findViewById(R.id.imageView3);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        imege.setAnimation(anim);

       // 연결 버튼 이벤트 처리
        connectBtn = (Button) findViewById(R.id.connectBtn);
        imegeBtn = (ImageView) findViewById(R.id.imageView2);

        connectBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                connect();
            }
        });
    }
    /**
     * 연결하기
     */
    private void connect() {
        try {
            Facebook mFacebook = new Facebook(BasicInfo.FACEBOOK_APP_ID);
            BasicInfo.FacebookInstance = mFacebook;

            mFacebook.authorize(this, BasicInfo.FACEBOOK_PERMISSIONS, new AuthorizationListener());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private  void connect_screen() {
            Intent intent = new Intent(getApplicationContext(), MenuActinity.class);
            startActivityForResult(intent, 1001);
    }

    /**
     * 메뉴 화면 보여주기
     */
    private void showMenu() {
        Log.d(TAG, "showMenu() called.");

        connectBtn.setVisibility(View.GONE);
        imegeBtn.setVisibility(View.GONE);

        connect_screen();
    }

    public void Exit(View v) {
        finish();
    }


    /**
     * 다른 액티비티로부터의 응답 처리
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        super.onActivityResult(requestCode, resultCode, resultIntent);

        if (resultCode == RESULT_OK) {
            if (requestCode == 32665) {
                BasicInfo.FacebookInstance.authorizeCallback(requestCode, resultCode, resultIntent);
            }
        }
    }

    protected void onPause() {
        super.onPause();

        saveProperties();
    }

    protected void onResume() {
        super.onResume();

        loadProperties();

    }

    private void saveProperties() {
        SharedPreferences pref = getSharedPreferences("FACEBOOK", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("FacebookLogin", BasicInfo.FacebookLogin);
        editor.putString("FACEBOOK_ACCESS_TOKEN", BasicInfo.FACEBOOK_ACCESS_TOKEN);
        editor.putString("FACEBOOK_NAME", BasicInfo.FACEBOOK_NAME);

        editor.commit();
    }

    private void loadProperties() {
        SharedPreferences pref = getSharedPreferences("FACEBOOK", MODE_PRIVATE);

        BasicInfo.FacebookLogin = pref.getBoolean("FacebookLogin", false);
        BasicInfo.FACEBOOK_ACCESS_TOKEN = pref.getString("FACEBOOK_ACCESS_TOKEN", "");
        BasicInfo.FACEBOOK_NAME = pref.getString("FACEBOOK_NAME", "");

    }

    /**
     * 로그인을 위한 리스너
     */
    public class AuthorizationListener implements Facebook.DialogListener {

        public void onCancel() {

        }

        public void onComplete(Bundle values) {
            try {
                String resultStr = BasicInfo.FacebookInstance.request("me");
                JSONObject obj = new JSONObject(resultStr);

                BasicInfo.FACEBOOK_NAME = obj.getString("name");

                BasicInfo.FacebookLogin = true;
                BasicInfo.FACEBOOK_ACCESS_TOKEN = BasicInfo.FacebookInstance.getAccessToken();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Log.d(TAG, "Authorization completed : " + BasicInfo.FACEBOOK_NAME);

            showMenu();

        }

        public void onError(DialogError e) {

        }

        public void onFacebookError(FacebookError e) {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
