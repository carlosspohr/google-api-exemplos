package com.wp.carlos4web.google.docs;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.PlainTextConstruct;
import com.google.gdata.data.docs.DocumentEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class FirstUploadExample {

	public static void main(String[] args) {
		DocsService service = new DocsService("Carlao-app");
		
		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);
			
			File arquivo = new File("/home/carlos/Documentos/champz.doc");
			
			DocumentEntry doc = new DocumentEntry();
			
			doc.setDescription("chips tio joão");
	        doc.setFile(arquivo, "text/plain");
	        doc.setTitle(new PlainTextConstruct(arquivo.getName()));
			doc.setEtag("e-tag");
			String url = "https://docs.google.com/feeds/default/private/full";
			URL feedURI = new URL(url);
			
			DocumentEntry insert = service.insert(feedURI, doc);
			
			System.out.println(insert.getFilename());
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
