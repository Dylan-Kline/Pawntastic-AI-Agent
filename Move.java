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

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return start + " -> " + end;
    }

}
