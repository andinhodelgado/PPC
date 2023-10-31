package br.com.usinasantafe.ppc.model.dao;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.OSBean;

public class OSDAO {

    public OSDAO() {
    }

    public boolean verifOS(Long nroOS){
        List<OSBean> osList = osList(nroOS);
        boolean ret = osList.size() > 0;
        osList.clear();
        return ret;
    }

    public OSBean getOS(Long nroOS){
        List<OSBean> osList = osList(nroOS);
        OSBean osBean = (OSBean) osList.get(0);
        osList.clear();
        return osBean;
    }

    private List<OSBean> osList(Long nroOS){
        OSBean osBean = new OSBean();
        List<OSBean> osList = osBean.get("nroOS", nroOS);
        return osList;
    }

    public void deleteAll(){
        OSBean osBean = new OSBean();
        osBean.deleteAll();
    }

}
