package max.inspiringprints;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

public class Posters extends Activity {


    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";
    
    Button send, count, info;
    RadioButton r100,r1000,r2000,r3000;
    CheckBox layout;
    TextView sum_out;
    int sum;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plakatu);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        r100 = (RadioButton)findViewById(R.id.radioButton);
        r1000 = (RadioButton)findViewById(R.id.radioButton1);
        r2000 = (RadioButton)findViewById(R.id.radioButton2);
        r3000 = (RadioButton)findViewById(R.id.radioButton3);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info =(Button)findViewById(R.id.button3);

        layout = (CheckBox)findViewById(R.id.checkBox1);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на печать плакатов\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info4(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,4);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(Posters.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void P1(View v){
        r1000.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void P2(View v){
        r100.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void P3(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void P4(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r2000.setChecked(false);
        Enablation();
    }
    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;

        if(layout.isChecked()&&(r100.isChecked()||r1000.isChecked()||r2000.isChecked()||r3000.isChecked())) sum +=100;
        if(r100.isChecked())sum +=75;
        else if(r1000.isChecked())sum +=100;
        else if(r2000.isChecked())sum +=150;
        else if(r3000.isChecked())sum +=200;
        sum_out.setText("Сумма: "+sum+" грн.");
    }

    public void Enablation(){
        count.setEnabled(true);
        send.setEnabled(true);
        count.setTextColor(Color.BLACK);
        send.setTextColor(Color.BLACK);
    }

    public void SendOrder(View v){

        count();

        if(layout.isChecked()) textOrder+="\n+ МАКЕТ";
        if(r100.isChecked())textOrder+="\nA2 (420x594mm)";
        else if(r1000.isChecked())textOrder+="\nA1 (594 x 841mm)" ;
        else if(r2000.isChecked())textOrder+="\nA0 (841 x 1189mm)";
        else if(r3000.isChecked())textOrder+="\nСитилайт (1800 x 1200mm)";
        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        Posters.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на печать плакатов\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
