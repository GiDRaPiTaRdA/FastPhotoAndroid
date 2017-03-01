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

public class Copy extends Activity {

    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";

    Button send, count, info;
    CheckBox lamination;
    RadioButton service;
    TextView sum_out,quantity;
    int sum, q = 1;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info =(Button)findViewById(R.id.button3);

        quantity = (TextView)findViewById(R.id.quant);

        lamination = (CheckBox)findViewById(R.id.checkBox);
        service = (RadioButton)findViewById(R.id.radioButton);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на Copy - услуги\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info8(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,8);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(Copy.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void Plus(View v){if(q<10) q++;quantity.setText(String.valueOf(q));}
    public void Minus(View v){if(q>1) q--;quantity.setText(String.valueOf(q));}

    public void Enablation(View v){
        Enablation();
    }
    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;
        if(lamination.isChecked())  sum = sum +  Integer.valueOf(quantity.getText().toString())* 2;
        else  sum = sum +  Integer.valueOf(quantity.getText().toString())* 1;
        if(service.isChecked()) sum +=5;
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

        if(service.isChecked()) textOrder+="\nПечать на А-4";
        if(lamination.isChecked()) textOrder+="\n+ Ламинация";
        textOrder+="\nКоличество копий: " + q + " шт.";
        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        Copy.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на Copy - услуги\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
