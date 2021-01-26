/**
 * @author s315272  
 * @author s305519
 * @author s305510
 * 
 * This class gives the
 * ability to save and load files.
 * 
 */
package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class FileHandler {
    
    public InputStream file = GameBoard.class.getResourceAsStream("HiScore.txt");

    static Enemy enemy = new Enemy();
    
    private static String firstName;
    private static String secondName;
    private static String thirdName;
    private static int firstScore;
    private static int secondScore;
    private static int thirdScore;
    
    private static int lowestScore;
     
     
    /**
     * This method saves the 
     * score progress made in 
     * the game.
     * If user decides plays the game,
     * and end the game, it will
     * save the score progress in a 
     * file.
     * @param a receives a parameter of an Integer value.
     */
    public void saveHiScore(int a) {
        try {
            
            URL path =  FileHandler.class.getResource("HiScore.txt");
            File f = new File(path.toURI());

            
            String str = "Me";
            BufferedWriter writer = new BufferedWriter(new FileWriter(f,true));
            writer.append("\n");
            writer.append(str+","+a);
            
            writer.close();
            
        }
        catch (IOException ex) {
            //Logger.getLogger(JavaFX_Text.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println (ex.toString());
      
        }
          catch (URISyntaxException ex) {
            System.out.println (ex.toString());
        }
    
    }
    
    /**
     * This method is used
     * for the score board
     * on the pane in
     * the game.
     * It reads from a file
     * called Hiscore.txt
     * It has the score progress
     * for the highscore.
     * 
     * @param rootPane receives a parameter with an AnchorPane value.
     * @throws FileNotFoundException throws an Exception
     * @throws IOException throws an Exception
     */
    public void hiScore(AnchorPane rootPane) throws FileNotFoundException, IOException{
        
        BufferedReader bufferedReader = null;
        bufferedReader = new BufferedReader(new InputStreamReader(file));
        
        int i =0;
        String line;
        Map<String,Integer> hs = new HashMap<String,Integer>();
        
        while ((line = bufferedReader.readLine()) != null){
            i++;
            
            String[] values = line.split(",");
            String name = values[0];
            int score = Integer.parseInt(values[1]);

            
            hs.put(name, score);

        }
            
        List<Integer> l = new ArrayList<Integer>(hs.values());
        Collections.sort(l,Collections.reverseOrder()); 
        l = l.subList(0, 3);


            for (Map.Entry<String, Integer> entry : hs.entrySet()) {
                if(entry.getValue() == l.get(0)){
                    firstName = entry.getKey();
                    firstScore = entry.getValue();
                }
                if(entry.getValue() == l.get(1)){
                    secondName = entry.getKey();
                    secondScore = entry.getValue();
                }
                
                if(entry.getValue() == l.get(2)){
                    thirdName = entry.getKey();
                    thirdScore = entry.getValue();
                    setLowestScore(entry.getValue());
                }
            

        }
        
        Text HiScore = new Text();
        HiScore.setFill(Color.RED);
        HiScore.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        HiScore.setText("Hiscore");
        HiScore.setLayoutX(700);
        HiScore.setLayoutY(300);
        rootPane.getChildren().addAll(HiScore);
        
        Text first = new Text();
        first.setFill(Color.WHITE);
        first.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        first.setText("1. " + firstName + " - " + firstScore);
        first.setLayoutX(700);
        first.setLayoutY(325);
        rootPane.getChildren().addAll(first);
        
         Text second = new Text();
        second.setFill(Color.WHITE);
        second.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        second.setText("2. " + secondName + " - " + secondScore);
        second.setLayoutX(700);
        second.setLayoutY(350);
        rootPane.getChildren().addAll(second);
        
         Text third = new Text();
        third.setFill(Color.WHITE);
        third.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));
        third.setText("3. " + thirdName + " - " + thirdScore);
        third.setLayoutX(700);
        third.setLayoutY(375);
        rootPane.getChildren().addAll(third);

    }
    
    /**
     * This method is called
     * ¨when the user decides to save the game.
     * It saves the score progress in txt file.
     * @param values receives a parameter of a table of Integer values.
     * @param file receives a parameter of File value
     */
    public void SaveFile(int[] values, File file){
        try {
            FileWriter fileWriter = null;
            fileWriter = new FileWriter(file);
           
            for(int i =0; i< values.length;i++){
                fileWriter.write(values[i] + "\r\n");
            }          
            
            fileWriter.close();
        } catch (IOException ex) {
            System.err.println("SaveFile didnt work, try again: " + ex);
        }
         
    }
    
    /**
     * This method is called
     * when the user is going to 
     * load a file.
     * It loads a file with a score progress
     * that has been saved.
     * @param file receives a parameter with File value.
     * @throws IOException throws an Exception.
     */
    public void loadGame(File file) throws IOException{
        
        BufferedReader bufferedReader = null;
        int score = 0;
        int level = 0;
        int wave = 0;
        int speed = 18;
        int felt = 0;
        String line;
        int i = 0; 
        
 
        bufferedReader = new BufferedReader(new FileReader(file));

        while ((line = bufferedReader.readLine()) != null && line.matches("^[0-9]*$")) {
            i++;
            if(i == 1){
                score = Integer.parseInt(line);
            }
            if(i == 2){
                level = Integer.parseInt(line);
            }
            if(i == 3){
                wave = Integer.parseInt(line);
            }
            if(i == 4){
                speed = Integer.parseInt(line);
            }
            if (i ==5){
                felt = Integer.parseInt(line);
            } 
            
        }
         if (i != 5){
             Alert alert = new Alert(AlertType.ERROR, "The file is corrupt, please try another file", ButtonType.OK);
             alert.showAndWait();
         }
         else{
             enemy.updateScore(score,level,wave, speed,felt);   
         }
         
        bufferedReader.close();
               
    }
    
    
    /**
     * Sets the value of the lowest score.
     * 
     * @param a receives a parameter with an Integer value.
     */
    public void setLowestScore(int a){
        lowestScore = a;
    }
    
    /**
     * Gets the value of the lowest score.
     * 
     * @return returns the value of the lowest score. 
     */
    public int getLowestScore(){
        return lowestScore;
    }

}
