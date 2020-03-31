package com.example.micha.billsreminder_251118.feature;

import android.app.AlarmManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.ustawPowiadomienia();
        this.wyswietlFaktury();
    }

    private void ustawPowiadomienia() {
        NotificationSetter notificationSetter = new NotificationSetter(getApplicationContext(),
                (AlarmManager) getSystemService(ALARM_SERVICE));
        notificationSetter.createRepeatNotification();
    }

    public void przelaczTryb(View view) {
        this.wyswietlFaktury();
    }



    private void wyswietlFaktury() {
        ZarzadzanieFakturami zarzadzanieFakturami = new ZarzadzanieFakturami(getSharedPreferences("zapisaneFaktury", Context.MODE_PRIVATE));
        Set<Faktura> faktury;
        ToggleButton guzikTrybWyswietlaniaFaktur = (ToggleButton) findViewById(R.id.trybWyswietlaniaFaktur);

        if(guzikTrybWyswietlaniaFaktur.getText().toString().equals("ZBLIŻAJĄCE SIĘ FAKTURY")){
            faktury = zarzadzanieFakturami.pobierzPrzyszle();
        } else {
            faktury = zarzadzanieFakturami.pobierzZalegle();
        }
        ArrayList<Faktura> fakturyDoWyswietlenia = new ArrayList<>(faktury);

        ListView listView = (ListView)findViewById(R.id.list);
        Collections.sort(fakturyDoWyswietlenia);
        CustomAdapter adapter = new CustomAdapter(fakturyDoWyswietlenia, getApplicationContext(), this);

        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void click(View view){
        int i = view.getId();
        if (i == R.id.fab) {
            Intent intent = new Intent(MainActivity.this, DodajActivity.class);
            startActivity(intent);
        }
    }

    final int CONTEXT_MENU_DELETE = 1;
    final int CONTEXT_MENU_EDIT = 2;
    final int CONTEXT_MENU_MARK_AS_PAID = 3;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.add(0, CONTEXT_MENU_DELETE, 0, "Usuń");
        menu.add(0, CONTEXT_MENU_EDIT, 1, "Edytuj");
        menu.add(0, CONTEXT_MENU_MARK_AS_PAID, 2, "Oznacz jako zapłacona");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Faktura faktura = Cache.getInstance().getZaznaczonaFaktura();
        ZarzadzanieFakturami zarzadzanieFakturami = new ZarzadzanieFakturami(getSharedPreferences("zapisaneFaktury", Context.MODE_PRIVATE));
        switch (item.getItemId()) {
            case 1:
                zarzadzanieFakturami.usunFakture(faktura);
                wyswietlFaktury();
                Cache.getInstance().setZaznaczonaFaktura(null);
                break;
            case 2:
                Cache.getInstance().setEdytowanaFaktura(faktura);
                Intent intent = new Intent(MainActivity.this, DodajActivity.class);
                startActivity(intent);
                break;
            case 3:
                if(faktura.isCzySiePowtarza()) {
                    zarzadzanieFakturami.usunFakture(faktura);
                    faktura.przedluzFakture();
                    zarzadzanieFakturami.dodajFakture(faktura);
                    Toast.makeText(this, "Faktura została oznaczona jako opłacona", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this, "Faktura do opłacenia jednorazowo", Toast.LENGTH_LONG).show();
                }
                wyswietlFaktury();
            default:

        }
        return true;
    }

    @Override
    public void onContextMenuClosed(Menu menu) {
        Cache.getInstance().setZaznaczonaFaktura(null);
        super.onContextMenuClosed(menu);
    }
}
