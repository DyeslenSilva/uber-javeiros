package uber.com.viagens.telas;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.JComboBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import uber.com.dao.PassageiroDAO;
import uber.com.model.Motorista;
import uber.com.model.Passageiros;
import uber.com.model.viagens.Viagem;
import uber.com.model.viagens.dao.ViagemDAO;
import uber.com.model.viagens.util.CalcularDistancia;
import uber.com.model.viagens.util.ViagemUtil;
import uber.com.utils.dao.PagamentoDAO;
import uber.com.utils.model.CEP;
import uber.com.utils.model.Sorteios;

public class SolicitarViagem extends Application{

	private GridPane gPane;
	private Label lbIdViagem, lbCPF, lbNomeDoPassageiro;
	private Label lbCEPDeOrigem, lbCEPDeDestino, lbEndrecoDeOrigem,lbEnderecoDestino;
	private Label lbNLocalOrigem, lbCidadeOrigem, lbEstadoOrigem;
	private Label lbNLocalDestino, lbCidadeDestino, lbEstadoDestino;
	private Label lbDataViagem, lbHoraDaViagem, lbNomeDoMotorista;
	private Label lbValorPassagem;
	private Label lbFinalCartao;
	private Label lbDistancia,lbDuracao;
	public TextField txIDViagem, txCPF, txNomeDoPassageiro;
	private TextField txCEPDeOrigem, txCEPDeDestino, txEnderecoDeOrigem, txEnderecoDeDestino;
	private TextField txNLocalOrigem, txCidadeOrigem, txEstadoOrigem;
	private TextField txNLocalDestino, txCidadeDestino, txEstadoDestino;
	private TextField txDataViagem, txHoraViagem, txNomeMotorista;
	private TextField  txValorDaPassagem;
	public TextField txFinalCartao;
	private TextField txDistancia , txDuracao;
	private  Button btSolicitarViagem,btLocalizaCEPOrigem,btLocalizaCEPDestino;
	private Button btLocalizarCPF,btGerarIDViagem, btCadastrarViagem;
	private ComboBox<String> cbTipoPagamento;
	
	private Viagem viagem;
	
	public SolicitarViagem() {
	    gPane = new GridPane(); // Adicionado
		
	    viagem = new Viagem();
	    ///passageiros = new Passageiros();
	    
		lbIdViagem = new Label("ID Viagem");
		lbCPF = new Label("CPF");
		lbNomeDoPassageiro = new Label("Nome do Passageiro");
		lbCEPDeOrigem =new Label("CEP de Origem");
		lbCEPDeDestino = new Label("CEP de Destino");
		lbEndrecoDeOrigem = new Label("Endereco de Origem");
		lbEnderecoDestino = new Label("Endereco de Destino");
		lbNLocalOrigem = new Label("nLocal Origem");
		lbCidadeOrigem = new Label("Cidade Origem");
		lbEstadoOrigem = new Label("Estado Origem");
		lbNLocalDestino = new Label("nLocal Destino");
		lbCidadeDestino = new Label("Cidade Destino");
		lbEstadoDestino = new Label("Estado Destino");
		lbDataViagem = new Label("Data da Viagem");
		lbHoraDaViagem = new Label("Hora da Viagem");
		lbNomeDoMotorista = new Label("Nome do Motorista");
		lbValorPassagem = new Label("Valor da Passagem");
		lbFinalCartao = new Label("Final do Cartao");
		lbDistancia = new Label("Distancia");
		lbDuracao = new Label("Duração da Viagem");
		
		
		txIDViagem = new TextField();
		txCPF = new TextField();
		txNomeDoPassageiro = new TextField();
		txCEPDeOrigem = new TextField();
		txCEPDeDestino = new TextField();
		txEnderecoDeOrigem = new TextField();
		txEnderecoDeDestino = new TextField();
		txNLocalOrigem = new TextField();
		txNLocalDestino = new TextField();
		txCidadeOrigem = new TextField();
		txCidadeDestino = new TextField();
		txEstadoOrigem = new TextField();
		txEstadoDestino = new TextField();
		txNomeMotorista = new TextField();
		txDataViagem = new TextField();
		txHoraViagem = new TextField();
		txValorDaPassagem = new TextField();
		txFinalCartao = new TextField();
		txDistancia = new TextField();
		txDuracao = new TextField();
		
		cbTipoPagamento = new ComboBox<String>();
		
		btSolicitarViagem = new Button("Solicitar Viagem");
		btLocalizaCEPOrigem = new Button("Localiza CEP de Origem");
		btLocalizaCEPDestino = new Button("Localiza CEP de Destino");
		btLocalizarCPF = new Button("Localizar CPF");
		btGerarIDViagem = new Button("Gerar ID");
		btCadastrarViagem = new Button("Cadastrar Viagem");
	}
	

