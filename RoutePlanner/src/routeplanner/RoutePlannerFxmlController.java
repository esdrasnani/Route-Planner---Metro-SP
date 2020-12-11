/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routeplanner;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;

import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.json.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author esdra
 */
public class RoutePlannerFxmlController implements Initializable {

    ObservableList listOrigem = FXCollections.observableArrayList();
    ObservableList listDestino = FXCollections.observableArrayList();
    String[] stationsFull = {"Jabaquara", "Conceição", "São Judas", "Saúde", "Praça da Árvore", "Santa Cruz", "Vila Mariana", "Ana Rosa", "Paraíso", "Vergueiro", "São Joaquim", "Japão-Liberdade", "Sé", "São Bento", "Luz", "Tiradentes", "Armênia", "Portuguesa-Tietê", "Carandiru", "Santana", "Jardim São Paulo-Aryton Senna", "Parada Inglesa", "Tucuruvi", "Jundaí", "Várzea Paulista", "Campo Limpo Paulista", "Botujuru", "Franciso Morato", "Baltazar Fidélis", "Franco da Rocha", "Caieiras", "Perus", "Vila Aurora", "Jaraguá", "Vila Clarice", "Pirituba", "Piqueri", "Lapa(Rubi)", "Água Branca", "Palmeiras Barra Funda", "Amador Bueno", "Santa Rita", "Itapevi", "Engenheiro Cardoso", "Sagrado Cadorso", "Jandira", "Jardim Silveira", "Jardim Belval", "Barueri", "Antonio João", "Santa Teresina", "Carapicuiba", "General Miguel Costa", "Quitaûna", "Comandante Sampaio", "Osasco", "Presidente Altino", "Imperatriz Leopoldina", "Domingos de Moraes", "Lapa(Diamante)", "Júlio Prestes", "São Paulo-Morumbi", "Butantã", "Pinheiros", "Faria Lima", "Fradique Coutinho", "Oscar Freire", "Paulista", "Higienópolis-Mackenzie", "República", "Marechal Deodoro", "Santa Cecília", "Anhangabaú", "Pedro II", "Brás", "Bresser-Mocca", "Belém", "Tatuapé", "Carrão", "Penha", "Vila Matilde", "Guilhermiana Esperança", "Patriarca-Vila Ré", "Arthur Alvim", "Corinthians Itaquera", "Grajaú", "Primavera-Interlagos", "Autódromo", "Jurubatuba", "Socorro", "Santo Amaro", "Granja Julieta", "Morumbi", "Berrini", "Vila Olímpia", "Cidade Jardim", "Hebraica Rebouças", "Cidade Universitária", "Vila Lobos Jaguaré", "Ceasa", "Capão Redondo", "Campo Limpo", "Vila das Belezas", "Giovanni Gronchi", "Largo Treze", "Adolfo Pinheiro", "Alto da Boa Vista", "Borba Gato", "Brooklin", "Campo Belo", "Eucaliptos", "Moema", "AACD-Servidor", "Hospital São Paulo", "Chácara Klabin", "Vila Madalena", "Sumaré", "Clínicas", "Consolação", "Trianon-Masp", "Brigadeiro", "Santos-Imigrantes", "Alto do Ipiranga", "Sacomã", "Tamanduateí", "Vila Prudente", "Oratório", "São Lucas", "Camilo Haddad", "Vila Tolstói", "Vila União", "Jardim Planalto", "Sapopemba", "Fazenda da Juta", "São Mateus", "Bio Grande da Serra", "Ribeirão Pires", "Guapituba", "Mauá", "Capuava", "Santo André", "Prefeito Saladino", "Utinga", "São Caetano do Sul", "Ipiranga", "Juventus Mooca", "Dom Bosco", "José Bonifácio", "Guaianases", "Antonio Gianetti Neto", "Ferraz de Vasconcelos", "Poá", "Calmon Viana", "Suzano", "Jundiapeba", "Braz Cubas", "Mogi das Cruzes", "Estudantes", "Aracaré", "Itaquaquecetuba", "Engeheiro Manoel Feio", "Jardim Romando", "Itaim Paulista", "Jardim Helena-Vila Mara", "São Miguel Paulista", "Comendador Ermelino", "USP Leste", "Engenheiro Goulart", "Guarulhos-Cecap", "Aeroporto-Guarulhos"};
    String[] stations = {"Jabaquara", "Conceição", "São Judas", "Saúde", "Praça da Árvore", "Santa Cruz", "Vila Mariana", "Ana Rosa", "Paraíso", "Vergueiro", "São Joaquim", "Japão-Liberdade", "Sé", "São Bento", "Luz", "Tiradentes", "Armênia", "Portuguesa-Tietê", "Carandiru", "Santana", "Jardim São Paulo-Aryton Senna", "Parada Inglesa", "Tucuruvi", "Jundaí", "Várzea Paulista", "Campo Limpo Paulista", "Botujuru", "Franciso Morato", "Baltazar Fidélis", "Franco da Rocha", "Caieiras", "Perus", "Vila Aurora", "Jaraguá", "Vila Clarice", "Pirituba", "Piqueri", "Lapa(Rubi)", "Água Branca", "Palmeiras Barra Funda", "Amador Bueno", "Santa Rita", "Itapevi", "Engenheiro Cardoso", "Sagrado Cadorso", "Jandira", "Jardim Silveira", "Jardim Belval", "Barueri", "Antonio João", "Santa Teresina", "Carapicuiba", "General Miguel Costa", "Quitaûna", "Comandante Sampaio", "Osasco", "Presidente Altino", "Imperatriz Leopoldina", "Domingos de Moraes", "Lapa(Diamante)", "Júlio Prestes", "São Paulo-Morumbi", "Butantã", "Pinheiros", "Faria Lima", "Fradique Coutinho", "Oscar Freire", "Paulista", "Higienópolis-Mackenzie", "República", "Marechal Deodoro", "Santa Cecília", "Anhangabaú", "Pedro II", "Brás", "Bresser-Mocca", "Belém", "Tatuapé", "Carrão", "Penha", "Vila Matilde", "Guilhermiana Esperança", "Patriarca-Vila Ré", "Arthur Alvim", "Corinthians Itaquera", "Grajaú", "Primavera-Interlagos", "Autódromo", "Jurubatuba", "Socorro", "Santo Amaro", "Granja Julieta", "Morumbi", "Berrini", "Vila Olímpia", "Cidade Jardim", "Hebraica Rebouças", "Cidade Universitária", "Vila Lobos Jaguaré", "Ceasa", "Capão Redondo", "Campo Limpo", "Vila das Belezas", "Giovanni Gronchi", "Largo Treze", "Adolfo Pinheiro", "Alto da Boa Vista", "Borba Gato", "Brooklin", "Campo Belo", "Eucaliptos", "Moema", "AACD-Servidor", "Hospital São Paulo", "Chácara Klabin", "Vila Madalena", "Sumaré", "Clínicas", "Consolação", "Trianon-Masp", "Brigadeiro", "Santos-Imigrantes", "Alto do Ipiranga", "Sacomã", "Tamanduateí", "Vila Prudente", "Oratório", "São Lucas", "Camilo Haddad", "Vila Tolstói", "Vila União", "Jardim Planalto", "Sapopemba", "Fazenda da Juta", "São Mateus", "Bio Grande da Serra", "Ribeirão Pires", "Guapituba", "Mauá", "Capuava", "Santo André", "Prefeito Saladino", "Utinga", "São Caetano do Sul", "Ipiranga", "Juventus Mooca", "Dom Bosco", "José Bonifácio", "Guaianases", "Antonio Gianetti Neto", "Ferraz de Vasconcelos", "Poá", "Calmon Viana", "Suzano", "Jundiapeba", "Braz Cubas", "Mogi das Cruzes", "Estudantes", "Aracaré", "Itaquaquecetuba", "Engeheiro Manoel Feio", "Jardim Romando", "Itaim Paulista", "Jardim Helena-Vila Mara", "São Miguel Paulista", "Comendador Ermelino", "USP Leste", "Engenheiro Goulart", "Guarulhos-Cecap", "Aeroporto-Guarulhos"};

