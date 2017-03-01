package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class Registration extends Activity {

    EditText name,number,surname;
    TextView estimationT;
    SharedPreferences sp;
    RatingBar estimation;
    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        name = (EditText)findViewById(R.id.name);
        number = (EditText)findViewById(R.id.number);
        surname = (EditText)findViewById(R.id.surname);
        estimation = (RatingBar)findViewById(R.id.ratingBar);
        estimationT = (TextView)findViewById(R.id.textView);

        sp = getSharedPreferences("settings",MODE_PRIVATE);

        name.setText(sp.getString(NAME,""));
        surname.setText(sp.getString(SURNAME,""));
        number.setText(sp.getString(NUMBER,""));
        estimation.setProgress(sp.getInt(ESTIMATION,0));

        estViev();

        findViewById(R.id.ratingBar).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_UP:
                        estViev();
                        break;
                }
                return false;
            }
        });
    }

        public void Inf(View v){
            Intent intent = new Intent(Registration.this,Contacts.class);
            startActivity(intent);
        }

        public void Log_in(View v){

            if(!name.getText().toString().equals("")&& !surname.getText().toString().equals("")&& !number.getText().toString().equals("")){
                SharedPreferences.Editor editor = sp.edit();
                editor.putString(NAME, name.getText().toString());
                editor.putString(SURNAME, surname.getText().toString());
                editor.putString(NUMBER, number.getText().toString());
                editor.putInt(ESTIMATION,estimation.getProgress());
                editor.commit();
                Intent intent = new Intent(Registration.this,MainChoise.class);
                startActivity(intent);
            }
            else Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
        }
    private   void estViev(){
        float q = estimation.getProgress();
        q /=2;
        if(estimation.getProgress()!=0)estimationT.setText("Оценка: "+String.valueOf(q));
        else estimationT.setText("Оцените нас");
    }
}
