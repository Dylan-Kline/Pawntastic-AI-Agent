import java.util.List;

public class Agent {
    
    private Player player; // Represents which side the agent plays as White or Black
    private int states_searched; // Number of states searched
    
    public Agent(Player player){
        this.player = player;
        this.states_searched = 0;
    }

    /**
     * Gets the best move for the AI agent from the current board state.
     */
    public Move get_best_move(State state){
        states_searched = 0;
        int[] result = minimax(state, player);
        Location initialLocation = new Location(result[1], result[2]);
        Location targetLocation = new Location(result[3], result[4]);
        Move best_move = new Move(initialLocation, targetLocation);
        System.out.println("  Total states searched: " + states_searched);
        return best_move;
    }
    
    /**
     * Implementation of minimax
     * @param state
     * @param player
     * @return int[] array containing bestScore, initial position x and y, end position x and y
     */
    private int[] minimax(State state, Player player) {
        states_searched++;

        List<Move> nextMoves = state.get_board().get_applicable_moves_for_player(player);
        int bestScore = (player == this.player) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int currentScore;
        int bestInitialRow = -1;
        int bestInitialCol = -1;
        int bestTargetRow = -1;
        int bestTargetCol = -1;
    
        if (state.is_terminal()) {  // Checking if the state is terminal
            bestScore = utility(state);

        } else {
            for (Move move : nextMoves) {
                State nextState = state.apply_move(move);
                if (player == this.player) { // AI player (maximizing)
                    currentScore = minimax(nextState, (this.player == Player.WHITE) ? Player.BLACK : Player.WHITE)[0];
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestInitialRow = move.get_start().get_x();
                        bestInitialCol = move.get_start().get_y();
                        bestTargetRow = move.get_end().get_x();
                        bestTargetCol = move.get_end().get_y();
                    }
                } else { // Opponent (minimizing)
                    currentScore = minimax(nextState, this.player)[0];
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestInitialRow = move.get_start().get_x();
                        bestInitialCol = move.get_start().get_y();
                        bestTargetRow = move.get_end().get_x();
                        bestTargetCol = move.get_end().get_y();
                    }
                }
            }
        }
        return new int[] {bestScore, bestInitialRow, bestInitialCol, bestTargetRow, bestTargetCol};
    }
       
    /** PlaceHolder
     * 
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
        
                if (pawn == Board.WHITE_PAWN && row == 0) {
                    return (this.player == Player.WHITE) ? 1 : -1;
                }
                else if (pawn == Board.BLACK_PAWN && row == board_size - 1) {
                    return (this.player == Player.BLACK) ? 1 : -1;
                }
            }
        }
        
        // Check if the max player has applicable moves left
        Player opponent = (this.player == Player.WHITE) ? Player.BLACK : player.WHITE;
        if (board.get_applicable_moves_for_player(this.player).isEmpty() && board.get_applicable_moves_for_player(opponent).isEmpty()) {
            return 0;  // tie
        }
        
        throw new IllegalStateException("Utility should not be called for non-terminal states.");
    }
    

    
}