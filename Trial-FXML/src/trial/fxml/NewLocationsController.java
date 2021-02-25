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
public class NewLocationsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField loc, est, hs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
     
    public void newLoc(){
        DBConnection connect = new DBConnection();
        if(!"".equals(loc.getText()) && !"".equals(est.getText()) && !"".equals(hs.getText()))
        {
            try{
                int house = Integer.parseInt(hs.getText());
                MyAlerts alert = new MyAlerts(connect.insertLocation(loc.getText(), est.getText(), house));
            }catch(Exception ex)
            {
                MyAlerts alert = new MyAlerts("Ensure House Number Is A Numerical Value");
            }      
            
        }
        else
        {
            MyAlerts alert = new MyAlerts("Ensure All Fields Are Filled");
        }
    }
    
}
