package com.wp.carlos4web.google.docs;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.Query;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentListEntry;
import com.google.gdata.data.docs.FolderEntry;
import com.google.gdata.data.gtt.DocumentEntry;
import com.google.gdata.data.gtt.DocumentFeed;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class FirstFolderListAndUploadExample {

	public static void main(String[] args) {
		DocsService service = new DocsService("Carlao-app");
		
		try {
			File arquivo = new File("/home/carlos/Downloads/gdata/COPYING");
			
			com.google.gdata.data.docs.DocumentEntry document = new com.google.gdata.data.docs.DocumentEntry();
			
			document.setDescription("chips");
	        document.setFile(arquivo, "text/plain");
	        document.setTitle(new PlainTextConstruct(arquivo.getName()));
	        
	        service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			String url = "https://docs.google.com/feeds/default/private/full/-/folder";
			
			URL feedURI = new URL(url);
			DocumentFeed feed = service.query(new Query(feedURI), DocumentFeed.class);
			
			List<DocumentEntry> entries = feed.getEntries();
			
			
			int i = 0;
			for (DocumentEntry doc : entries) {
				System.out.println(doc.getTitle().getPlainText());
				System.out.println(doc.getId());
				System.out.println(doc.getHtmlLink().getHref());
				
				if(i == 2){
					break;
				}
				i++;
				
				DocumentListEntry folder = new FolderEntry();
				folder.setId(doc.getId());
				folder.setMediaSource(new MediaFileSource(arquivo, "text/plain"));
				
				System.out.println("Inserindo...");
				service.insert(new URL(doc.getId()), folder);
				System.out.println("inserido!");
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
