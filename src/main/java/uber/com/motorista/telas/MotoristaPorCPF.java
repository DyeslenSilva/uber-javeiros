package uber.com.motorista.telas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.MotoristaDAO;
import uber.com.model.Motorista;

public class MotoristaPorCPF extends Application{
	
	
	private GridPane tabelaMotorista;
	private TableView<Motorista> twMotorista;
	private ObservableList<Motorista> motoristaData = FXCollections.observableArrayList();
	private TableColumn<Motorista, Integer> tcIDMotorista, tcDDD, tcNLocal;
	private TableColumn<Motorista, String> tcCPF,tcNomeMotorista ,tcTelefone,tcCEP,tcEndereco;
	private TableColumn<Motorista, String> tcCidade, tcEstado;

	private Label lbCPF;
	private TextField txCPF;
	private Button buscaPorCPF;	
	
	public MotoristaPorCPF() {
		tabelaMotorista = new GridPane();
				
		lbCPF = new Label("CPF");
		txCPF = new TextField();
		buscaPorCPF = new Button("Buscar Motorista");
		
		twMotorista = new TableView<>();
		motoristaData = FXCollections.observableArrayList();
		
		tcIDMotorista = new TableColumn<>("ID");
		tcCPF = new TableColumn<>("CPF");
		tcNomeMotorista = new TableColumn<>("Nome do Motorista");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereco");
		tcNLocal = new TableColumn<>("N Local");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
		
	}
	
	
	private void gPane() {
		tabelaMotorista.setPadding(new Insets(10,10,10,10));
		tabelaMotorista.setHgap(10);
		tabelaMotorista.setVgap(10);
		
	    tabelaMotorista.add(twMotorista, 0, 1,55,1);  // Problema aqui
	}
	
	@SuppressWarnings("unchecked")
	private void setTabel() {
		tcIDMotorista.setCellValueFactory(new PropertyValueFactory<>("idMotorista"));
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
		tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCEP.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcNLocal.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
	
		twMotorista.getColumns().addAll(tcIDMotorista,tcCPF,tcNomeMotorista,tcDDD,
				tcTelefone,tcCEP,tcEndereco,tcNLocal,tcCidade,tcEstado);
		
		tcNomeMotorista.setPrefWidth(150);
		tcTelefone.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
	}
	
	private void comps() {
		tabelaMotorista.add(lbCPF, 0, 0);
		tabelaMotorista.add(txCPF, 1, 0);
		tabelaMotorista.add(buscaPorCPF, 2, 0);
		
		buscaPorCPF.setOnAction(bpc ->{
			String cpf = txCPF.getText();
			MotoristaDAO motoDAO = new MotoristaDAO();
			Motorista motorista = motoDAO.motoristaPorCPF(cpf);
			
			if(motorista!=null) {
				motoristaData.add(motorista);
				twMotorista.setItems(motoristaData);
			}else {
				Alert telaAlerta = new Alert(AlertType.ERROR);
				telaAlerta.setTitle("Funcionario nao encontrado");
				telaAlerta.setHeaderText(null);
				telaAlerta.setContentText("Nenhum funcionário encontrado com o CPF fornecido.");
				telaAlerta.showAndWait();
			}
		});
	}
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(tabelaMotorista,1200,500);
		comps();
		gPane();
		setTabel();
		
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
