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

public class Holst extends Activity {

    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";

    Button send, count, info;
    RadioButton r100,r1000,r2000,r3000,r1,r2;
    CheckBox layout;
    TextView sum_out;
    int sum;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holst);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        r100 = (RadioButton)findViewById(R.id.radioButton);
        r1000 = (RadioButton)findViewById(R.id.radioButton1);
        r2000 = (RadioButton)findViewById(R.id.radioButton2);
        r3000 = (RadioButton)findViewById(R.id.radioButton3);

        r1 = (RadioButton)findViewById(R.id.radioButton4);
        r2 = (RadioButton)findViewById(R.id.radioButton5);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info =(Button)findViewById(R.id.button3);

        layout = (CheckBox)findViewById(R.id.checkBox1);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на печать холстов\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info7(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,7);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(Holst.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void RH(View v){
        r1000.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void RH1(View v){
        r100.setChecked(false);
        r2000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void RH2(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r3000.setChecked(false);
        Enablation();
    }
    public void RH3(View v){
        r100.setChecked(false);
        r1000.setChecked(false);
        r2000.setChecked(false);
        Enablation();
    }
    public void RH4(View v){
        r2.setChecked(false);
        Enablation();
    }
    public void RH5(View v){
        r1.setChecked(false);
        Enablation();
    }

    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;
        if(r1.isChecked()){
            if(layout.isChecked()) sum +=50;
            if(r100.isChecked())sum +=200;
            else if(r1000.isChecked())sum +=260;
            else if(r2000.isChecked())sum +=310;
            else if(r3000.isChecked())sum +=420;
            sum_out.setText("Сумма: "+sum+" грн.");
        }
        else {
            if(layout.isChecked()) sum +=50;
            if(r100.isChecked())sum +=250;
            else if(r1000.isChecked())sum +=310;
            else if(r2000.isChecked())sum +=380;
            else if(r3000.isChecked())sum +=490;
            sum_out.setText("Сумма: "+sum+" грн.");
        }

    }

    public void Enablation(){
        if((r100.isChecked()||r1000.isChecked()||r2000.isChecked()||r3000.isChecked())&&(r1.isChecked()||r2.isChecked())){
            count.setEnabled(true);
            send.setEnabled(true);
            count.setTextColor(Color.BLACK);
            send.setTextColor(Color.BLACK);

        }
    }

    public void SendOrder(View v){

        count();

        if(layout.isChecked()) textOrder+="\n+ МАКЕТ";
        if(r1.isChecked())textOrder+="\nНатяжка стандартная";
        else if(r2.isChecked())textOrder+="\nНатяжка галерейная";
        if(r100.isChecked())textOrder+="\n20х30 cm";
        else if(r1000.isChecked())textOrder+="\n30х40 cm" ;
        else if(r2000.isChecked())textOrder+="\n40х50 cm";
        else if(r3000.isChecked())textOrder+="\n50x70";
        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        Holst.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на печать холстов\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
