public class Location {
    
    private int x; // row position
    private int y; // column position

    public Location (int x, int y){
        this.x = x;
        this.y = y;
    }

    // Grabbers for x and y
    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }

    // Setters for x and y
    public void set_x(int x) {
        this.x = x;
    }

    public void set_y(int y) {
        this.y = y;
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

        Location location = (Location) obj;
        return x == location.x && y == location.y;
    }

    // // Checks whether the location is a valid location on the board
    // public boolean is_valid_location(int x, int y){

        
    // }

    public static void main(String[] args){

        Location location1 = new Location(0, 0);
        Location location2 = new Location(0, 0);

        System.out.print(location1.equals(location2));

        location1.set_x(5);

        System.out.print(location1.equals(location2));
    }


}
