import java.util.List;
import java.util.Random;

public class Agent {
    
    private Player player; // Represents which side the agent plays as White or Black
    private int max_depth; // the depth to which the minimax algorithm will search
    
    public Agent(Player player, int max_depth){
        this.player = player;
        this.max_depth = max_depth;
    }

    /**
     * Gets the best move for the agent's player from the current board state.
     */
    public Move get_best_move(State current_state){
        int best_value = (current_state.get_current_player() == Player.WHITE ? Integer.MIN_VALUE : Integer.MAX_VALUE);
        Move best_move = null;

        for (Move move : current_state.get_board().get_applicable_moves_for_player(player)) {
            State next_state = current_state.apply_move(move);
            int move_value;

            if (player == Player.WHITE){
                move_value = minimax(next_state, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                
                if (move_value > best_value) {
                    best_value = move_value;
                    best_move = move;
                }
            }
            else {
                move_value = minimax(next_state, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                
                if (move_value > best_value) {
                    best_value = move_value;
                    best_move = move;
                }
            }
        }

        return best_move;
    }

    /**
     * Implementation of the minimax algorithm
     */
    private int minimax(State state, int alpha, int beta, boolean is_maximizing){
        
        if (state.is_terminal()) {
            return utility(state);
        }
    }

    /**
     * Implementation of the H-minimax algorithm with alpha/beta pruning
     */
    private int h_minimax_ab(State state){
        return 0;
    }

    /**
     * Implementation of the utility function 
     */
    private int utility(State state) {
        Board board = state.get_board();
        int board_size = board.get_board_size();

        for (int row = 0; row < board_size; row++) {
            for (int col = 0; col < board_size; col++) {

                char pawn = board.get_pawn(row, col);
                if (pawn == '-') {
                    continue;
                }

                if (pawn == Board.WHITE_PAWN && row = 0) {
                    return (state.get_current_player() == Player.WHITE) ? 1 : -1;
                }


            }
        }
    }
}
