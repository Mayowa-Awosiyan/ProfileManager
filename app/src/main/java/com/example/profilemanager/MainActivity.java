package com.example.profilemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnOpenInGoogleMaps (View view) {
        EditText teamAddres = (EditText) findViewById(R.id.editLocation);
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("http://maps.google.co.in/maps?q="+teamAddres.getText());
        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");
        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }

    public void OnSetAvatarButton(View view) {
        //Application Context and Activity
        //TODO ADD Second Activity
        Intent intent = new Intent(getApplicationContext(), ImagesList.class);
        startActivityForResult (intent,0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) return;
        //Getting the Avatar Image we show to our users
        ImageView avatarImage = (ImageView) findViewById(R.id.imageProfile);
        //Figuring out the correct image
        String drawableName = "ic_logo_00";
        switch (data.getIntExtra("imageID",R.id.gridImages)) {
            case R.id.imageFirst:
                drawableName = "bruins";
                break;
            case R.id.imageSecond:
                drawableName = "miami";
                break;
            case R.id.imageThird:
                drawableName = "celtics";
                break;
            case R.id.imageFourth:
                drawableName = "bull";
                break;
            case R.id.imageFifth:
                drawableName = "bucks";
                break;
            case R.id.imageSixth:
                drawableName = "laker";
                break;
            default:
                drawableName = "laker";
                break;
        }
        int resID = getResources().getIdentifier(drawableName, "drawable",
                getPackageName());
        avatarImage.setImageResource(resID);
    }
}