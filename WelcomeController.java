/**
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * 
 * This Controller
 * allows the user
 * to choose which image
 * to use for the avatar and
 * the gameboard.
 * 
 */
package Controller;

import Model.GameBoard;
import Model.*;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class WelcomeController implements Initializable {
    
    
    // FXML
    @FXML private AnchorPane rootPane;
    @FXML private Button viper;
    @FXML private Button audi;
    @FXML private Button orange;
    @FXML private Button police;
    @FXML private Button ambulance;
    @FXML private Button mini_van;
    @FXML private Button track1;
    @FXML private Button track2;
    @FXML private Button start;
    
    
    // Objects
    Avatar avatar = new Avatar();
    GameBoard board = new GameBoard();
    ImageView view1 = new ImageView(new Image("/View/Black_viper.png"));
    ImageView view2 = new ImageView(new Image("/View/Audi.png"));
    ImageView view3 = new ImageView(new Image("/View/Car.png"));
    ImageView view4 = new ImageView(new Image("/View/Ambulance.png"));
    ImageView view5 = new ImageView(new Image("/View/Police.png"));
    ImageView view6 = new ImageView(new Image("/View/Mini_van.png"));
    ImageView bane1 = new ImageView(new Image("/View/Bane.png"));
    ImageView bane2 = new ImageView(new Image("/View/2.jpg"));
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(avatar.getAvatar().getImage() == null){
                avatar.setAvatar(view1);
            }
            
            if(board.getBoard().getImage() == null){
                board.setBoard(bane1);
            }
        
        viper.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view1);
                changeButtonColor(viper);
        });
        audi.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view2);
                changeButtonColor(audi);
        });
        orange.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view3);
                changeButtonColor(orange);
        });
        ambulance.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view4);
                changeButtonColor(ambulance);
        });
        police.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view5);
                changeButtonColor(police);
        });
        mini_van.setOnAction((ActionEvent event) -> {
                avatar.setAvatar(view6);
               changeButtonColor(mini_van);
        });
        track1.setOnAction((ActionEvent event) -> {
                board.setBoard(bane1);
                changeButtonColor(track1);
        });
        track2.setOnAction((ActionEvent event) -> {
                board.setBoard(bane2);
                changeButtonColor(track2);
        });
        
        start.setOnAction((ActionEvent event) -> {
               
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/Spill.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(WelcomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Scene scene = new Scene(root);
            
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
        
    }    
    
    /**
     * This method changes the 
     * color for the button when 
     * the user click on it.
     * @param button receives a parameter with a Button value.
     */
    public void changeButtonColor(Button button){
        
        if(button == viper){
            button.getStyleClass().add("button_effect");
            audi.getStyleClass().remove("button_effect");
            orange.getStyleClass().remove("button_effect");
            ambulance.getStyleClass().remove("button_effect");
            police.getStyleClass().remove("button_effect");
            mini_van.getStyleClass().remove("button_effect");
        }
        
        if(button == audi){
            button.getStyleClass().add("button_effect");
            viper.getStyleClass().remove("button_effect");
            orange.getStyleClass().remove("button_effect");
            ambulance.getStyleClass().remove("button_effect");
            police.getStyleClass().remove("button_effect");
            mini_van.getStyleClass().remove("button_effect");
        }
        
        if(button == orange){
            button.getStyleClass().add("button_effect");
            audi.getStyleClass().remove("button_effect");
            viper.getStyleClass().remove("button_effect");
            ambulance.getStyleClass().remove("button_effect");
            police.getStyleClass().remove("button_effect");
            mini_van.getStyleClass().remove("button_effect");
        }
        
        if(button == ambulance){
            button.getStyleClass().add("button_effect");
            audi.getStyleClass().remove("button_effect");
            orange.getStyleClass().remove("button_effect");
            viper.getStyleClass().remove("button_effect");
            police.getStyleClass().remove("button_effect");
            mini_van.getStyleClass().remove("button_effect");
        }
        
        if(button == police){
            button.getStyleClass().add("button_effect");
            audi.getStyleClass().remove("button_effect");
            orange.getStyleClass().remove("button_effect");
            ambulance.getStyleClass().remove("button_effect");
            viper.getStyleClass().remove("button_effect");
            mini_van.getStyleClass().remove("button_effect");
        }
        
        if(button == mini_van){
            button.getStyleClass().add("button_effect");
            audi.getStyleClass().remove("button_effect");
            orange.getStyleClass().remove("button_effect");
            ambulance.getStyleClass().remove("button_effect");
            police.getStyleClass().remove("button_effect");
            viper.getStyleClass().remove("button_effect");
        }
        
        if(button == track1){
            button.getStyleClass().add("button_effect");
            track2.getStyleClass().remove("button_effect");
        }
        
        if(button == track2){
            button.getStyleClass().add("button_effect");
            track1.getStyleClass().remove("button_effect");
        }
    }
}
