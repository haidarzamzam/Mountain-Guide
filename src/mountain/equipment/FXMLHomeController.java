/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountain.equipment;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Root
 */

public class FXMLHomeController implements Initializable {

    static final String JDBC_DRIVER="com.mysl.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/mountain";
    
    static final String USER="root";
    static final String PASS = "";
    
    @FXML
    private Label label;
    @FXML
    private TextField OrangTxt;
    @FXML
    private TextField NginapTxt;
    @FXML
    private Button ButtonOK;
    @FXML
    private Label jmlCarrier;
    @FXML
    private Label jmlSOS;
    @FXML
    private Label jmlHL;
    @FXML
    private Label jmlTenda;
    @FXML
    private Label jmlHP;
    @FXML
    private Label jmlPakaian;
    @FXML
    private Label jmlRainCoat;
    @FXML
    private Label jmlSepatu;
    @FXML
    private Label jmlSB;
    @FXML
    private Label jmlKompas;
    @FXML
    private Label jmlMatras;
    @FXML
    private Label jmlTP;
    @FXML
    private Button ButtonOK1;
    @FXML
    private TextField nama;
    @FXML
    private TextField alamat;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
 @FXML
    void Print(ActionEvent event) {
        Connection conn =null;
        Statement stm=null;
        
        String Carrier, SOS, HL, Tenda, HP, Pakaian, RC, Sepatu, SB, Kompas, Matras, TP, Name, Address;
        
        Carrier= jmlCarrier.getText();
        SOS=jmlSOS.getText();
        Tenda=jmlTenda.getText();
        HL=jmlHL.getText();
        HP=jmlHP.getText();
        Pakaian =jmlPakaian.getText();
        RC=jmlRainCoat.getText();
        Sepatu=jmlSepatu.getText();
        SB=jmlSB.getText();
        Kompas=jmlKompas.getText();
        Matras=jmlMatras.getText();
        TP=jmlTP.getText();
        Name=nama.getText();
        Address=alamat.getText();
        
        if((nama.getText() == null || nama.getText().length() == 0)){
            new Alert(AlertType.ERROR, "Lengkapi Nama").showAndWait();
        }
        else if((alamat.getText() == null || alamat.getText().length() == 0)){
            new Alert(AlertType.ERROR, "Lengkapi Alamat").showAndWait();
        }
        else{
            try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            
            stm=(Statement) conn.createStatement();
            
           String sql ="INSERT INTO `tb_barang`(`id_cek`, `nama`, `alamat`,`jmlCarrier`, `jmlSOS`, `jmlHL`, `jmlTenda`, `jmlHP`, `jmlPakaian`, `jmlRainCoat`, `jmlSepatu`, `jmlSB`, `jmlKompas`, `jmlMatras`, `jmlTP`) VALUES (NULL,'"+Name+"','"+Address+"','"+Carrier+"','"+SOS+"','"+HL+"','"+Tenda+"','"+HP+"','"+Pakaian+"','"+RC+"','"+Sepatu+"','"+SB+"','"+Kompas+"','"+Matras+"','"+TP+"')";
           stm.executeUpdate(sql);
            stm.close();
            conn.close();
                        try{
                ((Node)(event.getSource())).getScene().getWindow().hide();
                FXMLLoader fxml = new FXMLLoader();
                fxml.setLocation(getClass().getResource("FXMLReport.fxml"));
                Scene scene = new Scene(fxml.load());
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("MenuAwal");
                stage.show();
            }catch(IOException e){
            System.out.println("Failed to create new window"+e);
            }
        }catch(Exception e) {
            
        }    
    }
}
    
    @FXML
    private void OK(ActionEvent event) {
        jmlCarrier.setText(OrangTxt.getText());
        jmlSOS.setText(OrangTxt.getText());
        jmlHL.setText(OrangTxt.getText());
        jmlHP.setText(OrangTxt.getText());
        jmlSepatu.setText(OrangTxt.getText());
        jmlRainCoat.setText(OrangTxt.getText());
        jmlSB.setText(OrangTxt.getText());
        jmlKompas.setText(OrangTxt.getText());
        jmlMatras.setText(OrangTxt.getText());
        jmlTP.setText(OrangTxt.getText());
        
        if((OrangTxt.getText() == null || OrangTxt.getText().length() == 0)){
            new Alert(AlertType.ERROR, "Lengkapi Jumlah Orang").showAndWait();
        }
          else if((NginapTxt.getText() == null || NginapTxt.getText().length() == 0)){
            new Alert(AlertType.ERROR, "Lengkapi Jumlah Hari").showAndWait();
        }
        
        if(Double.parseDouble(OrangTxt.getText()) % 2 == 0){
           jmlTenda.setText(Integer.parseInt(OrangTxt.getText()) / 2+"");
        }else {
            double a = ((Double.parseDouble(OrangTxt.getText()) / 2.0) + 0.5);
            int b = (int) a;
           jmlTenda.setText( b+"");
        }
        jmlPakaian.setText(Integer.parseInt(NginapTxt.getText()) * Integer.parseInt(OrangTxt.getText())+"");
        ButtonOK1.setDisable(false);
    }


    
}
