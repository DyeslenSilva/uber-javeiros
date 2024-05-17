package uber.com.model.viagens;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import uber.com.model.Motorista;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="viagens")
@Data
public class Viagem {
   
	@Id
	private int idViagem;
	
	@Column
	private String cpf;

	@Column
	private String nomePassageiro;
	
	@Column
	private String cepOrigem;
	
	@Column
	private String cepDestino;

	@Column
	private String endOrigem;
	
	@Column
	private String endDestino;

	@Column
	private int nLocalOrigem;
	
	@Column
	private int nLocalDestino;

	@Column
	private String cidadeOrigem;

	@Column
	private String cidadeDestino;

	@Column
	private String estadoOrigem;

	@Column
	private String estadoDestino;

	@Column
	private String dataSolicitacao;

	@Column
	private String horaSolicitacao;

	@Column
	private String nomeMotorista;
	
	@Column
	private double valorViagem;

	@Column
	private String tipoPagamento;

	@Column
	private String ultimosDigitos;
	
	@Column
	private double distanciaEmKM;
	
	@Column
	private String tempoViagem;
	
}
