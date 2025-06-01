package at.htlleonding.viergewinnt.view;

import at.htlleonding.viergewinnt.controller.Repository;
import at.htlleonding.viergewinnt.model.CurrentPlayer;
import at.htlleonding.viergewinnt.model.GameModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.List;

public class GamePresenter {

    private final GameModel model;
    private final GameView view;
    Repository repository = new Repository();

    public GamePresenter(GameModel model, GameView view) {
        this.model = model;
        this.view = view;
        initializeButtons();
        updateCurrentPlayerLabel();
        attachEvents();
        showWinnerList();
    }


    private void attachEvents() {
        view.getResetButton().setOnAction(e -> {
            model.resetGame();
            updateCurrentPlayerLabel();
            resetButtons();
        });
        view.getDeleteButton().setOnAction(e -> {deleteGame();
        });
    }
    private void initializeButtons() {
        Button[][] buttons = view.getButtons();

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int r = row;
                final int c = col;

                buttons[r][c].setOnAction(e -> {
                    if (model.makeMove(r, c)) {
                        buttons[r][c].setText(String.valueOf(model.getBoard()[r][c]));
                        updateCurrentPlayerLabel();

                        CurrentPlayer winner = model.checkWinner();
                        if (winner != null) {
                            showWinner(winner);
                            repository.insert(winner);
                            resetButtons();
                        }
                    }
                });
            }
        }
    }

    private void showWinner(CurrentPlayer winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Spiel beendet");
        alert.setHeaderText(null);
        alert.setContentText("Spieler " + winner + " hat gewonnen!");
        alert.showAndWait();
    }

    private void updateCurrentPlayerLabel() {
        view.setCurrentPlayerLabel(model.getCurrentPlayer());
    }

    private void resetButtons() {
        for (Button[] row : view.getButtons()) {
            for (Button btn : row) {
                btn.setDisable(false);
                btn.setText(" ");
            }
        }
    }

    private List<String> showWinnerList() {
        List<String> winnerList = repository.getWinnerList();
        for (int i = 0; i < winnerList.size(); i++) {
            System.out.println(i+": "+winnerList.get(i));
        };
        return winnerList;
    }



    private void deleteGame() {
        String idToDelete = view.getGameId().getText();
        repository.deleteWinner(Integer.parseInt(idToDelete));

    }

}
