package com.kp.mwi.telkomtanjung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanLogin extends AppCompatActivity {
    @BindView(R.id.logoLogin)
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.telkomlogo).fit().into(logo);
    }

    @OnClick(R.id.btnMasuk)
    public void masuk() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
