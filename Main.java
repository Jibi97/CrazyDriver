package Model;




import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    
    public Scene scene;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/View/Start.fxml"));
        root.setId("pane");
        
        scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/View/Style.css").toExternalForm());
       
        
        stage.setScene(scene);
        stage.show();
        
        
    }
    
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
