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

package com.codeskraps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.RemoteViews;

public class UpdateWidgetService extends Service {
	private static final String TAG = UpdateWidgetService.class.getSimpleName();
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		Log.d(TAG, "onStart started");
		
		// Create some random data
		Random random = new Random();
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this.getApplicationContext());

		int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		
		if (appWidgetIds.length > 0) {
		
			for (int widgetId : appWidgetIds) {
				List<String> qList = getQuotes();
				int nextInt = random.nextInt(qList.size());
				
				RemoteViews remoteViews = new RemoteViews(getPackageName(),	R.layout.widget);
				remoteViews.setTextViewText(R.id.widget_textview, qList.get(nextInt));
				appWidgetManager.updateAppWidget(widgetId, remoteViews);
			}
			stopSelf();
		}
		super.onStart(intent, startId);
	}
	
	public List<String> getQuotes(){
		List<String> qList = new ArrayList<String>();
		qList.add("When Life Gives You Questions, Google has Answers");
		qList.add("1f u c4n r34d th1s u r34lly n33d t0 g37 l41d ");
		qList.add("Microsoft: \"You've got questions. We've got dancing paperclips.\"");
		qList.add("If at first you don't succeed; call it version 1.0 ");
		qList.add("There are 10 types of people in the world: those who understand binary, and those who don't.");
		qList.add("I'm not anti-social; I'm just not user friendly");
		qList.add("The glass is neither half-full nor half-empty: it's twice as big as it needs to be.");
		qList.add("I would love to change the world, but they won't give me the source code");
		qList.add("A Life? Cool! Where can I download one of those?");
		qList.add("Artificial Intelligence is no match for Natural Stupidity.");
		qList.add("Windows has detected you do not have a keyboard. Press 'F9\" to continue.");
		qList.add("In a world without fences and walls, who needs Gates and Windows?");
		qList.add("MICROSOFT = Most Intelligent Customers Realize Our Software Only Fools Teenagers");
		qList.add("\"Concept: On the keyboard of life, always keep one finger on the escape button.\"");
		qList.add("My software never has bugs. It just develops random features.");
		qList.add("The box said 'Requires Windows 95 or better'. So I installed LINUX.");
		qList.add("Never make fun of the geeks, one day they will be your boss.");
		qList.add("Girls are like internet domain names, the ones I like are already taken.");
		qList.add("Better to be a geek than an idiot.");
		qList.add("Failure is not an option -- it comes bundled with Windows.");
		return qList;
	}
}
