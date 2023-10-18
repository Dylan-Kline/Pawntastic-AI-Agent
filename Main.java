/**
     * Pawntastic Minimax AI agent Project
     * Author: Dylan Kline
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Choose game size
            System.out.println("Choose your game:");
            System.out.println("4. Tiny 4x4 Pawntastic");
            System.out.println("5. Very small 5x5 Pawntastic");
            System.out.println("6. Small 6x6 Pawntastic");
            System.out.println("8. Standard 8x8 Pawntastic");
            System.out.println("10. Jumbo 10x10 Pawntastic");
            System.out.println("Or enter any size >=4 to play that size game");
            System.out.print("Your choice? ");
            int board_size = Integer.parseInt(reader.readLine());
            System.out.print("\n");

            // Choose opponent type
            System.out.println("Choose your opponent:");
            System.out.println("1. An agent that uses MINIMAX");
            System.out.println("2. An agent that uses H-MINIMAX with a fixed depth cutoff and alpha-beta pruning");
            System.out.print("Your choice? ");
            int opponent_type = Integer.parseInt(reader.readLine());
            System.out.print("\n");

            // Ask user for depth limit
            int depth = 1;
            if (opponent_type == 2) {
                System.out.println("Choose your difficulty level (1 - 10): ");
                System.out.print("Your choice? ");
                depth = Integer.parseInt(reader.readLine());
                System.out.print("\n");
            }

            // Choose playing side
            System.out.print("Do you want to play for O's (B) or X's (W)? (X plays first) ");
            char side_choice = Character.toLowerCase(reader.readLine().charAt(0));
            System.out.print("\n");

            // Set player and agent sides
            Player player_type;
            Player agent_type;
            if (side_choice == 'b') {
                player_type = Player.BLACK;
                agent_type = Player.WHITE;
            }
            else {
                player_type = Player.WHITE;
                agent_type = Player.BLACK;
            }

            // Begin game
            Game game = new Game(board_size, player_type, agent_type, opponent_type, depth);
            game.play();

        } catch (IOException e) {
            System.out.println("Error reading input, please try again.");
        }
    }
}
