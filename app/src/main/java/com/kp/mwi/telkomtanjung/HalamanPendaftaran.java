package com.kp.mwi.telkomtanjung;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HalamanPendaftaran extends AppCompatActivity {
    @BindView(R.id.logoDaftar)
    ImageView logo;
    @BindView(R.id.edUsernameDaftar)
    EditText username;
    @BindView(R.id.edPassDaftar)
    EditText password;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    MaterialDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_pendaftaran);
        ButterKnife.bind(this);

        Picasso.with(this).load(R.drawable.telkomlogo).fit().into(logo);
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.btnDaftar)
    public void daftar() {
        if (!username.getText().toString().equals("") && !password.getText().toString().equals("")) {
            showDialog();
            String mail = username.getText().toString();
            String pass = password.getText().toString();
            mAuth.createUserWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(HalamanPendaftaran.this, "Alamat email / password anda tidak valid",
                                        Toast.LENGTH_SHORT).show();
                                dismissDialog();
                            } else {
                                Toast.makeText(HalamanPendaftaran.this, "Akun berhasil didaftarkan !",
                                        Toast.LENGTH_SHORT).show();
                                dismissDialog();
                                Intent loginIntent = new Intent(getApplicationContext(), HalamanLogin.class);
                                loginIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                                startActivity(loginIntent);
                                finish();
                            }
                        }
                    });
        } else {
            Toast.makeText(this, "Email dan password tidak boleh kosong !", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.txtLogin)
    public void login() {
        Intent loginIntent = new Intent(getApplicationContext(), HalamanLogin.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(loginIntent);
        finish();
    }

    private void showDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this)
                .title(R.string.prosesdaftar)
                .progress(true, 0)
                .content(R.string.proses);

        dialog = builder.build();
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }
}
