

package WhackAMole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class App_WhackAMole {
    public static void main(String[] args) {
        WhackAMole whackAMole= new WhackAMole();
    }
}

class WhackAMole implements ActionListener {
    int boardWidth = 600;
    int boardHeight = 650;
    static int maxScore;
    static String maxScorer;
    JLabel points,maxPlayer,maxScore1;

    JFrame homeScreen = new JFrame();
    JFrame gameFrame = new JFrame("Mario: Whack a Mole!");
    JFrame gameOverScreen = new JFrame();

    JPanel homePanel = new JPanel();
    JPanel textPanel= new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel overPanel = new JPanel();

    JLabel scoreLabel = new JLabel();
    JLabel homeNameLabel = new JLabel("Name:");
    JLabel gameNameLabel = new JLabel();
    JLabel gameTimeLabel = new JLabel();
    JLabel player;


    JTextField nameField = new JTextField(9);

    JButton board[] = new JButton[9];
    JButton playButton = new JButton("Play");
    JButton playAgain;

    ImageIcon moleIcon;
    ImageIcon plantIcon;

    Random random = new Random();

    private Timer molePlantTimer;
    private Timer gameTimer;

    int currentTime = 30;
    int fplantPosition;
    int fpp;
    int fmolePosition;
    int fmp;
    int splantPosition;
    int spp;
    int tplantPosition;
    int tpp;
    int score=0;
    boolean gameOver = false;
    boolean play = false;

    String playerName;


    WhackAMole()  {
        //        setPlantPositions();
        setHomeScreen();
        homeScreen.setVisible(true);
        setupBoard();
        setupMoleAndPlant();
        setGameOver();
    }
    void setPlantPositions(){
        splantPosition = 10;
        tplantPosition = 10;
    }

