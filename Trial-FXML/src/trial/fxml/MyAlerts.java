/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trial.fxml;

import javafx.scene.control.Alert;

/**
 *
 * @author Chumba
 */
public class MyAlerts {
    
    public MyAlerts(String alertMessage)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Dialog");
        alert.setHeaderText("Warning Message");
        alert.setContentText(alertMessage);
        alert.getDialogPane().setMaxWidth(280);
        alert.getDialogPane();
        alert.setX(538);
        alert.setY(290);
        alert.showAndWait();
    }
    
}
