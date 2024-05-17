package uber.com.menus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.motorista.telas.CadastroDeMotorista;
import uber.com.motorista.telas.MotoristaPorCEP;
import uber.com.motorista.telas.MotoristaPorCPF;
import uber.com.motorista.telas.MotoristaPorEstado;
import uber.com.motorista.telas.MotoristaPorID;
import uber.com.motorista.telas.TabelaMotoristas;

public class MenuMotoristas extends Application{

	private GridPane gPane;
	private Button cadastrarMotorista, motoristaPorCEP,motoristaPorCPF;
	private Button motoristaPorEstado,motoristaPorID, todosOsMotoristas;
	private Stage stage;
	
	public MenuMotoristas() {
		gPane = new GridPane();
		cadastrarMotorista = new Button("Cadastrar Motorista");
		motoristaPorCPF = new Button("Motorista Por CPF");
		motoristaPorCEP = new Button("Motorista por CEP");
		motoristaPorEstado = new Button("Motorista por estado");
		motoristaPorID = new Button("Motorista Por ID");
		todosOsMotoristas = new Button("Todos os Motorista");
		stage  =new Stage();
	}
	
	private void gPane() {
		gPane.setPadding(new Insets(10,10,10,10));
		gPane.setVgap(10);
		gPane.setHgap(10);
	}
	
	private void buttons() {
		gPane.add(cadastrarMotorista, 0, 0);
		gPane.add(motoristaPorCPF, 1, 0);
		gPane.add(motoristaPorCEP, 2, 0);
		gPane.add(motoristaPorEstado, 0, 1);
		gPane.add(motoristaPorID, 1, 1);
		gPane.add(todosOsMotoristas, 2, 1);
	
		cadastrarMotorista.setOnAction(cm ->{
			CadastroDeMotorista cadMotorista = new CadastroDeMotorista();
			try {
				cadMotorista.start(stage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		motoristaPorCPF.setOnAction(mcpf ->{
			MotoristaPorCPF motoCPF = new MotoristaPorCPF();
			try {
				motoCPF.start(stage);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		});
		
		
		motoristaPorCEP.setOnAction(mcep ->{
			MotoristaPorCEP motoCEP = new MotoristaPorCEP();
			try {
				motoCEP.start(stage);
			} catch (Exception e3) {
				e3.printStackTrace();
			}
		});
		
		motoristaPorEstado.setOnAction(estado ->{
			MotoristaPorEstado  motoEstado   =new MotoristaPorEstado();
			try {
				motoEstado.start(stage);
			}catch (Exception e) {
				e.printStackTrace();
			}
		});
		
		motoristaPorID.setOnAction(id ->{
			MotoristaPorID motoID = new MotoristaPorID();
			try {
				motoID.start(stage);
			} catch (Exception e4) {
				e4.printStackTrace();
			}
		});
		
		todosOsMotoristas.setOnAction(tdm ->{
			TabelaMotoristas tabelaMotorista = new TabelaMotoristas();
			try {
				tabelaMotorista.start(stage);
			} catch (Exception e5) {
				e5.printStackTrace();
			}
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(gPane,400,400);
		buttons();
		gPane();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
}
