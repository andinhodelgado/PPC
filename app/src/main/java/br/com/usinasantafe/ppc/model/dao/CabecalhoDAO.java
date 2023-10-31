package br.com.usinasantafe.ppc.model.dao;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.model.bean.variaveis.CabecalhoBean;
import br.com.usinasantafe.ppc.model.pst.EspecificaPesquisa;

public class CabecalhoDAO {

    private CabecalhoBean cabecalhoBean;

    public CabecalhoDAO() {
    }

    public CabecalhoBean getCabecBean() {
        if (cabecalhoBean == null)
            cabecalhoBean = new CabecalhoBean();
        return cabecalhoBean;
    }

    public void setCabecBean(CabecalhoBean cabecalhoBean) {
        this.cabecalhoBean = cabecalhoBean;
    }

    public boolean verifCabecAberto(){
        List<CabecalhoBean> cabecList = cabecAbertoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verifCabecFechado(){
        List<CabecalhoBean> cabecList = cabecFechadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verifCabecFechadoEnviado(){
        List<CabecalhoBean> cabecList = cabecFechadoEnviadoList();
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public boolean verifColhedoraRepetida(Long codColhedora){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        pesqArrayList.add(getPesqCodColhedora(codColhedora));

        CabecalhoBean cabecalhoBean = new CabecalhoBean();
        List<CabecalhoBean> cabecList = cabecalhoBean.get(pesqArrayList);
        boolean ret = cabecList.size() > 0;
        cabecList.clear();
        return ret;
    }

    public void salvarCabecAberto(){
        cabecalhoBean.setStatusCabec(1L);
        cabecalhoBean.insert();
    }

    public void fecharCabec(Long idCabec){
        CabecalhoBean cabecalhoBean = getCabecId(idCabec);
        cabecalhoBean.setStatusCabec(2L);
        cabecalhoBean.update();
    }

    public void excluirCabec(Long idCabec){
        CabecalhoBean cabecalhoBean = getCabecId(idCabec);
        cabecalhoBean.delete();
    }

    public CabecalhoBean getCabecAberto(){
        List<CabecalhoBean> cabecList = cabecAbertoList();
        CabecalhoBean cabecalhoBean = cabecList.get(0);
        cabecList.clear();
        return cabecalhoBean;
    }

    public CabecalhoBean getCabecId(Long idCabec){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqIdCabec(idCabec));
        List<CabecalhoBean> cabecList = cabecAbertoList();
        CabecalhoBean cabecalhoBean = cabecList.get(0);
        cabecList.clear();
        return cabecalhoBean;
    }

    public List<CabecalhoBean> cabecAbertoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecAberto());
        CabecalhoBean cabecalhoBean = new CabecalhoBean();
        return cabecalhoBean.get(pesqArrayList);
    }

    public List<CabecalhoBean> cabecFechadoList(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecFechado());
        CabecalhoBean cabecalhoBean = new CabecalhoBean();
        return cabecalhoBean.get(pesqArrayList);
    }

    public List<CabecalhoBean> cabecFechadoEnviadoList(){

        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecFechado());

        CabecalhoBean cabecalhoBean = new CabecalhoBean();
        return cabecalhoBean.get(pesqArrayList);
    }

    public List<CabecalhoBean> cabecListEnviado(){
        ArrayList pesqArrayList = new ArrayList();
        pesqArrayList.add(getPesqCabecEnviado());
        CabecalhoBean cabecalhoBean = new CabecalhoBean();
        List<CabecalhoBean> cabecList = cabecalhoBean.getAndOrderBy(pesqArrayList, "idCabec", true);
        pesqArrayList.clear();
        return cabecList;
    }

    public Long excluirCabec(){
        List<CabecalhoBean> cabecList = cabecListEnviado();
        int qtdeCEC = cabecList.size();
        if (qtdeCEC > 10) {
            CabecalhoBean cabecalhoBean = cabecList.get(0);
            cabecalhoBean.delete();
            return cabecalhoBean.getIdCabec();
        }
        else{
            return 0L;
        }
    }

    public String dadosEnvioCabecFechado(){

        List<CabecalhoBean> cabecList = cabecFechadoList();

        JsonArray jsonArrayCabec = new JsonArray();

        for (int i = 0; i < cabecList.size(); i++) {

            CabecalhoBean cabecalhoBean = cabecList.get(i);
            Gson gsonCabec = new Gson();
            jsonArrayCabec.add(gsonCabec.toJsonTree(cabecalhoBean, cabecalhoBean.getClass()));

        }

        cabecList.clear();

        JsonObject jsonCabec = new JsonObject();
        jsonCabec.add("cabec", jsonArrayCabec);

        return jsonCabec.toString();

    }

    public ArrayList<Long> idCabecFechadoList(){

        List<CabecalhoBean> cabecList = cabecFechadoList();

        ArrayList<Long> idCabecList = new ArrayList<>();

        for (int i = 0; i < cabecList.size(); i++) {
            CabecalhoBean cabecalhoBean = cabecList.get(i);
            idCabecList.add(cabecalhoBean.getIdCabec());
        }

        cabecList.clear();

        return idCabecList;

    }

    public void updateCabecAberto(String retorno){

        try{

            int pos1 = retorno.indexOf("_") + 1;
            String objPrinc = retorno.substring(pos1);

            JSONObject jObjCabec = new JSONObject(objPrinc);
            JSONArray jsonArrayCabec = jObjCabec.getJSONArray("cabec");


            for (int i = 0; i < jsonArrayCabec.length(); i++) {

                JSONObject objCabec = jsonArrayCabec.getJSONObject(i);
                Gson gsonCabec = new Gson();
                CabecalhoBean cabecalhoBean = gsonCabec.fromJson(objCabec.toString(), CabecalhoBean.class);

                List<CabecalhoBean> cabecList = cabecalhoBean.get("idCabec", cabecalhoBean.getIdCabec());
                CabecalhoBean cabecalhoBeanBD = cabecList.get(0);
                cabecList.clear();

                cabecalhoBeanBD.setStatusCabec(3L);
                cabecalhoBeanBD.update();

            }

        } catch(Exception e){
        }

    }

    private EspecificaPesquisa getPesqIdCabec(Long idCabec){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idCabec");
        pesquisa.setValor(idCabec);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCodColhedora(Long codColhedora){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("codColhedoraCabec");
        pesquisa.setValor(codColhedora);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecAberto(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(1L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecFechado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(2L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

    private EspecificaPesquisa getPesqCabecEnviado(){
        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("statusCabec");
        pesquisa.setValor(3L);
        pesquisa.setTipo(1);
        return pesquisa;
    }

}
