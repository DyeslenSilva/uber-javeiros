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

public class MotoristaPorID extends Application{

	private GridPane motoristaPorID;
	private TableView<Motorista> tvMotoristaPorID;
	private TableColumn<Motorista, Integer> tcIDMotorista;
	private TableColumn<Motorista, String> tcCPF, tcNomeMotorista, tcTelefone, tcCEP, tcEndereco, tcCidade, tcEstado;
	private TableColumn<Motorista, Integer> tcDDD, tcNLocal;
	private Label lbIDMotorista;
	private TextField txIDMotorista;
	private Button btBuscarMotoristaPorID;
	
	public MotoristaPorID() {
		motoristaPorID = new GridPane();
		
		tvMotoristaPorID = new TableView<>();
		
		lbIDMotorista = new Label("Id Motorista");
		txIDMotorista = new TextField();
		
		btBuscarMotoristaPorID = new Button("Buscar Motorista");
		
		tcIDMotorista = new TableColumn<>("ID");
		tcCPF = new TableColumn<>("CPF");
		tcNomeMotorista = new TableColumn<>("Nome do Motorista");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereço");
		tcNLocal = new TableColumn<>("Nº Local");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
	}
	
	
	private void gPane() {
		motoristaPorID.setPadding(new Insets(10,10,10,10));
		motoristaPorID.setVgap(10);
		motoristaPorID.setHgap(10);
		
		motoristaPorID.add(tvMotoristaPorID, 0	,1,70, 1);
	}
	
	@SuppressWarnings("unchecked")
	private void setTable() {
		tcIDMotorista.setCellValueFactory(new PropertyValueFactory<>("idMotorista"));
		tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tcNomeMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
		tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
		tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
		tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		tcNLocal.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
		tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
		tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
		
		tvMotoristaPorID.getColumns().addAll(tcIDMotorista, tcCPF, tcNomeMotorista,
                tcDDD, tcTelefone, tcCEP, tcEndereco, tcNLocal, tcCidade, tcEstado);
	
			tcNomeMotorista.setPrefWidth(200);
			tcTelefone.setPrefWidth(150);
			tcEndereco.setPrefWidth(200);
	}
	
	private void comps() {
		motoristaPorID.add(lbIDMotorista, 0, 0);
		motoristaPorID.add(txIDMotorista, 1, 0);
		motoristaPorID.add(btBuscarMotoristaPorID, 2, 0);
		
		btBuscarMotoristaPorID.setOnAction(bmid ->{
			int idMotorista = Integer.parseInt(txIDMotorista.getText());
			MotoristaDAO motoDAO = new MotoristaDAO();
			Motorista motorista = motoDAO.motoristaPorID(idMotorista);
			
			if(motorista !=null) {
				ObservableList<Motorista> motoOb = FXCollections.observableArrayList();
				motoOb.add(motorista);
				tvMotoristaPorID.setItems(motoOb);
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
		Scene cenario = new Scene(motoristaPorID,1150,500);
		gPane();
		comps();
		setTable();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
