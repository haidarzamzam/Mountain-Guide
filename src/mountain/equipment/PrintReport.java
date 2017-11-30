/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mountain.equipment;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static mountain.equipment.FXMLReportController.DB_URL;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Root
 */
public class PrintReport  extends javax.swing.JFrame{    
    static final String JDBC_DRIVER="com.mysl.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/mountain";

    static final String USER="root";
    static final String PASS = "";

    Connection conn =null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public PrintReport() throws HeadlessException {
    }

    public void showReport(){
         try {
             Class.forName("com.mysql.jdbc.Driver");
             conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(DB_URL,USER,PASS);
             JasperReport jasperReport = JasperCompileManager.compileReport("newReport.jrxml");
             JasperPrint JasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);
             JRViewer viewer = new JRViewer(JasperPrint);
             viewer.setOpaque(true);
             viewer.setVisible(true);

             this.add(viewer);
             this.setSize(900,500); // JFrame size
             this.setVisible(true);

         } catch (Exception e) {
             JOptionPane.showMessageDialog(rootPane, e.getMessage());
         } 
    }
}
