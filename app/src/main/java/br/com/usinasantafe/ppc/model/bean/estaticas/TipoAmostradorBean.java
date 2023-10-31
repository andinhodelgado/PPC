package br.com.usinasantafe.ppc.model.bean.estaticas;

import br.com.usinasantafe.ppc.model.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbtipoamostradorest")
public class TipoAmostradorBean extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long codTipoAmostrador;
	@DatabaseField
    private String descTipoAmostrador;
	
	public TipoAmostradorBean() {
	}
	
	public Long getCodTipoAmostrador() {
		return codTipoAmostrador;
	}
	
	public void setCodTipoAmostrador(Long codTipoAmostrador) {
		this.codTipoAmostrador = codTipoAmostrador;
	}
	
	public String getDescTipoAmostrador() {
		return descTipoAmostrador;
	}
	
	public void setDescTipoAmostrador(String descTipoAmostrador) {
		this.descTipoAmostrador = descTipoAmostrador;
	}
	
}
