package com.xiaofan.contentobseverdemo.util;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.xiaofan.contentobseverdemo.R;
import com.xiaofan.contentobseverdemo.application.App;

/**
 * 顶部通知栏工具类
 */
public class NotifacationUtil {

    private static int NOTIFY_ID = 0;

    @SuppressWarnings({"deprecation", "static-access"})
    public static void sendNotifacation(String titleString, String content, Intent intent) {

        NOTIFY_ID = ( ++NOTIFY_ID >= 3) ? 0 : NOTIFY_ID;

        NotificationManager mNotificationManager = (NotificationManager) App.getInstance().getSystemService(App.getInstance().NOTIFICATION_SERVICE);
        Notification n = new Notification(R.drawable.contact_photo, titleString, System.currentTimeMillis());

        n.flags = Notification.FLAG_AUTO_CANCEL;
        n.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        if (intent != null) {
            PendingIntent pIntent = PendingIntent.getActivity(App.getInstance(), NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            n.setLatestEventInfo(App.getInstance(), titleString, content, pIntent);
        } else {
            n.setLatestEventInfo(App.getInstance(), titleString, content, null);
        }
        mNotificationManager.notify(NOTIFY_ID, n);
    }

    /**
     * 消除创建的所有通知
     */
    public static void cancelAllNotifacation() {

        NotificationManager mNotificationManager =
                (NotificationManager) App.getInstance().getSystemService(App.getInstance().NOTIFICATION_SERVICE);
        mNotificationManager.cancelAll();
    }
}
