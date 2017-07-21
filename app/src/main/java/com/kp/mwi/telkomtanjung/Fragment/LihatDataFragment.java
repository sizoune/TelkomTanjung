package com.kp.mwi.telkomtanjung.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kp.mwi.telkomtanjung.Adapter.LihatDataAdapter;
import com.kp.mwi.telkomtanjung.Model.ODP;
import com.kp.mwi.telkomtanjung.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LihatDataFragment extends Fragment {
    private RecyclerView listODP;
    private LihatDataAdapter ODPAdapter;
    private EditText cariOdp;
    private Spinner kategori;
    String keyword = "";
    String[] kat;

    FirebaseAuth mAuth;
    FirebaseDatabase database;

    public LihatDataFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lihat_data, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listODP = (RecyclerView) view.findViewById(R.id.list);
        listODP.setLayoutManager(llm);
        ODPAdapter = new LihatDataAdapter(getContext());
        listODP.setAdapter(ODPAdapter);

        cariOdp = (EditText) view.findViewById(R.id.edCari);

        kategori = (Spinner) view.findViewById(R.id.katCari);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount())); //"Hint to be displayed"
                }

                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1; // you dont display last item. It is used as hint.
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if (mAuth.getCurrentUser().getUid().equals("u1dLITGaKNeU4AgTQjxe0rRQloq2")) {
            adapter.add("Nama ODP");
            adapter.add("Port OLT");
            adapter.add("Berdasarkan");
        } else {
            adapter.add("Nama ODP");
            adapter.add("Berdasarkan");
        }
        kategori.setAdapter(adapter);
        kategori.setSelection(adapter.getCount());
        getData();
        cariOdp.addTextChangedListener(searchListener);
    }

    TextWatcher searchListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!kategori.getSelectedItem().toString().equals("Berdasarkan")) {
                keyword = s.toString();
                ODPAdapter.filter(keyword, kategori.getSelectedItem().toString());
            } else {
                Toast.makeText(getContext(), "Silahkan pilih kategori pencarian dahulu !", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    private void getData() {
        database.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        ODP odp = dataSnapshot1.getValue(ODP.class);
                        ODPAdapter.addData(odp);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
