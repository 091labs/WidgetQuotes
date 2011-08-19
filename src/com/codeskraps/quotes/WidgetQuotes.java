/**
 * WidgetQuotes
 * Copyright (C) Carles Sentis 2011 <carlesentis@gmail.com>
 *
 * WidgetQuotes is free software: you can
 * redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later
 * version.
 *  
 * WidgetQuotes is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *  
 * You should have received a copy of the GNU
 * General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.codeskraps.quotes;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WidgetQuotes extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		
		// Build the intent to call the service
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);
		Intent intent = new Intent(context.getApplicationContext(), UpdateWidgetService.class);
		intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

		// To react to a click we have to use a pending intent as the
		// onClickListener is
		// excecuted by the homescreen application
		PendingIntent pendingIntent = PendingIntent.getService(
				context.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		remoteViews.setOnClickPendingIntent(R.id.widget_textview, pendingIntent);

		// Finally update all widgets with the information about the click
		// listener
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);

		// Update the widgets via the service
		context.startService(intent);
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}
}