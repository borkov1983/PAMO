package com.example.billsreminderapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;


public class DodajActivity extends AppCompatActivity {
    EditText kwota, odbiorca, uwagi, dzien;
    RadioButton powtarzasie, niepowtarzasie;
    RadioGroup czySiePowtarzaRadio;
    Spinner powtarza, coile;
    Button akceptuj;
    Bundle bundle = new Bundle();
    boolean czyDataWybrana = false;

    private static final String TAG = "DodajActivity";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Faktura edytowanaFaktura;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        edytowanaFaktura = Cache.getInstance().getEdytowanaFaktura();
        Cache.getInstance().setEdytowanaFaktura(null);
        if(edytowanaFaktura != null) {
            Cache.getInstance().setZaznaczonaFaktura(null);
        }
        Log.d("aktywność", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        kwota = (EditText) findViewById(R.id.kwota);
        odbiorca = (EditText) findViewById(R.id.odbiorca);
        uwagi = (EditText) findViewById(R.id.uwagi);
        dzien = (EditText) findViewById(R.id.dzien);
        powtarzasie = (RadioButton) findViewById(R.id.powtarzasie);
        niepowtarzasie = (RadioButton) findViewById(R.id.niepowtarzasie);
        czySiePowtarzaRadio = (RadioGroup) findViewById(R.id.radio_group);
        powtarza = (Spinner) findViewById(R.id.powtarza);
        coile = (Spinner) findViewById(R.id.coile);
        akceptuj = (Button) findViewById(R.id.Akceptuj);

        mDisplayDate = (TextView) findViewById(R.id.tvdate);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", new Locale("pl", "PL"));
        mDisplayDate.setText(df.format(new Date()));
        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){

                czyDataWybrana = true;

                DateTimeFormatter format = DateTimeFormat.forPattern("dd-MM-yyyy");
                DateTime dateTime = format.parseDateTime(mDisplayDate.getText().toString());
                int year = dateTime.getYear();
                int month = dateTime.getMonthOfYear() - 1;
                int day = dateTime.getDayOfMonth();
                DatePickerDialog dialog = new DatePickerDialog(
                        DodajActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd//yy:" + month + "-" + dayOfMonth+ "-" + year);
                String date = dayOfMonth + "-" + month + "-" + year;

                DateTimeFormatter format = DateTimeFormat.forPattern("dd-MM-yyyy");
                DateTime time = format.parseDateTime(date);
                mDisplayDate.setText(time.toString("dd-MM-yyyy"));
                wlaczPrzyciskAkceptujDlaPoprawnychDanych();
            }
        };

