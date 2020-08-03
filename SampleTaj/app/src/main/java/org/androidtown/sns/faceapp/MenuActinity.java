package org.androidtown.sns.faceapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.messenger.MessengerUtils;
import com.facebook.messenger.MessengerThreadParams;
import com.facebook.messenger.ShareToMessengerParams;

public class MenuActinity extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onButton1Clicked(View v) {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/taxitaj/?ref=hl"));
        startActivity(myIntent);
    }

    public void onButton2Clicked(View v) {
        Intent intent = new Intent(getApplicationContext(), QuickNaviActivity.class);
        startActivityForResult(intent, 1001);
    }

   /* public void onButton2Clicked(View v) {
        String mimeType = "image/jpeg";
        String facebookUrl = "https://www.facebook.com/";
        Uri contenturi = Uri.parse("fb://facewebmodal/f?href=" + facebookUrl);

       ShareToMessengerParams shareToMessengerParams =
               ShareToMessengerParams.newBuilder(contenturi, mimeType).build();

        MessengerUtils.shareToMessenger(
               this, 30, shareToMessengerParams);

    }*/

}
