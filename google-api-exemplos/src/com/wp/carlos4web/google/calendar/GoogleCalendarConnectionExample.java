package com.wp.carlos4web.google.calendar;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class GoogleCalendarConnectionExample {

	public static void main(String[] args) {
		
		CalendarService service = new CalendarService("@carlosjrcabello");
		
		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			URL url = new URL("https://www.google.com/calendar/feeds/default/allcalendars/full");
			
			CalendarFeed feed = service.getFeed(url, CalendarFeed.class);
			
			List<CalendarEntry> calendarios = feed.getEntries();
			
			for (int i = 0; i < calendarios.size(); i++) {
				CalendarEntry cal = calendarios.get(i);
				
				System.out.println(cal.getTitle().getPlainText());
			}				
			
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
