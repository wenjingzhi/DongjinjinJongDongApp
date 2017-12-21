package com.baway.admin.dongjinjinjongdongapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class SouSuoActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView sou,fh;
    private EditText head_sou;
    private TextView ss;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initview();
    }

    private void initview() {
        sou= (ImageView) findViewById(R.id.sou);
        fh= (ImageView) findViewById(R.id.fh);
        head_sou= (EditText) findViewById(R.id.head_sou);
        ss= (TextView) findViewById(R.id.ss);
        ss.setOnClickListener(this);
        fh.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ss:
                Intent intent=new Intent(SouSuoActivity.this,ZiLeiActivity.class);
                name=head_sou.getText().toString();
                intent.putExtra("name",name);
                System.out.println("====name"+name);
                startActivity(intent);
                break;
            case R.id.fh:
                finish();
                break;
        }
    }
}
