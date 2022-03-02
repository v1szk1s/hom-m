import javafx.application.Application;  
import javafx.scene.Group;  
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;  
import javafx.scene.shape.Rectangle;  
import javafx.stage.Stage;  
public class main extends Application{  

    public void start(Stage primaryStage) throws Exception {  
    primaryStage.setTitle("Heroes of Might & Magic");  

    VBox menu = new VBox();


    Scene scene = new Scene(menu,200,300);  
    primaryStage.setScene(scene);  
    primaryStage.show();  
}  
public static void main(String[] args) {  
    launch(args);  
}  
  
}  