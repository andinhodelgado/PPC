package br.com.usinasantafe.ppc.model.dao;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.ColhedoraBean;

public class ColhedoraDAO {

    public ColhedoraDAO() {
    }

    public boolean verifColhedora(Long nroColhedora){
        List<ColhedoraBean> colhedoraList = colhedoraList(nroColhedora);
        boolean ret = colhedoraList.size() > 0;
        colhedoraList.clear();
        return ret;
    }

    private List<ColhedoraBean> colhedoraList(Long nroColhedora){
        ColhedoraBean colhedoraBean = new ColhedoraBean();
        List<ColhedoraBean> colhedoraList = colhedoraBean.get("nroColhedora", nroColhedora);
        return colhedoraList;
    }
}
