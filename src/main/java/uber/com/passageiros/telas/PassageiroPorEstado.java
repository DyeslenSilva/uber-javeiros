package uber.com.passageiros.telas;

import java.util.List;

import estado.sistema.model.Estado;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Passageiros;
import uber.com.utils.dao.EstadoDAO;

public class PassageiroPorEstado extends Application {

    private GridPane passageiroPorEstado;
    private Label lbEstado;
    private ComboBox<String> cbEstados;
    private Button btBuscaEstado;
    private TableView<Passageiros> tvPassageiros;
    private TableColumn<Passageiros, String> tcCPF, tcNomePassageiro, tcTelefone, tcCEP;
    private TableColumn<Passageiros, Integer> tcDDD, tcNLocal;
    private TableColumn<Passageiros, String> tcEndereco, tcCidade, tcEstado;
    private ObservableList<Passageiros> listaPassageiros;

    public PassageiroPorEstado() {
        passageiroPorEstado = new GridPane();

        lbEstado = new Label("Estado");
        cbEstados = new ComboBox<String>();
        btBuscaEstado = new Button("Buscar Estado");

        tvPassageiros = new TableView<Passageiros>();

        tcCPF = new TableColumn<>("CPF");
        tcNomePassageiro = new TableColumn<>("Nome do Passageiro");
        tcDDD = new TableColumn<>("DDD");
        tcTelefone = new TableColumn<>("Telefone");
        tcCEP = new TableColumn<>("CEP");
        tcEndereco = new TableColumn<>("Endereco");
        tcNLocal = new TableColumn<>("N Local");
        tcCidade = new TableColumn<>("Cidade");
        tcEstado = new TableColumn<>("Estado");

        listaPassageiros = FXCollections.observableArrayList();
    }

    private void gPane() {
        passageiroPorEstado.setPadding(new Insets(10, 10, 10, 10));
        passageiroPorEstado.setHgap(10);
        passageiroPorEstado.setVgap(10);

        passageiroPorEstado.add(tvPassageiros, 0, 1, 55, 1);
    }

    private void comps() {
        passageiroPorEstado.add(lbEstado, 0, 0);
        passageiroPorEstado.add(btBuscaEstado, 2, 0);
        
        btBuscaEstado.setOnAction(be ->{
        	String estado = cbEstados.getValue();
        	PassageiroDAO daoPass = new PassageiroDAO();
        	List<Passageiros> listaPass = daoPass.passageirosPorEstado(estado);
        	
        	listaPassageiros.clear();
        	
        	if(listaPass!=null) {
        		listaPassageiros.addAll(listaPass);
        		tvPassageiros.setItems(listaPassageiros);
        	}
        });
    }
    
    
    private void combobox() {
        passageiroPorEstado.add(cbEstados, 1, 0);
        EstadoDAO estadoDAO = new EstadoDAO();
        List<String> estados = estadoDAO.listaTodosEstados();
        cbEstados.getItems().addAll(estados);
    }

    @SuppressWarnings("unchecked")
	private void setTable() {
        tcCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        tcNomePassageiro.setCellValueFactory(new PropertyValueFactory<>("nomePassageiro"));
        tcDDD.setCellValueFactory(new PropertyValueFactory<>("ddd"));
        tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        tcCEP.setCellValueFactory(new PropertyValueFactory<>("cep"));
        tcEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tcNLocal.setCellValueFactory(new PropertyValueFactory<>("nLocal"));
        tcCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tcEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tvPassageiros.getColumns().addAll(tcCPF, tcNomePassageiro, tcDDD, tcTelefone,
                tcCEP, tcEndereco, tcNLocal, tcCidade, tcEstado);
        
        tcNomePassageiro.setPrefWidth(200);
        tcTelefone.setPrefWidth(150);
        tcEndereco.setPrefWidth(150);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        gPane();
        comps();
        combobox();
        setTable();

        Scene scene = new Scene(passageiroPorEstado, 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Passageiros por Estado");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
