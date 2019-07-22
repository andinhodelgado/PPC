package br.com.usinasantafe.ppc.conWEB;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import br.com.usinasantafe.ppc.bo.ManipDadosEnvio;

import android.os.AsyncTask;
import android.util.Log;

public class ConHttpPostGenerico extends AsyncTask<String, Void, String>  {

	private static ConHttpPostGenerico instance = null;
	private Map<String, Object> parametrosPost = null;
	
	public ConHttpPostGenerico() {
		// TODO Auto-generated constructor stub
	}
	
    public static ConHttpPostGenerico getInstance() {
        if (instance == null)
        instance = new ConHttpPostGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... arg) {
		// TODO Auto-generated method stub
		
		BufferedReader bufferedReader = null;
		String resultado = null;
		
		String url = arg[0];
		
		try {

			String parametros = getQueryString(parametrosPost);
			URL urlCon = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
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
					ManipDadosEnvio.getInstance().respostaEnvio(false);
				} catch (Exception e2) {
					Log.i("ERRO", "Erro = " + e);
				}
				
			}
			
		}
		finally{
			
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
				ManipDadosEnvio.getInstance().deletarAnalise();
			}
			else{
				ManipDadosEnvio.getInstance().respostaEnvio(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.i("ERRO", "Erro2 = " + e);
			ManipDadosEnvio.getInstance().respostaEnvio(false);
		}
		
    }

	public void setParametrosPost(Map<String, Object> parametrosPost) {
		this.parametrosPost = parametrosPost;
	}

	private String getQueryString(Map<String, Object> params) throws Exception {
		if (params == null || params.size() == 0) {
			return null;
		}
		String urlParams = null;
		Iterator<String> e = (Iterator<String>) params.keySet().iterator();
		while (e.hasNext()) {
			String chave = (String) e.next();
			Object objValor = params.get(chave);
			String valor = objValor.toString();
			urlParams = urlParams == null ? "" : urlParams + "&";
			urlParams += chave + "=" + valor;
		}
		return urlParams;
	}
	
}