    private Label label;
    @FXML
    private ComboBox<String> origem;
    @FXML
    private ComboBox<String> destino;
    @FXML
    private Button btn_gera_rota;
    @FXML
    private TableView tb_rota;
    @FXML
    private TableColumn<Linhas, String> tb_linha;
    @FXML
    private TableColumn<Linhas, String> tb_estacao;

    @FXML
    private void btn_gera_rota(ActionEvent event) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, IOException, UnknownHostException, ClassNotFoundException, InterruptedException, FileNotFoundException, JSONException {

        String stationOrigem = origem.getSelectionModel().getSelectedItem();
        String stationDestino = destino.getSelectionModel().getSelectedItem();
        int indexOrigem = 0, indexDestino = 0;
        for (int i = 0; i < stationsFull.length; i++) {
            if (stationOrigem.equals(stationsFull[i])) {
                indexOrigem = i;
            }
            if (stationDestino.equals(stationsFull[i])) {
                indexDestino = i;
            }
        }

        byte[] encryptOrigem = RSAUtil.encrypt(Integer.toString(indexOrigem));
        byte[] encryptDestino = RSAUtil.encrypt(Integer.toString(indexDestino));

        tb_rota.getItems().clear();

        callServer(encryptOrigem, encryptDestino);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();        
        new AutoCompleteComboBox<>(origem);
        new AutoCompleteComboBox<>(destino);
    }

