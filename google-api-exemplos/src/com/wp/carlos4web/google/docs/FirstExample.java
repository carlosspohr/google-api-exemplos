package com.wp.carlos4web.google.docs;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.DocumentListFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class FirstExample {

	public static void main(String[] args) {
		DocsService service = new DocsService("@carlosjrcabello");
		
		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			String url = "https://docs.google.com/feeds/default/private/full";
			
			URL feedURI = new URL(url);
			
			DocumentListFeed feed = service.getFeed(feedURI, DocumentListFeed.class);
			
			List<DocumentListEntry> entries = feed.getEntries();
			
			for (DocumentListEntry doc : entries) {
				System.out.println(doc.getFilename());
				System.out.println(doc.getDocumentLink().getHref());
				System.out.println(doc.getDocumentLink().getTitle());
			}
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
