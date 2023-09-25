import java.util.List;
import java.util.ArrayList;

public class Board {
    
    // Unicode values for pawn icons
    public static final char WHITE_PAWN = 'X';
    public static final char BLACK_PAWN = 'O';

    // Board variables
    private int size;
    private char[][] board;

    public Board(int size){

        if (size < 4){
            throw new IllegalArgumentException("Board size must be greater than or equal to 4x4.");
        }

        this.size = size;
        this.board = new char[size][size];
        initialize_board();
    }

    // Initializzes the board with pawns 
    private void initialize_board(){

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){

                if (i == 1){
                    board[i][j] = BLACK_PAWN;
                }
                else if (i == size - 2){
                    board[i][j] = WHITE_PAWN;
                }
                else{
                    board[i][j] = '-';
                }
            }
        }
    }

    public Board clone(){

        Board cloned_Board = new Board(this.size);

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                cloned_Board.board[i][j] = board[i][j];
            }
        }

        return cloned_Board;
    }

    // Prints out the board to console
    public void display_board(){

        for (int i = 0; i < size; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.print(i + 1 + " ");
            System.out.println();
        }

        System.out.print("\n");
    }

    // Checks if the specified location is empty
    public boolean is_empty(int x, int y){
        return board[x][y] == '-';
    }

    // Sets a pawn at a specific location
    public void set_pawn(int x, int y, char pawn){
        board[x][y] = pawn;
    }

    // Get the pawn at a specific location
    public char get_pawn(int x, int y) {

        if (board[x][y] == WHITE_PAWN || board[x][y] == BLACK_PAWN){
            return board[x][y];
        }
        return '-';
    }

    // Checks whether there is a pawn at the given location
    public boolean is_pawn_location(int row, int col) {
        return board[row][col] == WHITE_PAWN || board[row][col] == BLACK_PAWN ? true : false;
    }

    // Get the size of the board
    public int get_board_size() {
        return this.size;
    }

    // Apply the given move to the board (modifying the position of pawns)
    public void apply_move(Move move){
        char pawn = board[move.get_start().get_x()][move.get_start().get_y()];
        board[move.get_start().get_x()][move.get_start().get_y()] = '-';
        board[move.get_end().get_x()][move.get_end().get_y()] = pawn;
    }

    // Get the possible moves from the specific location
    public List<Move> get_applicable_moves_for_player(Player current_player){
        List<Move> possible_moves = new ArrayList<>();

        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++)
            {
                if (board[i][j] == current_player.get_pawn_symbol()){

                    // White's possible moves
                    if (current_player == Player.WHITE){
                        
                        // Forward move
                        if ( i > 0 && board[i-1][j] == '-'){
                            possible_moves.add(new Move(new Location(i, j), new Location(i - 1, j)));
                        }

                        // Double forward move if the pawn is in the start position
                        if (i == size - 2 && board[i-1][j] == '-' && board[i-2][j] == '-'){
                            possible_moves.add(new Move(new Location(i, j), new Location(i - 2, j)));
                        }

                        // Diagonal left capture
                        if (i > 0 && j > 0 && board[i - 1][j - 1] == Player.BLACK.get_pawn_symbol()){
                            possible_moves.add(new Move(new Location(i, j), new Location(i - 1, j - 1)));
                        }

                        // Diagonal right capture
                        if (i > 0 && j < size - 1 && board[i - 1][j + 1] == Player.BLACK.get_pawn_symbol()){
                            possible_moves.add(new Move(new Location(i, j), new Location(i - 1, j + 1)));
                        }
                    }
                    else if (current_player == Player.BLACK){
                        
                        // Forward move
                        if ( i < size - 1 && board[i + 1][j] == '-'){
                            possible_moves.add(new Move(new Location(i, j), new Location(i + 1, j)));
                        }

                        // Double forward move if the pawn is in the start position
                        if (i == 1 && board[i + 1][j] == '-' && board[i + 1][j] == '-'){
                            possible_moves.add(new Move(new Location(i, j), new Location(i + 2, j)));
                        }

                        // Diagonal right capture
                        if (i < size - 1 && j > 0 && board[i + 1][j - 1] == Player.WHITE.get_pawn_symbol()){
                            possible_moves.add(new Move(new Location(i, j), new Location(i + 1, j - 1)));
                        }

                        // Diagonal left capture
                        if (i < size - 1 && j < size - 1 && board[i + 1][j + 1] == Player.WHITE.get_pawn_symbol()){
                            possible_moves.add(new Move(new Location(i, j), new Location(i + 1, j + 1)));
                        }
                    
                    }
                }
            }
        }
        return possible_moves;
    }
}
