package com.wp.carlos4web.google.calendar;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.extensions.EventEntry;
import com.google.gdata.data.extensions.EventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class GoogleCalendarCarregaPorID {

	public static void main(String[] args) {
		
		CalendarService service = new CalendarService("@carlosjrcabello");
		
		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			URL eventURL = new URL("https://www.google.com/calendar/feeds/carlosjrcabello@gmail.com/private/full");
			EventFeed eventFeed = service.getFeed(eventURL, EventFeed.class);
			
			List<EventEntry> eventos = eventFeed.getEntries();
			
			for (EventEntry evento : eventos) {
				System.out.println(evento.getTitle().getPlainText());
				System.out.println(evento.getId());
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
