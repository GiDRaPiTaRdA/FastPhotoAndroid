package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Contacts extends Activity {

    TextView text;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        text = (TextView)findViewById(R.id.textView);
        layout = (RelativeLayout)findViewById(R.id.lay);
        text.setMovementMethod(new ScrollingMovementMethod());

    }

    public void About(View v) {

        Intent intent = new Intent(Contacts.this, About.class);
        startActivity(intent);
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        layout.startAnimation(animation9);
    }
}
