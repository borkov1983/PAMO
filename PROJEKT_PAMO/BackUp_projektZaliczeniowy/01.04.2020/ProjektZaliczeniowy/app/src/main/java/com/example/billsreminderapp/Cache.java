package com.example.billsreminderapp;

public class Cache {
    private Faktura zaznaczonaFaktura = null;
    private Faktura edytowanaFaktura = null;

    private static final Cache cache = new Cache();
    public static Cache getInstance() {return cache;}

    public Faktura getZaznaczonaFaktura() {
        return zaznaczonaFaktura;
    }

    public void setZaznaczonaFaktura(Faktura zaznaczonaFaktura) {
        this.zaznaczonaFaktura = zaznaczonaFaktura;
    }

    public Faktura getEdytowanaFaktura() {
        return edytowanaFaktura;
    }

    public void setEdytowanaFaktura(Faktura edytowanaFaktura) {
        this.edytowanaFaktura = edytowanaFaktura;
    }
}
