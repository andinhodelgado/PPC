package br.com.usinasantafe.ppc.model.bean.estaticas;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import br.com.usinasantafe.ppc.model.pst.Entidade;

/**
 * Created by anderson on 12/04/2017.
 */
@DatabaseTable(tableName="tbosest")
public class OSBean extends Entidade {

    private static final long serialVersionUID = 1L;

    @DatabaseField(id=true)
    private Long nroOS;

    public OSBean() {
    }

    public Long getNroOS() {
        return nroOS;
    }

    public void setNroOS(Long nroOS) {
        this.nroOS = nroOS;
    }
}
