package at.htlleonding.viergewinnt;

import at.htlleonding.viergewinnt.model.GameModel;
import at.htlleonding.viergewinnt.view.GamePresenter;
import at.htlleonding.viergewinnt.view.GameView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var model = new GameModel();
        var view = new GameView();
        var presenter = new GamePresenter(model,view);
        Scene scene = new Scene(view, 1000, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}