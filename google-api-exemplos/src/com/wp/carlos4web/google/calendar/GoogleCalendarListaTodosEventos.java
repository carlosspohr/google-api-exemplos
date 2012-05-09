package com.wp.carlos4web.google.calendar;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class GoogleCalendarListaTodosEventos {

	public static void main(String[] args) {
		
		CalendarService service = new CalendarService("@carlosjrcabello");
		
		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			URL eventURL = new URL("https://www.google.com/calendar/feeds/carlosjrcabello@gmail.com/private/full");
			CalendarEventFeed eventFeed = service.getFeed(eventURL, CalendarEventFeed.class);
			
			List<CalendarEventEntry> eventos = eventFeed.getEntries();
			
			for (CalendarEventEntry evento : eventos) {
				System.out.println("Evento: " + evento.getTitle().getPlainText());
				System.out.println(evento.getIcalUID());
			}
			
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
