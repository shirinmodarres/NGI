package UI.Screen.Board;

import Core.Manager.BoardManager;
import Core.Model.Board;

import java.util.Date;
import java.util.List;

public class BoardController {
    private BoardManager boardManager;

    public BoardController(BoardManager boardManager) {
        this.boardManager = boardManager;
    }

    public void addBoard(String name) {
        boardManager.addBoard(name);
    }

    public List<Board> getAllBoards() {
        return boardManager.getAllBoards();
    }

    public Board getBoardByTitle(String title) {
        return boardManager.getBoardByTitle(title);
    }

    public void editBoard(String title, String newName, Date newDate) {
        boardManager.editBoard(title, newName, newDate);
    }

    public void deleteBoard(String title) {
        boardManager.deleteBoard(title);
    }

    public BoardManager getBoardManager() {
        return boardManager;
    }
}
