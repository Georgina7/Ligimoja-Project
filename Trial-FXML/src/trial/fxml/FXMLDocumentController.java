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
 *
 * @author Chumba
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML private Button employee, employer;
    
    static String userType;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    //Method to change scene
    public void changeScene(ActionEvent e) throws IOException
    {
        if(e.getSource() == employee)
        {
            userType = "employee";
        }else if(e.getSource() == employer)
        {
            userType = "employer";
        }
       Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
       Scene loginViewScene = new Scene(loginViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(loginViewScene);
       window.show();
    }
    
}
