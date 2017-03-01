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
import android.widget.Toast;

public class FotoDocuments extends Activity {


    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";

    Button send, count, info;
    RadioButton r100,r1000,r2000,r3000,r4000;
    CheckBox layout;
    TextView sum_out;
    int sum;
    final int x = 15;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_documents);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        r100 = (RadioButton)findViewById(R.id.radioButton);
        r1000 = (RadioButton)findViewById(R.id.radioButton1);
        r2000 = (RadioButton)findViewById(R.id.radioButton2);
        r3000 = (RadioButton)findViewById(R.id.radioButton3);
        r4000 = (RadioButton)findViewById(R.id.radioButton4);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info =(Button)findViewById(R.id.button3);

        layout = (CheckBox)findViewById(R.id.checkBox1);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на печать фото на документы\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info10(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,10);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(FotoDocuments.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void RFD1(View v){
        r1000.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        r4000.setChecked(false);
        Enablation();
    }
    public void RFD2(View v){
        r100.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        r4000.setChecked(false);
        Enablation();
    }
    public void RFD3(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r3000.setChecked(false);
        r4000.setChecked(false);
        Enablation();
    }
    public void RFD4(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r2000.setChecked(false);
        r4000.setChecked(false);
        Enablation();
    }
    public void RFD5(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;

        if(layout.isChecked()) sum +=15;
        if(r100.isChecked())sum +=x;
        else if(r1000.isChecked())sum +=x;
        else if(r2000.isChecked())sum +=x;
        else if(r3000.isChecked())sum +=x;
        else if(r4000.isChecked())sum +=x;
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

        Toast.makeText(this, "Не забудте прикрепить фото :)", Toast.LENGTH_LONG).show();

        if(layout.isChecked()) textOrder+="\n+ Обработка фотографии";
        if(r100.isChecked())textOrder+="\n3х4 cm - 6шт.";
        else if(r1000.isChecked())textOrder+="\n3,5х4,5 cm - 4шт." ;
        else if(r2000.isChecked())textOrder+="\n4х5 cm - 4 шт.";
        else if(r3000.isChecked())textOrder+="\n5х5 cm - 2шт.";
        else if(r4000.isChecked())textOrder+="\n9х12 cm - 1шт,";
        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        FotoDocuments.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на печать фото на документы\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
