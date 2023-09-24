import java.util.ArrayList;
import java.util.List;

import javax.swing.undo.StateEdit;
public class State {
    
    private Board board; // Current board state
    private Player current_player; // The player

    public State(Board board, Player current_player){
        this.board = board;
        this.current_player = current_player;
    }

    // Getters for the board and player vars
    public Board get_board(){
        return this.board;
    }

    public Player get_current_player(){
        return this.current_player;
    }

    // Setters for the board and player vars
    public void set_board(Board board){
        this.board = board;
    }

    public void set_current_player(Player player){
        this.current_player = player;
    }

    // placeholder
    // Determines whether the state is a terminal state (end of the game)
    public boolean is_terminal(){
        return false;
    }

    // Get all applicable moves for the current player in this state (if no moves are received it is a stalemate (tie))
    public List<Move> get_applicable_moves() {
        return board.get_applicable_moves_for_player(current_player); 
    }

    // Applies the given move and returns the new state that results from it
    public State apply_move(Move move){
        Board new_board = board.clone();
        new_board.
    }

    public static void main(String[] args){
        Board new_board = new Board(8);
        State state = new State(new_board, Player.WHITE);

        List<Move> moves = state.get_applicable_moves();

    }
}

