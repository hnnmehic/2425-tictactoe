package at.htlleonding.viergewinnt.view;

import at.htlleonding.viergewinnt.model.CurrentPlayer;
import at.htlleonding.viergewinnt.model.GameModel;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameView extends VBox {


    private final Button[][] buttons;

    private final TextField gameId = new TextField();
    private final Button deleteButton  =new Button("Lösche");


    private final Label currentPlayerLabel = new Label("Current Player : ");
    private final Label currentPlayer = new Label();

    private final Button resetButton = new Button("Reset");


    public GameView() {
        this.buttons = new Button[3][3];
        gameId.setPromptText("Game id to delete");

        VBox currentPlayerBox = new VBox(gameId,deleteButton,currentPlayerLabel,currentPlayer);
        currentPlayerBox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(currentPlayerBox,fillGridPaneWithButtons(),resetButton);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

    }

    public GridPane fillGridPaneWithButtons() {

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button(" ");
                buttons[i][j] = button;
                gridPane.add(button,i,j);
            }
        }
        return gridPane;
    }

    public Button[][] getButtons() {
        return buttons;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public void setCurrentPlayerLabel(CurrentPlayer player) {
        currentPlayer.setText(String.valueOf(player));
    }

    public TextField getGameId() {
        return gameId;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }
}
