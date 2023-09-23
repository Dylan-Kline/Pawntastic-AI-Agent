public class Move {

    private Location start; // Starting position of the move
    private Location end; // Ending position of the move

    public Move(Location start, Location end){
        this.start = start;
        this.end = end;
    }

    // Getters for start and end locations
    public Location get_start(){
        return this.start;
    }

    public Location get_end(){
        return this.end;
    }

    // Setters for start and end locations
    public void set_start(Location start){
        this.start = start;
    }

    public void set_end(Location end){
        this.end = end;
    }

    // Checks if two locations are the same (for attacks or to prevent illegal moves)
    @Override
    public boolean equals(Object obj){

        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Move move = (Move) obj;
        return start.equals(move.start) && end.equals(move.end);
    }

    // Main Test
    public static void main(String[] args){
        Move move1 = new Move(new Location(0, 0), new Location(1, 0));
        Move move2 = new Move(new Location(0, 0), new Location(0, 0));

        System.out.print(move1.equals(move2));
    }
}