    void setHomeScreen(){
        homeScreen.setSize(boardWidth+boardWidth-130, boardHeight-100);
        homeScreen.setLocationRelativeTo(null);
        homeScreen.setResizable(false);
        homeScreen.setLayout(new BorderLayout());

        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));
        homePanel.setBackground(Color.WHITE);

        JPanel row1 = new JPanel();
        row1.setBackground(Color.WHITE);
        JLabel gameName = new JLabel("Mario! Whack A Mole");
        gameName.setFont(new Font("Arial", Font.PLAIN, 25));
        gameName.setForeground(Color.BLACK);
        row1.add(gameName);

        JPanel row2 = new JPanel();
        row2.setBackground(Color.WHITE);
        homeNameLabel.setPreferredSize(new Dimension(100, 100));
        homeNameLabel.setFont(new Font("Arial", Font.BOLD, 25));
        homeNameLabel.setForeground(Color.BLACK);
        row2.add(homeNameLabel);

        nameField.setPreferredSize(new Dimension(100, 50));
        nameField.setFont(new Font("Arial", Font.BOLD, 25));
        nameField.setForeground(Color.BLACK);
        row2.add(nameField);


        JLabel Line1 = new JLabel();
        Line1.setForeground(Color.black);
        Line1.setFont(new Font("Arial", Font.BOLD, 20));
        Line1.setText("This is a Mario Whack A Mole game, where there is a mole and a plant inside a 3 cross 3 grid.");
        JLabel Line2 = new JLabel();
        Line2.setForeground(Color.BLACK);
        Line2.setFont(new Font("Arial", Font.BOLD, 20));
        Line2.setText("The mole and the plant jump around the grid every .85 seconds. For each time the user clicks");
        JLabel Line3 = new JLabel();
        Line3.setForeground(Color.BLACK);
        Line3.setText("the mole his/her points increase by 10. If he/she clicks the plant the game is over. The user's");
        Line3.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel Line4 = new JLabel();
        Line4.setForeground(Color.BLACK);
        Line4.setFont(new Font("Arial", Font.BOLD, 20));
        Line4.setText("goal is to score as many points as possible in 30 seconds. The game is going to get harder every");
        JLabel Line5 = new JLabel();
        Line5.setForeground(Color.BLACK);
        Line5.setFont(new Font("Arial", Font.BOLD, 20));
        Line5.setText("10 seconds. How? well that's a surprise!");

        JPanel row3 = new JPanel();
        row3.setBackground(Color.WHITE);
        playButton.setForeground(Color.BLACK);
        playButton.setBackground(Color.RED);
        playButton.setFont(new Font("Arial", Font.PLAIN, 23));
        playButton.setPreferredSize(new Dimension(100, 60));
        row3.add(playButton);
        playButton.addActionListener(this);
        //        playButton.setSize(100, 70);


        homePanel.add(row1);
        homePanel.add(Line1);
        homePanel.add(Line2);
        homePanel.add(Line3);
        homePanel.add(Line4);
        homePanel.add(Line5);
        homePanel.add(row2);
        homePanel.add(row3);


        homeScreen.add(homePanel);

        //        This is a Mario Whack A Mole game, where there is a mole and a plant inside a 3 cross 3 grid. The mole and the plant jump around the grid every .75 seconds. For each time the user clicks the mole his/her points increase by 10. If he/she clicks the plant the game is over. The user's goal is to score as many points as possible.
    }
    void setGameOver(){
        gameOverScreen.setSize(boardWidth+boardWidth-400, boardHeight-150);
        gameOverScreen.setLocationRelativeTo(null);
        gameOverScreen.setResizable(false);
        gameOverScreen.setLayout(new BorderLayout(50, 30));

        overPanel.setLayout(new BoxLayout(overPanel, BoxLayout.Y_AXIS));
        gameOverScreen.add(overPanel);

        JPanel row1 = new JPanel();
        JLabel overLabel = new JLabel("GAME OVER!");
        overLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        overLabel.setForeground(Color.white);
        row1.add(overLabel);
        row1.setBackground(Color.black);

        JPanel row2 = new JPanel();
        player = new JLabel("Player: "+playerName);
        player.setFont(new Font("Arial", Font.PLAIN, 20));
        player.setForeground(Color.white);
        points = new JLabel(" ");
        points.setFont(new Font("Arial", Font.PLAIN, 20));
        points.setForeground(Color.white);
        row2.add(player);
        row2.add(points);
        row2.setBackground(Color.black);

        JPanel row3 = new JPanel();
        row3.setBackground(Color.black);
        maxPlayer = new JLabel();
        maxPlayer.setFont(new Font("Arial", Font.PLAIN, 20));
        maxPlayer.setForeground(Color.white);
        row3.add(maxPlayer);
        maxScore1 = new JLabel();
        maxScore1.setFont(new Font("Arial", Font.PLAIN, 20));
        maxScore1.setForeground(Color.white);
        row3.add(maxScore1);

        JPanel row4 = new JPanel();
        row4.setBackground(Color.black);
        playAgain = new JButton("Want to play again?");
        playAgain.setBackground(Color.green);
        playAgain.setFont(new Font("Arial", Font.PLAIN, 17));
        playAgain.setForeground(Color.BLUE);
        playAgain.addActionListener(this);
        row4.add(playAgain);

        overPanel.add(row1);
        overPanel.add(row2);
        overPanel.add(row3);
        overPanel.add(row4);
        //        overPanel.add(playAgain);

    }
    void setupBoard(){
        gameFrame.setSize(boardWidth, boardHeight);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setResizable(false);
        gameFrame.setLayout(new BorderLayout());

        gameNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20 ) );
        gameNameLabel.setText("Name: "+playerName+",");
        gameNameLabel.setHorizontalAlignment(JLabel.CENTER);
        gameNameLabel.setOpaque(true);

        scoreLabel.setFont(new Font("Consolas", Font.PLAIN, 20 ) );//Setting font size and style
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setText("Score: 0");
        scoreLabel.setOpaque(true);

        gameTimeLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        gameTimeLabel.setHorizontalAlignment(JLabel.CENTER);
        gameTimeLabel.setText("Time: ");
        gameTimeLabel.setOpaque(true);

        JPanel innerPanel = new JPanel(new GridLayout(1,3));
        innerPanel.add(gameNameLabel);
        innerPanel.add(scoreLabel);
        innerPanel.add(gameTimeLabel);
        textPanel.add(innerPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));//Makes a grid of three rows and three columns
        gameFrame.add(boardPanel);
        gameFrame.add(textPanel, BorderLayout.NORTH);
    }
    void setupMoleAndPlant(){
        plantIcon = new ImageIcon(getClass().getResource("Piranha_plant.png"));//This line creates a new ImageIcon object
        // from an image file located at the specified path (./Piranha_plant.png). The method getClass().getResource is used to
        // find the resource (image file) relative to the class's location in the file system or JAR archive.

        Image plantImage = plantIcon.getImage();//This line extracts the Image object from the previously created ImageIcon object.
        // The getImage() method returns the Image contained in the ImageIcon.

        plantIcon= new ImageIcon(plantImage.getScaledInstance(170, 170, Image.SCALE_SMOOTH));//This line scales the extracted
        // image to the specified dimensions (170x170 pixels) using smooth scaling. The getScaledInstance method is used for this purpose.
        // The scaled image is then wrapped in a new ImageIcon object, which replaces the original plantIcon.

        //Bringing monty mole into the grid same way as piranhaPlant
        moleIcon = new ImageIcon(getClass().getResource("MontyMole.jpg"));
        Image moleImage = moleIcon.getImage();
        moleIcon = new ImageIcon(moleImage.getScaledInstance(170, 170, Image.SCALE_SMOOTH));

        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;//Making each grid box a clickable button
            //            board[i].addActionListener(this);
            boardPanel.add(board[i]);
        }

    }

    void pickSpotForMoleAndPlant(){
        board[fmolePosition].setIcon(null);
        board[fplantPosition].setIcon(null);
        board[splantPosition].setIcon(null);
        board[tplantPosition].setIcon(null);
        //Picking spot for mole
        fmolePosition= random.nextInt(9);

        fmp = fmolePosition;//making a copy

        //Picking spot for plant
        do{
            fplantPosition= random.nextInt(9);
        }while (fplantPosition == fmolePosition);

        fpp = fplantPosition;//making a copy


        board[fmolePosition].setIcon(moleIcon);
        board[fplantPosition].setIcon(plantIcon);

        {//second and third plant at the right time.
            if (currentTime <= 20) {
                do {
                    splantPosition= random.nextInt(9);
                }while(splantPosition == fmolePosition || splantPosition == fplantPosition);
                spp = splantPosition;//making copy
                board[splantPosition].setIcon(plantIcon);
                if (currentTime <= 10) {
                    do{
                        tplantPosition = random.nextInt(9);
                    }while(tplantPosition == fmolePosition || tplantPosition == fplantPosition || tplantPosition == splantPosition);
                    tpp = tplantPosition;//making copy
                    board[tplantPosition].setIcon(plantIcon);
                }
            }
        }
        molePlantTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changeMoleAndPlant();
            }
        });
        molePlantTimer.start();
    }

    void changeMoleAndPlant(){
        board[fmolePosition].removeActionListener(this);
        board[fplantPosition].removeActionListener(this);

        if(currentTime <= 20){
            board[splantPosition].removeActionListener(this);
            if(currentTime <= 10){
                board[tplantPosition].removeActionListener(this);
            }
        }

        board[fmolePosition].setIcon(null); // Clear previous mole
        board[fplantPosition].setIcon(null);// Clear previous plant
        {//start clearing splantPostiton when t <=20 & start clearing tplantPostion when t<= 10.
            if (currentTime <= 20) {
                board[splantPosition].setIcon(null);
                if (currentTime <= 10) {
                    board[tplantPosition].setIcon(null);
                }
            }
        }

        fmolePosition = random.nextInt(9);
        if (fmolePosition == fmp) {
            if (fmp == 0) {
                fmolePosition = fmp + 1;
            } else if (fmp == 8) {
                fmolePosition = fmp - 1;
            } else {
                fmolePosition += 1;
            }
        }

        do {
            fplantPosition = random.nextInt(9);
        } while (fplantPosition == fmolePosition);

        if (fplantPosition == fpp) {
            if (fpp == 0) {
                fplantPosition = fpp + 1;
            } else if (fpp == 8) {
                fplantPosition = fpp - 1;
            } else {
                fplantPosition += 1;
            }
        }
        if(currentTime<=20){
            do {
                splantPosition= random.nextInt(9);
            }while(splantPosition == fmolePosition || splantPosition == fplantPosition);
            if(currentTime <= 10){
                do{
                    tplantPosition = random.nextInt(9);
                }while(tplantPosition == fmolePosition || tplantPosition == fplantPosition || tplantPosition == splantPosition);
            }
        }

        board[fmolePosition].setIcon(moleIcon);
        board[fplantPosition].setIcon(plantIcon);
        if(currentTime <=20){
            board[splantPosition].setIcon(plantIcon);
            if(currentTime <= 10){
                board[tplantPosition].setIcon(plantIcon);
            }
        }

        fmp = fmolePosition;
        fpp = fplantPosition;
        spp = splantPosition;
        tpp = tplantPosition;

        board[fmolePosition].addActionListener(this);
        board[fplantPosition].addActionListener(this);
        if(currentTime <= 20){
            board[splantPosition].addActionListener(this);
            if(currentTime <= 10){
                board[splantPosition].addActionListener(this);
            }
        }

        if (gameOver) {
            molePlantTimer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == board[fmolePosition]){
            if (!gameOver) {
                score += 10;
                scoreLabel.setText("Score: " + score);
            }
        }
        if(e.getSource() == board[fplantPosition]) {
            gameOver = true;
            if(score>maxScore){
                maxScore = score;
                maxScorer = nameField.getText();
            }
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 17));
              molePlantTimer.stop();
              gameTimer.stop();


            gameOver();
        }
        if(e.getSource() == board[splantPosition] && currentTime <= 20){//I am amazing
            gameOver = true;
            if(score>maxScore){
                maxScore = score;
                maxScorer = nameField.getText();
            }
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 17));
              molePlantTimer.stop();
              gameTimer.stop();
              /*board[splantPosition].removeActionListener(this);
              splantPosition = 0;*/
              gameOver();

        }
        if(e.getSource() == board[tplantPosition] && currentTime <= 10){//I am amazing
            gameOver = true;
            if(score>maxScore){
                maxScore = score;
                maxScorer = nameField.getText();
            }
            scoreLabel.setFont(new Font("Arial", Font.PLAIN, 17));
              molePlantTimer.stop();
              gameTimer.stop();
              /*board[tplantPosition].removeActionListener(this);
              tplantPosition = 0;*/
              gameOver();
        }
        if(e.getSource() == playButton){
            playerName = nameField.getText();
            //            playerName = nameField.getText();
            gameNameLabel.setText("Name: "+playerName+",");

            player.setText("Player: "+playerName);
            //scoreLabel.setText(" Points: " + score);

            currentTime = 30;
            homeScreen.setVisible(false);
            score = 0;
            scoreLabel.setText("Score: " + score);
            gameFrame.setVisible(true);
            gameTimeLabel.setText("Time: "+currentTime);
            {//We are brilliant.
                board[splantPosition].setIcon(null);
                board[tplantPosition].setIcon(null);
                board[splantPosition].removeActionListener(this);
                board[tplantPosition].removeActionListener(this);
                splantPosition = 0;
                tplantPosition = 0;
            }
            gameTimer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gameTimeLabel.setText("Time: "+ --currentTime);
                    if(gameTimeLabel.getText().equals("Time: "+0) || gameOver){
                        if(score > maxScore){
                            maxScore = score;
                            maxScorer = nameField.getText();
                        }
                        gameOver = true;

                        scoreLabel.setText(" Points: " + score+",");
                        gameTimer.stop();




                        gameOver();
                    }
                }
            });
            gameTimer.start();
            pickSpotForMoleAndPlant();
        }
        if(e.getSource()== playAgain){
            gameOver = false;
            score  = 0;
            gameOverScreen.setVisible(false);
            homeScreen.setVisible(true);
            //            setHomeScreen();
            nameField.setText(null);
        }
    }
    void gameOver(){
        gameFrame.setVisible(false);
        gameNameLabel.setText("Name: "+playerName+",");
        points.setText("Points scored:"+score);
        maxScore1.setText("Top score: "+maxScore);
        maxPlayer.setText("Top scorer name:"+maxScorer);
        gameOverScreen.setVisible(true);
    }
}
