package br.com.usinasantafe.ppc.model.dao;

import java.util.List;

import br.com.usinasantafe.ppc.model.bean.estaticas.AuditorBean;

public class AuditorDAO {

    public AuditorDAO() {
    }

    public boolean hasElements(){
        AuditorBean auditorBean = new AuditorBean();
        return  auditorBean.hasElements();
    }

    public boolean verifAuditor(Long matricAuditor){
        List<AuditorBean> auditorList = auditorMatricList(matricAuditor);
        boolean ret = auditorList.size() > 0;
        auditorList.clear();
        return ret;
    }

    private List<AuditorBean> auditorMatricList(Long matricAuditor){
        AuditorBean auditorBean = new AuditorBean();
        List<AuditorBean> auditorList = auditorBean.get("matricAuditor", matricAuditor);
        return auditorList;
    }

}
