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

    // Prints out the board to console
    public void display_board(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
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

    // Get the possible moves from the specific location
    public List<Move> get_applicable_moves(Player current_player){
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
