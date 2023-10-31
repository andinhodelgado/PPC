package br.com.usinasantafe.ppc.control;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.variaveis.AmostraBean;
import br.com.usinasantafe.ppc.model.dao.AmostraDAO;
import br.com.usinasantafe.ppc.model.dao.AuditorDAO;
import br.com.usinasantafe.ppc.model.dao.CabecalhoDAO;
import br.com.usinasantafe.ppc.model.dao.ColhedoraDAO;
import br.com.usinasantafe.ppc.model.dao.OSDAO;
import br.com.usinasantafe.ppc.model.dao.OperadorDAO;
import br.com.usinasantafe.ppc.util.EnvioDadosServ;

public class PerdaCTR {

    private CabecalhoDAO cabecalhoDAO;
    private AmostraDAO amostraDAO;

    public PerdaCTR() {
    }

    public CabecalhoDAO getCabecDAO(){
        if (cabecalhoDAO == null)
            cabecalhoDAO = new CabecalhoDAO();
        return cabecalhoDAO;
    }

    public AmostraDAO getAmostraDAO(){
        if (amostraDAO == null)
            amostraDAO = new AmostraDAO();
        return amostraDAO;
    }

    public void fecharAnalise(Long idCabec){
        CabecalhoDAO cabecalhoDAO = new CabecalhoDAO();
        cabecalhoDAO.fecharCabec(idCabec);

        EnvioDadosServ.getInstance().envioDados();


    }

    public void deletarAnalise(Long idCabec){
        CabecalhoDAO cabecalhoDAO = new CabecalhoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        amostraDAO.delAmostraIdCabec(idCabec);
        cabecalhoDAO.excluirCabec(idCabec);
    }

    public String dadosAnaliseEnvio(){

        CabecalhoDAO cabecalhoDAO = new CabecalhoDAO();
        AmostraDAO amostraDAO = new AmostraDAO();
        String dadosCabecEnvio = cabecalhoDAO.dadosEnvioCabecFechado();
        String dadosAmostraEnvio = amostraDAO.dadosEnvioAmostra(cabecalhoDAO.idCabecFechadoList());

        return dadosCabecEnvio + "_" + dadosAmostraEnvio;

    }

    /////////////////////////////////// CABECALHO ////////////////////////////////////////////////

    public boolean verifCabecFechado(){
        return cabecalhoDAO.verifCabecFechado();
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// AMOSTRA //////////////////////////////////////////////////

    public void salvarAmostra(){
        amostraDAO.salvarAmostra(cabecalhoDAO.getCabecAberto().getIdCabec());
    }

    public boolean verifAmostraIdCabec(Long idCabec){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.verifAmostraIdCabec(idCabec);
    }

    public int qtdeAmostra(Long idCabec){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.qtdeAmostra(idCabec);
    }

    public List<AmostraBean> getAmostraList(Long idCabec){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.getAmostraList(idCabec);
    }

    public int countAmostraList(Long idCabec){
        AmostraDAO amostraDAO = new AmostraDAO();
        return amostraDAO.countAmostraList(idCabec);
    }

    public void delAmostraId(Long idAmostra){
        AmostraDAO amostraDAO = new AmostraDAO();
        amostraDAO.delAmostraId(idAmostra);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// AUDITOR //////////////////////////////////////////////////

    public boolean hasElemAuditor(){
        AuditorDAO auditorDAO = new AuditorDAO();
        return  auditorDAO.hasElements();
    }

    public boolean verifAuditor(Long matricAuditor){
        AuditorDAO auditorDAO = new AuditorDAO();
        return auditorDAO.verifAuditor(matricAuditor);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////// OS //////////////////////////////////////////////////

    public boolean verifOS(Long nroOS){
        OSDAO osDAO = new OSDAO();
        return osDAO.verifOS(nroOS);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////// COLHEDORA ////////////////////////////////////////////////

    public boolean verifColhedora(Long codColhedora){
        ColhedoraDAO colhedoraDAO = new ColhedoraDAO();
        return colhedoraDAO.verifColhedora(codColhedora);
    }

    public boolean verifColhedoraRepetido(Long codColhedora){
        CabecalhoDAO cabecalhoDAO = new CabecalhoDAO();
        return cabecalhoDAO.verifColhedoraRepetida(codColhedora);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////// OPERADOR ///////////////////////////////////////////////

    public boolean verifOperador(Long matricAuditor){
        OperadorDAO operadorDAO = new OperadorDAO();
        return operadorDAO.verifOperador(matricAuditor);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

}