	public Parent gPane() {
		gPane.setPadding(new Insets(10,10,10,10));	
		gPane.setVgap(10);
		gPane.setHgap(10);
		return gPane;
	}
	
	public void labels() {
		gPane.add(lbIdViagem, 0, 0);
		gPane.add(lbCPF, 0, 1);
		gPane.add(lbNomeDoPassageiro, 0, 2);
		gPane.add(lbCEPDeOrigem, 0, 3);
		gPane.add(lbCEPDeDestino, 0, 4);
		gPane.add(lbEndrecoDeOrigem, 0, 5);
		gPane.add(lbEnderecoDestino, 0, 6);
		gPane.add(lbNLocalOrigem, 2, 5);
		gPane.add(lbCidadeOrigem, 4, 5);
		gPane.add(lbEstadoOrigem, 6, 5);
		gPane.add(lbNLocalDestino, 2, 6);
		gPane.add(lbCidadeDestino, 4, 6);
		gPane.add(lbEstadoDestino, 6, 6);
		
		gPane.add(lbDataViagem, 0, 8);
		gPane.add(lbHoraDaViagem, 0, 9);
		gPane.add(lbNomeDoMotorista, 0, 10);
		gPane.add(lbValorPassagem, 0, 11);
		gPane.add(lbFinalCartao, 3, 11);
	
		gPane.add(lbDistancia, 3, 7);
		gPane.add(lbDuracao, 5, 7);
	}
	
	public void textField() {
		gPane.add(txIDViagem, 1, 0);
		gPane.add(txCPF, 1, 1);
		gPane.add(txNomeDoPassageiro, 1, 2);
		gPane.add(txCEPDeOrigem, 1, 3);
		gPane.add(txCEPDeDestino, 1, 4);
		gPane.add(txEnderecoDeOrigem, 1, 5);
		gPane.add(txEnderecoDeDestino, 1,6);
		gPane.add(txNLocalOrigem, 3, 5);
		gPane.add(txCidadeOrigem, 5 ,5);
		gPane.add(txEstadoOrigem, 7, 5);
		gPane.add(txNLocalDestino, 3, 6);
		gPane.add(txCidadeDestino, 5 ,6);
		gPane.add(txEstadoDestino, 7, 6);
		
		gPane.add(txDataViagem, 1, 8);
		gPane.add(txHoraViagem, 1, 9);
		gPane.add(txNomeMotorista, 1, 10);
		gPane.add(txValorDaPassagem, 1, 11);	
		gPane.add(txFinalCartao, 4, 11);
		
		gPane.add(txDistancia, 4, 7);
		gPane.add(txDuracao, 6, 7);
	}
	
	
	
	public void combobox() {
	    gPane.add(cbTipoPagamento, 2, 11);

	    PagamentoDAO pagamentoDAO = new PagamentoDAO();
	    List<String> pagamentos = pagamentoDAO.todosOsPagamentos();

	    cbTipoPagamento.getItems().addAll(pagamentos);

	    cbTipoPagamento.setOnAction(soa -> {
	        String itemSelecionado = cbTipoPagamento.getSelectionModel()
	                .getSelectedItem();
	        if ("Cartao de Debito".equals(itemSelecionado)) {
	        	janelaDebito();
	        } else if ("Cartao de Credito".equals(itemSelecionado)) {
	        	janelaCredito();
	        }else if("Dinheiro".equals(itemSelecionado)) {
	        	txFinalCartao.setText("XXXX");
	        }else if("Uber Cash".equals(itemSelecionado)) {
	        	txFinalCartao.setText("XXXX");
	        }
	    });
	}

	
	
