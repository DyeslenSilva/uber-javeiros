package uber.com.utils.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "estados")
@Entity
public class Estados {

	
	
	
	@Id
	@Column
	private String siglaEstado;
	
	@Column
	private String nomeEstado;
}
