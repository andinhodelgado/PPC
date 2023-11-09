package br.com.usinasantafe.ppc.model.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.OSBean;
import br.com.usinasantafe.ppc.model.pst.EspecificaPesquisa;

public class OSDAO {

    public OSDAO() {
    }

    public boolean verifNroOS(Long nroOS, Long idSecao){

        ArrayList pesqList = new ArrayList();

        EspecificaPesquisa pesquisa = new EspecificaPesquisa();
        pesquisa.setCampo("idSecao");
        pesquisa.setValor(idSecao);
        pesqList.add(pesquisa);

        EspecificaPesquisa pesquisa2 = new EspecificaPesquisa();
        pesquisa2.setCampo("nroOS");
        pesquisa2.setValor(nroOS);
        pesqList.add(pesquisa2);

        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get(pesqList);
        boolean ret = osList.size() > 0;
        osList.clear();

        return  ret;

    }

    public boolean verifCodSecao(Long codSecao){
        List<OSBean> osList = secaoListCod(codSecao);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public Long getIdSecao(Long codSecao){
        List<OSBean> osList = secaoListCod(codSecao);
        OSBean osBean = osList.get(0);
        osList.clear();
        return osBean.getIdSecao();
    }

    private List<OSBean> secaoListCod(Long codSecao){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get("codSecao", codSecao);
        return osList;
    }

    public void deleteAll(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

}