    private void loadData() {
        listOrigem.removeAll();
        listDestino.removeAll();

        tb_linha.setCellValueFactory(new PropertyValueFactory<>("linha"));
        tb_estacao.setCellValueFactory(new PropertyValueFactory<>("estacao"));

        Arrays.sort(stations);

        for (int i = 0; i < stations.length; i++) {
            listOrigem.add(stations[i]);
            listDestino.add(stations[i]);
        }

        origem.getItems().addAll(listOrigem);
        destino.getItems().addAll(listDestino);
    }

    private void callServer(byte[] iorigem, byte[] idestino) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException, FileNotFoundException, IOException, JSONException {
        InetAddress host = InetAddress.getLocalHost();
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        socket = new Socket(host.getHostName(), 9876);
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending Origem to Server");
        oos.writeObject(iorigem);
        oos.close();
        Thread.sleep(100);

        socket = new Socket(host.getHostName(), 9876);
        //write to socket using ObjectOutputStream
        oos = new ObjectOutputStream(socket.getOutputStream());
        System.out.println("Sending Destino to Server");
        oos.writeObject(idestino);

        //read the server response message
        ois = new ObjectInputStream(socket.getInputStream());
        List<String> message = (List<String>) ois.readObject();
        //close resources
        ois.close();
        oos.close();

        System.out.println("-----------------------------------------------------");
        String linha = "azul";
        boolean achou = false;

        System.out.println(message.size());
        List<String> linhasNomes = findLinha(message);
        System.out.println(linhasNomes.size());
        for (int i = 0; i < message.size(); i++) {
            System.out.println(message.get(i) + " " + linhasNomes.get(i));
            tb_rota.getItems().add(new Linhas(linhasNomes.get(i), message.get(i)));
        }
        

        System.out.println("-----------------------------------------------------");

    }

