package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Fotodruk extends Activity {

    ImageButton b,b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotodruk);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        b = (ImageButton)findViewById(R.id.imageButton);
        b1 = (ImageButton)findViewById(R.id.imageButton2);
    }
    public void Print(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b.startAnimation(animation9);
        Intent intent =  new Intent(Fotodruk.this, PrintFoto.class);
        startActivity(intent);

    }
    public void FotoDoc(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        b1.startAnimation(animation9);
        Intent intent =  new Intent(Fotodruk.this, FotoDocuments.class);
        startActivity(intent);
    }

}
