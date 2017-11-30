/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountain.equipment;

import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.event.EventHandler;
import static mountain.equipment.FXMLReportController.DB_URL;

/**
 *
 * @author Root
 */
public class MountainEquipment extends Application {
    static final String JDBC_DRIVER="com.mysl.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/mountain";
    
    static final String USER="root";
    static final String PASS = "";
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
        stage.setResizable(false);
        Scene scene = new Scene(root);
        String source="FXMLReport.fxml";
        
        stage.setOnCloseRequest( event -> {
        com.mysql.jdbc.Connection conn =null;
        Statement stm=null;
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(DB_URL,USER,PASS);
            
            stm=(Statement) conn.createStatement();
            
            //PrintReport viewReport = new PrintReport();
            //viewReport.showReport();
            
            String sql ="TRUNCATE tb_barang";
            stm.executeUpdate(sql);
            
            stm.close();
            conn.close();
        }catch(SQLException ex){
            System.out.print(ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLReportController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } );
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
