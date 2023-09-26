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
                return Board.WHITE_PAWN; // char for white pawn
            case BLACK:
                return Board.BLACK_PAWN; // char for black pawn
            default:
                throw new IllegalArgumentException("Invalid player type.");
        }
    }

}
