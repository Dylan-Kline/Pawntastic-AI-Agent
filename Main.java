import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /**
     * Pawntastic Minimax Project
     * Author: Dylan Kline
     * Date: 9/25/2023
     */
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            // Choose game size
            System.out.println("Choose your game:");
            System.out.println("4. Tiny 4x4 Pawntastic");
            System.out.println("5. Very small 5x5 Pawntastic");
            System.out.print("Your choice? ");
            int board_size = Integer.parseInt(reader.readLine());
            System.out.print("\n");

            // Choose opponent type
            System.out.println("Choose your opponent:");
            System.out.println("1. An agent that uses MINIMAX");
            System.out.print("Your choice? ");
            int opponent_type = Integer.parseInt(reader.readLine());
            System.out.print("\n");

            // Choose playing side
            System.out.print("Do you want to play for O's (B) or X's (W)? (X plays first) ");
            char side_choice = Character.toLowerCase(reader.readLine().charAt(0));
            System.out.print("\n");

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


            Game game = new Game(board_size, player_type, agent_type);
            game.play();

        } catch (IOException e) {
            System.out.println("Error reading input, please try again.");
        }
    }
}
