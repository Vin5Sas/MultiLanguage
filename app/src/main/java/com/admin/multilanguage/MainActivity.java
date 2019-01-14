package com.admin.multilanguage;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Spinner languageOption;
    Locale myLocale;
    String currentLanguage = "en", currentLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentLanguage = getIntent().getStringExtra(currentLang);
        languageOption = (Spinner) findViewById(R.id.LanguageSpinner);

        ArrayList<String> languageList = new ArrayList<String>();

        languageList.add("Languages");
        languageList.add("English");
        languageList.add("தமிழ்");
        languageList.add("हिंदी");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, languageList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageOption.setAdapter(adapter);

        languageOption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch(i)
                {
                    case 0: break;
                    case 1: setLocale("en");
                    break;
                    case 2: setLocale("tm");
                    break;
                    case 3: setLocale("hi");
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void setLocale(String localeName)
    {
        if(!localeName.equals(currentLanguage))
        {
            myLocale = new Locale(localeName);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf,dm);
            Intent refresh = new Intent(getApplicationContext(),MainActivity.class);
            refresh.putExtra(currentLang,localeName);
            startActivity(refresh);
        }
        else
        {
            Toast.makeText(MainActivity.this,"Language Already Selected",Toast.LENGTH_SHORT);
        }
    }

    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

}
