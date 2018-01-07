package com.prosoft.controledeestoque;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView im1, im2;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im1 = (ImageView) findViewById(R.id.im_one);
        im2 = findViewById(R.id.im_two);
        t = findViewById(R.id.txt);

        //Aplicar animação às imagens e texto
        Animation animation = new AnimationUtils().loadAnimation(this, R.anim.mytransition);
        im1.startAnimation(animation);
        im2.startAnimation(animation);
        t.startAnimation(animation);
        
        //Ligar essa tela com a tela de login depois de 1500 millisegundos
        final Intent intent = new Intent(this, LoginActivity.class);
        Thread timer = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(1500);
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                finally{
                    startActivity(intent);
                }
            }
        };timer.start();
    }

  @Override
  public void onBackPressed() {

  }
}
