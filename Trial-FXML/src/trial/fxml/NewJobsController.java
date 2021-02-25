/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import java.io.IOException;
import java.net.URL;
import static java.sql.JDBCType.NULL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
public class NewJobsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private ChoiceBox cats, locs;
    @FXML private DatePicker date;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        DBConnection connect = new DBConnection();
        cats.setItems(FXCollections.observableArrayList(connect.getCategories()));
        locs.setItems(FXCollections.observableArrayList(connect.getLocations()));
       
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
    
    public void newJob()
    {
        DBConnection connect = new DBConnection();
        LocalDate today = java.time.LocalDate.now();
       // System.out.println("To String " + cats.getValue().toString());
        if(cats.getValue() != null && locs.getValue() != null && null != date.getValue())
        {
            if(today.isAfter(date.getValue()))
            {
                MyAlerts alert = new MyAlerts("The date is invalid. It is before today");            
            }else
            {
                MyAlerts alert = new MyAlerts(connect.insertJob(cats.getValue().toString(), locs.getValue().toString(), date.getValue()));
            }
        }else
        {
            MyAlerts alert = new MyAlerts("Ensure All Fields Are Filled");  
        }
        
    }
        
       
}
    
    

