/*
 * CPT project: building Blackjack
 */

/**Author: Siying Zhu, Victoria Zhao, Yume Yamamoto
 * Course: ICS4U
 * Date: July 26th
 * Culminating activity 
 * 
 * Final copy
 */

import java.util.ArrayList; // importing packages
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.*;
import javax.swing.JScrollPane;
import java.awt.*;
import java.io.FileWriter; 
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;


public class Blackjack extends javax.swing.JFrame {


    /**
     * Creates new form Blackjack
     */
    public Blackjack() {
        initComponents();
        btnStand.setEnabled(false); // ensures that you can not stand at the beginning of program
        btnHit.setEnabled(false);
       
        int agereq = JOptionPane.showOptionDialog(null, "Please confirm that you are above 18","Age Requirement", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, agereqconfirmbuttons, null);
        if (agereq == JOptionPane.YES_OPTION) {
        JTextArea textArea = new JTextArea( // taken frmo https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function)
            "~ Objective ~\n" +
"As the player of the game, your objective will be to either achieve a sum of 21 or reach 5 cards with a sum less than 21. Sums are calculated using the face value of each card, with exceptions for ace, jack, queen, and king, which will be explained in the “How to Play” section.The purpose of this application is for users to understand and play BlackJack from the comfort of their own homes\n" +
"\n" +
"~ Installation Guide ~\n" +
"Step 1: Download the program’s zipped folder\n" +
"Step 2: Unzip the program\n" +
"Step 3: Open the program using Netbeans 8.2\n" +
"Step 4: Run the program, using the run button or F6\n" +
"\n" +
"~ How to Play ~\n" +
"Each turn, the player is dealt 1 card, and the dealer is dealt two cards. The dealer flips their first card over, and then the player flips their card over. Based on the card received, the player can choose the action hit or stand, which is completed using the associated button in the application. Choosing hit deals the player another card, whereas stand means that the player will no longer receive any cards, and the dealer will begin flipping their cards. \n" +
"It is important to note that an ace, which has the symbol A, can either have the value of 1 or 11. Additionally, the face cards (jack, queen, and king) all have the value of 10. \n" +
"As the player, the goal is to achieve a sum of 21 or attain a 5 Card Charlie, which is having 5 cards with a sum less than 21. Either of these allows the player to win the round. The dealer is forced to hit until they have a sum of 17 or more, at which point they will stand and can win the round depending on the sum of their cards. To win as the player, you must either achieve a sum of 21 or have a 5 Card Charlie. The scoreboard in the middle of the application will display the points that the player and dealer have each collected. For further instructions, please click the tutorial button\n" +
"\n" +
"~ Game Modifications ~\n" +
"For a more exciting game, there will be modifications. If the player gets either a 21, or a 5 Card Charlie, the player will automatically win without the dealer having to flip any of their cards. This allows for the player to have an advantage, and is a fresh new way to play to allow beginners to get a taste of victory! \n" +
"\n" +
"~ Key Features ~\n" +
"In this Blackjack application, the dealer’s cards are located in the top half of the screen, while the player’s hand is in the lower half. Additionally, the dealer’s cards are shown with a smaller size. Between the two sets of cards is the scoreboard, which lists the number of times each player has won.Directly below the player’s hand are the two options to either hit or stand during the player’s turn.\n" +
"You also have the ability to choose how many players you want to play at once (1 player or two players) by inputting the number in the “Enter Player Number” text box. \n" +
"Lastly, the exit and help buttons are at the bottom of the application, as well as an area where messages are outputted to users. The exit button allows users to exit the application at any time. Clicking the Help button will show this page of instructions and can assist users with remembering the game rules. The messages outputted will include notifications of actions performed, error messages, congratulation messages, and more.\n" +
"\n" +
"~ Age Requirement ~\n" +
"Before the game begins, users will be asked to input their age. Depending on whether they are legal (18 or over), the game will proceed or quit. \n" +
"\n" +
"~ Help & Tutorial ~\n" +
"Please use the Help button located at the bottom right of the application to access this page at any time during the game. For direct further instructions, please click the tutorial button. \n" +
"\n" +
"Our team wishes you the best of luck!");
    JScrollPane scrollPane = new JScrollPane(textArea);  
    textArea.setLineWrap(true);  
    textArea.setWrapStyleWord(true); 
    scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
    JOptionPane.showMessageDialog(null, scrollPane, "Help Page",  
                                       JOptionPane.INFORMATION_MESSAGE, tutoriallayout);
        } else { 
            JOptionPane.showMessageDialog(null, "You are too young to be playing. You will not be allowed to play");
            System.exit(0);
        }

    }
    String[] cardnumberarr = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"}; //creating parellel arrays to store card values
    int[] cardvaluearr = {11, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10};
    String[] cardtypearr = {"diamonds", "clubs", "hearts", "spades"};
    ArrayList<Integer> totalpointsarr1 = new ArrayList<Integer>(); // creating point arrays for player 
    ArrayList<Integer> totalpointsarr2 = new ArrayList<Integer>();
    ArrayList<Integer> totalpointsarrsorted = new ArrayList<Integer>();
    ArrayList<String> totalcardarr = new ArrayList<String>();
    ArrayList<Integer> dealtotalpointsarr = new ArrayList<Integer>(); 
    ArrayList<String> dealtotalcardarr = new ArrayList<String>();   
    Object[] hitconfirmbuttons = { "Yes, please hit.", "No, I don't want to hit."}; // buttons for joptionpanes
    Object[] standconfirmbuttons = {"Yes, please stand.", "No, let me think about it."};
    Object[] agereqconfirmbuttons = { "I am 18 or older.", "I am younger than 18." }; 
    int totalpoints1 = 0; // defining variables
    int totalpoints2 = 0;
    int dealtotalpoints = 0;
    int player1Win = 0;
    int player2Win = 0;
    int dealerWin = 0;
    int playerNum;
    int playCount = 0;
    int c = 0;
    int cs = 0;
    
    int[] numberarr = null;
    
    //two
    ImageIcon clubstwo = new ImageIcon(".\\src\\images\\2C.png"); // creating image icons for each card number + suit
    ImageIcon diamondstwo = new ImageIcon(".\\src\\images\\2D.png");
    ImageIcon heartstwo = new ImageIcon(".\\src\\images\\2H.png");
    ImageIcon spadestwo = new ImageIcon(".\\src\\images\\2S.png");
    
    ImageIcon clubsthree = new ImageIcon(".\\src\\images\\3C.png");
    ImageIcon diamondsthree = new ImageIcon(".\\src\\images\\3D.png");
    ImageIcon heartsthree = new ImageIcon(".\\src\\images\\3H.png");
    ImageIcon spadesthree = new ImageIcon(".\\src\\images\\3S.png");
    
    ImageIcon clubsfour = new ImageIcon(".\\src\\images\\4C.png");
    ImageIcon diamondsfour = new ImageIcon(".\\src\\images\\4D.png");
    ImageIcon heartsfour = new ImageIcon(".\\src\\images\\4H.png");
    ImageIcon spadesfour = new ImageIcon(".\\src\\images\\4S.png");
    
    ImageIcon clubsfive = new ImageIcon(".\\src\\images\\5C.png");
    ImageIcon diamondsfive = new ImageIcon(".\\src\\images\\5D.png");
    ImageIcon heartsfive = new ImageIcon(".\\src\\images\\5H.png");
    ImageIcon spadesfive = new ImageIcon(".\\src\\images\\5S.png");
    
    ImageIcon clubssix = new ImageIcon(".\\src\\images\\6C.png");
    ImageIcon diamondssix = new ImageIcon(".\\src\\images\\6D.png");
    ImageIcon heartssix = new ImageIcon(".\\src\\images\\6H.png");
    ImageIcon spadessix = new ImageIcon(".\\src\\images\\6S.png");
    
    ImageIcon clubsseven = new ImageIcon(".\\src\\images\\7C.png");
    ImageIcon diamondsseven = new ImageIcon(".\\src\\images\\7D.png");
    ImageIcon heartsseven = new ImageIcon(".\\src\\images\\7H.png");
    ImageIcon spadesseven = new ImageIcon(".\\src\\images\\7S.png");
    
    ImageIcon clubseight = new ImageIcon(".\\src\\images\\8C.png");
    ImageIcon diamondseight = new ImageIcon(".\\src\\images\\8D.png");
    ImageIcon heartseight = new ImageIcon(".\\src\\images\\8H.png");
    ImageIcon spadeseight = new ImageIcon(".\\src\\images\\8S.png");
    
