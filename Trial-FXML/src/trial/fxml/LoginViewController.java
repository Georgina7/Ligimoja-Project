/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Chumba
 */
public class LoginViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField userID;
    @FXML private PasswordField password;
    
    static int ID;
    
    Alert a = new Alert(AlertType.NONE);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     //Method to change scene
    public void changeScene(ActionEvent e) throws IOException
    {
       Parent landingViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
       Scene landingViewScene = new Scene(landingViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       Platform.runLater(() -> window.setScene(landingViewScene));
       window.show();
    }
    
    public void loginScene(ActionEvent e) throws IOException
    {
        DBConnection connect = new DBConnection();        
        
        if(!"".equals(userID.getText()) && !"".equals(password.getText()))
        {
            try{
                int id = Integer.parseInt(userID.getText());
                String pass = password.getText();
            
            connect.Login(id, pass);
            if(connect.getState() == true)
            {
                ID = id;
                String landingPage = "";
                if(FXMLDocumentController.userType.equals("employee"))
                {
                    landingPage = "EmployeeLanding.fxml";
                }else if(FXMLDocumentController.userType.equals("employer"))
                {
                    landingPage = "EmployerLanding.fxml";
                }
                Parent employeeLandingViewParent = FXMLLoader.load(getClass().getResource(landingPage));
                Scene employeeLandingViewScene = new Scene(employeeLandingViewParent);

                //Getting stage info.
                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

                Platform.runLater(() -> window.setScene(employeeLandingViewScene));
                window.show();
                
            }
            else
            {
                ID = 0;
                MyAlerts alert = new MyAlerts("Invalid Credentials");                
            }
            }catch(Exception ex)
            {
                MyAlerts alert = new MyAlerts("Ensure User ID is a numerical value");
            }
            
            
            
        }
        
        
       
    }
    
}
