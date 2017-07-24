package com.kp.mwi.telkomtanjung.Fragment;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.kp.mwi.telkomtanjung.Model.ODP;
import com.kp.mwi.telkomtanjung.R;
import com.novoda.merlin.Merlin;
import com.novoda.merlin.MerlinsBeard;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

import static android.app.Activity.RESULT_OK;
import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class InputDataFragment extends Fragment {
    String path, ekstensi;
    boolean ada;
    MaterialDialog dialog;

    FirebaseDatabase database;

    private ArrayList<ODP> dataODP;
    private ArrayList<String> dataODPFromserver = new ArrayList<>();

    @BindView(R.id.path)
    TextView lokasi;

    ArrayList<Integer> newData = new ArrayList<>();

    boolean ijin = false;
    boolean noDatainserver = false;
    boolean alreadyloaddata = false;

    Merlin merlin;
    MerlinsBeard merlinsBeard;

    public InputDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_input_data, container, false);
        ButterKnife.bind(this, v);
        database = FirebaseDatabase.getInstance();
        path = null;
        ada = false;
        merlin = new Merlin.Builder().withConnectableCallbacks().build(getContext());
        merlinsBeard = MerlinsBeard.from(getContext());
        return v;
    }

    @OnClick(R.id.btnImport)
    public void impor() {
        showFileChooser();
    }

    @OnClick(R.id.path)
    public void selengkap() {
        if (ada) {
            Toast.makeText(InputDataFragment.this.getContext(), lokasi.getText(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btnSimpan)
    public void simpan() {
        if (merlinsBeard.isConnected()) {
            if (ada) {
                showDialog();
                dataODP = new ArrayList<>();
                readDataFromExcel();
                if (!dataODP.isEmpty()) {
                    updateOldData();
                }
            } else {
                Toast.makeText(InputDataFragment.this.getContext(), "Silahkan pilih file terlebih dahulu !"
                        , Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Maaf tidak dapat mengupload data ke server !\n" +
                    "karena anda tidak terhubung dengan internet !", Toast.LENGTH_SHORT).show();
        }
    }

    private void cekNewData() {
        boolean ada = false;
        for (int i = 0; i < dataODP.size(); i++) {
            for (String nama : dataODPFromserver) {
                if (nama.equals(dataODP.get(i).getNama())) {
                    ada = true;
                }
            }
            if (!ada) {
                newData.add(i);
            }
            ada = false;
        }
    }

    private void updateOldData() {
        database.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ODP odpfromserver = dataSnapshot1.getValue(ODP.class);
                        dataODPFromserver.add(odpfromserver.getNama());
                        for (ODP odp1 : dataODP) {
                            if (odp1.getNama().equals(odpfromserver.getNama())) {
                                database.getReference(dataSnapshot1.getKey()).setValue(odp1);
                            }
                        }
                    }
                    cekNewData();
                    if (newData.size() > 0) {
                        for (int i : newData) {
                            database.getReference().push().setValue(dataODP.get(i));
                        }
                    }
                    Toast.makeText(InputDataFragment.this.getContext(),
                            "Data berhasil di upload ke server !", Toast.LENGTH_SHORT).show();
                    path = null;
                    ada = false;
                    lokasi.setText(R.string.pathfile);
                } else {
                    for (ODP data : dataODP) {
                        database.getReference().push().setValue(data);
                    }
                }
                dismissDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getContext(), "database error !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showFileChooser() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Dexter.withActivity(this.getActivity())
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            ijin = true;
                            bacaMem();
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            Toast.makeText(getContext(), "Ijin untuk membaca memori tidak diberikan !", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {/* ... */}
                    }).check();
        } else {
            FilePickerBuilder.getInstance().setMaxCount(1)
                    .setActivityTheme(R.style.AppTheme)
                    .pickFile(this);
        }

    }

    private void bacaMem() {
        if (ijin) {
            FilePickerBuilder.getInstance().setMaxCount(1)
                    .setActivityTheme(R.style.AppTheme)
                    .pickFile(this);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    ArrayList<String> paths = new ArrayList<>();
                    paths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS));
                    if (paths.size() > 0) {
                        String ext = paths.get(0).substring(paths.get(0).lastIndexOf('.') + 1);
                        ekstensi = ext;
                        if (ext.equals("xls") || ext.equals("xlsx")) {
                            path = paths.get(0);
                            lokasi.setText(paths.get(0));
                            ada = true;
                        } else {
                            Toast.makeText(InputDataFragment.this.getContext(),
                                    "Format file harus berupa .xls atau .xlsx !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
        }
    }

    private void readDataFromExcel() {
//        Toast.makeText(InputDataFragment.this.getContext(), path, Toast.LENGTH_SHORT).show();
        File data = new File(path);

        try {
            InputStream inputStream = new FileInputStream(data);
            if (ekstensi.equals("xlsx")) {
                XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
                XSSFSheet sheet = workbook.getSheet("ODC FB");
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

                for (int r = 3; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    ODP odp = new ODP();
                    odp.setNama(getCellAsString(row, 0, formulaEvaluator));
                    odp.setIpolt(getCellAsString(row, 1, formulaEvaluator));
                    odp.setPortolt(getCellAsString(row, 2, formulaEvaluator));
                    odp.setPanel(getCellAsString(row, 20, formulaEvaluator));
                    odp.setPort(getCellAsString(row, 21, formulaEvaluator));
                    odp.setCore(getCellAsString(row, 22, formulaEvaluator));
                    odp.setSpl(getCellAsString(row, 23, formulaEvaluator));
                    odp.setKoordinat(getCellAsString(row, 24, formulaEvaluator));
                    odp.setLastupdate(getCellAsString(row, 25, formulaEvaluator));
                    odp.setAlamat(getCellAsString(row, 26, formulaEvaluator));
                    odp.setKap(getCellAsString(row, 27, formulaEvaluator));
                    odp.setTipe(getCellAsString(row, 28, formulaEvaluator));

                    odp.getFtm().getEside().setPanel(getCellAsString(row, 3, formulaEvaluator));
                    odp.getFtm().getEside().setPort(getCellAsString(row, 4, formulaEvaluator));

                    odp.getFtm().getOside().setPanel(getCellAsString(row, 5, formulaEvaluator));
                    odp.getFtm().getOside().setPort(getCellAsString(row, 6, formulaEvaluator));

                    odp.getFtm().getEtrans().setPanel(getCellAsString(row, 7, formulaEvaluator));
                    odp.getFtm().getEtrans().setPort(getCellAsString(row, 8, formulaEvaluator));

                    odp.getOtb().setOdf(getCellAsString(row, 9, formulaEvaluator));
                    odp.getOtb().setPanel(getCellAsString(row, 10, formulaEvaluator));
                    odp.getOtb().setPort(getCellAsString(row, 11, formulaEvaluator));
                    odp.getOtb().setCore(getCellAsString(row, 12, formulaEvaluator));
                    odp.getOtb().setKap(getCellAsString(row, 13, formulaEvaluator));

                    odp.getOdc().setNama(getCellAsString(row, 14, formulaEvaluator));
                    odp.getOdc().setPanel(getCellAsString(row, 15, formulaEvaluator));
                    odp.getOdc().setPort(getCellAsString(row, 16, formulaEvaluator));
                    odp.getOdc().setSpl(getCellAsString(row, 17, formulaEvaluator));
                    odp.getOdc().setKap(getCellAsString(row, 18, formulaEvaluator));
                    odp.getOdc().setKoordinat(getCellAsString(row, 19, formulaEvaluator));

                    dataODP.add(odp);
                }
            } else if (ekstensi.equals("xls")) {
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                HSSFSheet sheet = workbook.getSheet("ODC FB");
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

                for (int r = 3; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    ODP odp = new ODP();
                    odp.setNama(getCellAsString(row, 0, formulaEvaluator));
                    odp.setIpolt(getCellAsString(row, 1, formulaEvaluator));
                    odp.setPortolt(getCellAsString(row, 2, formulaEvaluator));
                    odp.setPanel(getCellAsString(row, 20, formulaEvaluator));
                    odp.setPort(getCellAsString(row, 21, formulaEvaluator));
                    odp.setCore(getCellAsString(row, 22, formulaEvaluator));
                    odp.setSpl(getCellAsString(row, 23, formulaEvaluator));
                    odp.setKoordinat(getCellAsString(row, 24, formulaEvaluator));
                    odp.setLastupdate(getCellAsString(row, 25, formulaEvaluator));
                    odp.setAlamat(getCellAsString(row, 26, formulaEvaluator));
                    odp.setKap(getCellAsString(row, 27, formulaEvaluator));
                    odp.setTipe(getCellAsString(row, 28, formulaEvaluator));

                    odp.getFtm().getEside().setPanel(getCellAsString(row, 3, formulaEvaluator));
                    odp.getFtm().getEside().setPort(getCellAsString(row, 4, formulaEvaluator));

                    odp.getFtm().getOside().setPanel(getCellAsString(row, 5, formulaEvaluator));
                    odp.getFtm().getOside().setPort(getCellAsString(row, 6, formulaEvaluator));

                    odp.getFtm().getEtrans().setPanel(getCellAsString(row, 7, formulaEvaluator));
                    odp.getFtm().getEtrans().setPort(getCellAsString(row, 8, formulaEvaluator));

                    odp.getOtb().setOdf(getCellAsString(row, 9, formulaEvaluator));
                    odp.getOtb().setPanel(getCellAsString(row, 10, formulaEvaluator));
                    odp.getOtb().setPort(getCellAsString(row, 11, formulaEvaluator));
                    odp.getOtb().setCore(getCellAsString(row, 12, formulaEvaluator));
                    odp.getOtb().setKap(getCellAsString(row, 13, formulaEvaluator));

                    odp.getOdc().setNama(getCellAsString(row, 14, formulaEvaluator));
                    odp.getOdc().setPanel(getCellAsString(row, 15, formulaEvaluator));
                    odp.getOdc().setPort(getCellAsString(row, 16, formulaEvaluator));
                    odp.getOdc().setSpl(getCellAsString(row, 17, formulaEvaluator));
                    odp.getOdc().setKap(getCellAsString(row, 18, formulaEvaluator));
                    odp.getOdc().setKoordinat(getCellAsString(row, 19, formulaEvaluator));

                    dataODP.add(odp);
                }
            }
        } catch (FileNotFoundException notfound) {
            dismissDialog();
            Toast.makeText(InputDataFragment.this.getContext(), "File tidak ditemukan !", Toast.LENGTH_SHORT).show();
        } catch (IOException ioeror) {
            dismissDialog();
            Toast.makeText(InputDataFragment.this.getContext(), "telah terjadi error ketika membaca file !", Toast.LENGTH_SHORT).show();
        }
    }

    private String getCellAsString(Row row, int colKe, FormulaEvaluator formulaEvaluator) {
        String value = "";

        try {
            Cell cell = row.getCell(colKe);
            CellValue cellValue = formulaEvaluator.evaluate(cell);
            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = "" + cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = "" + cellValue.getNumberValue();
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = "" + cellValue.getStringValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "-";
                    break;
                default:
                    Toast.makeText(InputDataFragment.this.getContext(), "Unknown Cell Type !", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException nullpointer) {
            Toast.makeText(InputDataFragment.this.getContext(), "Ada kolom yang kosong !", Toast.LENGTH_SHORT).show();
        }
        return value;
    }

    private void showDialog() {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(InputDataFragment.this.getContext())
                .title(R.string.prosessimpan)
                .progress(true, 0)
                .content(R.string.proses);

        dialog = builder.build();
        dialog.show();
    }

    private void dismissDialog() {
        dialog.dismiss();
    }
}
