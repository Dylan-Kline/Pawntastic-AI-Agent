import java.util.List;
import java.util.ArrayList;

public class Board {
    
    // Unicode values for pawn icons
    private static final char WHITE_PAWN = '\u2659';
    private static final char BLACK_PAWN = '\u265F';

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
        return board[x][y] == ' ';
    }

    // Sets a pawn at a specific location
    public void set_pawn(int x, int y, char pawn){
        board[x][y] = pawn;
    }

    // Get the pawn at a specific location
    public char get_pawn(int x, int y) {
        return board[x][y];
    }

    // Apply the given move to the board (modifying the position of pawns)
    public void apply_move(Move move){
        
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
        // get location of the pawn of the current_player
        // check for valid moves (such as whether or not the player is currently against the boundary)
        // or also check whether the move is straight towards a location holding a enemy pawn (illegal move)
    }

    public static void main(String[] args){
        Board board4x4 = new Board(4);
        board4x4.display_board();

        Board board8x8 = new Board(8);
        board8x8.display_board();
    }
}
