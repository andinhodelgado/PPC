package br.com.usinasantafe.ppc.model.dao;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.OperadorBean;

public class OperadorDAO {

    public OperadorDAO() {
    }

    public boolean verifOperador(Long matricOperador){
        List<OperadorBean> operadorList = operadorMatricList(matricOperador);
        boolean ret = operadorList.size() > 0;
        operadorList.clear();
        return ret;
    }

    private List<OperadorBean> operadorMatricList(Long matricOperador){
        OperadorBean auditorBean = new OperadorBean();
        List<OperadorBean> operadorList = auditorBean.get("matricOperador", matricOperador);
        return operadorList;
    }


}