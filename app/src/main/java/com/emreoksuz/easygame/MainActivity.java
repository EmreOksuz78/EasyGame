package com.emreoksuz.easygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeText = findViewById(R.id.timeText);
        scoreText =findViewById(R.id.scoreText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);


        //Bir loop a alıp kapatmak için
        imageArray = new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};
        hideImages();


        score=0;


        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long l) {
                timeText.setText("Time = "+l/1000);
            }

            @Override
            public void onFinish() {
            timeText.setText("Zaman Doldu");
            //Runnable ı durdurmak için bu komut kullanılıyor.
            handler.removeCallbacks(runnable);
                for (ImageView image :imageArray){
                    //Görünmez yapmayı sağlayan koddur
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                      //Yes e tıklanınca restart atmasını sağlar
                        //Intent komutu Kendi aktivitemizi tekrar başlatmak için kullanılır
                        Intent intent = getIntent();
                        //Uygulamayı çok yormamak için aktivitemizi öncelikle bitirmemiz gerekiyor ve finish komutunu kullanıyoruz.Sonradan tekrardan başlatıyoruz.
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                    }
                });
                //Alertin çalışması için kullanılır.
                alert.show();

            }
        }.start();

    }


    //Buradaki increaseScore un geldiği yer activityMain deki code kısmına onclick metodu ile atadık.
    //Bunun sebebi resimlerin üstüne tıklandıgında bir buton görevi görmesi için.
    public void increaseScore(View view){
    scoreText.setText("Score = "+score);
    score++;
    }

    public void hideImages(){
        //Burada handler ve runnable'ı tanımlama yapıyoruz.
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                //imageArray içinde loop a sokmak için imageArray i yazıyoruz.
                for (ImageView image :imageArray){
                    //Görünmez yapmaya sağlayan koddur
                    image.setVisibility(View.INVISIBLE);
                }
                //Rastgele değer yapmak için random komutu kullanılır
                Random random = new Random();
                //Burada 0 ile 8 arasında rastgele eleman getirir.
                int i = random.nextInt(9);
                //Bunu rastgele kullanabilmek için arrayin içine i yazıyoruz.
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,400);
            }
        };

        handler.post(runnable);


    }
}