package uber.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class Gerente {

	@Id
	private int idGerente;
	
	@Column
	private String nomeGerente;
	
	
	
}
