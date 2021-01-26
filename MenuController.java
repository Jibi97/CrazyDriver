/**
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * This controller
 * is used for the 
 * main menu page.
 * 
 * 
 */
package Controller;

import Model.FileHandler;
import java.io.File;
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
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class MenuController implements Initializable {

    // FXML
    @FXML private AnchorPane rootPane;
    
    // Objects
    FileHandler saveGame = new FileHandler();
    

    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    
    /**
     * This method allows
     * the user start the game.
     * 
     * @param event receives a parameter with an ActionEvent value.
     * @throws IOException throws an Exception.
     */
    @FXML
    private void NewGame(ActionEvent event) throws IOException {
        
          
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Welcome.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }
    
    
    /**
     * This method allows the user
     * to see how to play
     * the game.
     * 
     * @param event receives a parameter with an ActionEvent value.
     * @throws IOException throws an Exception.
     */
    @FXML
    private void loadOptions(ActionEvent event) throws IOException {
        
         
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Options.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

    /**
     * This method allows
     * the user to load a file
     * with the score progress that 
     * has been saved.
     * 
     * @param event receives a parameter with an ActionEvent values.
     * @throws IOException throws an Exception.
     */
    @FXML
    private void load(ActionEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
                 
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extFilter);
                
                Stage stage = (Stage) rootPane.getScene().getWindow();
                //Show save file dialog
                File file = fileChooser.showOpenDialog(stage);
                if(file != null){
                    saveGame.loadGame(file);
        
        
    }
    }
    
}