    ImageIcon clubsnine = new ImageIcon(".\\src\\images\\9C.png");
    ImageIcon diamondsnine = new ImageIcon(".\\src\\images\\9D.png");
    ImageIcon heartsnine = new ImageIcon(".\\src\\images\\9H.png");
    ImageIcon spadesnine = new ImageIcon(".\\src\\images\\9S.png");
    
    ImageIcon clubsten = new ImageIcon(".\\src\\images\\10C.png");
    ImageIcon diamondsten = new ImageIcon(".\\src\\images\\10D.png");
    ImageIcon heartsten = new ImageIcon(".\\src\\images\\10H.png");
    ImageIcon spadesten = new ImageIcon(".\\src\\images\\10S.png");
    
    ImageIcon clubsjack = new ImageIcon(".\\src\\images\\JC.png");
    ImageIcon diamondsjack = new ImageIcon(".\\src\\images\\JD.png");
    ImageIcon heartsjack = new ImageIcon(".\\src\\images\\JH.png");
    ImageIcon spadesjack = new ImageIcon(".\\src\\images\\JS.png");
    
    ImageIcon clubsqueen = new ImageIcon(".\\src\\images\\QC.png");
    ImageIcon diamondsqueen = new ImageIcon(".\\src\\images\\QD.png");
    ImageIcon heartsqueen = new ImageIcon(".\\src\\images\\QH.png");
    ImageIcon spadesqueen = new ImageIcon(".\\src\\images\\QS.png");
    
    ImageIcon clubsking = new ImageIcon(".\\src\\images\\KC.png");
    ImageIcon diamondsking = new ImageIcon(".\\src\\images\\KD.png");
    ImageIcon heartsking = new ImageIcon(".\\src\\images\\KH.png");
    ImageIcon spadesking = new ImageIcon(".\\src\\images\\KS.png");
    
    ImageIcon clubsace = new ImageIcon(".\\src\\images\\AC.png");
    ImageIcon diamondsace = new ImageIcon(".\\src\\images\\AD.png");
    ImageIcon heartsace = new ImageIcon(".\\src\\images\\AH.png");
    ImageIcon spadesace = new ImageIcon(".\\src\\images\\AS.png");
    
