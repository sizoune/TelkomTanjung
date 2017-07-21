package com.kp.mwi.telkomtanjung;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.kp.mwi.telkomtanjung.Model.ODP;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.txtNamaODPDetail)
    TextView namaODP;
    @BindView(R.id.txtPortOLTDetail)
    TextView portOLT;
    @BindView(R.id.txtPanelDetail)
    TextView panelODP;
    @BindView(R.id.txtPortDetail)
    TextView portODP;
    @BindView(R.id.txtCoreDetail)
    TextView coreODP;
    @BindView(R.id.txtSPLDetail)
    TextView splODP;
    @BindView(R.id.txtKoordinatDetail)
    TextView koorODP;
    @BindView(R.id.txtKapDetail)
    TextView kapODP;
    @BindView(R.id.txtTipeDetail)
    TextView tipeODP;

    @BindView(R.id.txtODFDetail)
    TextView odfOTB;
    @BindView(R.id.txtPanelOtbDetail)
    TextView panelOTB;
    @BindView(R.id.txtPortOtbDetail)
    TextView portOTB;
    @BindView(R.id.txtCoreOtbDetail)
    TextView coreOTB;
    @BindView(R.id.txtKapOtbDetail)
    TextView kapOTB;

    @BindView(R.id.txtPanelOdcDetail)
    TextView panelODC;
    @BindView(R.id.txtPortOdcDetail)
    TextView portODC;
    @BindView(R.id.txtSPLOdcDetail)
    TextView splODC;
    @BindView(R.id.txtKapOdcDetail)
    TextView kapODC;

    String nama, koord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle;
        bundle = getIntent().getExtras();
        if (bundle != null) {
            ODP odp = bundle.getParcelable("data");
            nama = odp.getNama();
            koord = odp.getKoordinat();

            namaODP.setText("Nama : " + odp.getNama());
            portOLT.setText("Port OLT : " + odp.getPortolt());
            panelODP.setText("Panel : " + odp.getPanel());
            portODP.setText("Port : " + odp.getPort());
            coreODP.setText("Core : " + odp.getCore());
            splODP.setText("SPL : " + odp.getSpl());
            koorODP.setText("Koordinat : " + odp.getKoordinat());
            kapODP.setText("KAP : " + odp.getKap());
            tipeODP.setText("Tipe ODP : " + odp.getTipe());

            odfOTB.setText("ODF : " + odp.getOtb().getOdf());
            panelOTB.setText("Panel : " + odp.getOtb().getPanel());
            portOTB.setText("Port : " + odp.getOtb().getPort());
            coreOTB.setText("Core : " + odp.getOtb().getCore());
            kapOTB.setText("KAP : " + odp.getOtb().getKap());

            panelODC.setText("Panel : " + odp.getOdc().getPanel());
            portODC.setText("Port : " + odp.getOdc().getPort());
            splODC.setText("SPL : " + odp.getOdc().getSpl());
            kapODC.setText("KAP : " + odp.getOdc().getKap());
        }
    }

    @OnClick(R.id.btnMap)
    public void openMap() {
        Intent intent = new Intent(getApplicationContext(), OnMapOpen.class);
        intent.putExtra("nama", nama);
        intent.putExtra("koord", koord);
        startActivity(intent);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
                this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));
                break;
        }
        return true;
    }

}
