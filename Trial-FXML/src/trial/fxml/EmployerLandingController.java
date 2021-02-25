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

/**
 * FXML Controller class
 *
 * @author Chumba
 */
public class EmployerLandingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextField uID, uName, uEmail, uTel; 
    
    @FXML private Button back, account;
    
    static String locName;
    static String locEstate, state;
    static int locHse, locID, jobid;   
    
    @FXML private TableView<DBConnection> locTable, jobs, completedJobs;
    @FXML private TableColumn<DBConnection, String> location, estate, date, status, completedDate, completedStatus;
    @FXML private TableColumn<DBConnection, Integer> hseNo, id, jobID, completedJobID;
    @FXML private TableColumn<DBConnection, Double> pay, completedPay;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DBConnection connect = new DBConnection();
        connect.userDets();

        uID.setText(Integer.toString(connect.getIdentity()));
        uName.setText(connect.getName());
        uEmail.setText(connect.getEmail());
        uTel.setText(Integer.toString(connect.getTel())); 
        
        id.setCellValueFactory(new PropertyValueFactory<>("dbID"));
        location.setCellValueFactory(new PropertyValueFactory<>("dbLocation"));
        estate.setCellValueFactory(new PropertyValueFactory<>("dbEstate"));
        hseNo.setCellValueFactory(new PropertyValueFactory<>("dbHseNo"));
        
        ObservableList<DBConnection> locations = FXCollections.observableArrayList();
        locations.addAll(connect.tableLocations());
        System.out.println(locations);
        locTable.setItems(locations);
        
        locTable.setRowFactory(tv -> {
    TableRow<DBConnection> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                locName = row.getItem().getDbLocation();
                locEstate = row.getItem().getDbEstate();
                locHse = row.getItem().getDbHseNo();
                locID = row.getItem().getDbID();
                updateLocationsScene(event);
                 
            }catch(IOException e)
            {
                e.getCause();
            }         
            //System.out.println(row.getItem().getDbEstate());
        }
    });
    return row ;
});
        
        jobID.setCellValueFactory(new PropertyValueFactory<>("dbJobID"));
        date.setCellValueFactory(new PropertyValueFactory<>("dbDate"));
        status.setCellValueFactory(new PropertyValueFactory<>("dbStatus"));
        pay.setCellValueFactory(new PropertyValueFactory<>("dbPay"));
        
        ObservableList<DBConnection> jobslist = FXCollections.observableArrayList();
        jobslist.addAll(connect.tableJobs("Incomplete","JobAccepted"));
        System.out.println(jobslist);
        jobs.setItems(jobslist);
        
        jobs.setRowFactory(tv -> {
        TableRow<DBConnection> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                jobid = row.getItem().getDbJobID();
                state = row.getItem().getDbStatus();
                JobDetailsScene(event);
                 
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
        completedDate.setCellValueFactory(new PropertyValueFactory<>("dbDate"));
        completedStatus.setCellValueFactory(new PropertyValueFactory<>("dbStatus"));
        completedPay.setCellValueFactory(new PropertyValueFactory<>("dbPay"));
        
        ObservableList<DBConnection> completedjobslist = FXCollections.observableArrayList();
        completedjobslist.addAll(connect.tableJobs("Complete", "CompletionStatus"));
        System.out.println(completedjobslist);
        completedJobs.setItems(completedjobslist);
        
        completedJobs.setRowFactory(tv -> {
        TableRow<DBConnection> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
        if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {
            try{
                DBConnection clickedRow = row.getItem();
                jobid = row.getItem().getDbJobID();
                state = row.getItem().getDbStatus();
                remarksScene(event);
                 
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
    
    public void loginScene(ActionEvent e) throws IOException
    {
       Parent loginViewParent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
       Scene loginViewScene = new Scene(loginViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(loginViewScene);
       window.show();
    }
    
    public void newJobsScene(ActionEvent e) throws IOException
    {
       Parent newJobsViewParent = FXMLLoader.load(getClass().getResource("NewJobs.fxml"));
       Scene newJobsViewScene = new Scene(newJobsViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(newJobsViewScene);
       window.show();
    }
    
    public void newLocationsScene(ActionEvent e) throws IOException
    {
       Parent newLocationsViewParent = FXMLLoader.load(getClass().getResource("NewLocations.fxml"));
       Scene newLocationsViewScene = new Scene(newLocationsViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(newLocationsViewScene);
       window.show();
    }
    
     public void updateLocationsScene(MouseEvent e) throws IOException
    {
       Parent updateLocationsViewParent = FXMLLoader.load(getClass().getResource("LocationUpdate.fxml"));
       Scene updateLocationsViewScene = new Scene(updateLocationsViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(updateLocationsViewScene);
       window.show();
    }
     
    public void JobDetailsScene(MouseEvent e) throws IOException
    {
       Parent JobDetailsViewParent = FXMLLoader.load(getClass().getResource("EmployerJobDetails.fxml"));
       Scene JobDetailsViewScene = new Scene(JobDetailsViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(JobDetailsViewScene);
       window.show();
    }
    
    public void remarksScene(MouseEvent e) throws IOException
    {
       Parent remarksViewParent = FXMLLoader.load(getClass().getResource("AddRemarks.fxml"));
       Scene remarksViewScene = new Scene(remarksViewParent);
       
       //Getting stage info.
       Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
       
       window.setScene(remarksViewScene);
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
                connect.editEmployerAccountDetails(uEmail.getText(), uTel.getText());
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
