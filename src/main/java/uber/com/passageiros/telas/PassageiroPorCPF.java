package uber.com.passageiros.telas;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;

public class PassageiroPorCPF extends Application{

	private GridPane passageiroCPF;
	private Label lbCPF;
	private TextField txCPF;
	private Button btBuscaCPF;
	private TableView<Passageiros> twPassageiros; 
	private ObservableList<Passageiros> passageiros = FXCollections.observableArrayList();
	private TableColumn<Passageiros, String> tcCPF, tcNomeCliente, tcCEP,tcTelefone, tcEndereco,tcCidade,tcEstado;
	private TableColumn<Passageiros, Integer> tcDDD, tcNcasa;
	
	public PassageiroPorCPF() {
		passageiroCPF = new GridPane();
		lbCPF = new Label("CPF");
		txCPF = new TextField();
		btBuscaCPF = new Button("Buscar CPF");
		
		tcCPF = new TableColumn<>("CPF");
		tcNomeCliente = new TableColumn<>("Nome do Passageiro");
		tcCEP= new TableColumn<>("CEP");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcEndereco = new TableColumn<>("Endereco");
		tcNcasa = new TableColumn<>("N Casa");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
		
		twPassageiros = new TableView<>();
	}
	
	
	private void gridPane() {
		passageiroCPF.setPadding(new Insets(10,10,10,10));
		passageiroCPF.setVgap(10);
		passageiroCPF.setHgap(10);
		
		passageiroCPF.add(twPassageiros, 0, 1,55,1);
	}
	
	private void comps() {
		passageiroCPF.add(lbCPF, 0, 0);
		passageiroCPF.add(txCPF, 1, 0);
		passageiroCPF.add(btBuscaCPF, 2, 0);
		
		btBuscaCPF.setOnAction(bcpf->{
			String cpf = txCPF.getText();
			PassageiroDAO passDAO = new PassageiroDAO();
			Passageiros passageiro = passDAO.passageiroPorCPF(cpf);
			
			if(passageiro!=null) {
				passageiros.add(passageiro);
				twPassageiros.setItems(passageiros);
			}else {
				Alert telaAlerta = new Alert(AlertType.ERROR);
				telaAlerta.setTitle("Funcionario nao encontrado");
				telaAlerta.setHeaderText(null);
				telaAlerta.setContentText("Nenhum funcionário encontrado com o CPF fornecido.");
				telaAlerta.showAndWait();
			}
		});
		
	}
	
	
	@SuppressWarnings("unchecked")
	private void setTable() {
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeCliente.setCellValueFactory(new PropertyValueFactory<>("nomePassageiro"));
		tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcNcasa.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
	
		twPassageiros.getColumns().addAll(tcCPF,tcNomeCliente,tcDDD, tcTelefone,tcCEP,tcEndereco,
				tcCidade, tcEstado);
	
		
		tcNomeCliente.setPrefWidth(150);
		tcTelefone.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
	}
	
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(passageiroCPF,1000,600);
		gridPane();
		comps();
		setTable();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