    private List<String> findLinha(List<String> stations) {
        List<String> azul = Arrays.asList("Tucuruvi", "Parada Inglesa", "Jardim São Paulo-Ayrton Senna", "Santana", "Carandiru", "Portuguesa-Tietê", "Armênia", "Tiradentes", "Luz", "São Bento", "Sé", "Japão-Liberdade", "São Joaquim", "Vergueiro", "Paraíso", "Ana Rosa", "Vila Mariana", "Santa Cruz", "Praça da Árvore", "Saúde", "São Judas", "Conceição", "Jabaquara");
        List<String> verde = Arrays.asList("Vila Madalena", "Sumaré", "Clínicas", "Consolação", "Trianon-Masp", "Brigadeiro", "Paraíso", "Ana Rosa", "Chácara Klabin", "Santos-Imigrantes", "Alto do Ipiranga", "Sacomã", "Tamanduateí", "Vila Prudente");
        List<String> vermelha = Arrays.asList("Marechal Deodoro", "Santa Cecília", "República", "Anhangabaú", "Sé", "Pedro II", "Brás", "Bresser-Mooca", "Belém", "Tatuapé", "Carrão", "Penha", "Vila Matilde", "Guilhermina-Esperança", "Patriarca", "Artur Alvim", "Corinthians-Itaquera");
        List<String> amarela = Arrays.asList("São Paulo-Morumbi", "Butantã", "Pinheiros", "Faria Lima", "Fradique Coutinho", "Oscar Freire", "Paulista", "Higienópolis-Mackenzie", "República", "Luz");
        List<String> lilas = Arrays.asList("Capão Redondo", "Campo Limpo", "Vila das Belezas", "Giovanni Gronchi", "Santo Amaro", "Largo Treze", "Adolfo Pinheiro", "Alto da Boa Vista", "Borba Gato", "Brooklin", "Campo Belo", "Eucaliptos", "Moema", "AACD-Servidor", "Hospital São Paulo", "Santa Cruz", "Chácara Klabin");
        List<String> rubi = Arrays.asList("Luz", "Palmeiras Barra Funda", "Água Branca", "Lapa(Rubi)", "Piqueri", "Pirituba", "Vila Clarice", "Jaraguá", "Perus", "Caieiras", "Franco da Rocha", "Baltazar Fidélis", "Francisco Morato", "Botujuru", "Campo Limpo Paulista", "Várzea Paulista", "Jundiaí");
        List<String> diamante = Arrays.asList("Júlio Prestes", "Lapa(Diamante)", "Domingos de Moraes", "Imperatriz Leopoldina", "Presidente Altino", "Osasco", "Comandante Sampaio", "Quitaúna", "General Miguel Costa", "Carapicuíba", "Santa Terezinha", "Antônio João", "Barueri", "Jardim Belval", "Jardim Silveira", "Jandira", "Sagrado Coração", "Engenheiro Cardoso", "Itapevi", "Santa Rita", "Amador Bueno");
        List<String> esmeralda = Arrays.asList("Osasco", "Presidente Altino", "Ceasa", "Vila Lobos-Jaguaré", "Cidade Universitária", "Pinheiros", "Hebraica Rebouças", "Cidade Jardim", "Vila Olímpia", "Berrini", "Morumbi", "Granja Julieta", "Santo Amaro", "Socorro", "Jurubatuba", "Autódromo", "Primavera-Interlagos", "Grajaú");
        List<String> turquesa = Arrays.asList("Brás", "Juventus Mooca", "Ipiranga", "Tamanduateí", "São Caetano", "Utinga", "Prefeito Saladino", "Prefeito Celso Daniel-Santo André", "Capuava", "Mauá", "Guapituba", "Ribeirão Pires", "Rio Grande da Serra");
        List<String> coral = Arrays.asList("Luz", "Brás", "Tatuapé", "Itaquera", "Dom Bosco", "José Bonifácio", "Guaianases", "Antônio Gianetti Neto", "Ferraz de Vasconcelos", "Poá", "Calmon Viana", "Suzano", "Jundiapeba", "Brás Cubas", "Mogi das Cruzes", "Estudantes");
        List<String> safira = Arrays.asList("Brás", "Tatuapé", "Engenheiro Goulart", "USP Leste", "Comendador Ermelino", "São Miguel Paulista", "Jardim Helena-Vila Mara", "Itaim Paulista", "Jardim Romano", "Engenheiro Manoel Feio", "Itaquaquecetuba", "Aracaré", "Calmon Viana");
        List<String> jade = Arrays.asList("Luz", "Brás", "Tatuapé", "Eng. Goulart", "Guarulhos-Cecap", "Aeroporto-Guarulhos");
        List<String> prata = Arrays.asList("Vila Prudente", "Oratório", "São Lucas", "Camilo Haddad", "Vila Tolstói", "Vila União", "Jd. Planalto", "Sapopemba", "Fazenda da Juta", "São Mateus");
        
        List<String> baldiacao = Arrays.asList("Osasco", "Presidente Altino", "Pinheiros", "Santo Amaro", "Santa Cruz", "Chácara Kanlin", "Tamanduateí", "Vila Prudente", "Brás", "Luz", "Ana Rosa", "Paraíso", "Sé", "Tatuapé", "Engenheiro Goulart", "Corinthians Itaquera", "Calmon Viana");
        
        List<Integer> faltam = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        
        boolean achouBald = false;
        boolean achouLin = false;

        for (int j = 0; j < stations.size(); j++) {
            achouBald = false;
            achouLin = false;
            System.out.println("Size lines: " +  lines.size());
            System.out.println("Faltam lines: " +  faltam.size());
            for (int i = 0; i < baldiacao.size(); i++) {
                System.out.println("Estação: " + stations.get(j));
                System.out.println("Baldiação: " + baldiacao.get(i));
                if (achouBald == false) {
                    if (stations.get(j).equals(baldiacao.get(i))) {
                        faltam.add(j);
                        lines.add("Baldiacao");
                        achouBald = true;
                        break;
                    }
                }
            }
            if (achouBald == false) {
                faltam.add(-1);
                if (achouLin == false) {
                    for (int i = 0; i < azul.size(); i++) {
                        if (stations.get(j).equals(azul.get(i))) {
                            achouLin = true;
                            lines.add("Azul");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < verde.size(); i++) {
                        if (stations.get(j).equals(verde.get(i))) {
                            achouLin = true;
                            lines.add("Verde");
                            break;
                        }

                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < vermelha.size(); i++) {
                        if (stations.get(j).equals(vermelha.get(i))) {
                            achouLin = true;
                            lines.add("Vermelha");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < amarela.size(); i++) {
                        if (stations.get(j).equals(amarela.get(i))) {
                            achouLin = true;
                            lines.add("Amarela");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < lilas.size(); i++) {
                        if (stations.get(j).equals(lilas.get(i))) {
                            achouLin = true;
                            lines.add("Lilas");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < rubi.size(); i++) {
                        if (stations.get(j).equals(rubi.get(i))) {
                            achouLin = true;
                            lines.add("Rubi");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < diamante.size(); i++) {
                        if (stations.get(j).equals(diamante.get(i))) {
                            achouLin = true;
                            lines.add("Diamante");
                            break;
                        }
                    }
                }

                if (achouLin == false) {
                    for (int i = 0; i < esmeralda.size(); i++) {
                        if (stations.get(j).equals(esmeralda.get(i))) {
                            achouLin = true;
                            lines.add("Esmeralda");
                            break;
                        }
                    }
                }
                if (achouLin == false) {
                    for (int i = 0; i < turquesa.size(); i++) {
                        if (stations.get(j).equals(turquesa.get(i))) {
                            achouLin = true;
                            lines.add("Turquesa");
                            break;
                        }
                    }
                }
                if (achouLin == false) {
                    for (int i = 0; i < coral.size(); i++) {
                        if (stations.get(j).equals(coral.get(i))) {
                            achouLin = true;
                            lines.add("Coral");
                            break;
                        }
                    }
                }
                if (achouLin == false) {
                    for (int i = 0; i < safira.size(); i++) {
                        if (stations.get(j).equals(safira.get(i))) {
                            achouLin = true;
                            lines.add("Safira");
                            break;
                        }
                    }
                }
                if (achouLin == false) {
                    for (int i = 0; i < jade.size(); i++) {
                        if (stations.get(j).equals(jade.get(i))) {
                            achouLin = true;
                            lines.add("Jade");
                            break;
                        }
                    }
                }
                if (achouLin == false) {
                    for (int i = 0; i < prata.size(); i++) {
                        if (stations.get(j).equals(prata.get(i))) {
                            achouLin = true;
                            lines.add("Prata");
                            break;
                        }
                    }
                }
            }
        }
        
        
        for (int i = 0; i < faltam.size(); i++) {
            System.out.println("=================================================================");
            System.out.println(faltam.get(i));
            if(faltam.get(i) != -1){
                System.out.println("Atual: " + lines.get(i));
                if(lines.get(i).equals("Baldiacao")){
                    int j = i+1;
                    System.out.println("j: " + j);
                    if(j < faltam.size()){
                        if(j == faltam.size() -1 ){
                            j = i-1;
                            if(!lines.get(j).equals("Baldiacao")){
                                lines.set(i, lines.get(j));
                            }
                            else{
                                j--;
                                if(!lines.get(j).equals("Baldiacao")){
                                    lines.set(i, lines.get(j));    
                                }
                                
                            }
                        }
                        else{
                            System.out.println("Atual j: " + lines.get(j));
                            if(!lines.get(j).equals("Baldiacao")){
                                lines.set(i, lines.get(j));
                            }
                            else{
                                for (int k = j; k < faltam.size(); k++) {
                                    System.out.println("Atual k"+k + ": " + lines.get(k));
                                    if(!lines.get(k).equals("Baldiacao")){
                                        lines.set(i, lines.get(k));
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else{
                        j = i-1;
                            if(!lines.get(j).equals("Baldiacao")){
                                lines.set(i, lines.get(j));
                            }
                            else{
                                j--;
                                if(!lines.get(j).equals("Baldiacao")){
                                    lines.set(i, lines.get(j));    
                                }
                                
                            }
                    }
                }
            }
            System.out.println("=================================================================");
        }

        return lines;
    }
}
