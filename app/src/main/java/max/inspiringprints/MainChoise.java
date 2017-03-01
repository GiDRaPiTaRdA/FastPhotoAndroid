package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainChoise extends Activity {

    Button b,b1,b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choise);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        b = (Button)findViewById(R.id.imageButton);
        b1 = (Button)findViewById(R.id.imageButton2);
        b2 = (Button)findViewById(R.id.imageButton3);
    }
    public void PoliGraf(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b.startAnimation(animation9);
        Intent intent = new Intent(MainChoise.this,PoliGraf.class);
        startActivity(intent);
    }
    public void Suveniru(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b1.startAnimation(animation9);
        Intent intent = new Intent(MainChoise.this,Suveniru.class);
        startActivity(intent);
    }
    public void Copy(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b2.startAnimation(animation9);
        Intent intent = new Intent(MainChoise.this,Fotodruk.class);
        startActivity(intent);
    }
}