	private void janelaCredito() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Credito");
		dialog.setHeaderText(null);
		dialog.setContentText("Digite o Numero do cartao de Credito");
		
		dialog.showAndWait().ifPresent(numeroCompleto ->{
			String[] grupos = numeroCompleto.split(" ");
			if(grupos.length ==4) {
				try {
					int[] cartaoDeCredito = new int[4];
					for(int i =0; i<cartaoDeCredito.length; i++) {
						cartaoDeCredito[i] = Integer.parseInt(grupos[i]);
					}
					String ultimosQuatroDigitos = grupos[3];
					txFinalCartao.setText(ultimosQuatroDigitos);
				}catch (Exception e) {
					mostrarAlertaErro("Numero de Cartao Invalido");
				}
			}
		});
	}


	private void janelaDebito() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Debito");
		dialog.setHeaderText(null);
		dialog.setContentText("Digite o Numero do seu cartao de Debito");
		
		dialog.showAndWait().ifPresent(numeroCompleto ->{
			String[] grupos = numeroCompleto.split(" ");
			if(grupos.length == 4) {
				try {
					int []cartao = new int [4];
					for(int i = 0; i<cartao.length; i++) {
						cartao[i] = Integer.parseInt(grupos[i]);
					}
					String ultimosQuatroDigitos = grupos[3];
					txFinalCartao.setText(ultimosQuatroDigitos);
				}catch (Exception e) {
					mostrarAlertaErro("Numero de Cartao Invalido");
				}
			}
		});
	}

	private void mostrarAlertaErro(String mensagem) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Erro");
		alert.setHeaderText(null);
		alert.setContentText(mensagem);
		alert.showAndWait();
	}

	public void button() {
		gPane.add(btGerarIDViagem, 2, 0);
		gPane.add(btLocalizarCPF, 2, 1);
		gPane.add(btLocalizaCEPOrigem, 2, 3);
		gPane.add(btLocalizaCEPDestino, 2, 4);
		gPane.add(btSolicitarViagem, 2, 7);
		gPane.add(btCadastrarViagem, 2, 12);
		
		btGerarIDViagem.setOnAction(gid->{
			int idViagem = Sorteios.gerarIDViagem();
			txIDViagem.setText(String.valueOf(idViagem));
		});
		
		btLocalizarCPF.setOnAction(lcpf->{
			String cpf = txCPF.getText();
			PassageiroDAO passDAO = new PassageiroDAO();
			Passageiros passageiros = passDAO.passageiroPorCPF(cpf);
			txNomeDoPassageiro.setText(passageiros.getNomePassageiro());
		});
		
		btLocalizaCEPOrigem.setOnAction(cepO->{
				String cepOrigem = txCEPDeOrigem.getText();
				Map<String, String> cepOr = CEP.consultarCEP(cepOrigem);
				
				String logradouro = cepOr.get("logradouro");
				String cidade = cepOr.get("cidade");
				String estado = cepOr.get("estado");
				
				txEnderecoDeOrigem.setText(logradouro);
				txCidadeOrigem.setText(cidade);
				txEstadoOrigem.setText(estado);
				
		});
		
		
		btLocalizaCEPDestino.setOnAction(cepD->{
			String cepDestino = txCEPDeDestino.getText();
			Map<String, String> cepDes = CEP.consultarCEP(cepDestino);
			
			String logradouro =cepDes.get("logradouro");
			String cidade = cepDes.get("cidade");
			String estado = cepDes.get("estado");
			
			txEnderecoDeDestino.setText(logradouro);
			txCidadeDestino.setText(cidade);
			txEstadoDestino.setText(estado);
		
		});
		
		
		btSolicitarViagem.setOnAction(sv ->{
			
			String cepOrigem = txCEPDeOrigem.getText();
			String cepDestino = txCEPDeDestino.getText();
			
			double distancia =CalcularDistancia.calcularDistancia(cepOrigem, cepDestino);
			
			double precoPassagem = ViagemUtil.calcularPrecoDaPassagem(distancia);
		
			DecimalFormat df = new DecimalFormat("#.00" , new DecimalFormatSymbols(Locale.US));
			double precoPassagemArr = Double.parseDouble(df.format(precoPassagem));
			
			txValorDaPassagem.setText(String.valueOf(precoPassagemArr));
			
		    LocalDateTime 	time = LocalDateTime.now();
		    
		    DateTimeFormatter formataData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		    DateTimeFormatter formataHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		    
		    String dataFormatada = time.format(formataData);
		    String horaFormatada = time.format(formataHora);
		    
		    txDataViagem.setText(dataFormatada);
		    txHoraViagem.setText(horaFormatada);
		    
		    Motorista motorista = Sorteios.sortearMotorista();
		    
		    txNomeMotorista.setText(motorista.getNomeMotorista());
		    
		    double distanciaArredondada = Math.round(distancia * 100.0) / 100.0;

		    txDistancia.setText(Double.toString(distanciaArredondada));
		   
		    String duracaoViagem = CalcularDistancia.calculcarTempoDeViagem(cepOrigem, cepDestino);
		    txDuracao.setText(duracaoViagem);
		    
		});
		
		
		btCadastrarViagem.setOnAction(cv ->{
			int idViagem = Integer.parseInt(txIDViagem.getText());
			String cpf= txCPF.getText();
			String nomePassageiro = txNomeDoPassageiro.getText();
			String cepOrigem = txCEPDeOrigem.getText();
			String cepDestino = txCEPDeDestino.getText();
			String endOrigem = txEnderecoDeOrigem.getText();
			String endDestino = txEnderecoDeDestino.getText();
			int nOrigem = Integer.parseInt(txNLocalOrigem.getText());
			int nDestino = Integer.parseInt(txNLocalDestino.getText());
			String cidadeOrigem = txCidadeOrigem.getText();
			String cidadeDestino = txCidadeDestino.getText();
			String estadoOrigem = txEstadoOrigem.getText();
			String estadoDestino = txEstadoDestino.getText();
	
			String dataViagem = txDataViagem.getText();
			String horaViagem = txHoraViagem.getText();
			String nomeMotorista = txNomeMotorista.getText();
			double valorPassagem = Double.parseDouble(txValorDaPassagem.getText());
			String tipoPagamento = cbTipoPagamento.getValue();
			String ut4Dig = txFinalCartao.getText();
			double distancia = Double.parseDouble(txDistancia.getText());
			String duracaoViagem = txDuracao.getText();
			
			viagem.setIdViagem(idViagem);
			viagem.setCpf(cpf);
			viagem.setNomePassageiro(nomePassageiro);
			viagem.setCepOrigem(cepOrigem);
			viagem.setCepDestino(cepDestino);
			viagem.setEndOrigem(endOrigem);
			viagem.setEndDestino(endDestino);
			viagem.setNLocalOrigem(nOrigem);
			viagem.setNLocalDestino(nDestino);
			viagem.setCidadeOrigem(cidadeOrigem);
			viagem.setCidadeDestino(cidadeDestino);
			viagem.setEstadoOrigem(estadoOrigem);
			viagem.setEstadoDestino(estadoDestino);
			viagem.setDataSolicitacao(dataViagem);
			viagem.setHoraSolicitacao(horaViagem);
			viagem.setNomeMotorista(nomeMotorista);
			viagem.setValorViagem(valorPassagem);
			viagem.setTipoPagamento(tipoPagamento);
			viagem.setUltimosDigitos(ut4Dig);
			viagem.setDistanciaEmKM(distancia);
			viagem.setTempoViagem(duracaoViagem);
			
			ViagemDAO viagemDAO = new ViagemDAO();
			viagemDAO.solicitarViagem(viagem);
			
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Viagem Solicitada");
			alert.setHeaderText(null);
			alert.setContentText("A viagem foi solicitada");
			alert.showAndWait();
			
			Stage stage = (Stage) btCadastrarViagem.getScene().getWindow();
			stage.close();
		});
	}

	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene cena = new Scene(gPane,1200,600);
		gPane();
		labels();
		textField();
		button();
		combobox();
		primaryStage.setScene(cena);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}