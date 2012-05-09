package com.wp.carlos4web.google.docs;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gdata.client.Query;
import com.google.gdata.client.docs.DocsService;
import com.google.gdata.data.MediaContent;
import com.google.gdata.data.gtt.DocumentEntry;
import com.google.gdata.data.gtt.DocumentFeed;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import com.wp.carlos4web.google.auth.CredencialStaticProvider;

public class FirstFolderDownloadExample {
	
	public static void readUrl(URL url, DocsService service) throws Exception {
		MediaContent mc = new MediaContent();
		mc.setUri(url.toString());
		MediaSource ms = service.getMedia(mc);
		
		InputStream is = ms.getInputStream();
		byte[] bytes = IOUtils.toByteArray(is);
		
		IOUtils.write(bytes, new FileOutputStream("/home/carlos/Downloads/temps/novo.doc"));
	}
	
	public static void saveUrl(String filename, String urlString) throws MalformedURLException, IOException
    {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try
        {
                in = new BufferedInputStream(new URL(urlString).openStream());
                fout = new FileOutputStream(filename);

                byte data[] = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1)
                {
                        fout.write(data, 0, count);
                }
        }
        finally
        {
                if (in != null)
                        in.close();
                if (fout != null)
                        fout.close();
        }
    }

	public static void main(String[] args) throws Exception {
		DocsService service = new DocsService("@carlosjrcabello");

		try {
			service.setUserCredentials(CredencialStaticProvider.EMAIL, CredencialStaticProvider.SENHA);

			String url = "https://docs.google.com/feeds/default/private/full";
			URL feedURI = new URL(url);
			DocumentFeed feed = service.query(new Query(feedURI), DocumentFeed.class);

			List<DocumentEntry> entries = feed.getEntries();
			int i = 0;
			for (DocumentEntry doc : entries) {
				System.out.println(doc.getTitle().getPlainText());
				System.out.println(doc.getId());
				
				if (doc.getId().equals("https://docs.google.com/feeds/id/document%3A1Nt4UVIfrOqW_0b3fOIM91rabVTK7Q5qaSuWrzPowWwI")) {

					String id = doc.getId().replaceAll("https://docs.google.com/feeds/id/document%3A", "");
					
					id = "https://docs.google.com/feeds/download/documents/Export?docID=" + id + "&exportFormat=doc&format=doc";
					System.out.println(id);
					
					URL ur = new URL(id);
					
					readUrl(ur, service);
				}

				i++;
				if (i == 1) {
					break;
				}
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
