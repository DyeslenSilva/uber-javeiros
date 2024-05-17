	package uber.com.model;
	
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.Id;
	import jakarta.persistence.Table;
	import lombok.Data;
	
	@Data
	@Table(name = "motorista")
	@Entity
	public class Motorista {
	
		@Id
		private int idMotorista;
		
		@Column
		private String cpf;
		
		@Column
		private String nomeMotorista;
		
		@Column
		private String cep;
		
		@Column
		private String endereco;
		
		@Column
		private int ddd;
		
		@Column
		private String telefone;
		
		@Column
		private int nLocal;
		
		@Column
		private String cidade;
		
		@Column
		private String estado;
	}
