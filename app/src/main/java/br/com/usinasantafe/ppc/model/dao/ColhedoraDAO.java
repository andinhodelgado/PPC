package br.com.usinasantafe.ppc.model.dao;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.ColhedoraBean;

public class ColhedoraDAO {

    public ColhedoraDAO() {
    }

    public boolean verifColhedora(Long codColhedora){
        List<ColhedoraBean> colhedoraList = equipList(codColhedora);
        boolean ret = colhedoraList.size() > 0;
        colhedoraList.clear();
        return ret;
    }

    public ColhedoraBean getColhedora(Long codColhedora){
        List<ColhedoraBean> colhedoraList = equipList(codColhedora);
        ColhedoraBean equipBean = colhedoraList.get(0);
        colhedoraList.clear();
        return equipBean;
    }

    private List<ColhedoraBean> equipList(Long codColhedora){
        ColhedoraBean colhedoraBean = new ColhedoraBean();
        List<ColhedoraBean> colhedoraList = colhedoraBean.get("codColhedora", codColhedora);
        return colhedoraList;
    }
}
