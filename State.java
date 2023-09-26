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
    public boolean is_terminal() {

        // Check if any pawn has reached the opposite side of the board
        int boardSize = this.board.get_board_size();
        for (int col = 0; col < boardSize; col++) {
            if (this.board.is_pawn_location(0, col) || this.board.is_pawn_location(boardSize - 1, col)) {
                return true;
            }
        }

        // Check if any player has no more legal moves left (tie)
        Player opponent = (current_player == Player.WHITE) ? Player.BLACK : Player.WHITE;
        if (this.get_applicable_moves().isEmpty() && get_applicable_moves_given_player(opponent).isEmpty()) {
            return true;
        }

        return false;
    }


    // Get all applicable moves for the current player in this state (if no moves are received it is a stalemate (tie))
    public List<Move> get_applicable_moves() {
        return board.get_applicable_moves_for_player(current_player); 
    }

    public List<Move> get_applicable_moves_given_player(Player player) {
        return board.get_applicable_moves_for_player(player); 
    }

    // Applies the given move and returns the new state that results from it
    public State apply_move(Move move){
        Board new_board = this.board.clone();
        new_board.apply_move(move);

        // Toggle player for the next turn
        Player next_player = (this.current_player == Player.WHITE) ? Player.BLACK : Player.WHITE;

        return new State(new_board, next_player);
    }

    public static void main(String[] args) {
        // Create an initial state with an empty board
        Board initialBoard = new Board(4);
        State initialState = new State(initialBoard, Player.WHITE);
    
        // Display the initial board
        System.out.println("Initial Board:");
        initialBoard.display_board();
    
        // Create a sample move (adjust this to your actual move)
        Move sampleMove = new Move(new Location(2, 0), new Location(1, 1));
    
        // Apply the move to the initial state
        State newState = initialState.apply_move(sampleMove);
    
        // Display the new state's board
        System.out.println("\nBoard after applying the move:");
        newState.get_board().display_board();
        
        // The board should now show the changes made by the move
    }
    

}

