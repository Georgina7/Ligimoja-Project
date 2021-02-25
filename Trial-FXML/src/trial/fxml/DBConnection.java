/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import java.sql.*;
import static java.sql.JDBCType.NULL;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author Chumba
 */
public class DBConnection{
    
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    Boolean state = false;

    int identity, tel, house;
    String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }
    String name, email, date, status, location, estate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
    
    double pay;
    
    private SimpleIntegerProperty dbID;
    private SimpleStringProperty dbLocation;
    private SimpleStringProperty dbEstate;
    private SimpleIntegerProperty dbHseNo;
    
    private SimpleIntegerProperty dbJobID;
    private SimpleStringProperty dbDate, dbStatus;
    private SimpleDoubleProperty dbPay;
    
       
    public DBConnection()
    {
        //EmptyConstructor
    }
    
    public DBConnection(int dbID, String dbLocation, String dbEstate, int dbHseNo){
        this.dbID = new SimpleIntegerProperty(dbID);
        this.dbLocation = new SimpleStringProperty(dbLocation);
        this.dbEstate = new SimpleStringProperty(dbEstate);
        this.dbHseNo = new SimpleIntegerProperty(dbHseNo);
    }
    
    public DBConnection(int dbJobID, String dbDate, Double dbPay, String dbLocation)
    {
        this.dbJobID = new SimpleIntegerProperty(dbJobID);
        this.dbDate = new SimpleStringProperty(dbDate);
        this.dbPay = new SimpleDoubleProperty(dbPay);
        this.dbLocation = new SimpleStringProperty(dbLocation);
    }
    
    public DBConnection(int dbJobID, String dbDate, String dbStatus, Double dbPay)
    {
        this.dbJobID = new SimpleIntegerProperty(dbJobID);
        this.dbDate = new SimpleStringProperty(dbDate);
        this.dbStatus = new SimpleStringProperty(dbStatus);
        this.dbPay = new SimpleDoubleProperty(dbPay);
    }
    
    public String getDbDate() {
        return dbDate.get();
    }

    public void setDbDate(String dbDate) {
        this.dbDate = new SimpleStringProperty(dbDate);
    }
    
    public String getDbStatus() {
        return dbStatus.get();
    }

    public void setDbStatus(String dbStatus) {
        this.dbStatus = new SimpleStringProperty(dbStatus);
    }
    
    public Integer getDbJobID() {
        return dbJobID.get();
    }

    public void setDbJobID(Integer dbJobID) {
        this.dbJobID = new SimpleIntegerProperty(dbJobID);
    }
    
    public Double getDbPay() {
        return dbPay.get();
    }

    public void setDbJobID(Double dbPay) {
        this.dbPay = new SimpleDoubleProperty(dbPay);
    }

    public String getDbLocation() {
        return dbLocation.get();
    }

    public void setDbLocation(String dbLocation) {
        this.dbLocation = new SimpleStringProperty(dbLocation);
    }

    public String getDbEstate() {
        return dbEstate.get();
    }

    public void setDbEstate(String dbEstate) {
        this.dbEstate = new SimpleStringProperty(dbEstate);
    }

    public Integer getDbHseNo() {
        return dbHseNo.get();
    }

    public void setDbHseNo(Integer dbHseNo) {
        this.dbHseNo = new SimpleIntegerProperty(dbHseNo);
    }
    
    public Integer getDbID() {
        return dbID.get();
    }

    public void setDbID(Integer dbID) {
        this.dbID = new SimpleIntegerProperty(dbID);
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
    
     public void DoConnect()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            String host = "jdbc:mysql://localhost/ligimoja"; //Database URL
            String uName = "root";
            String uPass = "Kihuga@007";
            
            conn = DriverManager.getConnection(host,uName,uPass); 
            
            //System.out.println("Connection thread running...");
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.print(e.getMessage());
        }
    }
    
    public void Login(int id, String pass)
    {
        DoConnect();
        try
        {        
        if(FXMLDocumentController.userType.equals("employer"))
        {
            ps = conn.prepareStatement("Select EID, Password Pin from employer where EID = ? and Password = ?");
        } else if(FXMLDocumentController.userType.equals("employee"))
        {
            ps = conn.prepareStatement("Select EmpID, Password Pin from employee where EmpID = ? and Password = ?");
        }
        ps.setInt(1, id);
        ps.setString(2, pass);
        rs = ps.executeQuery();
        if(rs.next())
         {
            this.setState(true);
                     
         }  
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
    }
    
    public void userDets(){
        try{
            DoConnect();
            if(FXMLDocumentController.userType.equals("employer"))
            {
                ps = conn.prepareStatement("Select EID, EName, ETel, EMail from employer where EID = ?");
                ps.setInt(1, LoginViewController.ID);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    this.setIdentity(rs.getInt("EID"));
                    this.setName(rs.getString("EName"));
                    this.setEmail(rs.getString("EMail"));
                    this.setTel(rs.getInt("ETel"));
                }
                
            } else if(FXMLDocumentController.userType.equals("employee"))
            {
                ps = conn.prepareStatement("Select EmpID, EmpName, EmpTel, EmpMail from employee where EmpID = ?");
                ps.setInt(1, LoginViewController.ID);
                rs = ps.executeQuery();
                if(rs.next())
                {
                    this.setIdentity(rs.getInt("EmpID"));
                    this.setName(rs.getString("EmpName"));
                    this.setEmail(rs.getString("EmpMail"));
                    this.setTel(rs.getInt("EmpTel"));
                }
            }           
            
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public String insertLocation(String location, String estate, int house)
    {
        String message = "";
        try{
            DoConnect();
            ps = conn.prepareStatement("SELECT LocID FROM location ORDER BY LocID DESC LIMIT 1");
            rs = ps.executeQuery();
            if(rs.next())
            {
                ps = conn.prepareStatement("Select LocName, LocEstateName, LocHseNo, EmployerID from location where LocID = ?");
                ps.setInt(1, rs.getInt("LocID"));
                rs = ps.executeQuery();
                if(rs.next())
                {
                   if(rs.getString("LocName").equals(location) && rs.getString("LocEstateName").equals(estate) && rs.getInt("LocHseNo") == (house) && rs.getInt("EmployerID") == LoginViewController.ID)
                   {
                       message = "Location Already Added";
                   }
                   else
                   {
                        ps = conn.prepareStatement("Insert into location(LocName, LocEstateName, LocHseNo, EmployerID) values(?,?,?,?)");
                        ps.setString(1, location);
                        ps.setString(2, estate);
                        ps.setInt(3, house);
                        ps.setInt(4, LoginViewController.ID);
                        ps.executeUpdate();
                        message = "Location Added Successfully";
                   }
                }
            }
            
        }catch(SQLException e)
        {
            e.getCause();
            //System.out.println(e.getCause());
        }
        
        return message;
    }
    
    public ArrayList getCategories(){
        ArrayList<String> categories = new ArrayList<String>();
        try{
            DoConnect();
            //System.out.println("No list");
            ps = conn.prepareStatement("Select CatID, CatName, Wage from category");
            rs = ps.executeQuery();
            while(rs.next())
            {
                String val =rs.getInt("CatID") + "->" + rs.getString("CatName") + " - " + rs.getDouble("Wage");
                categories.add(val);
            } 
            
            
            
        }catch(SQLException e)
        {
            e.getCause();
            System.out.println(e.getMessage());
        }        
        return categories;        
    }    
    
     public ArrayList getLocations(){
        ArrayList<String> locations = new ArrayList<String>();
        try{
            DoConnect();
            
            ps = conn.prepareStatement("Select LocID, LocName, LocEstateName, LocHseNo from location where EmployerID = ?");
            ps.setInt(1, LoginViewController.ID);
            rs = ps.executeQuery();
            
            while(rs.next())
            {                
                String val =rs.getInt("LocID") + "->" + rs.getString("LocName") + " - " + rs.getString("LocEstateName") + ", Hse No." + rs.getInt("LocHseNo");
                locations.add(val);
            } 
            
            
        }catch(SQLException e)
        {
            e.getCause();
            System.out.println(e.getMessage());
        }        
        return locations;        
    }
     
    public String insertJob(String category, String location, LocalDate date)
    {
        String message = "";
        String [] arrCats = category.split("->", 0);
               
        String [] arrLocs = location.split("->", 0);        
        
        //System.out.println(java.time.LocalDate.now()); 
        
            try{
            DoConnect();
            ps = conn.prepareStatement("SELECT JobID FROM job ORDER BY JobID DESC LIMIT 1");
            rs = ps.executeQuery();
            if(rs.next())
            {
                ps = conn.prepareStatement("Select JobCat, EmployerID, LocationID, JobDate from job where JobID = ?");
                ps.setInt(1, rs.getInt("JobID"));
                rs = ps.executeQuery();
                if(rs.next())
                {
                    if(rs.getInt("JobCat") == Integer.parseInt(arrCats[0]) && rs.getInt("EmployerID") == LoginViewController.ID && rs.getInt("LocationID") == Integer.parseInt(arrLocs[0]) && rs.getString("JobDate").equals(date.toString()))
                    {
                        message = "Job Already Added";
                    }
                    else
                    {
                        ps = conn.prepareStatement("Insert into job(JobCat, EmployerID, LocationID, JobDate) values (?,?,?,?)");
                        ps.setInt(1, Integer.parseInt(arrCats[0]));
                        ps.setInt(2, LoginViewController.ID);
                        ps.setInt(3, Integer.parseInt(arrLocs[0]));
                        ps.setString(4, date.toString());
                        //System.out.println(java.sql.Date.valueOf(date) + " " + Integer.parseInt(arrCats[0]) + " " + LoginViewController.ID + " " + Integer.parseInt(arrLocs[0]));
                        ps.executeUpdate();
                        message = "Job Added Successfully";
                    }
                }
            }
            
            
            }catch(SQLException e)
            {
                System.out.println(e.getCause());
            }
        
        return message;
    }
    
    public ArrayList tableLocations(){
       ArrayList<DBConnection> locations = new ArrayList<DBConnection>(); 
       //ObservableList<DBConnection> locations = FXCollections.observableArrayList();
        try{
            DoConnect();
            ps = conn.prepareStatement("Select LocID, LocName, LocEstateName, LocHseNo from location where EmployerID = ?");
            ps.setInt(1, LoginViewController.ID);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //System.out.println(rs.getString("LocEstateName"));
                locations.add(new DBConnection(rs.getInt("LocID"), rs.getString("LocName"), rs.getString("LocEstateName"), rs.getInt("LocHseNo")));
            }
                       
            
        }catch(SQLException e){
            e.getCause();
        }        
      return locations; 
}
    
    public String editLocation(String loc, String est, String hse)
    {
        String message = "";
        try{
            DoConnect();
            ps = conn.prepareStatement("Select LocationID from job where CompletionStatus = ? and LocationID = ?");
            ps.setString(1, "Complete");
            ps.setInt(2, EmployerLandingController.locID);
            rs = ps.executeQuery();
            if(rs.next())
            {
                message = "You Are Not Allowed To Update This Location";
                
            }else
            {
                ps = conn.prepareStatement("Update location set LocName = ?, LocEstateName = ?, LocHseNo = ? where LocID = ?");
                ps.setString(1, loc);
                ps.setString(2, est);
                try{
                    ps.setInt(3, Integer.parseInt(hse));
                }catch(Exception ex)
                {
                    message = "Ensure House Number Is A Numerical Value";
                }                
                ps.setInt(4, EmployerLandingController.locID);
                ps.executeUpdate();
                message = "Location Details Updated Successfully";
            }
            
        }catch(SQLException e)
        {
            e.getCause();
        }
        
        return message;
    }
    
    public ArrayList tableJobs(String state, String col){
       ArrayList<DBConnection> jobs = new ArrayList<DBConnection>(); 
       //ObservableList<DBConnection> locations = FXCollections.observableArrayList();
        try{
            DoConnect();
            ps = conn.prepareStatement("Select job.JobID, job.JobDate, job.JobAccepted, job.CompletionStatus, category.Wage from job join category on job.JobCat = category.CatID and job.EmployerID = ? and job.CompletionStatus = ?");
            ps.setInt(1, LoginViewController.ID);
            ps.setString(2, state);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //System.out.println(rs.getString("LocEstateName"));
                jobs.add(new DBConnection(rs.getInt("JobID"), rs.getString("JobDate"), rs.getString(col), rs.getDouble("Wage")));
            }
                       
            
        }catch(SQLException e){
            e.getCause();
        }        
      return jobs; 
}
    public void delJob()
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Delete from job where JobID = ?");
            ps.setInt(1, EmployerLandingController.jobid);
            ps.executeUpdate();
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
        
    public void bookedJobDets(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Select employee.EmpName, employee.EmpTel, location.LocName, location.LocEstateName, location.LocHseNo, category.Wage, job.JobDate from job join employee on job.EmployeeID = employee.EmpID join category on job.JobCat = category.CatID join location on job.LocationID = location.LocID and job.jobID = ?");
            ps.setInt(1, EmployerLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                this.setName(rs.getString("EmpName"));
                this.setTel(rs.getInt("EmpTel"));
                this.setPay(rs.getDouble("Wage"));
                this.setDate(rs.getString("JobDate"));
                this.setLocation(rs.getString("LocName"));
                this.setEstate(rs.getString("LocEstateName"));
                this.setHouse(rs.getInt("LocHseNo"));
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public void notBookedJobDets(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Select location.LocName, location.LocEstateName, location.LocHseNo, category.Wage, job.JobDate from job join category on job.JobCat = category.CatID join location on job.LocationID = location.LocID and job.jobID = ?");
            ps.setInt(1, EmployerLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                this.setPay(rs.getDouble("Wage"));
                this.setDate(rs.getString("JobDate"));
                this.setLocation(rs.getString("LocName"));
                this.setEstate(rs.getString("LocEstateName"));
                this.setHouse(rs.getInt("LocHseNo"));
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public ArrayList employeeTableJobs(String act, String status){
       ArrayList<DBConnection> jobs = new ArrayList<DBConnection>(); 
       //ObservableList<DBConnection> locations = FXCollections.observableArrayList();
        try{
            DoConnect();
            ps = conn.prepareStatement("Select job.JobID, job.JobDate, job.JobAccepted, category.Wage, location.LocName from job join category on job.JobCat = category.CatID join location on job.LocationID = location.LocID and job.JobAccepted = ? and CompletionStatus = ?");
            ps.setString(1, act);
            ps.setString(2, status);
            rs = ps.executeQuery();            
            while(rs.next())
            {
                //System.out.println(rs.getString("LocEstateName"));
                jobs.add(new DBConnection(rs.getInt("JobID"), rs.getString("JobDate"), rs.getDouble("Wage"), rs.getString("LocName")));
            }
                       
            
        }catch(SQLException e){
            e.getCause();
        }        
      return jobs; 
}
    
    public void employeeJobDets(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Select employer.EName, employer.ETel, location.LocName, location.LocEstateName, location.LocHseNo, category.Wage, job.JobDate from job join employer on job.EmployerID = employer.EID join category on job.JobCat = category.CatID join location on job.LocationID = location.LocID and job.jobID = ?");
            ps.setInt(1, EmployeeLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                this.setName(rs.getString("EName"));
                this.setTel(rs.getInt("ETel"));
                this.setPay(rs.getDouble("Wage"));
                this.setDate(rs.getString("JobDate"));
                this.setLocation(rs.getString("LocName"));
                this.setEstate(rs.getString("LocEstateName"));
                this.setHouse(rs.getInt("LocHseNo"));
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public void empBooking(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Update job set JobAccepted = ?, EmployeeID = ? where JobID = ?");
            ps.setString(1, "Booked");
            ps.setInt(2, LoginViewController.ID);
            ps.setInt(3, EmployeeLandingController.jobid);
            ps.executeUpdate(); 
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
        
    }
    
    public String empTypeAction()
    {
        String act = "";
        try{            
            DoConnect();
            ps = conn.prepareStatement("Select JobAccepted from job where JobID = ?");
            ps.setInt(1, EmployeeLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                act = rs.getString("JobAccepted");
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
        
        return act;
    }
    
    public String completeAction()
    {
         String act = "";
        try{            
            DoConnect();
            ps = conn.prepareStatement("Select CompletionStatus from job where JobID = ?");
            ps.setInt(1, EmployeeLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                act = rs.getString("CompletionStatus");
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
        
        return act;
    }
            
    public void empCancelBooking(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Update job set JobAccepted = ?, EmployeeID = NULL where JobID = ?");
            ps.setString(1, "Not Booked");
            ps.setInt(2, EmployeeLandingController.jobid);
            ps.executeUpdate(); 
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
        
    }
    
    public void empCompleteJob(){
        try{
            DoConnect();
            ps = conn.prepareStatement("Update job set CompletionStatus = ? where JobID = ?");
            ps.setString(1, "Complete");
            ps.setInt(2, EmployeeLandingController.jobid);
            ps.executeUpdate();
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
        
    }
    
    public void editAccountDetails(String mail, String contact)
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Update employee set EmpMail = ?, EmpTel = ? where EmpID = ? ");
            ps.setString(1, mail);
            try{
               ps.setInt(2, Integer.parseInt(contact)); 
            }catch(Exception ex)
            {
                this.setState(false);
            }            
            ps.setInt(3, LoginViewController.ID);
            ps.executeUpdate();
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public void editEmployerAccountDetails(String mail, String contact)
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Update employer set EMail = ?, ETel = ? where EID = ? ");
            ps.setString(1, mail);
            try{
               ps.setInt(2, Integer.parseInt(contact)); 
            }catch(Exception ex)
            {
                this.setState(false);
            }        
            ps.setInt(3, LoginViewController.ID);
            ps.executeUpdate();
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public void addRemark(String remark)
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Update job set Remarks = ? where JobID = ?");
            ps.setString(1, remark);
            ps.setInt(2, EmployerLandingController.jobid);
            ps.executeUpdate(); 
            this.setState(true);
        }catch(SQLException e)
        {
            e.getCause();
        }
    }
    
    public void getRemarkforEmployer()
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Select Remarks from job where JobID = ?");
            ps.setInt(1, EmployerLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                if(!rs.getString("Remarks").equals("None"))
                {
                    this.setState(true);
                    this.setRemarks(rs.getString("Remarks"));                    
                }
                
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
        
    }
    
    public void getRemarkforEmployee()
    {
        try{
            DoConnect();
            ps = conn.prepareStatement("Select Remarks from job where JobID = ?");
            ps.setInt(1, EmployeeLandingController.jobid);
            rs = ps.executeQuery();
            if(rs.next())
            {
                this.setState(true);
                this.setRemarks(rs.getString("Remarks"));
            }
        }catch(SQLException e)
        {
            e.getCause();
        }
        
    }
}
