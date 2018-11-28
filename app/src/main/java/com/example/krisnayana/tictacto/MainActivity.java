package com.example.krisnayana.tictacto;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    private TableLayout tb;
    private Button btn[][];
    private TableRow tblr[];
    private ArrayList<Integer> hasil1, asal1;
    private Random r1 = new Random();
    private Random r2 = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tb = findViewById(R.id.tabelLayout);

        btn = new Button[10][10];
        tblr = new TableRow[10];
        TableRow.LayoutParams tbrp = new TableRow.LayoutParams(105, 110);
        tbrp.setMargins(0,0,0,0);
        TableRow.LayoutParams tbrpM = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        tbrp.setMargins(0,0,0,0);
        int wr = ContextCompat.getColor(this, R.color.abu);
        for(int i=0; i<10; i++){
            tblr[i] = new TableRow(this);
            tblr[i].setLayoutParams(tbrpM);
            for (int j=0; j<10; j++){
                btn[i][j] = new Button(this);
                btn[i][j].setText("");
                btn[i][j].setLayoutParams(tbrp);

                btn[i][j].setBackgroundResource(R.drawable.bck);

                tblr[i].addView(btn[i][j]);
            }
            tb.addView(tblr[i]);
        }
        Button keluar = findViewById(R.id.buttonKeluar);
        keluar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        final Button ulang = findViewById(R.id.buttonUlang);
        ulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ulang();
            }
        });
        ulang();
    }

    private void ulang() {
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                btn[i][j].setText("");

                btn[i][j].setBackgroundResource(R.drawable.bck);
            }
        }
        asal1 = new ArrayList<Integer>();
        for(int i=0; i<10; i++){
            asal1.add(i);
        }

        hasil1 = new ArrayList<Integer>();
        int n = 10;
        for (int i=0; i<6; i++){
            int y = r1.nextInt(n);
            hasil1.add(asal1.get(y));
            asal1.remove(y);
            n--;
        }
        for (int i:hasil1){
            for(int j=0; j<10; j++){
                int x = r2.nextInt(10);
                btn[i][x].setText("*");

                btn[i][x].setBackgroundResource(R.drawable.bck1);
            }
        }
        for (int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                int jumlahbom=0;
                try{
                    if (btn[i-1][j-1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if(btn[i-1][j].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if(btn[i-1][j+1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i][j-1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i][j].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i][j+1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i+1][j-1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i+1][j].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                try{
                    if (btn[i+1][j+1].getText().equals("*"))
                        jumlahbom++;
                }catch (Exception e){}
                if(jumlahbom > 0){
                    btn[i][j].setText("" + jumlahbom);
                }
            }
        }
    }


}