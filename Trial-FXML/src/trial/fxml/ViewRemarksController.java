/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chumba
 */
public class ViewRemarksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextArea remarks;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connect = new DBConnection();
        connect.getRemarkforEmployee();
        
        remarks.setText(connect.getRemarks());
        
    }
    
    public void employeeLandingScene(ActionEvent e) throws IOException
    {
       Parent employeeLandingViewParent = FXMLLoader.load(getClass().getResource("EmployeeLanding.fxml"));
       Scene employeeLandingViewScene = new Scene(employeeLandingViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(employeeLandingViewScene);
       window.show();
    }
    
}
