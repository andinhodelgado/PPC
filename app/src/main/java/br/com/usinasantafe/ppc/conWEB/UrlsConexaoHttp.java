package br.com.usinasantafe.ppc.conWEB;

import br.com.usinasantafe.ppc.PPCContext;

public class UrlsConexaoHttp {

	public static String versao = "versao_" + PPCContext.versaoWS.replace(".", "_");

  public static String urlPrincipal = "https://www.usinasantafe.com.br/ppcdev/view/";
//	public static String urlPrincipal = "https://www.usinasantafe.com.br/ppcqa/view/";
//    public static String urlPrincipal = "https://www.usinasantafe.com.br/ppcprod/" + versao + "/view/";
	
	public static String localPSTVariavel = "br.com.usinasantafe.ppc.to.tb.variaveis.";
	public static String localPSTEstatica = "br.com.usinasantafe.ppc.to.tb.estaticas.";
	public static String localUrl = "br.com.usinasantafe.ppc.conWEB.UrlsConexaoHttp";
	
	public static String AuditorESTTO = urlPrincipal + "auditor.php";
	public static String ColhedoraESTTO = urlPrincipal + "colhedora.php";
	public static String ObservacaoESTTO = urlPrincipal + "observacao.php";
	public static String OperadorESTTO = urlPrincipal + "operador.php";
	public static String TipoAmostradorESTTO = urlPrincipal + "tipoamostrador.php";
	public static String OSESTTO = urlPrincipal + "os.php";

	public UrlsConexaoHttp() {
	}

	public String getsInsereAnaliseProd(){
		return urlPrincipal + "apontperda.php";
	}
	
}