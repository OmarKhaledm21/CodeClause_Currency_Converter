package Controllers;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class ConverterWindow extends Application {
    public static final String FXML_NAME = ConverterWindow.class.getSimpleName() + ".fxml";
    private FXMLLoader loader = null;

    @Override
    public void start(Stage stage) throws Exception {
        loader = WindowLoader.getLoader(FXML_NAME);

        Parent root = loader.load();

        root.getStylesheets().addAll(Context.getContext().getCurrentTheme());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void view(){
        launch();
    }
}
