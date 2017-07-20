package com.kp.mwi.telkomtanjung.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.firebase.database.FirebaseDatabase;
import com.kp.mwi.telkomtanjung.Model.ODP;
import com.kp.mwi.telkomtanjung.R;


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

    @BindView(R.id.path)
    TextView lokasi;

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
        if (ada) {
            dataODP = new ArrayList<>();
            showDialog();
            readDataFromExcel();
            if (!dataODP.isEmpty()) {
                for (ODP data : dataODP) {
                    database.getReference().push().setValue(data);
                }
                Toast.makeText(InputDataFragment.this.getContext(),
                        "Data berhasil di upload ke server !", Toast.LENGTH_SHORT).show();
            }
            dismissDialog();
        } else {
            Toast.makeText(InputDataFragment.this.getContext(), "Silahkan pilih file terlebih dahulu !"
                    , Toast.LENGTH_SHORT).show();
        }
    }

    private void showFileChooser() {
        FilePickerBuilder.getInstance().setMaxCount(1)
                .setActivityTheme(R.style.AppTheme)
                .pickFile(this);
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

                for (int r = 2; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    ODP odp = new ODP();
                    odp.setNama(getCellAsString(row, 0, formulaEvaluator));
                    odp.setPortolt(getCellAsString(row, 1, formulaEvaluator));
                    odp.setPanel(getCellAsString(row, 13, formulaEvaluator));
                    odp.setPort(getCellAsString(row, 14, formulaEvaluator));
                    odp.setCore(getCellAsString(row, 15, formulaEvaluator));
                    odp.setSpl(getCellAsString(row, 16, formulaEvaluator));
                    odp.setKoordinat(getCellAsString(row, 17, formulaEvaluator));
                    odp.setLastupdate(getCellAsString(row, 18, formulaEvaluator));
                    odp.setAlamat(getCellAsString(row, 19, formulaEvaluator));
                    odp.setKap(getCellAsString(row, 20, formulaEvaluator));
                    odp.setTipe(getCellAsString(row, 21, formulaEvaluator));

                    odp.getOtb().setOdf(getCellAsString(row, 2, formulaEvaluator));
                    odp.getOtb().setPanel(getCellAsString(row, 3, formulaEvaluator));
                    odp.getOtb().setPort(getCellAsString(row, 4, formulaEvaluator));
                    odp.getOtb().setCore(getCellAsString(row, 5, formulaEvaluator));
                    odp.getOtb().setKap(getCellAsString(row, 6, formulaEvaluator));

                    odp.getOdc().setNama(getCellAsString(row, 7, formulaEvaluator));
                    odp.getOdc().setPanel(getCellAsString(row, 8, formulaEvaluator));
                    odp.getOdc().setPort(getCellAsString(row, 9, formulaEvaluator));
                    odp.getOdc().setSpl(getCellAsString(row, 10, formulaEvaluator));
                    odp.getOdc().setKap(getCellAsString(row, 11, formulaEvaluator));

                    dataODP.add(odp);
                }
            } else if (ekstensi.equals("xls")) {
                HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
                HSSFSheet sheet = workbook.getSheet("ODC FB");
                int rowsCount = sheet.getPhysicalNumberOfRows();
                FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();

                for (int r = 2; r < rowsCount; r++) {
                    Row row = sheet.getRow(r);
                    ODP odp = new ODP();
                    odp.setNama(getCellAsString(row, 0, formulaEvaluator));
                    odp.setPortolt(getCellAsString(row, 1, formulaEvaluator));
                    odp.setPanel(getCellAsString(row, 13, formulaEvaluator));
                    odp.setPort(getCellAsString(row, 14, formulaEvaluator));
                    odp.setCore(getCellAsString(row, 15, formulaEvaluator));
                    odp.setSpl(getCellAsString(row, 16, formulaEvaluator));
                    odp.setKoordinat(getCellAsString(row, 17, formulaEvaluator));
                    odp.setLastupdate(getCellAsString(row, 18, formulaEvaluator));
                    odp.setAlamat(getCellAsString(row, 19, formulaEvaluator));
                    odp.setKap(getCellAsString(row, 20, formulaEvaluator));
                    odp.setTipe(getCellAsString(row, 21, formulaEvaluator));

                    odp.getOtb().setOdf(getCellAsString(row, 2, formulaEvaluator));
                    odp.getOtb().setPanel(getCellAsString(row, 3, formulaEvaluator));
                    odp.getOtb().setPort(getCellAsString(row, 4, formulaEvaluator));
                    odp.getOtb().setCore(getCellAsString(row, 5, formulaEvaluator));
                    odp.getOtb().setKap(getCellAsString(row, 6, formulaEvaluator));

                    odp.getOdc().setNama(getCellAsString(row, 7, formulaEvaluator));
                    odp.getOdc().setPanel(getCellAsString(row, 8, formulaEvaluator));
                    odp.getOdc().setPort(getCellAsString(row, 9, formulaEvaluator));
                    odp.getOdc().setSpl(getCellAsString(row, 10, formulaEvaluator));
                    odp.getOdc().setKap(getCellAsString(row, 11, formulaEvaluator));

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
                    value = "";
                    break;
                default:
                    Toast.makeText(InputDataFragment.this.getContext(), "Unknown Cell Type !", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException nullpointer) {
            Toast.makeText(InputDataFragment.this.getContext(), "Null Pointer !", Toast.LENGTH_SHORT).show();
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
