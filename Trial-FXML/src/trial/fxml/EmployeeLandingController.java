/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import static trial.fxml.EmployerLandingController.locName;

/**
 * FXML Controller class
 *
 * @author Chumba
 */
public class EmployeeLandingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField uID, uName, uEmail, uTel; 
    
    @FXML private Button account, back;
    
    @FXML private TableView<DBConnection> allJobs, bookedJobs, completedJobs;
    @FXML private TableColumn<DBConnection, String> location, date, bookedDate, bookedLocation, completedDate, completedLocation;
    @FXML private TableColumn<DBConnection, Integer> jobID, bookedJobID, completedJobID;
    @FXML private TableColumn<DBConnection, Double> wage, bookedWage, completedWage;
    
    static int jobid;
//    
//    @FXML private TextArea textJob;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
      DBConnection connect = new DBConnection();
      connect.userDets();
      
      uID.setText(Integer.toString(connect.getIdentity()));
      uName.setText(connect.getName());
      uEmail.setText(connect.getEmail());
      uTel.setText(Integer.toString(connect.getTel()));
      
        jobID.setCellValueFactory(new PropertyValueFactory<>("dbJobID"));
        location.setCellValueFactory(new PropertyValueFactory<>("dbLocation"));
        wage.setCellValueFactory(new PropertyValueFactory<>("dbPay"));
        date.setCellValueFactory(new PropertyValueFactory<>("dbDate"));
        
        ObservableList<DBConnection> jobs = FXCollections.observableArrayList();
        jobs.addAll(connect.employeeTableJobs("Not Booked", "Incomplete"));
        System.out.println(jobs);
        allJobs.setItems(jobs);
        
        allJobs.setRowFactory(tv -> {
        TableRow<DBConnection> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                jobid = row.getItem().getDbJobID();
                jobDetailsScene(event);
                 
            }catch(IOException e)
            {
                e.getCause();
            }         
            //System.out.println(row.getItem().getDbEstate());
        }
    });
    return row ;
});
        
        bookedJobID.setCellValueFactory(new PropertyValueFactory<>("dbJobID"));
        bookedLocation.setCellValueFactory(new PropertyValueFactory<>("dbLocation"));
        bookedWage.setCellValueFactory(new PropertyValueFactory<>("dbPay"));
        bookedDate.setCellValueFactory(new PropertyValueFactory<>("dbDate"));
        
        ObservableList<DBConnection> bookedjobs = FXCollections.observableArrayList();
        bookedjobs.addAll(connect.employeeTableJobs("Booked", "Incomplete"));
        System.out.println(bookedjobs);
        bookedJobs.setItems(bookedjobs);
        
        bookedJobs.setRowFactory(tv -> {
        TableRow<DBConnection> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                jobid = row.getItem().getDbJobID();
                jobDetailsScene(event);
                 
            }catch(IOException e)
            {
                e.getCause();
            }         
            //System.out.println(row.getItem().getDbEstate());
        }
    });
    return row ;
});
        
        completedJobID.setCellValueFactory(new PropertyValueFactory<>("dbJobID"));
        completedLocation.setCellValueFactory(new PropertyValueFactory<>("dbLocation"));
        completedWage.setCellValueFactory(new PropertyValueFactory<>("dbPay"));
        completedDate.setCellValueFactory(new PropertyValueFactory<>("dbDate"));
        
        ObservableList<DBConnection> completedjobs = FXCollections.observableArrayList();
        completedjobs.addAll(connect.employeeTableJobs("Booked", "Complete"));
        System.out.println(completedjobs);
        completedJobs.setItems(completedjobs);
        
        completedJobs.setRowFactory(tv -> {
        TableRow<DBConnection> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                jobid = row.getItem().getDbJobID();
                jobDetailsScene(event);
                 
            }catch(IOException e)
            {
                e.getCause();
            }         
            //System.out.println(row.getItem().getDbEstate());
        }
    });
    return row ;
});
        
    }  
        
     
    
    
    
//    public void setTextJob(String text) {
//        // set text from another class
//        textJob.setText(text);
//    } 
    
    public void loginScene(ActionEvent e) throws IOException
    {
       Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
       Scene loginViewScene = new Scene(loginViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(loginViewScene);
       window.show();
    }
    
    public void jobDetailsScene(MouseEvent e) throws IOException
    {
       Parent jobDetailsViewParent = FXMLLoader.load(getClass().getResource("EmployeeJobDetails.fxml"));
       Scene jobDetailsViewScene = new Scene(jobDetailsViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(jobDetailsViewScene);
       window.show();
    }
    
    public void editAccountDets()
    {
        if(account.getText().equals("Edit"))
        {
            uEmail.setEditable(true);
            uTel.setEditable(true);
            account.setText("Update");
            back.setVisible(true);
            back.setDisable(false);
        }else 
        {
            if(!"".equals(uEmail.getText()) && !"".equals(uTel.getText())){
                DBConnection connect = new DBConnection();
                connect.editAccountDetails(uEmail.getText(), uTel.getText());
                if(connect.getState() == true)
                {
                    MyAlerts alert = new MyAlerts("Details Updated Successfully");
                }else
                {
                    MyAlerts alert = new MyAlerts("Details Not Updated");
                }
            }else
            {
                MyAlerts alert = new MyAlerts("Ensure All Fields Are Filled");
            }
                    
        }
        
    }
    
    public void backBtn()
    {
        uEmail.setEditable(false);
        uTel.setEditable(false);
        account.setText("Edit");
        back.setVisible(false);
        back.setDisable(true);
    }
}
