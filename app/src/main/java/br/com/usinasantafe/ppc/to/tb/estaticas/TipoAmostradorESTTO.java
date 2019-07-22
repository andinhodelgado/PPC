package br.com.usinasantafe.ppc.to.tb.estaticas;

import br.com.usinasantafe.ppc.pst.Entidade;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="tbtipoamostradorest")
public class TipoAmostradorESTTO extends Entidade {

	private static final long serialVersionUID = 1L;
	
	@DatabaseField(id=true)
    private Long cdTipoAmostrador;
	@DatabaseField
    private String descTipoAmostrador;
	
	public TipoAmostradorESTTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getCdTipoAmostrador() {
		return cdTipoAmostrador;
	}
	
	public void setCdTipoAmostrador(Long cdTipoAmostrador) {
		this.cdTipoAmostrador = cdTipoAmostrador;
	}
	
	public String getDescTipoAmostrador() {
		return descTipoAmostrador;
	}
	
	public void setDescTipoAmostrador(String descTipoAmostrador) {
		this.descTipoAmostrador = descTipoAmostrador;
	}
	
}
