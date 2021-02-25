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
public class EmployeeJobDetailsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField clName, clContact,location, estate, hseNo, date, pay;
    @FXML private Button action, completed;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connect = new DBConnection();
        connect.employeeJobDets();
        
        clName.setText(connect.getName());
        clContact.setText(Integer.toString(connect.getTel()));
        location.setText(connect.getLocation());
        estate.setText(connect.getEstate());
        hseNo.setText(Integer.toString(connect.getHouse()));
        date.setText(connect.getDate());
        pay.setText(Double.toString(connect.getPay()));
        
        if(connect.empTypeAction().equals("Not Booked"))
        {
            action.setText("Book");
        }else
        {
            if(connect.completeAction().equals("Complete"))
            {
                action.setMinWidth(92);
                action.setText("View Remarks");
            }
            else
            {
                action.setText("Cancel");
                completed.setVisible(true);
                completed.setDisable(false);
            }
            
        }
        
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
    
    public void actionOnJob (ActionEvent e) throws IOException
    {
        DBConnection connect = new DBConnection();
        if(action.getText().equals("Book"))
        {
            connect.empBooking();
            if(connect.getState() == true)
            {
                MyAlerts alert = new MyAlerts("Job Booking Successful");
            }else
            {
                MyAlerts alert = new MyAlerts("Job Booking Unsuccessful");
            }
        }else if(action.getText().equals("Cancel"))
        {
            connect.empCancelBooking();
            if(connect.getState() == true)
            {
                MyAlerts alert = new MyAlerts("Job Cancelled");
            }else
            {
                MyAlerts alert = new MyAlerts("Job Not Cancelled");
            }
        }else
        {
            Parent RemarksViewParent = FXMLLoader.load(getClass().getResource("ViewRemarks.fxml"));
            Scene RemarksViewScene = new Scene(RemarksViewParent);

            //Getting stage info.
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

            window.setScene(RemarksViewScene);
            window.show();
            
        }
        
    }
    
    public void completeJob()
    {
        DBConnection connect = new DBConnection();
        connect.empCompleteJob();
        if(connect.getState() == true)
        {
            MyAlerts alert = new MyAlerts("Job Completed. Collect Payment");
        }else
        {
            MyAlerts alert = new MyAlerts("Job Not Completed.");
        }
    }   
    
}
