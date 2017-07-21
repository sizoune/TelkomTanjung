package com.kp.mwi.telkomtanjung;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    @BindView(R.id.txtNamaODPDetail)
    TextView namaODP;
    @BindView(R.id.txtPortOLTDetail)
    TextView portOLT;
    @BindView(R.id.txtPanelDetail)
    TextView PanelODP;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

    }

}
