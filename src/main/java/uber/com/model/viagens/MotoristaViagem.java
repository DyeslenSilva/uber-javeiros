package uber.com.model.viagens;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uber.com.model.Motorista;

@Data
@EqualsAndHashCode(callSuper = false)
public class MotoristaViagem extends Motorista{
	
	private boolean motoristaDisponivel;
	
	private boolean viagemConfirmada;
	
	private LocalDate dataHoraVigem;
}
