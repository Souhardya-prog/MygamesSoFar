package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;

public class Snake extends JPanel implements ActionListener {
    private static final int FRAME_RATE = 20;
    public final int width;
    public final int height;
    boolean gameStarted = false;
    public Snake(int  width, int height) {
        super();
        this.width = width;
        this.height= height;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.black);
    }
    public void startGame(){
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        requestFocusInWindow();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE){
                    gameStarted = true;
                }
            }
        });
//        new Timer( 1000 / FRAME_RATE, this).start();
        Timer timer = new Timer(1000/FRAME_RATE, this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.WHITE);
        graphics.setFont(graphics.getFont().deriveFont(30F));// setting font size by getting the current font size and then deriving  to the desired size.
        int currentHeight = height/4;
        var graphics2D = (Graphics2D) graphics;//Graphics2D is a subclass of the abstract class Graphics.
        var frc = graphics2D.getFontRenderContext();
        String message = "Press space bar to \nstart.";
        if(!gameStarted){
            for(var line : message.split("\n")){//For-each loop
                var layout = new TextLayout(line, graphics.getFont(), frc);//Constructs a TextLayout from a String and a Font.
                                                                            // All the text is styled using the specified Font.
                // Params: 1. line(String), 2. font(Font), frc( contains information about a graphics device which is
                // needed to measure the text correctly.)

                var bounds = layout.getBounds();//getting the size of the layout and storing in bounds
                var targetWidth = (float) (width - bounds.getWidth())/2;//Calculations to centre the text.
                layout.draw(graphics2D, targetWidth, currentHeight);//Renders this TextLayout at the specified
                // location in the specified Graphics2D context. targetWidth and currentHeight are x and y coordinates of the
                // origin of the TextLayout
                currentHeight += graphics.getFontMetrics().getHeight();// updating currentHeight to height of font.
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}


































/*
package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake implements KeyListener {
    int boardLength = 600;
    int boardWidth = 600;
    int snakePosition;
    int foodPosition;
    int currentLength =1;
    boolean gameOver =false;
    int currentDirection;

    static JFrame frame = new JFrame("Snake");

    JPanel textpanel = new JPanel();
    JPanel boardpanel = new JPanel();

    JButton board[] = new JButton[289];

    Timer timer;
    Random random = new Random();
    Snake(){
        setupBoard();
        setupSnake();
        setupFood();
        frame.addKeyListener(this);

        {
            // I don't know why but without these two lines the keylisteners won't work
            frame.setFocusable(true);
            frame.requestFocusInWindow();
        }
        frame.setVisible(true);

    }
// setting up the board
    public void setupBoard(){
        frame.setSize(boardWidth, boardLength);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        boardpanel.setLayout(new GridLayout(17,17));
        for (int i = 0; i< board.length ;i++){
            JButton tile = new JButton();
            board[i]=tile;
            board[i].setBackground(Color.black);
            boardpanel.add(board[i]);
        }
        boardpanel.setBackground(Color.black);
        frame.add(boardpanel);
    }
//setting up the snake
    public void setupSnake(){
        snakePosition = random.nextInt(288);
        board[snakePosition].setBackground(Color.GREEN);
    }public void setupFood(){
        foodPosition = random.nextInt(288);
        board[foodPosition].setBackground(Color.RED);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        currentDirection= e.getKeyCode();
        System.out.println(e.getKeyCode());
        switch (e.getKeyCode()){
            //Left
            case 37:
                while(currentDirection==37){
//                    System.out.println("error");
                    board[snakePosition].setBackground(null);
                    board[snakePosition - 1].setBackground(Color.GREEN);
                    snakePosition -= 1;
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            //Up
            case 38:
                board[snakePosition].setBackground(null);
                board[snakePosition - 17].setBackground(Color.GREEN);
                snakePosition -= 17;
                break;
            //Right
            case 39:
                board[snakePosition].setBackground(null);
                board[snakePosition + 1].setBackground(Color.GREEN);
                snakePosition += 1;
                break;
            //Down
            case 40:

                board[snakePosition].setBackground(null);
                board[snakePosition + 17].setBackground(Color.GREEN);
                snakePosition += 17;
        }
    }

    public static void main(String[] args) {
        new Snake();
    }





    @Override
    public void keyReleased(KeyEvent e) {

    }
    //No usages

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
*/
