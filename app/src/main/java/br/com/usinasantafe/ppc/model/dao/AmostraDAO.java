package br.com.usinasantafe.ppc.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.model.bean.variaveis.AmostraBean;
import br.com.usinasantafe.ppc.model.pst.EspecificaPesquisa;

public class AmostraDAO {

    private AmostraBean amostraBean;

    public AmostraDAO() {
    }

    public AmostraBean getAmostraBean() {
        if (amostraBean == null)
            amostraBean = new AmostraBean();
        return amostraBean;
    }

    public boolean verifAmostraIdCabec(Long idCabecAmostra){
        List<AmostraBean> amostraList = getAmostraList(idCabecAmostra);
        boolean ret = amostraList.size() > 0;
        amostraList.clear();
        return ret;
    }

    public int qtdeAmostra(Long idCabecAmostra){
        List<AmostraBean> amostraList = getAmostraList(idCabecAmostra);
        int ret = amostraList.size() + 1;
        amostraList.clear();
        return ret;
    }

    public void salvarAmostra(Long idCabecPerda){
        amostraBean.setIdCabecAmostra(idCabecPerda);
        amostraBean.insert();
    }

    public void delAmostraId(Long idAmostra){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdAmostra(idAmostra));
        AmostraBean amostraBean = new AmostraBean();
        List<AmostraBean> amostraBeanList = amostraBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraBean = amostraBeanList.get(0);
        amostraBean.delete();
        amostraBeanList.clear();

    }

    public void delAmostraIdCabec(Long idCabec){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecAmostra(idCabec));
        AmostraBean amostraBean = new AmostraBean();
        List<AmostraBean> amostraList = amostraBean.get(pesqArrayList);
        pesqArrayList.clear();

        for(AmostraBean amostraBeanBD : amostraList){
            amostraBeanBD.delete();
        }

        amostraList.clear();

    }

    public AmostraBean getAmostra(Long idCabecPerda, Long seqAmostra){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecAmostra(idCabecPerda));
        pesqArrayList.add(getPesqSeqAmostra(seqAmostra));
        AmostraBean amostraBean = new AmostraBean();
        List<AmostraBean> amostraBeanList = amostraBean.get(pesqArrayList);
        pesqArrayList.clear();

        amostraBean = amostraBeanList.get(0);
        amostraBeanList.clear();

        return amostraBean;

    }

    public List<AmostraBean> getAmostraList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecAmostra(idCabec));
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.get(pesqArrayList);
    }

    public int countAmostraList(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabecAmostra(idCabec));
        AmostraBean amostraBean = new AmostraBean();
        return amostraBean.get(pesqArrayList).size();
    }

    public List<AmostraBean> amostraListEnvio(){

    }

    public void delListAmostra(List amostraList){
        for(int i = 0; i < amostraList.size(); i++) {
            AmostraBean amostraBean = (AmostraBean) amostraList.get(i);
            amostraBean.delete();
        }
    }

    public String dadosEnvioAmostra(ArrayList<Long> idCabecList){

        AmostraBean amostraBean = new AmostraBean();
        List<AmostraBean> amostraList = amostraBean.in("idCabecAmostra", idCabecList);

        JsonArray jsonArrayAmostra = new JsonArray();

        for (AmostraBean amostraBDBean : amostraList) {
            Gson gson = new Gson();
            jsonArrayAmostra.add(gson.toJsonTree(amostraBDBean, amostraBDBean.getClass()));
        }

        amostraList.clear();

        JsonObject jsonAmostra = new JsonObject();
        jsonAmostra.add("amostra", jsonArrayAmostra);

        return jsonAmostra.toString();

    }

    private EspecificaPesquisa getPesqIdCabecAmostra(Long idCabecAmostra){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabecAmostra");
        pesquisa.setValor(idCabecAmostra);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqIdAmostra(Long idAmostra){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idAmostra");
        pesquisa.setValor(idAmostra);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqSeqAmostra(Long seqAmostra){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("seqAmostra");
        pesquisa.setValor(seqAmostra);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
