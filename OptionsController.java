/**
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * This controller
 * is used for the 
 * how to play page.
 * 
 * 
 */

package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class OptionsController implements Initializable {

    // FXML
    @FXML private AnchorPane rootPane;
    
    
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    /**
     * This allows the user
     * to go back to the main menu.
     * @param event receives a parameter with an ActionEvent value.
     * @throws IOException throws an Exception.
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        
           
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Menu.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

   
}
