import java.util.List;

public class Game {

    private Board board; 
    private State current_state; 
    private Player current_player;
    private Player human_player;
    private Agent agent; // AI agent
    private int agent_type; // minimax(1), h_minimax with a/b and depth cutoff(2)
    private int depth; // max depth of searching for agent

    // Initialize Game
    public Game (int board_size, Player human_player_color, Player agent_color, int agent_type, int depth){
        this.board = new Board (board_size);
        this.current_player = Player.WHITE;
        this.current_state = new State(board, current_player);
        this.human_player = human_player_color;
        this.depth = depth;
        this.agent = new Agent(agent_color, this.depth);
        this.agent_type = agent_type;
    }

    // Main game loop
    public void play(){
        while (!is_game_over()){

            board.display_board();
            if (current_state.get_current_player() == human_player){
                handle_human_turn();
            }
            else{
                handle_agent_turn();
            }
        }

        declare_winner();
    }

    // Print out whether one side won or there is a tie
    public void declare_winner() {
        board.display_board();

        if (board.get_applicable_moves_for_player(Player.WHITE).isEmpty() ||
            board.get_applicable_moves_for_player(Player.BLACK).isEmpty()) {
                System.out.println("The game ends in a stalemate.");
        }
        else if (current_state.get_current_player() == Player.WHITE){
            System.out.println("Black wins!");
        }
        else {
            System.out.println("White wins!");
        }
    }

    // Handle the AI agent's best move algorithms
    public void handle_agent_turn() {
        System.out.println("I'm thinking....");

        // Find best move given current state, and update the current Game variables
        Move best_move = agent.get_best_move(this.current_state, agent_type);
        current_state = current_state.apply_move(best_move);
        current_player = current_state.get_current_player();
        board = current_state.get_board();
        System.out.println("Agent moved: " + best_move);
    }

    // Handle the user's turn
    public void handle_human_turn() {
        Move move;

        do {
            move = get_user_input_move();
            if (move == null) {
                System.out.println("Invalid input format, please try again.");
            }
            else if (!is_valid_move(move)) {
                System.out.println("Illegal move, please try again.");
                move = null;
            }
        } while (move == null);

        // Update Game state, player, and board based on user's move
        current_state = current_state.apply_move(move);
        current_player = current_state.get_current_player();
        board = current_state.get_board();
        System.out.println("Human Player moved: " + move);
    }

    // Checks whether the given move is within Pawntastic rules
    private boolean is_valid_move(Move move) {
        List<Move> possible_moves = board.get_applicable_moves_for_player(current_player);
        return possible_moves.contains(move);
    }

    // Computes the user's move
    private Move get_user_input_move() {
        
        System.out.print("Enter your move (Ex: a2-a3): ");

        try {
            int input_char;
            StringBuilder input = new StringBuilder();

            // Read characters from input until a new line is detected
            while ((input_char = System.in.read()) != '\n'){
                if (input_char != '\r') {
                    input.append((char) input_char);
                }
            }
            
            // Start of user's move
            char start_column = input.charAt(0);
            int start_row = Character.getNumericValue(input.charAt(1));
            Location start_location = new Location(start_row, start_column - 'a');

            // End of user's move
            char end_column = input.charAt(3);
            int end_row = Character.getNumericValue(input.charAt(4));
            Location end_location = new Location(end_row, end_column - 'a');

            return new Move(start_location, end_location);
        }
        catch (Exception e) {
            return null; // Returning null if there is any issue with parsing
        }
    }

    // Returns whether or not any side has no moves, or if one side has a pawn that has reached the opposite side
    private boolean is_game_over() {

        // Check if any player has no more legal moves left
        if (board.get_applicable_moves_for_player(current_player).isEmpty()) {
            return true;
        }

        // Check if any pawn has reached the opposite side of the board
        for (int col = 0; col < board.get_board_size(); col++){
            if (board.is_pawn_location(0, col) || 
                board.is_pawn_location(board.get_board_size() - 1, col)) {
                    return true;
                }
        }
        return false;
    }

}