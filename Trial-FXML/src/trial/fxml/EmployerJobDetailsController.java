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
public class EmployerJobDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField clName, clContact,location, estate, hseNo, date, pay;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connect = new DBConnection();
                
        if(EmployerLandingController.state.equals("Not Booked"))
        {
            connect.notBookedJobDets();
            clName.setDisable(true);
            clContact.setDisable(true);
            location.setText(connect.getLocation());
            estate.setText(connect.getEstate());
            hseNo.setText(Integer.toString(connect.getHouse()));
            date.setText(connect.getDate());
            pay.setText(Double.toString(connect.getPay()));
        }
        else
        {
            connect.bookedJobDets();
            clName.setText(connect.getName());
            clContact.setText(Integer.toString(connect.getTel()));
            location.setText(connect.getLocation());
            estate.setText(connect.getEstate());
            hseNo.setText(Integer.toString(connect.getHouse()));
            date.setText(connect.getDate());
            pay.setText(Double.toString(connect.getPay()));
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
    
    public void delJob()
    {
        DBConnection connect = new DBConnection();
        connect.delJob();
        if(connect.getState() == true){
            MyAlerts alert = new MyAlerts("Job Deleted Successfully");
        }else
        {
            MyAlerts alert = new MyAlerts("Job Not Deleted");
        }
    }
    
//    public void insertRemark(){
//        DBConnection connect = new DBConnection();
//        if(!"".equals(remarks.getText())){
//            connect.addRemark(remarks.getText());            
//        }
//    }
    
}
