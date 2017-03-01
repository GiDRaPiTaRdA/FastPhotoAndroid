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

public class Cups extends Activity {

    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";

    Button send, count, info;
    RadioButton r100,r1000,r2000;
    CheckBox layout;
    TextView sum_out;
    int sum;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chashka);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        this.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        r100 = (RadioButton)findViewById(R.id.radioButton);
        r1000 = (RadioButton)findViewById(R.id.radioButton1);
        r2000 = (RadioButton)findViewById(R.id.radioButton2);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info = (Button)findViewById(R.id.button3);

        layout = (CheckBox)findViewById(R.id.checkBox1);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на печать на чашке\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info2(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,2);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(Cups.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void CU1(View v){
        r1000.setChecked(false);
        r2000.setChecked(false);
        Enablation();
    }
    public void CU2(View v){
        r100.setChecked(false);
        r2000.setChecked(false);
        Enablation();
    }
    public void CU3(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        Enablation();
    }
//    public void CU4(View v){
//        r100.setChecked(false);
//        r1000.setChecked(false);
//        r2000.setChecked(false);
//        Enablation();
//    }
    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;

        if(layout.isChecked()&&(r100.isChecked()||r1000.isChecked()||r2000.isChecked())) sum +=50;
        if(r100.isChecked())sum +=90;
        else if(r1000.isChecked())sum +=120;
        else if(r2000.isChecked())sum +=250;
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
        if(r100.isChecked())textOrder+="\nБелая чашка";
        else if(r1000.isChecked())textOrder+="\nЦветная чашкка" ;
        else if(r2000.isChecked())textOrder+="\nЧашка хамелеон";
        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        Cups.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на печать на чашке\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
