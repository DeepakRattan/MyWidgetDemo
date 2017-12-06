package com.example.deepakrattan.mywidgetdemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Deepak Rattan on 04-Dec-17.
 */

public class MyWidgetProvider extends AppWidgetProvider {


    //Updating App Widget UI from within an App Widget Provider’s update handler
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        //Iterate through each widget
        for (int i = 0; i < appWidgetIds.length; i++) {

            int widgetID = appWidgetIds[i];

            //Create a Remote View
            //Remote Views represent a View hierarchy displayed in another process — in this instance,
            // it will be  used to define a set of changes to be applied to the UI of a running Widget.
            //Simply to access the view hierarchy of app widget's layout we are using the remote view

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            //Create random number
            int number = (int) (Math.random() * 100);
            //Set the text
            remoteViews.setTextViewText(R.id.txtUpdate, String.valueOf(number));

            Intent intent = new Intent(context, MyWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            //The PendingIntent class provides a mechanism for creating Intents that can
            // be fired on your application’s behalf by another application at a later time.

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.txtUpdate, pendingIntent);

            // Notify the App Widget Manager to update the widget using
            // the modified remote view.
            appWidgetManager.updateAppWidget(widgetID, remoteViews);


        }


    }
}
