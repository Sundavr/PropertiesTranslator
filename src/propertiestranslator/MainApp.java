package propertiestranslator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * MainApp de l'interface graphique du traducteur de fichiers properties.
 * @author Johan
 */
public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(event -> {
            exit();
        });
        stage.setTitle("Properties translator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
        
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("fxml/Home.fxml"));
        Scene homeScene = new Scene(homeLoader.load());
        HomeController homeController = ((HomeController)homeLoader.getController());
        homeController.initialize();
        
        stage.setScene(homeScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Stop l'application.
     */
    public void exit() {
        Platform.runLater(() -> {
            Platform.exit();
            System.exit(0); //s'assure de correctement stopper l'application
        });
    }
}
