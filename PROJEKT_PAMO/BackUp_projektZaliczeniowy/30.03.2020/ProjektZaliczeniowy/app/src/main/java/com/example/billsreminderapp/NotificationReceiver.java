package com.example.billsreminderapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import androidx.constraintlayout.widget.ConstraintLayout;

//import android.support.v4.app.NotificationCompat;

import androidx.annotation.RequiresApi;

import java.util.Set;

//odpwoiedzialny za skonstruowanie wyglądu przypomnienia

public class NotificationReceiver extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        wyswietlNotyfikacje(context, intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void wyswietlNotyfikacje(Context context, Intent intent) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            // Get the PendingIntent containing the entire back stack

            ZarzadzanieFakturami zarzadzanieFakturami = new ZarzadzanieFakturami(context.getSharedPreferences("zapisaneFaktury", Context.MODE_PRIVATE));
            Set<Faktura> zalegleFaktury = zarzadzanieFakturami.pobierzZalegle();
            Set<Faktura> zblizajaceFaktury = zarzadzanieFakturami.pobierzZblizajace();

//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, repeatingIntent,
//                    PendingIntent.FLAG_UPDATE_CURRENT);

            if(zalegleFaktury.size() > 0) {
                StringBuilder content = new StringBuilder();
                for(Faktura zalegla : zalegleFaktury) {
                    content.append(String.format("Odbiorca: %s, kwota: %szł, termin: %s \n",
                            zalegla.getOdbiorca(), zalegla.getKwota(), zalegla.getTerminDoWyswietlenia()));
                }
                Notification.Builder mBuilder = new Notification.Builder(context, pobierzIdKanalu(notificationManager));
                Notification n = mBuilder
                        .setContentTitle(String.format("%s zaległych faktur", zalegleFaktury.size()))
                        .setSmallIcon(R.drawable.ico2)
                        .setStyle(new Notification.BigTextStyle().bigText(content.toString()))
                        .setContentText(content.toString()).build();

                notificationManager.notify(0, n);
            }

            if(zblizajaceFaktury.size() > 0) {
                StringBuilder content = new StringBuilder();
                for(Faktura zblizajaca : zblizajaceFaktury) {
                    content.append(String.format("Odbiorca: %s, kwota: %szł, termin: %s \n",
                            zblizajaca.getOdbiorca(), zblizajaca.getKwota(), zblizajaca.getTerminDoWyswietlenia()));
                }
                Notification.Builder mBuilder = new Notification.Builder(context, pobierzIdKanalu(notificationManager));
                Notification n = mBuilder
                        .setContentTitle(String.format("%s zbliżających się faktur", zblizajaceFaktury.size()))
                        .setSmallIcon(R.drawable.ico2)
                        .setStyle(new Notification.BigTextStyle().bigText(content.toString()))
                        .setContentText(content.toString()).build();

                notificationManager.notify(1, n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String pobierzIdKanalu(NotificationManager notificationManager) {
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = "my_channel";
        String Description = "This is my channel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        mChannel.setDescription(Description);
        mChannel.enableLights(true);
        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        mChannel.setShowBadge(false);
        notificationManager.createNotificationChannel(mChannel);
        return CHANNEL_ID;
    }
}
