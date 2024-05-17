package uber.com.passageiros.telas;

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
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;

public class PassageiroPorCEP extends Application{

	private GridPane passageiroPorCEP;
	private Label lbCEP;
	private TextField txCEP;
	private Button btBuscarPassageiro;
	private TableView<Passageiros> twPassageiroPorCEP;
	private TableColumn<Passageiros,String> tcCPF, tcNomePassageiro,tcTelefone;
	private TableColumn<Passageiros, Integer> tcDDD, tcNcasa;
	private TableColumn<Passageiros, String> tcCEP, tcEndereco,tcCidade, tcEstado;
	private ObservableList<Passageiros> listaPassageiros;
;
	
	public PassageiroPorCEP() {
		passageiroPorCEP = new GridPane();
		
		lbCEP = new Label("CEP");
		txCEP = new TextField();
		btBuscarPassageiro = new Button("Buscar Passageiro");
		
		
		twPassageiroPorCEP = new TableView<Passageiros>();
		
		tcCPF = new TableColumn<>("CPF");
		tcNomePassageiro = new TableColumn<>("Nome do Passageiro");
		tcDDD = new TableColumn<>("DDD");
		tcTelefone = new TableColumn<>("Telefone");
		tcCEP = new TableColumn<>("CEP");
		tcEndereco = new TableColumn<>("Endereco");
		tcNcasa = new TableColumn<>("N Casa");
		tcCidade = new TableColumn<>("Cidade");
		tcEstado = new TableColumn<>("Estado");
		
		listaPassageiros = FXCollections.observableArrayList();
	}
	
	private void gPane() {
		passageiroPorCEP.setVgap(10);
		passageiroPorCEP.setHgap(10);
		passageiroPorCEP.setPadding(new Insets(10,10,10,10));
		
		passageiroPorCEP.add(twPassageiroPorCEP, 0, 1,55,1);
	}
	
	private void comps() {
		passageiroPorCEP.add(lbCEP, 0, 0);
		passageiroPorCEP.add(txCEP, 1, 0);
		passageiroPorCEP.add(btBuscarPassageiro, 2, 0);
		
		btBuscarPassageiro.setOnAction(bp ->{
			String cep = txCEP.getText();
			PassageiroDAO passDAO = new PassageiroDAO();
			List<Passageiros> listaPassageiro = passDAO.passageirosPorCEP(cep);
			
			if(listaPassageiro!=null) {
				listaPassageiros.addAll(listaPassageiro);
				twPassageiroPorCEP.setItems(listaPassageiros);
			}
		});
	}
	
	
	@SuppressWarnings("unchecked")
	private void setTable() {
	    tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
	    tcNomePassageiro.setCellValueFactory(new PropertyValueFactory<>("nomePassageiro"));
	    tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
	    tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
	    tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
	    tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
	    tcNcasa.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
	    tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
	    tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
	   
	    twPassageiroPorCEP.getColumns().addAll(
		        tcCPF, tcNomePassageiro, tcDDD, tcTelefone, 
		        tcCEP, tcEndereco, tcNcasa, tcCidade, tcEstado
		    );
	    
	    tcNomePassageiro.setPrefWidth(150);
		tcTelefone.setPrefWidth(150);
		tcEndereco.setPrefWidth(150);
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cenario = new Scene(passageiroPorCEP, 1000,600);
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
