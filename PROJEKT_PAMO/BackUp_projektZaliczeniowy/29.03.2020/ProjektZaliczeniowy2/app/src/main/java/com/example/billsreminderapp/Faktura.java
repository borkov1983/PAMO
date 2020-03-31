package com.example.billsreminderapp;

//import android.support.annotation.NonNull;

import androidx.annotation.NonNull;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Faktura implements Comparable {
    private String odbiorca;
    private Double kwota;
    private Date termin;
    private String uwagi;
    private Integer coIlePlatnosc = 0;
    private boolean czySiePowtarza;
    private String okresPowtarzania;
    private Integer ilePrzedPowiadomic;
    private boolean czyOplacona;

    public Faktura(String odbiorca, Double kwota, String termin, String uwagi){
        this.odbiorca = odbiorca;
        this.kwota = kwota;
        this.uwagi = uwagi;
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", new Locale("pl", "PL"));
        try {
            this.termin =  df.parse(termin);
        } catch (ParseException e) {
            this.termin = null;
        }
    }

    public void przedluzFakture() {
        DateTime oryginalnaData = new DateTime(this.getTermin());
        DateTime przedluzonaData;
        switch (this.okresPowtarzania) {
            case "DZIEŃ":
                przedluzonaData = oryginalnaData.plusDays(this.getCoIlePlatnosc());
                break;
            case "TYDZIEŃ":
                przedluzonaData = oryginalnaData.plusWeeks(this.getCoIlePlatnosc());
                break;
            case "MIESIĄC":
                przedluzonaData = oryginalnaData.plusMonths(this.getCoIlePlatnosc());
                break;
            case "ROK":
                przedluzonaData = oryginalnaData.plusYears(this.getCoIlePlatnosc());
                break;
            default:
                przedluzonaData = oryginalnaData;
        }
        this.setTermin(przedluzonaData.toDate());
    }

    public String getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(String odbiorca) {
        this.odbiorca = odbiorca;
    }

    public Double getKwota() {
        return kwota;
    }

    public void setKwota(Double kwota) {
        this.kwota = kwota;
    }

    public Date getTermin() {
        return termin;
    }

    public String getTerminString() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(this.termin);
    }

    public String getTerminDoWyswietlenia() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(this.termin);
    }

    public Integer getCoIlePlatnosc() {
        return coIlePlatnosc;
    }

    public boolean isCzySiePowtarza() {
        return czySiePowtarza;
    }

    public String getOkresPowtarzania() {
        return okresPowtarzania;
    }

    public Integer getIlePrzedPowiadomic() {
        return ilePrzedPowiadomic;
    }

    public boolean isCzyOplacona() {
        return czyOplacona;
    }

    public void setTermin(Date termin) {
        this.termin = termin;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    public void setCoIlePlatnosc(Integer coIlePlatnosc) {
        this.coIlePlatnosc = coIlePlatnosc;
    }

    public void setCzySiePowtarza(boolean czySiePowtarza) {
        this.czySiePowtarza = czySiePowtarza;
    }

    public void setOkresPowtarzania(String okresPowtarzania) {
        this.okresPowtarzania = okresPowtarzania;
    }

    public void setIlePrzedPowiadomic(Integer ilePrzedPowiadomic) {
        this.ilePrzedPowiadomic = ilePrzedPowiadomic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faktura faktura = (Faktura) o;

        if (czySiePowtarza != faktura.czySiePowtarza) return false;
        if (czyOplacona != faktura.czyOplacona) return false;
        if (!odbiorca.equals(faktura.odbiorca)) return false;
        if (!kwota.equals(faktura.kwota)) return false;
        if (!termin.equals(faktura.termin)) return false;
        if (!uwagi.equals(faktura.uwagi)) return false;
        if (!coIlePlatnosc.equals(faktura.coIlePlatnosc)) return false;
        if (!okresPowtarzania.equals(faktura.okresPowtarzania)) return false;
        return ilePrzedPowiadomic.equals(faktura.ilePrzedPowiadomic);
    }

    @Override
    public int hashCode() {
        int result = odbiorca.hashCode();
        result = 31 * result + kwota.hashCode();
        result = 31 * result + termin.hashCode();
        result = 31 * result + uwagi.hashCode();
        result = 31 * result + coIlePlatnosc.hashCode();
        result = 31 * result + (czySiePowtarza ? 1 : 0);
        result = 31 * result + okresPowtarzania.hashCode();
        result = 31 * result + ilePrzedPowiadomic.hashCode();
        result = 31 * result + (czyOplacona ? 1 : 0);
        return result;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return this.termin.compareTo(((Faktura) o).getTermin());
    }
}
