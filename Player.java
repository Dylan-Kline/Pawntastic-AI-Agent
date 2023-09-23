public enum Player {
    WHITE, BLACK;

    // Get the opponent of the current player
    public Player get_opponent(){
        return this == WHITE ? BLACK : WHITE;
    }

    // Return the pawn symbol associated with the player
    public char get_pawn_symbol(){

        switch(this){
            case WHITE:
                return '\u2659'; // Unicode for white pawn
            case BLACK:
                return '\u265F'; // Unicode for black pawn
            default:
                throw new IllegalArgumentException("Invalid player type.");
        }
    }

}
