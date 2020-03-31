package com.example.micha.billsreminder_251118.feature;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ZarzadzanieFakturami {

    SharedPreferences sharedPreferences;

    public ZarzadzanieFakturami(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public Set<Faktura> pobierzFaktury() {
        Set<String> fakturyJson = sharedPreferences.getStringSet("listaFaktur", new HashSet<String>());
        final Gson gson = new Gson();
        Set<Faktura> faktury = new HashSet<>();

        for (String fakturaString: fakturyJson) {
            faktury.add(gson.fromJson(fakturaString, Faktura.class));
        }
        return faktury;
    }

    public Set<Faktura> pobierzZalegle() {
        Set<Faktura> wszystkieFaktury = this.pobierzFaktury();

        Date now = new Date();
        Set<Faktura> faktury = new HashSet<>();

        for (Faktura fak: wszystkieFaktury) {
            if(fak.getTermin().compareTo(now) <= 0){
                faktury.add(fak);
            }
        }
        return faktury;
    }

    public Set<Faktura> pobierzPrzyszle() {
        Set<Faktura> wszystkieFaktury = this.pobierzFaktury();

        Date now = new Date();
        Set<Faktura> faktury = new HashSet<>();

        for (Faktura fak: wszystkieFaktury) {
            if(fak.getTermin().compareTo(now) > 0){
                faktury.add(fak);
            }
        }
        return faktury;
    }

    public Set<Faktura> pobierzZblizajace() {
        Set<Faktura> wszystkieFaktury = this.pobierzFaktury();
        Set<Faktura> faktury = new HashSet<>();

        for (Faktura fak: wszystkieFaktury) {
            Integer ilePrzedPowiadomic = fak.getIlePrzedPowiadomic();
            if(ilePrzedPowiadomic != 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                calendar.add(Calendar.DAY_OF_MONTH, ilePrzedPowiadomic);

                Calendar today = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                if(fak.getTermin().after(today.getTime()) && fak.getTermin().compareTo(calendar.getTime()) <= 0){
                    faktury.add(fak);
                }
            }
        }
        return faktury;
    }

    public void usunFakture(Faktura faktura) {
        Set<String> fakturyJson = sharedPreferences.getStringSet("listaFaktur", new HashSet<String>());
        final Gson gson = new Gson();
        Set<Faktura> faktury = new HashSet<>();

        for (String fakturaString: fakturyJson) {
            faktury.add(gson.fromJson(fakturaString, Faktura.class));
        }
        faktury.remove(faktura);

        Set<String> noweFaktury = new HashSet<>();
        for (Faktura faktura1: faktury) {
            noweFaktury.add(gson.toJson(faktura1));
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("listaFaktur", noweFaktury).apply();
    }

    public void dodajFakture(Faktura faktura) {
        Set<String> zapisaneFaktury = sharedPreferences.getStringSet("listaFaktur", new HashSet<String>());
        Set<String> noweFaktury = new HashSet<>(zapisaneFaktury);
        Gson gson = new Gson();

        String fakturaString = gson.toJson(faktura);
        noweFaktury.add(fakturaString);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("listaFaktur", noweFaktury).apply();
    }
}
