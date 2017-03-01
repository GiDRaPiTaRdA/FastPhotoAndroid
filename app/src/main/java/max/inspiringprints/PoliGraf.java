package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class PoliGraf extends Activity {

    ImageButton b, b2, b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poli_graf);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        b = (ImageButton) findViewById(R.id.imageButton2);
        b2 = (ImageButton) findViewById(R.id.imageButton4);
        b4 = (ImageButton) findViewById(R.id.imageButton);
        b5 = (ImageButton) findViewById(R.id.imageButton6);
    }

    public void Vizitki(View v) {
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b.startAnimation(animation9);
        Intent intent = new Intent(PoliGraf.this, Business_cards.class);
        startActivity(intent);
    }

    public void Flaeru(View v) {
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b2.startAnimation(animation9);
        Intent intent = new Intent(PoliGraf.this, Flyers.class);
        startActivity(intent);
    }

    public void Plakatu(View v) {
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b4.startAnimation(animation9);
        Intent intent = new Intent(PoliGraf.this, Posters.class);
        startActivity(intent);
    }
    public void CopyPrint(View v) {
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b5.startAnimation(animation9);
        Intent intent = new Intent(PoliGraf.this, Copy.class);
        startActivity(intent);
    }
}