        if(edytowanaFaktura == null) {
            kwota.setText(bundle.getString("kwota"));
            odbiorca.setText(bundle.getString("odbiorca"));
            uwagi.setText(bundle.getString("uwagi"));
            dzien.setText(bundle.getString("dzien"));
        } else {
            setTitle("Edytuj Przypomnienie");
            czyDataWybrana = true;
            kwota.setText(edytowanaFaktura.getKwota().toString());
            odbiorca.setText(edytowanaFaktura.getOdbiorca());
            uwagi.setText(edytowanaFaktura.getUwagi());

            dzien.setText(edytowanaFaktura.getCoIlePlatnosc().toString());
            switch (edytowanaFaktura.getOkresPowtarzania()) {
                case "DZIEŃ":
                    powtarza.setSelection(0);
                    break;
                case "TYDZIEŃ":
                    powtarza.setSelection(1);
                    break;
                case "MIESIĄC":
                    powtarza.setSelection(2);
                    break;
                case "ROK":
                    powtarza.setSelection(3);
                    break;
                default:
                    powtarza.setSelection(0);
            }
            coile.setSelection(edytowanaFaktura.getIlePrzedPowiadomic());
            if(edytowanaFaktura.isCzySiePowtarza()) {
                powtarzasie.performClick();
            } else {
                niepowtarzasie.performClick();
            }
            mDisplayDate.setText(edytowanaFaktura.getTerminDoWyswietlenia());
            wlaczPrzyciskAkceptujDlaPoprawnychDanych();
        }

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        ustawWalidacje();
    }

    private void ustawWalidacje() {
        kwota.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wlaczPrzyciskAkceptujDlaPoprawnychDanych();
            }
        });

        odbiorca.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wlaczPrzyciskAkceptujDlaPoprawnychDanych();
            }
        });
    }

    private void wlaczPrzyciskAkceptujDlaPoprawnychDanych() {
        if(czyWpisaneDaneSaPoprane()) {
            akceptuj.setEnabled(true);
        } else {
            akceptuj.setEnabled(false);
        }
    }

    private boolean czyWpisaneDaneSaPoprane() {
        String odbiorca = this.odbiorca.getText().toString();
        double kwota;
        if(this.kwota.getText().toString().equals("")) {
            kwota = -1D;
        } else {
            kwota = Double.parseDouble(this.kwota.getText().toString());
        }

        return !odbiorca.equals("") && kwota != -1 && czyDataWybrana;
    }


    @Override
    protected void onStart() {
        Log.d("aktywność", "onStart");
        super.onStart();
    }
    protected void onResume() {
        Log.d("aktywność", "onResume");
        super.onResume();
    }
    @Override
    protected void onPause() {
        Log.d("aktywność", "onPause");
        //text = kwota.getText().toString();
        bundle.putString( "odbiorca", odbiorca.getText().toString() );

        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("aktywność", "onRestart");
        super.onRestart();
    }
    @Override
    protected void onStop() {
        Log.d("aktywność", "onStop");
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        Log.d("aktywność", "onDestroy");
        super.onDestroy();
    }


    public void Akceptuj(View view){
        ZarzadzanieFakturami zarzadzanieFakturami = new ZarzadzanieFakturami(getSharedPreferences("zapisaneFaktury", Context.MODE_PRIVATE));

        String odbiorca = this.odbiorca.getText().toString();
        Double kwota = Double.parseDouble(this.kwota.getText().toString());
        String uwagi = this.uwagi.getText().toString();
        String tvdate = this.mDisplayDate.getText().toString();
        String dzien = this.dzien.getText().toString();
        String powtarza = this.powtarza.getSelectedItem().toString();
        String coile = this.coile.getSelectedItem().toString().replaceAll("\\D+", "");

        Faktura faktura = new Faktura(odbiorca, kwota, tvdate, uwagi);
        if(!dzien.equals("")) {
            faktura.setCoIlePlatnosc(Integer.valueOf(dzien));
        } else {
            faktura.setCoIlePlatnosc(0);
        }

        boolean czySiePowtarza = false;
        if(czySiePowtarzaRadio.getCheckedRadioButtonId() == R.id.powtarzasie) {
            czySiePowtarza = true;
        }
        faktura.setCzySiePowtarza(czySiePowtarza);
        faktura.setOkresPowtarzania(powtarza);

        if(coile.equals("")) {
            faktura.setIlePrzedPowiadomic(0);
        } else {
            faktura.setIlePrzedPowiadomic(Integer.valueOf(coile));
        }
        if(edytowanaFaktura != null) {
            zarzadzanieFakturami.usunFakture(edytowanaFaktura);
        }
        zarzadzanieFakturami.dodajFakture(faktura);
        Toast.makeText(getApplicationContext(), "Faktura została zapisana", Toast.LENGTH_LONG).show();
        Cache.getInstance().setEdytowanaFaktura(null);
        Anuluj(view);
    }

    public void Anuluj(View view){
        startActivity(new Intent(DodajActivity.this,MainActivity.class));
    }

    public void wylaczCoIlePlatnosc(View view) {
        powtarza.setEnabled(false);
        dzien.setEnabled(false);
    }

    public void wlaczCoIlePlatnosc(View view) {
        powtarza.setEnabled(true);
        dzien.setEnabled(true);
    }
}
