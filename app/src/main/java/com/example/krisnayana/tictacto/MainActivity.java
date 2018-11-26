package com.example.krisnayana.tictacto;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*implements onclick listener ini berfungsi untuk membuat listener onclick
    * pada button, sehingga onclick dapat dipusatkan pada sebuah overriding
    * method onClick..*/

    /*Untuk menambahkan linear layout horizontal pada layout atas, LinearLayout
     * ini dibuat array karena pada aplikasi akan membuat linearlayout horizontal
     * sebanyak baris yang dibutuhkan sehingga tampilan akan menjadi seperti grid
     * layout*/
    private LinearLayout linearlayout[];

    /*Array button untuk button pada papan permainan, ukuran 3 x 3*/
    private Button btn[][];

    /*Deklarasi untuk menamai LinearLayout atas yang sudah dibuat di xml activitymain.xml*/
    private LinearLayout latas;

    /*Untuk merubah giliran antara pemain O dan X*/
    private int giliran = 0;

    //Menampung variabel warna yang sudah dibuat pada color
    private int merah, hijau, abu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Memanggil warna-warna yang sudah dibuat
        merah = ContextCompat.getColor(this,R.color.merah);
        hijau = ContextCompat.getColor(this,R.color.hijau);
        abu = ContextCompat.getColor(this,R.color.abu);

        /*Variabel point untuk menangkap lebarnya layar, digunakan untuk
         * menentukan ukuran tombol pada papan permainan*/
        Point layar = new Point();
        getWindowManager().getDefaultDisplay().getSize(layar);

        /*Karena ukuran permainan adalah 3 x 3, maka lebar layar dibagi 3*/
        int lebarTombol = (int)(layar.x/3);

        /*Object linear layout horizontal dibuat 3 buah, untuk 3 baris*/
        linearlayout = new LinearLayout[3];

        //Object button dibuat 3 x 3 sesuai papan permainan
        btn = new Button[3][3];

        /*Menangkap layout atas, kemudian diberi nama latas (layout di xml
        * yang berupa LinearLayout Vertical*/
        latas = findViewById(R.id.layoutatas);

        /*Membuat param dari linear layout horizontal yang akan menjadi child
        * pada latas, dibuat param agar ukuran sesuai lebar dan besar content*/
        LinearLayout.LayoutParams x = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        /*Inisialisasi giliran dimulai dari 0*/
        giliran = 0;

        /*Inisialisasi id masing-masing button sehingga mudah di kenal pada onclick
        * terpusat dengan membangdingkan id dari button yang di click*/
        int n = 0;

        /*Perulangan inner looping untuk membuat linear layout horizontal yang merupakan
        * child dari latas dan pembuatan button yagn merupakan array 3 x 3*/
        for(int i=0; i<3; i++){

            /*Membuat linear layout child dari layout latas*/
            linearlayout[i] = new LinearLayout(this);
            linearlayout[i].setOrientation(LinearLayout.HORIZONTAL);
            linearlayout[i].setLayoutParams(x);

            //Menambahkan LinearLayout horizontal pada latas
            latas.addView(linearlayout[i]);

            for(int j=0; j<3; j++){

                //Membuat button permainan 3 x 3
                btn[i][j] = new Button(this);

                //Set id masing-masing button
                btn[i][j].setId(n++);
                btn[i][j].setText("");
                btn[i][j].setTextSize(20);

                //Menambahkan perintah onClick
                btn[i][j].setOnClickListener(this);

                /*Setting lebar sesuai kebutuhan lebar layer/3 dan tinggi
                * masing-masing tombol adalah 400px*/
                btn[i][j].setWidth(lebarTombol);
                btn[i][j].setHeight(200);

                //Menambahkan button yang dibuat ke linearlayout horizontal
                linearlayout[i].addView(btn[i][j]);
            }
        }

        //Menambahkan perintah pada button ulang
        Button ulang = findViewById(R.id.buttonUlang);
        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Mengembalikan giliran menjadi 0*/
                giliran = 0;
                for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){

                        //Mengebalikan text masing-masing tombol menjadi kosong
                        btn[i][j].setText("");

                        /*Mengembalikan tombol ke posisi siap di klik/enable*/
                        btn[i][j].setEnabled(true);

                        //Mengembalikan warna tombol menjadi abu
                        btn[i][j].setBackgroundColor(abu);
                    }
                }
            }
        });

        //Menambahkan perintah pada button keluar
        Button keluar = findViewById(R.id.buttonKeluar);
        keluar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    /*Overriding dari oClickListener, semua button yang ada setOnClickListener(this);
    * akan mengirimkan signal ke method ini*/
    @Override
    public void onClick(View v) {
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){

                /*Membandingkan button mana yang diklik dengan perbandingan id*/
                if(v.getId()==btn[i][j].getId()){

                    /*Jika giliran genap maka tombol akan di set text O dan
                    * warnanya diganti merah*/
                    if(giliran%2==0){
                        btn[i][j].setText("O");

                        //Mematikan tombol dengan disable set enable = false
                        btn[i][j].setEnabled(false);
                        btn[i][j].setBackgroundColor(merah);
                    }else{

                        /*Jika giliran ganjil maka tombol akan di set text O dan
                        * warnanya diganti merah*/
                        btn[i][j].setText("X");
                        btn[i][j].setEnabled(false);
                        btn[i][j].setBackgroundColor(hijau);
                    }

                    /*Menambahkan giliran dengan 1 (autoincrement)*/
                    giliran++;
                }
            }
        }
    }
}