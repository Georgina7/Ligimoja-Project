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
public class AddRemarksController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextArea remarks;
    @FXML private Button submit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connect = new DBConnection();
        connect.getRemarkforEmployer();
        
        if(connect.getState() == true)
        {
            remarks.setEditable(false);
            remarks.setText(connect.getRemarks());
            
            submit.setVisible(false);
            submit.setDisable(true);
        }
    }  
    
    public void employerLandingScene(ActionEvent e) throws IOException
    {
       Parent employerLandingViewParent = FXMLLoader.load(getClass().getResource("EmployerLanding.fxml"));
       Scene employerLandingViewScene = new Scene(employerLandingViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(employerLandingViewScene);
       window.show();
    }
    
    public void addRemark()
    {
        DBConnection connect = new DBConnection();
        if(!"".equals(remarks.getText()))
        {
            connect.addRemark(remarks.getText());
            if(connect.getState() == true)
            {
                MyAlerts alert = new MyAlerts("Remark Successfully Submited");
            }else
            {
                MyAlerts alert = new MyAlerts("Remark Not Submited");
            }
        }
        else
        {
            MyAlerts alert = new MyAlerts("Ensure TextArea Is Filled");
        }
    }
    
}
