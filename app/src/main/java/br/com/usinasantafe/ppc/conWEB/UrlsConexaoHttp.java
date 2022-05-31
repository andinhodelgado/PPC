package br.com.usinasantafe.ppc.conWEB;

import br.com.usinasantafe.ppc.PPCContext;

public class UrlsConexaoHttp {

	public static String versao = "versao_" + PPCContext.versaoWS.replace(".", "_");

//  public static String url = "https://www.usinasantafe.com.br/ppcdev/view/";
	public static String url = "https://www.usinasantafe.com.br/ppcqa/view/";
//    public static String url = "https://www.usinasantafe.com.br/ppcprod/" + versao + "/view/";
	
	public static String localPSTVariavel = "br.com.usinasantafe.ppc.to.tb.variaveis.";
	public static String localPSTEstatica = "br.com.usinasantafe.ppc.to.tb.estaticas.";
	public static String localUrl = "br.com.usinasantafe.ppc.conWEB.UrlsConexaoHttp";
	
	public static String AuditorESTTO = url + "auditor.php";
	public static String ColhedoraESTTO = url + "colhedora.php";
	public static String ObservacaoESTTO = url + "observacao.php";
	public static String OperadorESTTO = url + "operador.php";
	public static String TipoAmostradorESTTO = url + "tipoamostrador.php";
	public static String OSESTTO = url + "os.php";

	public UrlsConexaoHttp() {
	}

	public String getsInsereAnaliseProd(){
		return url + "apontperda.php";
	}
	
}