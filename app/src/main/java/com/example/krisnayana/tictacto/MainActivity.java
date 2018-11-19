package com.example.krisnayana.tictacto;

import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String asli, hasil, split, reverse;
    private EditText editAsli;
    private TextView txtHasil;
    private int n;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editAsli = findViewById(R.id.editKataAsli);
        txtHasil = findViewById(R.id.textHasil);
        Button btnok = findViewById(R.id.buttonOK);
        btnok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                String str = editAsli.getText().toString();
                String reverse = new StringBuffer(str).reverse().toString();
                txtHasil.setText("Kalimat terbalik :"+reverse);
                /*asli = editAsli.getText().toString();
                int n = asli.length();
                hasil = "";
                for(int i = n-1; i >= 0; i--){
                    hasil = hasil + asli.charAt(i);
                }
                txtHasil.setText("Kata terbalik :" + hasil);*/
            }
        });
        Button btnKeluar = findViewById(R.id.buttonKeluar);
        btnKeluar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                System.exit(0);
            }
        });
    }
}
