package com.kp.mwi.telkomtanjung;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanLogin extends AppCompatActivity {
    @BindView(R.id.logoLogin)
    ImageView logo;
    @BindView(R.id.edUsername)
    EditText username;
    @BindView(R.id.edPass)
    EditText password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_login);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.telkomlogo).fit().into(logo);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }


    private void showDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(R.string.proseslogin)
                .progress(true, 0)
                .content(R.string.proses);

        dialog = builder.build();
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }

    @OnClick(R.id.btnMasuk)
    public void masuk() {
        if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
            String mail = username.getText().toString();
            String pass = password.getText().toString();
            showDialog();
            mAuth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(HalamanLogin.this, "Email / password anda salah",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent gotoIntent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(gotoIntent);
                                finish();
                            }
                            dismissDialog();
                        }
                    });
        } else {
            Toast.makeText(this, "Email dan password tidak boleh kosong !", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txtDaftar)
    public void daftar() {
        startActivity(new Intent(getApplicationContext(), HalamanPendaftaran.class));
    }
}
