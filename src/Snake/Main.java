package Snake;

import javax.swing.*;

public class Main {
    private static final int  WIDTH = 800;
    private static final int  HEIGHT  = 600;
    public static void main(String[] args) {
        final JFrame frame = new JFrame("Snake Game");
        Snake game = new Snake(WIDTH, HEIGHT);
        frame.add(game);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        game.startGame();
    }

}
