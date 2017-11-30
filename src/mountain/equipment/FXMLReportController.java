/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountain.equipment;

import com.mysql.jdbc.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import static mountain.equipment.FXMLHomeController.DB_URL;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable.PrintMode;

/**
 * FXML Controller class
 *
 * @author Root
 */
public class FXMLReportController implements Initializable {

    
    static final String JDBC_DRIVER="com.mysl.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/mountain";
    
    static final String USER="root";
    static final String PASS = "";
    
    @FXML
    private TableView<Data.ViewData> TabelY;
    @FXML
    private TableColumn<?, ?> jmlCarrier;
    @FXML
    private TableColumn<?, ?> jmlSOS;
    @FXML
    private TableColumn<?, ?> jmlHL;
    @FXML
    private TableColumn<?, ?> jmlTenda;
    @FXML
    private TableColumn<?, ?> jmlHP;
    @FXML
    private TableColumn<?, ?> jmlPakaian;
    @FXML
    private TableColumn<?, ?> jmlRainCoat;
    @FXML
    private TableColumn<?, ?> jmlSepatu;
    @FXML
    private TableColumn<?, ?> jmlSB;
    @FXML
    private TableColumn<?, ?> jmlKompas;
    @FXML
    private TableColumn<?, ?> jmlMatras;
    @FXML
    private TableColumn<?, ?> jmlTP;
    private ObservableList<Data.ViewData> isi = FXCollections.observableArrayList();
   

    private PreparedStatement stmt = null;
    @FXML
    private Button Print;
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        com.mysql.jdbc.Connection conn =null;
        Statement stm=null;
        setKolomTable();
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            
            stm=(Statement) conn.createStatement();
            
           String sql ="SELECT * FROM `tb_barang`";
           ResultSet rs = stm.executeQuery(sql);
           
            
            while (rs.next()){
              isi.add(new Data.ViewData(rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
            }
           stm.close();
           conn.close();
        }catch(SQLException ex){
            System.out.print(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
        TabelY.setItems(isi);
        
    }
    private void setKolomTable(){
        jmlCarrier.setCellValueFactory(new PropertyValueFactory<>("jmlCarrier"));
        jmlSOS.setCellValueFactory(new PropertyValueFactory<>("jmlSOS"));
        jmlHL.setCellValueFactory(new PropertyValueFactory<>("jmlHL"));
        jmlTenda.setCellValueFactory(new PropertyValueFactory<>("jmlTenda"));
        jmlHP.setCellValueFactory(new PropertyValueFactory<>("jmlHP"));
        jmlPakaian.setCellValueFactory(new PropertyValueFactory<>("jmlPakaian"));
        jmlRainCoat.setCellValueFactory(new PropertyValueFactory<>("jmlRainCoat"));
        jmlSepatu.setCellValueFactory(new PropertyValueFactory<>("jmlSepatu"));
        jmlSB.setCellValueFactory(new PropertyValueFactory<>("jmlSB"));
        jmlKompas.setCellValueFactory(new PropertyValueFactory<>("jmlKompas"));
        jmlMatras.setCellValueFactory(new PropertyValueFactory<>("jmlMatras"));
        jmlTP.setCellValueFactory(new PropertyValueFactory<>("jmlTP"));
    }

   
    @FXML
    private void Invoice(ActionEvent event) {

            //PrintReport viewReport = new PrintReport();
            //viewReport.showReport();
            
    }
}
            
           
