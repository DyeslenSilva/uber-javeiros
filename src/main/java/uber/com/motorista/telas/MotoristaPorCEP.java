package uber.com.motorista.telas;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

public class MotoristaPorCEP extends Application{

	private GridPane motoristaPorCEP;
	private TableView<Motorista> tvMotoristaPorCEP;
	private TableColumn<Motorista, Integer> tcIDMotorista;
	private TableColumn<Motorista, String> tcCPF, tcNomeMotorista, tcTelefone, tcCEP, tcEndereco, tcCidade, tcEstado;
	private TableColumn<Motorista, Integer> tcDDD, tcNLocal;
	private ObservableList<Motorista> tableListMotorista =FXCollections.observableArrayList();
	private Label lbCEP;
	private TextField txCEP;
	private Button btBuscaPorCEP;
	
	public MotoristaPorCEP() {
		motoristaPorCEP = new GridPane();
		
		tvMotoristaPorCEP = new TableView<>();
		
		tcIDMotorista = new TableColumn<>("ID Motorista");
		tcCPF = new TableColumn<>("CPF");
		tcNomeMotorista = new TableColumn<>("Nome do Motorista");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereco");
		tcNLocal = new TableColumn<>("N Local");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
		
		lbCEP = new Label("CEP");
		txCEP = new TextField();
		btBuscaPorCEP = new Button("Busca Por CEP");
	}
	
	
	private void gPane() {
		motoristaPorCEP.setPadding(new Insets(10,10,10,10));
		motoristaPorCEP.setHgap(10);
		motoristaPorCEP.setVgap(10);
		
		motoristaPorCEP.add(tvMotoristaPorCEP, 0, 1,55,1);
	}

	
	private void comps() {
		motoristaPorCEP.add(lbCEP, 0, 0);
		motoristaPorCEP.add(txCEP, 1, 0);
		motoristaPorCEP.add(btBuscaPorCEP, 2, 0);
		
		btBuscaPorCEP.setOnAction(eh ->{
			String cep = txCEP.getText();
			
			MotoristaDAO motoDAO = new MotoristaDAO();
			List<Motorista> motoristas  =motoDAO.motoristaPorCEP(cep);
			
			if(motoristas !=null) {
				tableListMotorista.addAll(motoristas);
				tvMotoristaPorCEP.setItems(tableListMotorista);
			}
			
		});
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
		
		tvMotoristaPorCEP.getColumns().addAll(tcIDMotorista,tcCPF,tcNomeMotorista,
					tcDDD,tcTelefone,tcCEP,tcEndereco,tcNLocal,tcCidade,tcEstado);
		
		tcNomeMotorista.setPrefWidth(200);
		tcTelefone.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(motoristaPorCEP,1500,600);
		setTable();
		gPane();
		comps();
		primaryStage.setScene(cenario);
		primaryStage.show();
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
