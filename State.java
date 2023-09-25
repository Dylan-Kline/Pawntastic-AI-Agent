import java.util.ArrayList;
import java.util.List;

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

    // Determines whether the state is a terminal state (end of the game)
    public boolean is_terminal(){

        // Check if any player has no more legal moves left (tie)
        if (this.get_applicable_moves().isEmpty()) {
            return true;
        }

        // Check if any pawn has reached the opposite side of the board
        for (int col = 0; col < board.get_board_size(); col++){

            if (this.board.is_pawn_location(0, col) || this.board.is_pawn_location(board.get_board_size() - 2, col)) {
                return true;
            }
        }

        return false;
    }

    // Get all applicable moves for the current player in this state (if no moves are received it is a stalemate (tie))
    public List<Move> get_applicable_moves() {
        return board.get_applicable_moves_for_player(current_player); 
    }

    // Applies the given move and returns the new state that results from it
    public State apply_move(Move move){
        Board new_board = board.clone();
        new_board.apply_move(move);

        // Toggle player for the next turn
        Player next_player = (current_player == Player.WHITE) ? Player.BLACK : Player.WHITE;

        return new State(new_board, next_player);
    }

    public static void main(String[] args){
        Board new_board = new Board(8);
        State state = new State(new_board, Player.WHITE);

        List<Move> moves = state.get_applicable_moves();

    }
}