    ImageIcon cardback = new ImageIcon(".\\src\\images\\green_back.png");
    ImageIcon cardbackPlayer = new ImageIcon(".\\src\\images\\red_back.png");
    ImageIcon tutoriallayout = new ImageIcon(".\\src\\images\\tutoriallayout.png");
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    public ImageIcon icongen(String cardnum, String cardtype) { // functions to display card image
        
        ImageIcon imgstr = null;        
        
        if (cardnum.equals("Ace")) { // checks the card number
             if ((cardtype.equals("diamonds"))   ) { // checks card type
                  imgstr = diamondsace; // shows whaat image icon it symbolizes
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsace;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsace;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesace;
             }
         } else if (cardnum.equals("Two")){ // same as above
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondstwo;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubstwo;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartstwo;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadestwo;
             }
         } else if (cardnum.equals("Three")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsthree;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsthree;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsthree;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesthree;
             }
         } else if (cardnum.equals("Four")){
             if ((cardtype.equals("diamonds"))    ){
                  imgstr = diamondsfour;
             } else if ((cardtype.equals("clubs"))    ){
                  imgstr = clubsfour;
             } else if ((cardtype.equals("hearts"))    ){
                  imgstr = heartsfour;
             } else if ((cardtype.equals("spades"))    ){
                  imgstr = spadesfour;
             }
         } else if (cardnum.equals("Five")){
             if ((cardtype.equals("diamonds"))    ){
                  imgstr = diamondsfive;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsfive;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsfive;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesfive;
             }
         } else if (cardnum.equals("Six")){
             if ((cardtype.equals("diamonds"))    ){
                  imgstr = diamondssix;
             } else if ((cardtype.equals("clubs"))    ){
                  imgstr = clubssix;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartssix;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadessix;
             }
         } else if (cardnum.equals("Seven")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsseven;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsseven;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsseven;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesseven;
             }
         } else if (cardnum.equals("Eight")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondseight;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubseight;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartseight;
             } else if ((cardtype.equals("spades"))    ){
                  imgstr = spadeseight;
             }
         } else if (cardnum.equals("Nine")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsnine;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsnine;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsnine;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesnine;
             }
         } else if (cardnum.equals("Ten")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsten;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsten;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsten;
             } else if ((cardtype.equals("spades"))    ){
                  imgstr = spadesten;
             }
         } else if (cardnum.equals("Jack")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsjack;
             } else if ((cardtype.equals("clubs"))   ) {
                  imgstr = clubsjack;
             } else if ((cardtype.equals("hearts"))    ) {
                  imgstr = heartsjack;
             } else if ((cardtype.equals("spades"))   ) {
                  imgstr = spadesjack;
             }
         } else if (cardnum.equals("Queen")){
             if ((cardtype.equals("diamonds"))   ) {
                  imgstr = diamondsqueen;
             } else if ((cardtype.equals("clubs")) ) {
                  imgstr = clubsqueen;
             } else if ((cardtype.equals("hearts"))   ) {
                  imgstr = heartsqueen;
             } else if ((cardtype.equals("spades")) ) {
                  imgstr = spadesqueen;
             }
          } else if (cardnum.equals("King")){
              if (cardtype.equals("diamonds")) {
                   imgstr = diamondsking;
              } else if (cardtype.equals("clubs")) {
                   imgstr = clubsking;
              } else if (cardtype.equals("hearts")) {
                   imgstr = heartsking;
              } else if (cardtype.equals("spades")) {
                   imgstr = spadesking;
              }
         }
                 
        return imgstr;
        
    }
     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelColour = new javax.swing.JPanel();
        btnHit = new javax.swing.JButton();
        btnStand = new javax.swing.JButton();
        btnHelp = new javax.swing.JButton();
        btnDealerCard7 = new javax.swing.JButton();
        btnPlayerCard1 = new javax.swing.JButton();
        btnPlayerCard2 = new javax.swing.JButton();
        btnPlayerCard5 = new javax.swing.JButton();
        btnPlayerCard4 = new javax.swing.JButton();
        btnPlayerCard3 = new javax.swing.JButton();
        txtOutput = new javax.swing.JTextField();
        btnExit = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblDealerWin = new javax.swing.JLabel();
        lblPlayer1Win = new javax.swing.JLabel();
        lblPlayer2Win = new javax.swing.JLabel();
        txtDealerWin = new javax.swing.JTextField();
        txtPlayer1Win = new javax.swing.JTextField();
        txtPlayer2Win = new javax.swing.JTextField();
        btnDealerCard1 = new javax.swing.JButton();
        btnDealerCard5 = new javax.swing.JButton();
        btnDealerCard2 = new javax.swing.JButton();
        btnDealerCard3 = new javax.swing.JButton();
        btnDealerCard6 = new javax.swing.JButton();
        btnDealerCard4 = new javax.swing.JButton();
        btnDealerCard8 = new javax.swing.JButton();
        btnDealerCard9 = new javax.swing.JButton();
        lblImage1 = new javax.swing.JLabel();
        lblImage2 = new javax.swing.JLabel();
        outputarea = new javax.swing.JTextField();
        btnReset = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        txtPlayerNum = new javax.swing.JTextField();
        lblPlayerNum = new javax.swing.JLabel();
        txtPlayer = new javax.swing.JTextField();
        txtPlayerNumber = new javax.swing.JTextField();
        lblSum1 = new javax.swing.JLabel();
        lblSum2 = new javax.swing.JLabel();
        txtSum1 = new javax.swing.JTextField();
        txtSum2 = new javax.swing.JTextField();
        btnTutorial = new javax.swing.JButton();
        lblHighScore = new javax.swing.JLabel();
        txtHighScore = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelColour.setBackground(new java.awt.Color(0, 102, 0));

        btnHit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHit.setText("Hit");
        btnHit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitActionPerformed(evt);
            }
        });

        btnStand.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnStand.setText("Stand");
        btnStand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStandActionPerformed(evt);
            }
        });

        btnHelp.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHelp.setText("Help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });

        btnDealerCard7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnPlayerCard1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlayerCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_back.png"))); // NOI18N

        btnPlayerCard2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlayerCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_back.png"))); // NOI18N

        btnPlayerCard5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlayerCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_back.png"))); // NOI18N

        btnPlayerCard4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlayerCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_back.png"))); // NOI18N

        btnPlayerCard3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlayerCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/red_back.png"))); // NOI18N

        txtOutput.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setText("Exit Game");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Welcome to Blackjack!");

        lblDealerWin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDealerWin.setForeground(new java.awt.Color(255, 255, 255));
        lblDealerWin.setText("Dealer's wins:");

        lblPlayer1Win.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlayer1Win.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer1Win.setText("Player 1's wins:");

        lblPlayer2Win.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlayer2Win.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayer2Win.setText("Player 2's wins:");

        txtDealerWin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPlayer1Win.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPlayer2Win.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnDealerCard1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        btnDealerCard9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDealerCard9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/green_back.png"))); // NOI18N

        outputarea.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnPlay.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPlay.setText("Play");
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        txtPlayerNum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblPlayerNum.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblPlayerNum.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerNum.setText("Enter player number (1 or 2):");

        txtPlayer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPlayerNumber.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblSum1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSum1.setForeground(new java.awt.Color(255, 255, 255));
        lblSum1.setText("Player 1 Sum:");

        lblSum2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSum2.setForeground(new java.awt.Color(255, 255, 255));
        lblSum2.setText("Player 2 Sum:");

        txtSum1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtSum2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        btnTutorial.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnTutorial.setText("Tutorial");
        btnTutorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTutorialActionPerformed(evt);
            }
        });

        lblHighScore.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblHighScore.setForeground(new java.awt.Color(255, 255, 255));
        lblHighScore.setText("High Score:");

        txtHighScore.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout panelColourLayout = new javax.swing.GroupLayout(panelColour);
        panelColour.setLayout(panelColourLayout);
        panelColourLayout.setHorizontalGroup(
            panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelColourLayout.createSequentialGroup()
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelColourLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblSum1)
                            .addComponent(lblSum2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSum1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSum2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtOutput)
                            .addComponent(txtPlayerNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addComponent(btnHelp)
                                .addGap(18, 18, 18)
                                .addComponent(btnTutorial)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit))
                            .addComponent(outputarea)))
                    .addGroup(panelColourLayout.createSequentialGroup()
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                                                .addComponent(btnPlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnPlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(btnPlay, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(btnDealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDealerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDealerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnDealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addGap(54, 54, 54)
                                        .addComponent(lblHighScore)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addComponent(btnDealerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDealerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDealerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDealerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnDealerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addComponent(btnPlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnPlayerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addComponent(btnHit)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnStand)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnReset))))
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(lblImage1)
                                .addGap(536, 536, 536)
                                .addComponent(lblImage2))
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addGap(298, 298, 298)
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblPlayer2Win)
                                    .addComponent(lblPlayer1Win)
                                    .addComponent(lblDealerWin))
                                .addGap(18, 18, 18)
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDealerWin, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(panelColourLayout.createSequentialGroup()
                                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPlayer2Win, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                                            .addComponent(txtPlayer1Win, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(99, 99, 99)
                                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelColourLayout.createSequentialGroup()
                                                .addComponent(lblPlayerNum)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtPlayerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(lblTitle)))
                        .addGap(0, 86, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelColourLayout.setVerticalGroup(
            panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelColourLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblImage1)
                            .addComponent(lblImage2))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                        .addComponent(btnDealerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelColourLayout.createSequentialGroup()
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnDealerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnDealerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnDealerCard6, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDealerCard7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDealerCard8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDealerCard9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnDealerCard3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDealerCard2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDealerWin)
                            .addComponent(txtDealerWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelColourLayout.createSequentialGroup()
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPlayer2Win)
                                    .addComponent(lblHighScore)
                                    .addComponent(txtHighScore, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3))
                            .addGroup(panelColourLayout.createSequentialGroup()
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPlayer1Win, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPlayer1Win)
                                    .addComponent(txtPlayerNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPlayerNum))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPlayer2Win, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(47, 47, 47)))
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnPlayerCard5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlayerCard4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlayerCard3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlayerCard2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPlayerCard1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHit)
                    .addComponent(btnStand)
                    .addComponent(btnPlay)
                    .addComponent(btnReset))
                .addGap(18, 18, 18)
                .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelColourLayout.createSequentialGroup()
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(outputarea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlayerNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblSum2)
                            .addComponent(txtSum2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHelp)
                            .addComponent(btnExit)
                            .addComponent(btnTutorial)))
                    .addGroup(panelColourLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblSum1)
                        .addComponent(txtSum1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelColour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelColour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        // Exits the system
        System.exit(0); // exits program
    }//GEN-LAST:event_btnExitActionPerformed
    
    private void normalHit(int playerNum){
        // Performs hit action only for the last player (i.e. second player or first player for solo play
        
        int randomcardnum, randomcardtype, cardvalue; // defining variables
        String cardnum, cardtype;
        int randmaxnum = 12;
        int randminnum = 0;
        int randmaxtype = 3;
        int randmintype = 0; 
        int hitresult = JOptionPane.showOptionDialog(null, "Please confirm that you'd like to hit by pressing okay.","Confirming hit", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, hitconfirmbuttons, null); //creates pane
        if (hitresult == JOptionPane.YES_OPTION) {
        randomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // generates random number
        randomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
        cardnum = cardnumberarr[randomcardnum]; // adds number to a variable
        cardvalue = cardvaluearr[randomcardnum];
        cardtype = cardtypearr[randomcardtype];
        
        txtPlayerNumber.setText("");
        
        if(playerNum == 1){
            totalpointsarr1.add(cardvalue);  // adds variable into array to be called to find card number
        }else{
            totalpointsarr2.add(cardvalue);  // adds variable into array to be called to find card number
        }
        
        totalcardarr.add(cardnum);
        
        if(playerNum == 1){
            totalpoints1 = 0; 
            for (int i = 0; i <totalpointsarr1.size(); i++) { // finding sum of i 
                totalpoints1 += totalpointsarr1.get(i);  // adds up every point in i to get total points
            }
        }else{
            totalpoints2 = 0;
            for (int i = 0; i <totalpointsarr2.size(); i++) { // finding sum of i 
                totalpoints2 += totalpointsarr2.get(i);  // adds up every point in i to get total points
            }
        }
        
        int checkpoints;
        
        // searchs whether there is a ace or not
        if(playerNum == 1){
            checkpoints = totalpoints1; 
        }else{
            checkpoints = totalpoints2;
        }
        
        if(playerNum == 1){
            for (int i = 0; i < totalpointsarr1.size(); i++)   { // for every variable in totalpoints array
                   if (totalcardarr.get(i).equalsIgnoreCase("Ace") && totalpoints1 > 21 ) { // checks if there is the word ace and if it is a bust
                       checkpoints = checkpoints - 10; // removes 10 off of the checkpoints, allows program to contuien running, assuming ace is now 1
                   } 
            }
        }else{
            for (int i = 0; i < totalpointsarr2.size(); i++)   { // for every variable in totalpoints array
                   if (totalpointsarr2.get(i) == 11 && totalpoints2 > 21 ) { // checks if there is  ace and if it is a bust
                       checkpoints = checkpoints - 10; // removes 10 off of the checkpoints, allows program to contuien running, assuming ace is now 1
                   } 
            }
        }
        
        // Card display
        if(playerNum == 1){
            if (totalpointsarr1.size() == 1) { 
            btnPlayerCard1.setVisible(true); // ensures that button is shown since they have hit at least once
            btnPlayerCard1.setIcon(icongen(cardnum,cardtype)); // calls function
            } else if (totalpointsarr1.size() == 2) { //if hit is called twice
            btnPlayerCard1.setVisible(true);        // ensure there are two buttons avalible
            btnPlayerCard2.setVisible(true);
            btnPlayerCard2.setIcon(icongen(cardnum,cardtype)); // set newest button to the cardnum and card type
            } else if (totalpointsarr1.size() == 3) { 
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);
            btnPlayerCard3.setVisible(true);    
            btnPlayerCard3.setIcon(icongen(cardnum,cardtype));
            } else if (totalpointsarr1.size() == 4) {
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);
            btnPlayerCard3.setVisible(true);        
            btnPlayerCard4.setVisible(true);
            btnPlayerCard4.setIcon(icongen(cardnum,cardtype));
            } else {
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);  
            btnPlayerCard3.setVisible(true);        
            btnPlayerCard4.setVisible(true);
            btnPlayerCard5.setVisible(true);   
            btnPlayerCard5.setIcon(icongen(cardnum,cardtype));
            }
        }else{
            if (totalpointsarr2.size() == 1) { //displays card icon 
            btnPlayerCard1.setVisible(true); // ensures that button is shown since they have hit at least once
            btnPlayerCard1.setIcon(icongen(cardnum,cardtype)); // calls function
            } else if (totalpointsarr2.size() == 2) { //if hit is called twice
            btnPlayerCard1.setVisible(true);        // ensure there are two buttons avalible
            btnPlayerCard2.setVisible(true);
            btnPlayerCard2.setIcon(icongen(cardnum,cardtype)); // set newest button to the cardnum and card type
            } else if (totalpointsarr2.size() == 3) { 
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);
            btnPlayerCard3.setVisible(true);    
            btnPlayerCard3.setIcon(icongen(cardnum,cardtype));
            } else if (totalpointsarr2.size() == 4) {
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);
            btnPlayerCard3.setVisible(true);        
            btnPlayerCard4.setVisible(true);
            btnPlayerCard4.setIcon(icongen(cardnum,cardtype));
            } else {
            btnPlayerCard1.setVisible(true);        
            btnPlayerCard2.setVisible(true);  
            btnPlayerCard3.setVisible(true);        
            btnPlayerCard4.setVisible(true);
            btnPlayerCard5.setVisible(true);   
            btnPlayerCard5.setIcon(icongen(cardnum,cardtype));
            }
        }
               
        if (checkpoints > 21) { // however if there isn't an ace, and there is a bust
            outputarea.setText("The sum of your cards is "+checkpoints); // print out sum of numbers
            if(playerNum == 1){
                txtSum1.setText("bust");
                stand(JOptionPane.YES_OPTION);
            }else{
                txtSum2.setText("bust");
                stand(JOptionPane.YES_OPTION);
            }
            btnHit.setEnabled(false); // doesn't allow them to hit or stand
            btnStand.setEnabled(false); 
            winnerCount();
              
        } else if (checkpoints <= 21) { // if they have not yet busted
            
            if (playerNum == 1){
                // For solo play
                if(checkpoints == 21) { // if they get blackjack
                    outputarea.setText("");
                    txtSum1.setText("21");
                    txtOutput.setText("You have gotten 21");
                    stand(JOptionPane.YES_OPTION);
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount();       
                }else if(totalpointsarr1.size() == 5) { // checks amount of cards to see if they win via 5 card charlie
                    txtOutput.setText("You have gotten 5 Card Charlie.");
                    txtSum1.setText("5 Card Charlie");
                    stand(JOptionPane.YES_OPTION);
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount();
                }else if  (totalpoints1 != checkpoints ){ // if total points isnt check points (comes from ace searching)
                     outputarea.setText("The sum of your cards is "+Integer.toString(checkpoints)); // display sum and type of card
                     txtOutput.setText("You have gotten "+cardnum+" of "+cardtype);
                     txtSum1.setText(Integer.toString(checkpoints));
                }else{
                    outputarea.setText("The sum of your cards is "+Integer.toString(totalpoints1));
                    txtOutput.setText("You have gotten "+cardnum+" of "+cardtype); 
                    txtSum1.setText(Integer.toString(checkpoints));
                }
            }else{
                // For second player only
                    if(checkpoints == 21) { // if they get blackjack
                    outputarea.setText("");
                    txtOutput.setText("You have gotten 21");
                    txtSum2.setText("21");
                    stand(JOptionPane.YES_OPTION);
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount();       
                } else if (totalpointsarr2.size() == 5) { // chekcs amount of cards to see if they win via 5 card charlie
                    txtOutput.setText("You have gotten 5 Card Charlie.");
                    txtSum2.setText("5 Card Charlie");
                    stand(JOptionPane.YES_OPTION);
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount(); 
                }else if  (totalpoints2 != checkpoints ) { // if total points isnt check points (comes from ace searching)
                     outputarea.setText("The sum of your cards is "+Integer.toString(checkpoints)); // display sum and type of card
                     txtOutput.setText("You have gotten "+cardnum+" of "+cardtype);
                     txtSum2.setText(Integer.toString(checkpoints));    
                }else{
                    outputarea.setText("The sum of your cards is "+Integer.toString(totalpoints2));
                    txtOutput.setText("You have gotten "+cardnum+" of "+cardtype); 
                    txtSum2.setText(Integer.toString(checkpoints));
                }
            }
            
        }
        
        } else { 
            if(playerNum == 1){
                if (totalpointsarr1.size() == 1) { // if user backs out from hit, displays all current buttons, no change to actual logic
                        btnPlayerCard1.setVisible(true);
                    } else if (totalpointsarr1.size() == 2) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                    } else if (totalpointsarr1.size() == 3) { 
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);    
                    } else if (totalpointsarr1.size() == 4) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                    } else {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);  
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                        btnPlayerCard5.setVisible(true);  
                }
            }else{
                if (totalpointsarr2.size() == 1) { // if user backs out from hit, displays all current buttons, no change to actual logic
                        btnPlayerCard1.setVisible(true);
                    } else if (totalpointsarr2.size() == 2) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                    } else if (totalpointsarr2.size() == 3) { 
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);    
                    } else if (totalpointsarr2.size() == 4) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                    } else {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);  
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                        btnPlayerCard5.setVisible(true);  
                }
            }
        }
    }
    
    private void firstHit(){
        // Performs hit action for first player of double player game
        
        int randomcardnum, randomcardtype, cardvalue; // defining variables
        String cardnum, cardtype;
        int randmaxnum = 12;
        int randminnum = 0;
        int randmaxtype = 3;
        int randmintype = 0; 
        int hitresult = JOptionPane.showOptionDialog(null, "Please confirm that you'd like to hit by pressing okay.","Confirming hit", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, hitconfirmbuttons, null); //creates pane
        int sum;
        
            
        if (hitresult == JOptionPane.YES_OPTION) {
            randomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // generates random number
            randomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
            cardnum = cardnumberarr[randomcardnum]; // adds number to a variable
            cardvalue = cardvaluearr[randomcardnum];
            cardtype = cardtypearr[randomcardtype];
            totalpointsarr1.add(cardvalue);  // adds variable into array to be called to find card number
            totalcardarr.add(cardnum);

            totalpoints1 = 0; 
            for (int i = 0; i <totalpointsarr1.size(); i++) { // finding sum of i 
                totalpoints1 += totalpointsarr1.get(i);  // adds up every point in i to get total points
            } 

            // searchs whether there is a ace or not
            int checkpoints = totalpoints1; 
            for (int i = 0; i < totalpointsarr1.size(); i++)   { // for every variable in totalpoints array
                   if (totalcardarr.get(i).equalsIgnoreCase("Ace") && totalpoints1 > 21 ) { // checks if there is the word ace and if it is a bust
                       checkpoints = checkpoints - 10; // removes 10 off of the checkpoints, allows program to contuien running, assuming ace is now 1
                   } 
            }
            
            // Card display
            if (totalpointsarr1.size() == 1) { 
                btnPlayerCard1.setVisible(true); // ensures that button is shown since they have hit at least once
                btnPlayerCard1.setIcon(icongen(cardnum,cardtype)); // calls function
                } else if (totalpointsarr1.size() == 2) { //if hit is called twice
                btnPlayerCard1.setVisible(true);        // ensure there are two buttons avalible
                btnPlayerCard2.setVisible(true);
                btnPlayerCard2.setIcon(icongen(cardnum,cardtype)); // set newest button to the cardnum and card type
                } else if (totalpointsarr1.size() == 3) { 
                btnPlayerCard1.setVisible(true);        
                btnPlayerCard2.setVisible(true);
                btnPlayerCard3.setVisible(true);    
                btnPlayerCard3.setIcon(icongen(cardnum,cardtype));
                } else if (totalpointsarr1.size() == 4) {
                btnPlayerCard1.setVisible(true);        
                btnPlayerCard2.setVisible(true);
                btnPlayerCard3.setVisible(true);        
                btnPlayerCard4.setVisible(true);
                btnPlayerCard4.setIcon(icongen(cardnum,cardtype));
                } else {
                btnPlayerCard1.setVisible(true);        
                btnPlayerCard2.setVisible(true);  
                btnPlayerCard3.setVisible(true);        
                btnPlayerCard4.setVisible(true);
                btnPlayerCard5.setVisible(true);   
                btnPlayerCard5.setIcon(icongen(cardnum,cardtype));
                }


            if (checkpoints > 21) { // however if there isn't an ace, and there is a bust
                outputarea.setText("The sum of your cards is "+ checkpoints); // print out sum of numbers
                txtOutput.setText("Player busted");
                txtSum1.setText("bust");
                c = 1;
                cs++;
                sum = initialPlayerSetup(2);
                dealWinCheck(sum);

            } else if (checkpoints < 21) { // if they have not yet busted
                if  ( totalpoints1 != checkpoints ) { // if total points isnt check points (comes frmo ace seraching)
                     outputarea.setText("The sum of your cards is "+Integer.toString(checkpoints)); // display sum and type of card
                     txtOutput.setText("You have gotten "+cardnum+" of "+cardtype);
                } else {
                    outputarea.setText("The sum of your cards is "+Integer.toString(totalpoints1));
                    txtOutput.setText("You have gotten "+cardnum+" of "+cardtype); 
                }
                if (totalpointsarr1.size() == 5) { // chekcs amount of cards to see if they win via 5 card charlie
                    txtOutput.setText("You win because of the 5 Card Charlie.");
                    txtSum1.setText("5 Card Charlie");
                    c = 1;
                    cs++;
                    sum = initialPlayerSetup(2);
                    dealWinCheck(sum);
                }
            } else if (checkpoints == 21) { // if they get blackjack
                txtOutput.setText("You have gotten 21");
                txtSum1.setText("21");
                c = 1;
                cs++;
                sum = initialPlayerSetup(2);
                dealWinCheck(sum);
            }
            
                
        } else { 
            if (totalpointsarr1.size() == 1) { // if user backs out from hit, displays all current buttons, no change to actual logic
                    btnPlayerCard1.setVisible(true);
                } else if (totalpointsarr1.size() == 2) {
                    btnPlayerCard1.setVisible(true);        
                    btnPlayerCard2.setVisible(true);
                } else if (totalpointsarr1.size() == 3) { 
                    btnPlayerCard1.setVisible(true);        
                    btnPlayerCard2.setVisible(true);
                    btnPlayerCard3.setVisible(true);    
                } else if (totalpointsarr1.size() == 4) {
                    btnPlayerCard1.setVisible(true);        
                    btnPlayerCard2.setVisible(true);
                    btnPlayerCard3.setVisible(true);        
                    btnPlayerCard4.setVisible(true);
                } else {
                    btnPlayerCard1.setVisible(true);        
                    btnPlayerCard2.setVisible(true);  
                    btnPlayerCard3.setVisible(true);        
                    btnPlayerCard4.setVisible(true);
                    btnPlayerCard5.setVisible(true);  
                }
    }
    }
    
    private void btnHitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitActionPerformed
        // TODO add your handling code here:
        
        // directs the program to the correct hit function (firstHit or nomalHit)
        
        playerNum = Integer.parseInt(txtPlayerNum.getText());
        
        if(playerNum == 1){
            // for solo play
            normalHit(playerNum);
        }else if(playerNum == 2 && playCount == 1){
            // for second player
            normalHit(playerNum);
        }else{
            firstHit();
        }
    }//GEN-LAST:event_btnHitActionPerformed

    private void stand(int standresult){
        // performs stand action and outputs results of the game 
        
        if (standresult == JOptionPane.YES_OPTION) { // if they hit that they want to stand
            btnHit.setEnabled(false); // stops user from inputting anything
            btnStand.setEnabled(false);
            
            txtPlayer.setText("");
            txtPlayerNumber.setText("");
         
            int dealrandomcardnum, dealrandomcardtype, dealcardvalue; // defining variables
            String dealcardnum, dealcardtype;
            int randmaxnum = 12;
            int randminnum = 0;
            int randmaxtype = 3;
            int randmintype = 0;
            int checkpoints = 0;
            
            dealtotalpoints = 0; 
                for (int i = 0; i < dealtotalpointsarr.size(); i++) { // sums up the numbers in array to get sum of cards
                    dealtotalpoints += dealtotalpointsarr.get(i); 
                } 

            while (dealtotalpoints <=16) { // as long as the dealer has a sum that is lower than 16

                dealrandomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // get random number and card type
                dealrandomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
                dealcardnum = cardnumberarr[dealrandomcardnum];  // save it to a variable
                dealcardvalue = cardvaluearr[dealrandomcardnum];
                dealcardtype = cardtypearr[dealrandomcardtype];
                dealtotalpointsarr.add(dealcardvalue); // add into array for further use
                dealtotalcardarr.add(dealcardnum);
                
                dealtotalpoints = 0; 
                for (int i = 0; i <dealtotalpointsarr.size(); i++) { // sums up the numbers in array to get sum of cards
                    dealtotalpoints += dealtotalpointsarr.get(i); 
                } 
                
                checkpoints = dealtotalpoints;  // searching whether there is an ace in this list
                for (int i = 0; i < dealtotalpointsarr.size(); i++)   {
                       if (dealtotalcardarr.get(i).equalsIgnoreCase("Ace") && dealtotalpoints > 21 ) { // if there is the word ace and they bust,
                           checkpoints = checkpoints - 10; // subtract 10 from sum, ensuring that only ace is worth one point
                           dealtotalpoints = dealtotalpoints - 10;
                       } 
                }

            }
            
            ArrayList<String> sortedlist = new ArrayList<String>(); // create new arraylist to store sorted variables 
            for(String text:cardnumberarr) { // for each element, add it into list2
               sortedlist.add(text);
            }
            
            
            // taken from https://stackoverflow.com/questions/18129807/in-java-how-do-you-sort-one-list-based-on-another
            //using collections sort, by assuming array dealtotalcard is the "sorted list"
            // it will sort any items through list dealtotalcardarr 
            Collections.sort(dealtotalcardarr,  Comparator.comparing(item -> sortedlist.indexOf(item)));
            
           
            String sorteddealcardnum; // intalize variable
           
            for (int i = 0; i < dealtotalcardarr.size(); i++){ // for the size of dealtotalcardarr
                sorteddealcardnum = dealtotalcardarr.get(i); // grab text i 
                dealrandomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype); // grab random suit
                dealcardtype = cardtypearr[dealrandomcardtype]; // display suit
                                 
                if (i == 0) { // if i = 0, assume that is card 1 and display
                btnDealerCard1.setVisible(true);
                btnDealerCard2.setVisible(true);
                btnDealerCard1.setIcon(icongen(sorteddealcardnum,dealcardtype));
                } else if (i == 1) {
                btnDealerCard1.setVisible(true);        // same thing with card 2
                btnDealerCard2.setVisible(true);
                btnDealerCard2.setIcon(icongen(sorteddealcardnum,dealcardtype));
                } else if (i == 2) { 
                btnDealerCard1.setVisible(true);        
                btnDealerCard2.setVisible(true);
                btnDealerCard3.setVisible(true);    
                btnDealerCard3.setIcon(icongen(sorteddealcardnum,dealcardtype));
                } else if (i == 3) {
                btnDealerCard1.setVisible(true);        
                btnDealerCard2.setVisible(true);
                btnDealerCard3.setVisible(true);        
                btnDealerCard4.setVisible(true);
                btnDealerCard4.setIcon(icongen(sorteddealcardnum,dealcardtype));
                } else if (i == 4){
                btnDealerCard1.setVisible(true);        
                btnDealerCard2.setVisible(true);  
                btnDealerCard3.setVisible(true);        
                btnDealerCard4.setVisible(true);
                btnDealerCard5.setVisible(true);  
                btnDealerCard5.setIcon(icongen(sorteddealcardnum,dealcardtype));
             }
            }
            
            // Getting non number sums such as "bust" or "5 Card Charlie"
            String display1 = txtSum1.getText();
            String display2 = txtSum2.getText();
 
            // Finds the correct game outcome and outputs the result
            if (checkpoints > 21) { // if sum is greater than 21
                
                if(totalpoints2 != 0){
                    player1Win++;
                    player2Win++;
                    txtOutput.setText("The dealer has busted, both players win"); // dealer busts
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount();
                }else{
                    player1Win++;
                    txtOutput.setText("The dealer has busted, player win"); // dealer busts
                    btnHit.setEnabled(false);
                    btnStand.setEnabled(false); 
                    winnerCount();
                }
            }else if (checkpoints <= 21 && checkpoints > 16) { // once they stop getting cards
                
                if(totalpoints2 != 0){
                    if(display1.equals("bust") && display2.equals("bust")){
                        dealerWin = dealerWin + 2;
                        txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player loses"); 
                        winnerCount();
                        txtPlayerNumber.setText(""); // Victoria's edit
                    }else if(display1.equals("bust")){
                        if(checkpoints > totalpoints2){
                            dealerWin = dealerWin + 2;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player loses"); 
                            winnerCount();   
                            txtPlayerNumber.setText(""); // Victoria's edit
                        }else if(checkpoints < totalpoints2){
                            player2Win++;
                            dealerWin++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 loses and player 2 wins"); 
                            winnerCount();   
                        }else if(checkpoints == totalpoints2){
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 loses and player 2 ties"); 
                            winnerCount();   
                        }
                    }else if(display2.equals("bust")){
                        if(checkpoints > totalpoints1){
                            dealerWin = dealerWin + 2;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player loses"); 
                            winnerCount();   
                            txtPlayerNumber.setText(""); // Victoria's edit
                        }else if(checkpoints < totalpoints1){
                            player1Win++;
                            dealerWin++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 loses and player 1 wins"); 
                            winnerCount();   
                        }else if(checkpoints == totalpoints1){
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 loses and player 1 ties"); 
                            winnerCount();   
                        }
                    }else if(display1.equals("5 Card Charlie") && display2.equals("5 Card Charlie")){
                        player1Win++;
                        player2Win++;
                        txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player wins"); 
                        winnerCount();
                    }else if(display1.equals("5 Card Charlie")){
                        if(checkpoints > totalpoints2){
                            dealerWin++;
                            player1Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 wins and player 2 loses"); 
                            winnerCount();   
                        }else if(checkpoints < totalpoints2){
                            player2Win++;
                            player1Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both players win"); 
                            winnerCount();   
                        }else if(checkpoints == totalpoints2){
                            player1Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 wins and player 2 ties"); 
                            winnerCount();   
                        }
                    }else if(display2.equals("5 Card Charlie")){
                        if(checkpoints > totalpoints1){
                            dealerWin++;
                            player2Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 wins and player 1 loses"); 
                            winnerCount();   
                        }else if(checkpoints < totalpoints1){
                            player2Win++;
                            player1Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both players win"); 
                            winnerCount();   
                        }else if(checkpoints == totalpoints1){
                            player2Win++;
                            txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 wins and player 1 ties"); 
                            winnerCount();   
                        }
                    }else if  (totalpoints1 < checkpoints && totalpoints2 < checkpoints) {
                         dealerWin = dealerWin + 2;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both players lose."); // tell user they lose
                         winnerCount();
                         txtPlayerNumber.setText(""); // Victoria's edit
                    }else if(totalpoints1 > checkpoints && totalpoints2 < checkpoints){
                         dealerWin++;
                         player1Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 wins and player 2 loses."); 
                         winnerCount();
                    }else if(totalpoints1 < checkpoints && totalpoints2 > checkpoints){     
                         dealerWin++;
                         player2Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 wins and player 1 loses."); 
                         winnerCount();
                    }else if(totalpoints1 > checkpoints && totalpoints2 > checkpoints){
                         player1Win++;
                         player2Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player win."); 
                         winnerCount();
                    }else if(checkpoints == totalpoints1 && checkpoints == totalpoints2){
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Both player tie."); 
                         winnerCount();
                    }else if(checkpoints == totalpoints1 && checkpoints > totalpoints2){
                         dealerWin++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 tie and player 2 loses"); 
                         winnerCount();
                    }else if(checkpoints == totalpoints1 && checkpoints < totalpoints2){
                         player2Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 1 tie and player 2 wins"); 
                         winnerCount();
                    }else if(checkpoints > totalpoints1 && checkpoints == totalpoints2){
                         player2Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 tie and player 1 loses"); 
                         winnerCount();
                    }else if(checkpoints < totalpoints1 && checkpoints == totalpoints2){
                         player2Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player 2 tie and player 1 wins"); 
                         winnerCount();
                    }
                }else{
                    if  (totalpoints1 < checkpoints) {
                         dealerWin++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player lose."); // tell user they lose
                         winnerCount();
                         txtPlayerNumber.setText(""); // Victoria's edit
                    }else if(totalpoints1 > checkpoints){
                         player1Win++;
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player win."); 
                         winnerCount();
                    }else if(checkpoints == totalpoints1){
                         txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player tie"); 
                         winnerCount();
                    }else if(display1.equals("bust")){
                        dealerWin++;
                        txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player loses"); 
                        winnerCount();    
                        txtPlayerNumber.setText(""); // Victoria's edit
                    }else if(display1.equals("5 Card Charlie")){
                        player1Win++;
                        txtOutput.setText("The dealer has gotten "+Integer.toString(checkpoints)+". Player wins"); 
                        winnerCount();   
                    }
                }
            }
              
            
            if (playerNum == 1){
                if (standresult == JOptionPane.NO_OPTION) { // if they decide to not want to stand right now
                    if (totalpointsarr1.size() == 1) { // does nothing to the code and just shows cards
                        btnPlayerCard1.setVisible(true);
                    } else if (totalpointsarr1.size() == 2) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                    } else if (totalpointsarr1.size() == 3) { 
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);    
                    } else if (totalpointsarr1.size() == 4) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                    } else {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);  
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                        btnPlayerCard5.setVisible(true);  
                    }
                }
            }else{
                if (standresult == JOptionPane.NO_OPTION) { // if they decide to not want to stand right now
                    if (totalpointsarr2.size() == 1) { // does nothing to the code and just shows cards
                        btnPlayerCard1.setVisible(true);
                    } else if (totalpointsarr2.size() == 2) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                    } else if (totalpointsarr2.size() == 3) { 
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);    
                    } else if (totalpointsarr2.size() == 4) {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                    } else {
                        btnPlayerCard1.setVisible(true);        
                        btnPlayerCard2.setVisible(true);  
                        btnPlayerCard3.setVisible(true);        
                        btnPlayerCard4.setVisible(true);
                        btnPlayerCard5.setVisible(true);  
                    }
                }
            }
        
    }
    }
    
    private void btnStandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStandActionPerformed
        // TODO add your handling code here:
        
        // Directs the user to stand action or let the second player play before that
        
        int points = 0;
        int points2 = 0;
                            
        int standresult = 0; // stand result
        
        // Asking user to confirm stand action 
        if (evt != null) {
            standresult = JOptionPane.showOptionDialog(null, "Please confirm that you'd like to stand by pressing okay.","Confirming stand", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null, standconfirmbuttons, null); // shows option pane
        }
                
        playerNum = Integer.parseInt(txtPlayerNum.getText());
        
        
        // recounting the points 
        
        for (int i = 0; i <totalpointsarr1.size(); i++) { // finding sum of i 
                points += totalpointsarr1.get(i);  // adds up every point in i to get total points
        }
                
        for (int i = 0; i <totalpointsarr2.size(); i++) { // finding sum of i 
                points2 += totalpointsarr2.get(i);  // adds up every point in i to get total points
            } 
        
        int check = points; 
            for (int i = 0; i < totalpointsarr1.size(); i++)   { // for every variable in totalpoints array
                   if (totalcardarr.get(i).equalsIgnoreCase("Ace") && points > 21 ) { // checks if there is the word ace and if it is a bust
                       check = check - 10; // removes 10 off of the checkpoints, allows program to contuien running, assuming ace is now 1
                   } 
            }
        int check2 = points2;
            for (int i = 0; i < totalpointsarr2.size(); i++)   { // for every variable in totalpoints array
                   if (totalpointsarr2.get(i) == 11 && points > 21 ) { // checks if there is the word ace and if it is a bust
                       check2 = check2 - 10; // removes 10 off of the checkpoints, allows program to contuien running, assuming ace is now 1
                   } 
            } 
        
        // Displaying the correct sum 
        if(playerNum == 2 && c == 0){
            txtSum1.setText(Integer.toString(check));
            totalpoints1 = check;
            c = 1;
        }else if(playerNum == 2 && c == 1){       
            txtSum2.setText(Integer.toString(check2));
            totalpoints2 = check2;
        }else if(playerNum == 1){
            txtSum1.setText(Integer.toString(check));
            totalpoints1 = check;
            cs++;
        }

        // Let player 2 play before stand 
        // or perform stand if necessary 
        if(cs == 0){
            txtOutput.setText("Before dealer opens, player 2 play.");
            cs++;
            int sum = initialPlayerSetup(2);
            dealWinCheck(sum);
        }else{
            stand(standresult);
        }
    }//GEN-LAST:event_btnStandActionPerformed

    private void winnerCount(){
        txtDealerWin.setText(Integer.toString(dealerWin));
        txtPlayer1Win.setText(Integer.toString(player1Win));
        txtPlayer2Win.setText(Integer.toString(player2Win));
        
        // Create a text file to read from and write to for storing high scores
        try { // Use try and catch
            
            // Create a new text file to store the high scores. 
            File file = new File ("scoresrecord.txt");
            
            // Check if the file already exists
            if (!file.exists()) { // If the file doesn't, create a new one
                file.createNewFile();
            }
            
            // Declare new PrintWriter. Found the PrintWriter class online, use 
            // this to print data into the created file
            PrintWriter print = new PrintWriter (file);
            // Store each player's score
            print.println("Player 1's score:" + "\n" + player1Win);
            print.println("Player 2's score:" + "\n" + player2Win);
            // Close the file printer to prevent unwanted change
            print.close(); 
            
            // Declare new scanner
            Scanner scan = new Scanner (file);
            // While the scanner scans a next line in the text file, proceed
            while (scan.hasNextLine()) {
                try { // Use try and catch for parsing, to avoid format exceptions
                    // Declare variable, parse score from string to integer
                    int score = Integer.parseInt(scan.nextLine());   
                    // Check if players have a high score (score larger than the previously saved scores)
                    if (player1Win > score) { // If player1 has set a high score, output message
                        txtPlayerNumber.setText("Player 1 has set a new high score!");
                        txtHighScore.setText(Integer.toString(player1Win)); // Victoria's edit
                    } else if (player2Win > score) { // If player 2 has set a high score, output message
                        txtPlayerNumber.setText("Player 2 has set a new high score!");
                        txtHighScore.setText(Integer.toString(player2Win)); // Victoria's edit
                    } else { // Set the text field as empty if no new high score has been set
                        txtPlayerNumber.setText(""); 
                    }
                } catch (NumberFormatException e) { // Catch number format exceptions                    
                }
            }          
        } catch (Exception e) { // Catch any exceptions
        }      
    }
    
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        btnPlayerCard1.setVisible(true);         
        btnPlayerCard2.setVisible(true);  
        btnPlayerCard3.setVisible(true);        
        btnPlayerCard4.setVisible(true);
        btnPlayerCard5.setVisible(true);
        btnDealerCard1.setVisible(true);
        btnDealerCard2.setVisible(true);
        btnDealerCard3.setVisible(true);
        btnDealerCard4.setVisible(true);
        btnDealerCard5.setVisible(true);
        btnDealerCard6.setVisible(true);
        btnDealerCard7.setVisible(true);
        btnDealerCard8.setVisible(true);
        btnDealerCard9.setVisible(true);
        
        btnHit.setEnabled(false);
        btnStand.setEnabled(false);
        btnPlay.setEnabled(true);
        
        
        btnDealerCard1.setIcon(cardback); // sets all cards to have a cardback
        btnDealerCard2.setIcon(cardback);
        btnDealerCard3.setIcon(cardback);
        btnDealerCard4.setIcon(cardback);
        btnDealerCard5.setIcon(cardback);
        
        btnPlayerCard1.setIcon(cardbackPlayer);         
        btnPlayerCard2.setIcon(cardbackPlayer);  
        btnPlayerCard3.setIcon(cardbackPlayer);        
        btnPlayerCard4.setIcon(cardbackPlayer);
        btnPlayerCard5.setIcon(cardbackPlayer);
        
        totalpoints1 = 0;
        totalpoints2 = 0;
        c = 0;
        cs = 0;
        playCount = 0;
        dealtotalpoints = 0; // clears variables, output boxes and arrays
        totalpointsarr1.clear();
        totalpointsarr2.clear();
        totalcardarr.clear();
        dealtotalpointsarr.clear();
        dealtotalcardarr.clear();
        txtOutput.setText("");
        outputarea.setText("");
        txtSum1.setText("");
        txtSum2.setText("");
        txtPlayer.setText("");
        txtPlayerNum.setText("");
        txtPlayerNumber.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private int initialPlayerSetup(int playerNum){
        
        // Displays the first 2 cards of the player and returns the sum of those cards
        
        btnHit.setEnabled(true);
        btnStand.setEnabled(true);
        
        btnPlayerCard3.setVisible(false);
        btnPlayerCard4.setVisible(false);
        btnPlayerCard5.setVisible(false);
        
        int randomcardnum, randomcardtype, cardvalue; // defining variables
        int randmaxnum = 12;
        int randminnum = 0;
        int randmaxtype = 3;
        int randmintype = 0;
        int sum = 0;
        String cardnum1, cardnum2, cardtype;
        
        randomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // generates random number
        randomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
        cardnum1 = cardnumberarr[randomcardnum]; // adds number to a variable
        cardvalue = cardvaluearr[randomcardnum];
        cardtype = cardtypearr[randomcardtype];
        if (playerNum == 1){
             totalpointsarr1.add(cardvalue);  // adds variable into array to be called to find card number
        }else{
             totalpointsarr2.add(cardvalue);  // adds variable into array to be called to find card number
             txtPlayerNumber.setText("Next player's turn");
             txtPlayer.setText("Player 2 is playing.");
             cs++;
             playCount = 1;
        }
       
        totalcardarr.add(cardnum1);
        
        btnPlayerCard1.setIcon(icongen(cardnum1,cardtype)); 
        sum += cardvalue;
        
        randomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // generates random number
        randomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
        cardnum2 = cardnumberarr[randomcardnum]; // adds number to a variable
        cardvalue = cardvaluearr[randomcardnum];
        cardtype = cardtypearr[randomcardtype];
        if (playerNum == 1){
            totalpointsarr1.add(cardvalue);  // adds variable into array to be called to find card number
        }else{
            totalpointsarr2.add(cardvalue);  // adds variable into array to be called to find card number
        }
        
        totalcardarr.add(cardnum2);
        
        btnPlayerCard2.setIcon(icongen(cardnum2,cardtype)); 
        
        sum += cardvalue;
        
        if(cardnum1.equals("Ace") || cardnum2.equals("Ace")){
            outputarea.setText("The sum of your cards is "+ sum + " or " + (sum-10));
        }else{
            outputarea.setText("The sum of your cards is "+ sum);
        }
        
        
        return sum;
    }
    
    private void dealWinCheck(int sum){
        // Checks if the first 2 cards dealed to the player equals 21
        // Directs the code to correct path if the player did get 21
        
        int num = Integer.parseInt(txtPlayerNum.getText());
        
        if(sum == 21 && num == 1){
            // Solo play
            player1Win++;
            outputarea.setText("");
            txtOutput.setText("You have gotten 21"); 
            txtSum1.setText("21");
            c = 1;
            cs++; 
            stand(JOptionPane.YES_OPTION);
            
        }else if(sum == 21 && num == 2 && cs == 0){
            // Double player game first player 
            outputarea.setText("");
            txtOutput.setText("You have gotten 21"); 
            txtSum1.setText("21");
            c = 1;
            cs++;
            totalpoints2 = initialPlayerSetup(2);
        }else if(sum == 21 && cs!= 0){
            // Double player game second player 
            outputarea.setText("");
            txtOutput.setText("You have gotten 21"); 
            txtSum2.setText("21");
            stand(JOptionPane.YES_OPTION);
        }
        
        
    }
    
    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        // TODO add your handling code here:
        
        // Starts the game
        // Sets up cards
        
        // error checking for amount of players
        if (txtPlayerNum.getText().equals("")) {
            txtPlayer.setText("Please input amount of players above.");
            return ;
        } else {
            try { 
                int inputnum = Integer.parseInt(txtPlayerNum.getText());
                if (inputnum > 2) {
                    txtPlayer.setText("Please input 1 or 2.");
                    return ;
                }
                
            } catch (NumberFormatException | NullPointerException e) {
                txtPlayer.setText("Please input an integer");
            }
        }  

        playerNum = Integer.parseInt(txtPlayerNum.getText());
        btnPlay.setEnabled(false);
        
        
        
        btnDealerCard1.setVisible(true);
        btnDealerCard2.setVisible(true);
        btnDealerCard3.setVisible(false);
        btnDealerCard4.setVisible(false);
        btnDealerCard5.setVisible(false); 
        btnDealerCard6.setVisible(false);
        btnDealerCard7.setVisible(false);
        btnDealerCard8.setVisible(false);
        btnDealerCard9.setVisible(false);
        
        txtPlayer.setText("Player 1 is playing.");
        
        // Displaying dealer's first card
        
        int dealrandomcardnum, dealrandomcardtype, dealcardvalue; // defining variables
        String dealcardnum, dealcardtype;
        int randmaxnum = 12;
        int randminnum = 0;
        int randmaxtype = 3;
        int randmintype = 0;
        dealtotalpoints = 0;    

        dealrandomcardnum = (int)Math.floor(Math.random()*(randmaxnum-randminnum+1)+randminnum); // get random number and card type
        dealrandomcardtype = (int)Math.floor(Math.random()*(randmaxtype-randmintype+1)+randmintype);
        dealcardnum = cardnumberarr[dealrandomcardnum];  // save it to a variable
        dealcardvalue = cardvaluearr[dealrandomcardnum];
        dealcardtype = cardtypearr[dealrandomcardtype];
        dealtotalpointsarr.add(dealcardvalue); // add into array for further use
        dealtotalcardarr.add(dealcardnum);    
        
        btnDealerCard1.setIcon(icongen(dealcardnum,dealcardtype));
        
        // Displaying player's first 2 cards
      
        totalpoints1 = initialPlayerSetup(1);
        
        dealWinCheck(totalpoints1);
    }//GEN-LAST:event_btnPlayActionPerformed
 
    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        // TODO add your handling code here:
        JTextArea textArea = new JTextArea( // taken frmo https://stackoverflow.com/questions/8375022/joptionpane-and-scroll-function)
            "~ Objective ~\n" +
"As the player of the game, your objective will be to either achieve a sum of 21 or reach 5 cards with a sum less than 21. Sums are calculated using the face value of each card, with exceptions for ace, jack, queen, and king, which will be explained in the “How to Play” section.The purpose of this application is for users to understand and play BlackJack from the comfort of their own homes\n" +
"\n" +
"~ Installation Guide ~\n" +
"Step 1: Download the program’s zipped folder\n" +
"Step 2: Unzip the program\n" +
"Step 3: Open the program using Netbeans 8.2\n" +
"Step 4: Run the program, using the run button or F6\n" +
"\n" +
"~ How to Play ~\n" +
"Each turn, the player is dealt 1 card, and the dealer is dealt two cards. The dealer flips their first card over, and then the player flips their card over. Based on the card received, the player can choose the action hit or stand, which is completed using the associated button in the application. Choosing hit deals the player another card, whereas stand means that the player will no longer receive any cards, and the dealer will begin flipping their cards. \n" +
"It is important to note that an ace, which has the symbol A, can either have the value of 1 or 11. Additionally, the face cards (jack, queen, and king) all have the value of 10. \n" +
"As the player, the goal is to achieve a sum of 21 or attain a 5 Card Charlie, which is having 5 cards with a sum less than 21. Either of these allows the player to win the round. The dealer is forced to hit until they have a sum of 17 or more, at which point they will stand and can win the round depending on the sum of their cards. To win as the player, you must either achieve a sum of 21 or have a 5 Card Charlie. The scoreboard in the middle of the application will display the points that the player and dealer have each collected. For further instructions, please click the tutorial button\n" +
"\n" +
"~ Game Modifications ~\n" +
"For a more exciting game, there will be modifications. If the player gets either a 21, or a 5 Card Charlie, the player will automatically win without the dealer having to flip any of their cards. This allows for the player to have an advantage, and is a fresh new way to play to allow beginners to get a taste of victory! \n" +
"\n" +
"~ Key Features ~\n" +
"In this Blackjack application, the dealer’s cards are located in the top half of the screen, while the player’s hand is in the lower half. Additionally, the dealer’s cards are shown with a smaller size. Between the two sets of cards is the scoreboard, which lists the number of times each player has won.Directly below the player’s hand are the two options to either hit or stand during the player’s turn.\n" +
"You also have the ability to choose how many players you want to play at once (1 player or two players) by inputting the number in the “Enter Player Number” text box. \n" +
"Lastly, the exit and help buttons are at the bottom of the application, as well as an area where messages are outputted to users. The exit button allows users to exit the application at any time. Clicking the Help button will show this page of instructions and can assist users with remembering the game rules. The messages outputted will include notifications of actions performed, error messages, congratulation messages, and more.\n" +
"\n" +
"~ Age Requirement ~\n" +
"Before the game begins, users will be asked to input their age. Depending on whether they are legal (18 or over), the game will proceed or quit. \n" +
"\n" +
"~ Help & Tutorial ~\n" +
"Please use the Help button located at the bottom right of the application to access this page at any time during the game. For direct further instructions, please click the tutorial button. \n" +
"\n" +
"Our team wishes you the best of luck!");
    JScrollPane scrollPane = new JScrollPane(textArea);  
    textArea.setLineWrap(true);  
    textArea.setWrapStyleWord(true); 
    scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
    JOptionPane.showMessageDialog(null, scrollPane, "Help Page",  
                                       JOptionPane.INFORMATION_MESSAGE, tutoriallayout);
    }//GEN-LAST:event_btnHelpActionPerformed

    private void btnTutorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutorialActionPerformed
        // TODO add your handling code here:
        JTextArea textArea = new JTextArea( 
            "~ Tutorial ~\n" +
"\n" +
"Please note, if you do not understand any of the terms, please refer to the reference manual, located below. \n" +
"\n" +
"Objective: As the player of the game, your objective will be to either achieve a sum of 21 or reach 5 cards with a sum less than 21. Sums are calculated using the face value of each card, with exceptions for ace, jack, queen, and king which are valued at 10. \n" +
"\n" +
"Step 1: Before playing the game, please locate the “Enter Player Number” field, and input either 1 or 2, depending on the number of players you have. \n" +
"\n" +
"Step 2: Hit the “Play” button to start the game. This will result in two cards for the player being flipped. \n" +
"\n" +
"Step 3: On the bottom left, there should be a number in the Player 1 sum box. The goal of the game is to get this sum as close as possible to 21, without going over.\n" +
"\n" +
"Step 4: Depending on the number in the Player 1 sum box, you can either choose to click the “Hit” button to receive another card, whose value will be added onto your current sum. Or, you can click stand, where you will stop receiving any cards, and ensures your sum will stay the same. Please note that if you go past 21, you will automatically lose. \n" +
"\n" +
"Step 5: After you have decided to click stand and have not yet busted, either Player 2 will be given 2 cards, where you will repeat the same process as Player 1, or the dealer’s cards will automatically generate. Depending on the dealer’s cards, you will win or lose the game. \n" +
"\n" +
"Step 6: If you wish to play again, click the reset button. If not, please click the exit button to leave. \n" +
"\n" +
"-------\n" +
"\n" +
"~ Reference Manual ~\n" +
"\nDealer cards: Located at the top of the screen with a green card back, they will show the dealers cards. \n" +
"\nDealer, Player 1 and Player 2 wins (Win Output): Located in the middle of the screen, this will constantly update with the amount of wins the dealer has, and each player has. \n" +
"\nEnter player number: Located near the top right corner of the screen, this JTextField allows users to list whether they have 1 or 2 players. \n" +
"\nExit Button: Exits program\n" +
"\nHelp button: Returns a JOptionPane detailing the objective, installation guide, how to play, game modifications, layout, and the age requirement.   \n" +
"\nHit button: Located at the bottom middle of the screen, clicking it will give the player a card, and increase the sum of a players cards. \n" +
"\nPlayer cards: Located above the play, hit, stand and reset buttons, this will show what cards the player has received\n" +
"\nPlayer one and two sum box: Located on the bottom left corner, it will display the sum of the player's cards. You must be careful not to let it go past 21, or else you will automatically lose the game. \n" +
"\nReset button: Resets the game to its original state, allowing users to play again. \n" +
"\nStand button: Will ensure that the player does not receive any more cards, and the sum will no longer change. ");
    JScrollPane scrollPane = new JScrollPane(textArea);  
    textArea.setLineWrap(true);  
    textArea.setWrapStyleWord(true); 
    scrollPane.setPreferredSize( new Dimension( 500, 500 ) );
    JOptionPane.showMessageDialog(null, scrollPane, "Tutorial Page",  
                                       JOptionPane.INFORMATION_MESSAGE,tutoriallayout);
        
    }//GEN-LAST:event_btnTutorialActionPerformed

    /**
     * @param args the command line arguments
     */
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Blackjack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Blackjack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Blackjack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Blackjack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Blackjack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDealerCard1;
    private javax.swing.JButton btnDealerCard2;
    private javax.swing.JButton btnDealerCard3;
    private javax.swing.JButton btnDealerCard4;
    private javax.swing.JButton btnDealerCard5;
    private javax.swing.JButton btnDealerCard6;
    private javax.swing.JButton btnDealerCard7;
    private javax.swing.JButton btnDealerCard8;
    private javax.swing.JButton btnDealerCard9;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnHit;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnPlayerCard1;
    private javax.swing.JButton btnPlayerCard2;
    private javax.swing.JButton btnPlayerCard3;
    private javax.swing.JButton btnPlayerCard4;
    private javax.swing.JButton btnPlayerCard5;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnStand;
    private javax.swing.JButton btnTutorial;
    private javax.swing.JLabel lblDealerWin;
    private javax.swing.JLabel lblHighScore;
    private javax.swing.JLabel lblImage1;
    private javax.swing.JLabel lblImage2;
    private javax.swing.JLabel lblPlayer1Win;
    private javax.swing.JLabel lblPlayer2Win;
    private javax.swing.JLabel lblPlayerNum;
    private javax.swing.JLabel lblSum1;
    private javax.swing.JLabel lblSum2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextField outputarea;
    private javax.swing.JPanel panelColour;
    private javax.swing.JTextField txtDealerWin;
    private javax.swing.JTextField txtHighScore;
    private javax.swing.JTextField txtOutput;
    private javax.swing.JTextField txtPlayer;
    private javax.swing.JTextField txtPlayer1Win;
    private javax.swing.JTextField txtPlayer2Win;
    private javax.swing.JTextField txtPlayerNum;
    private javax.swing.JTextField txtPlayerNumber;
    private javax.swing.JTextField txtSum1;
    private javax.swing.JTextField txtSum2;
    // End of variables declaration//GEN-END:variables
}
