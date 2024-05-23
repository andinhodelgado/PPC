package br.com.usinasantafe.ppc.util.conHttp;

import br.com.usinasantafe.ppc.PPCContext;

public class UrlsConexaoHttp {

	public static String versao = "versao_" + PPCContext.versaoWS.replace(".", "_");

//  public static String url = "https://www.usinasantafe.com.br/ppcdev/view/";
//	public static String url = "https://www.usinasantafe.com.br/ppcqa/view/";
    public static String url = "https://www.usinasantafe.com.br/ppcprod/" + versao + "/view/";

	public static String localPSTEstatica = "br.com.usinasantafe.ppc.model.bean.estaticas.";
	public static String localUrl = "br.com.usinasantafe.ppc.util.conHttp.UrlsConexaoHttp";
	
	public static String AuditorBean = url + "auditor.php";
	public static String ColhedoraBean = url + "colhedora.php";
	public static String OperadorBean = url + "operador.php";
	public static String OSBean = url + "os.php";
	public static String TalhaoBean = url + "talhao.php";

	public UrlsConexaoHttp() {
	}

	public String getsInserirAnalise(){
		return url + "inseriranalise.php";
	}

	public String urlVerifica(String classe) {
		String retorno = "";
		if (classe.equals("Token")) {
			retorno = url + "aparelho.php";
		}
		return retorno;
	}

}