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
import android.widget.RelativeLayout;
import android.widget.TextView;

public class T_Shirts extends Activity {

    final String NAME = "NAME";
    final String SURNAME = "SURNAME";
    final String NUMBER = "NUMBER";
    final String ESTIMATION = "ESTIMATION";
    final String INFO_V = "INFO_V";

    Button send, count, info;
    RadioButton r100,r1000,r2000,r3000,r4000,r5000;
    CheckBox layout;
    TextView sum_out;
    RelativeLayout linearLayout;
    int sum;
    String textOrder;

    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_futbolka);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        r100 = (RadioButton)findViewById(R.id.radioButton);
        r1000 = (RadioButton)findViewById(R.id.radioButton1);
        r2000 = (RadioButton)findViewById(R.id.radioButton2);
        r3000 = (RadioButton)findViewById(R.id.radioButton3);
        r4000 = (RadioButton)findViewById(R.id.radioButton4);
        r5000 = (RadioButton)findViewById(R.id.radioButton5);
        linearLayout = (RelativeLayout)findViewById(R.id.layout);

        count =(Button)findViewById(R.id.button);
        send =(Button)findViewById(R.id.button2);
        info =(Button)findViewById(R.id.button3);

        layout = (CheckBox)findViewById(R.id.checkBox1);

        sum_out = (TextView)findViewById(R.id.sum_out);

        sp = getSharedPreferences("settings", MODE_PRIVATE);

        textOrder = "Заказ на печать футболки\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }

    public void Info6(View v){
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(INFO_V,6);
        editor.commit();
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        info.startAnimation(animation9);
        Intent intent = new Intent(T_Shirts.this,InformationalActivity.class);
        startActivity(intent);
    }

    public void FUT1(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        linearLayout.startAnimation(animation9);
        if(r2000.isChecked())linearLayout.setBackgroundResource(R.drawable.t_shirt_1);
        else linearLayout.setBackgroundResource(R.drawable.t_shirt_3);


        r1000.setChecked(false);
        Enablation();
    }
    public void FUT2(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        linearLayout.startAnimation(animation9);
        if(r2000.isChecked())linearLayout.setBackgroundResource(R.drawable.t_shirt_2);
        else linearLayout.setBackgroundResource(R.drawable.t_shirt_4);


        r100.setChecked(false);
        Enablation();
    }
    public void FUT3(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        linearLayout.startAnimation(animation9);
        if(r100.isChecked())linearLayout.setBackgroundResource(R.drawable.t_shirt_1);
        else linearLayout.setBackgroundResource(R.drawable.t_shirt_2);


        r3000.setChecked(false);
        Enablation();
    }
    public void FUT4(View v){
        Animation animation9 = AnimationUtils.loadAnimation(this, R.anim.but_ani);
        linearLayout.startAnimation(animation9);
        if(r100.isChecked())linearLayout.setBackgroundResource(R.drawable.t_shirt_3);
        else linearLayout.setBackgroundResource(R.drawable.t_shirt_4);

        r2000.setChecked(false);
        Enablation();
    }
    public void FUT5(View v){
        r5000.setChecked(false);
        Enablation();
    }
    public void FUT6(View v){
        r4000.setChecked(false);
        Enablation();
    }
    public void Count(View v){
        count();
    }

    public void count(){
        sum = 0;

        boolean colorW,size;
        if(r100.isChecked())colorW = true;
        else colorW = false;
        if(r4000.isChecked())size = true;
        else size = false;

        if(layout.isChecked()&&((r100.isChecked()||r1000.isChecked())&&(r2000.isChecked()||r3000.isChecked())&&(r4000.isChecked()||r5000.isChecked()))) sum +=50;

        if(colorW&&size)sum +=270;
        else if((colorW&&!size))sum+=350;

        if(!colorW&&size)sum +=350;
        else if (!colorW&&!size) sum+=470;

        sum_out.setText("Сумма: "+sum+" грн.");
    }

    public void Enablation(){
        if((r100.isChecked()||r1000.isChecked())&&(r4000.isChecked()||r5000.isChecked())&&(r2000.isChecked()||r3000.isChecked())) {
            count.setEnabled(true);
            send.setEnabled(true);
            count.setTextColor(Color.BLACK);
            send.setTextColor(Color.BLACK);
        }
    }

    public void SendOrder(View v){

        count();

        if(layout.isChecked()) textOrder+="\n+ МАКЕТ";

        if(r100.isChecked())textOrder+="\nБелая футболка";
        else textOrder+="\nЧерная футболка" ;

        if(r2000.isChecked())textOrder+="\nМужская";
        else textOrder+="\nЖенская";

        if(r4000.isChecked())textOrder+="\nС принтом размера А4";
        else textOrder+="\nС принтом размера А3";

        sum_out.setText("Сумма: "+sum+" грн.");


        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        emailIntent.setType("plain/text");

        // Кому
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,  new String[]{"xxmaxdrivezz@gmail.com"});
        // Зачем
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"ЗАКАЗ НА ПЕЧАТЬ");
        // О чём
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,textOrder+"\nСумма: "+sum+" грн.\n\nEstimation "+((float)sp.getInt(ESTIMATION,0))/2+"\n INSPIRING PRINTS");


        T_Shirts.this.startActivity(Intent.createChooser(emailIntent,
                "Отправка заказа..."));

        textOrder = "Заказ на печать футболки\n\n"+sp.getString(NAME, "")+" "+sp.getString(SURNAME, "")+"\n"+"TEL: "+sp.getString(NUMBER, "");
    }
}
