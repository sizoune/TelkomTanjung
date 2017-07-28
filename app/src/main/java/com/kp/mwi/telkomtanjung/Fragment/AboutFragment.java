package com.kp.mwi.telkomtanjung.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kp.mwi.telkomtanjung.R;
import com.vstechlab.easyfonts.EasyFonts;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    TextView judul;
    HtmlTextView iconutama, icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9;


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        judul = (TextView) view.findViewById(R.id.judul);
        judul.setTypeface(EasyFonts.androidNationBold(getContext()));

        iconutama = (HtmlTextView) view.findViewById(R.id.iconutama);
        iconutama.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/those-ico" +
                "ns\" title=\"Those Icons\">Those Icons</a> from <a href=\"https://www.flaticon.com" +
                "/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creat" +
                "ivecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blan" +
                "k\">CC 3.0 BY</a></div>");

        icon1 = (HtmlTextView) view.findViewById(R.id.icon1);
        icon1.setHtml("<div>Icons made by <a href=\"http://www.freepik.com\" " +
                "title=\"Freepik\">Freepik</a> from <a href=\"https://www.flaticon.com/\"" +
                " title=\"Flaticon\">www.flaticon.com</a> is licensed by " +
                "<a href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative " +
                "Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>");

        icon2 = (HtmlTextView) view.findViewById(R.id.icon2);
        icon2.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/simpleicon\" " +
                "title=\"SimpleIcon\">SimpleIcon</a> from <a href=\"https://www.flaticon.com/\" " +
                "title=\"Flaticon\">www.flaticon.com</a> is licensed by " +
                "<a href=\"http://creativecommons.org/licenses/by/3.0/\" " +
                "title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>");

        icon3 = (HtmlTextView) view.findViewById(R.id.icon3);
        icon3.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/elias-bikbulatov" +
                "\" title=\"Elias Bikbulatov\">Elias Bikbulatov</a> from <a href=\"https:/" +
                "/www.flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a " +
                "href=\"http://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY " +
                "3.0\" target=\"_blank\">CC 3.0 BY</a></div>");

        icon4 = (HtmlTextView) view.findViewById(R.id.icon4);
        icon4.setHtml("<div>Icons made by <a href=\"http://www.freepik.com\" title=\"Freepik\">" +
                "Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">" +
                "www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/" +
                "by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>");

        icon5 = (HtmlTextView) view.findViewById(R.id.icon5);
        icon5.setHtml("<div>Icons made by <a href=\"http://www.freepik.com\" title=\"Freepik\">" +
                "Freepik</a> from <a href=\"https://www.flaticon.com/\" title=\"Flaticon\">" +
                "www.flaticon.com</a> is licensed by <a href=\"http://creativecommons.org/licenses/" +
                "by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">CC 3.0 BY</a></div>");

        icon6 = (HtmlTextView) view.findViewById(R.id.icon6);
        icon6.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/gregor-cresnar\"" +
                " title=\"Gregor Cresnar\">Gregor Cresnar</a> from <a href=\"https://www.flaticon." +
                "com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://cre" +
                "ativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_bl" +
                "ank\">CC 3.0 BY</a></div>");

        icon7 = (HtmlTextView) view.findViewById(R.id.icon7);
        icon7.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/madebyoliver\" " +
                "title=\"Madebyoliver\">Madebyoliver</a> from <a href=\"https://www.flaticon.com/\" " +
                "title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativeco" +
                "mmons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">" +
                "CC 3.0 BY</a></div>");

        icon8 = (HtmlTextView) view.findViewById(R.id.icon8);
        icon8.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/madebyoliver\"" +
                " title=\"Madebyoliver\">Madebyoliver</a> from <a href=\"https://www.flaticon.com/\"" +
                " title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"http://creativec" +
                "ommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" target=\"_blank\">" +
                "CC 3.0 BY</a></div>");

        icon9 = (HtmlTextView) view.findViewById(R.id.icon9);
        icon9.setHtml("<div>Icons made by <a href=\"https://www.flaticon.com/authors/alfredo-hernan" +
                "dez\" title=\"Alfredo Hernandez\">Alfredo Hernandez</a> from <a href=\"https://www." +
                "flaticon.com/\" title=\"Flaticon\">www.flaticon.com</a> is licensed by <a href=\"h" +
                "ttp://creativecommons.org/licenses/by/3.0/\" title=\"Creative Commons BY 3.0\" tar" +
                "get=\"_blank\">CC 3.0 BY</a></div>");

        return view;
    }

}
