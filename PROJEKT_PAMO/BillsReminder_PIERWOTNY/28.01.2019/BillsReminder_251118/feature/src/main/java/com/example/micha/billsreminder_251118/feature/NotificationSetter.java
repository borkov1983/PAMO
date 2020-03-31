package com.example.micha.billsreminder_251118.feature;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import org.joda.time.DateTime;
import org.joda.time.Interval;

public class NotificationSetter {
    Context context;
    AlarmManager alarmManager;

    public NotificationSetter(Context context, AlarmManager alarmManager) {
        this.context = context;
        this.alarmManager = alarmManager;
    }

    public void createRepeatNotification() {
        //utworzenie powiadomienia i przekazanie do niego parametrów
        Intent intent = new Intent(context, NotificationReceiver.class);
        //ustawienie alarmu
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        long intervalMinute = 60 * 1000;
        long intervalDay = AlarmManager.INTERVAL_DAY;
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  new DateTime().withTime(10, 30, 0, 0).getMillis(),
                intervalMinute, pendingIntent);
    }
}
