package br.com.usinasantafe.ppc.conWEB;


import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;
import android.util.Log;

public class ConHttpMultipartPost extends AsyncTask<String, Void, String>   {

	private String url = "http://internal.usinasantafe.com.br:8180/WebServiceTeste/UploadServlet";
	private byte[] data;
	
	public ConHttpMultipartPost() {
		// TODO Auto-generated constructor stub
	}
	
	public ConHttpMultipartPost(byte[] data) {
		this.data = data;
	}

	@Override
	protected String doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		try{
			/*
			HttpClientMultipart client = new HttpClientMultipart(url);
			client.connectForMultipart();
			client.addFilePart("upload", "teste.jpg", data);
			client.finishMultipart();
			String data = client.getResponse();
			Log.i("ECM Teste", data);
			*/
			Log.i("ECM Teste", "");
		} catch (Exception e) {
			
		}
		finally{
			
		}
		
		return null;
	}
	
	
	
}
