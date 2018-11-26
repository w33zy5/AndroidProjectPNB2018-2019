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

    private LinearLayout linearlayout[];
    private Button btn[][];
    private LinearLayout latas;
    private int giliran = 0;
    private int merah, hijau, abu;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        merah = ContextCompat.getColor(this,R.color.merah);
        hijau = ContextCompat.getColor(this,R.color.hijau);
        abu = ContextCompat.getColor(this,R.color.abu);

        Point layar = new Point();
        getWindowManager().getDefaultDisplay().getSize(layar);

        int lebarTombol = (int)(layar.x/3);

        linearlayout = new LinearLayout[3];

        btn = new Button[3][3];

        latas = findViewById(R.id.layoutatas);

        LinearLayout.LayoutParams x = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        giliran = 0;

        int n = 0;

        for(int i = 0; i < 3; i++){
            linearlayout[i] = new LinearLayout(this);
            linearlayout[i].setOrientation(LinearLayout.HORIZONTAL);
            linearlayout[i].setLayoutParams(x);

            latas.addView(linearlayout[i]);
            for(int j = 0; j < 3; j++){
                btn[i][j] = new Button(this);
                btn[i][j].setId(n++);
                btn[i][j].setText("");
                btn[i][j].setTextSize(20);

                btn[i][j].setOnClickListener(this);
                btn[i][j].setWidth(lebarTombol);
                btn[i][j].setHeight(400);

                linearlayout[i].addView(btn[i][j]);
            }
        }

        Button ulang = findViewById(R.id.buttonUlang);
        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giliran = 0;
                for(int i = 0; i < 3; i++){
                    for(int j = 0; j < 3; j++){
                        btn[i][j].setText("");
                        btn[i][j].setEnabled(true);
                        btn[i][j].setBackgroundColor(abu);
                    }
                }
            }
        });

        Button keluar = findViewById(R.id.buttonKeluar);
        keluar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }

    @Override
    public void onClick(View v) {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(v.getId()==btn[i][j].getId()){
                    if(giliran%2==0){
                        btn[i][j].setText("O");
                        btn[i][j].setEnabled(false);
                        btn[i][j].setBackgroundColor(merah);
                    }else{
                        btn[i][j].setText("X");
                        btn[i][j].setEnabled(false);
                        btn[i][j].setBackgroundColor(hijau);
                    }
                    giliran++;
                }
            }
        }
    }
}