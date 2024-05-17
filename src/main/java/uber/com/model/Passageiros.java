package uber.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "passageiro")
public class Passageiros {

	@Id
	private String cpf;
	
	@Column
	private String nomePassageiro;
	
	
	@Column
	private Integer ddd;
	
	
	@Column
	private String telefone;
	
	
	@Column
	private String cep;
	
	@Column
	private String endereco;
	
	@Column
	private int nLocal;
	
	@Column
	private String cidade;
	
	@Column
	private String estado;
}
