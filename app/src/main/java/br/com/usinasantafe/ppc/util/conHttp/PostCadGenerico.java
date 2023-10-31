package br.com.usinasantafe.ppc.util.conHttp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import br.com.usinasantafe.ppc.util.EnvioDadosServ;

import android.os.AsyncTask;
import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class PostCadGenerico extends AsyncTask<String, Void, String>  {

	private static PostCadGenerico instance = null;
	private Map<String, Object> parametrosPost = null;
	
	public PostCadGenerico() {
	}
	
    public static PostCadGenerico getInstance() {
        if (instance == null)
        instance = new PostCadGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... arg) {
		
		BufferedReader bufferedReader = null;
		String resultado = null;
		
		String url = arg[0];
		
		try {

			String parametros = getQueryString(parametrosPost);
			URL urlCon = new URL(url);
			HttpsURLConnection connection = (HttpsURLConnection) urlCon.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts(), new java.security.SecureRandom());
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier((s, sslSession) -> true);
			connection.connect();

			OutputStream out = connection.getOutputStream();
			byte[] bytes = parametros.getBytes("UTF8");
			out.write(bytes);
			out.flush();
			out.close();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line + LS);
			}
			bufferedReader.close();
			resultado = stringBuffer.toString();
			
		} catch (Exception e) {
			Log.i("ERRO", "Erro = " + e);
			
			if(bufferedReader != null){
				try {
					bufferedReader.close();
					EnvioDadosServ.status = 1;
				} catch (Exception e2) {
					Log.i("ERRO", "Erro = " + e);
				}
				
			}
			
		} finally {
			
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception e) {
					Log.i("ERRO", "Erro = " + e);
				}
				
			}
			
		}
		return resultado;
		
	}
    
	protected void onPostExecute(String result) {

		try {
			Log.i("ECM", "VALOR RECEBIDO --> " + result);
			if(result.trim().equals("GRAVOU-ANALISE")){
				EnvioDadosServ.getInstance().deletarAnalise();
			}
			else{
				EnvioDadosServ.status = 1;
			}
		} catch (Exception e) {
			Log.i("ERRO", "Erro2 = " + e);
			EnvioDadosServ.status = 1;
		}
		
    }

	public void setParametrosPost(Map<String, Object> parametrosPost) {
		this.parametrosPost = parametrosPost;
	}

	private String getQueryString(Map<String, Object> params) {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		Iterator<String> e = params.keySet().iterator();
		while (e.hasNext()) {
			String chave = e.next();
			Object objValor = params.get(chave);
			String valor = objValor.toString();
			urlParams = urlParams == null ? "" : urlParams + "&";
			urlParams += chave + "=" + valor;
		}
		return urlParams;
	}

	public TrustManager[] trustAllCerts(){
		return new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers()
					{
						return null;
					}
					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {}
				}
		};
	}
}
