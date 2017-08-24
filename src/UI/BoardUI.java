/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import Manager.ExecutionManager;
import Manager.ExecutionManagerImpl;
import POJO.Move;
import configuration.BoardConfig;
import configuration.GameConfig;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author saksham
 */
public class BoardUI extends JFrame {

    /**
     * Creates new form BoardUI
     * -1 for empty cell
     * 0 Black
     * 1 White
     */
    private final GameConfig gameConfig;
    private int timeLimit;
    private int sleepTime;
    private BoardConfig boardConfig;
    private JPanel boardCells[][];
    private JFileChooser fileChooser;
    private File black;
    private File white;
    private int state[][];
    private int stateLogs[][][];
    private DefaultListModel movesLogListModel;
    private int moveIndex;
    private boolean isStopActive;
    private Thread gameThread;
    private volatile boolean isGameThreadActive;
    public ExecutionManager executionManager;

    public BoardUI() {
        initComponents();
        moveIndex = 0;
        isStopActive = false;
        fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        boardConfig = new BoardConfig();
        boardCells=new JPanel[boardConfig.HEIGHT][boardConfig.WIDTH];
        stateLogs = new int[500][boardConfig.HEIGHT][boardConfig.WIDTH];
        gameConfig = new GameConfig();
        this.board.setLayout(new GridLayout(boardConfig.HEIGHT, boardConfig.WIDTH));
        sleepTimeTextField.setText(Integer.toString(gameConfig.defaultSleepTime));
        timeLimitTextField.setText(Integer.toString(gameConfig.defaultTimeLimit));
        //TODO set the Execution manager
        blackFileTextFiled.setEditable(false);
        whiteFileTextField.setEditable(false);
//        playButton.setEnabled(false);
        newGameButton.setEnabled(false);
        newGameButton.setSize(93, 29);  
        
        movesLogListModel = new DefaultListModel();
        movesLogList.setModel(movesLogListModel);
        movesLogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setMovesLogSelectionListener();
        
//        moves.setForeground(Color.black);
//        moves.setFont(new Font(Font.SANS_SERIF,Font.PLAIN, 20));
        executionManager=new ExecutionManagerImpl();
        state=executionManager.generateStateMatrix(boardConfig.HEIGHT, boardConfig.WIDTH);
        this.generateInitialBoard();
       // System.out.println(this.board.getSize());
      //  for (int i = 0; i < gameConfig.time_limits.size(); i++) {
        //    timeLimitSelector.addItem(gameConfig.time_limits.get(i));
       // }
     /*   for (int i = 0; i < gameConfig.sleep_times.size(); i++) {
            sleepSelector.addItem(gameConfig.sleep_times.get(i));
        }*/
       
       // this.board.setPreferredSize(new Dimension(812,812));
        // this.board.setSize(new Dimension(9512,1512));
    }
    
    // This function add current state in statesLog array
    public void addStateInLog() {
        for(int i=0;i<boardConfig.HEIGHT;i++) {
            for(int j=0;j<boardConfig.WIDTH;j++) {
                stateLogs[moveIndex][i][j] = state[i][j];
            }
        }
    }
    
    public void generateInitialBoard()
    {
        for (int i = 0; i < boardConfig.HEIGHT; i++) {
            for (int j = 0; j < boardConfig.WIDTH; j++) {
                 boardCells[i][j] = new JPanel(new BorderLayout());
                JPanel panel=boardCells[i][j];
                if ((i + j + 1) % 2 == 0) {
                    panel.setBackground(boardConfig.getFirstColor());
                } else {
                    panel.setBackground(boardConfig.getSecondColor());
                }
                if(state[i][j]== Piece.BLACK.intmap)
                {
                    drawPiece(panel, Piece.BLACK.image);
                   
                }
                if(state[i][j]== Piece.WHITE.intmap)
                {
                    drawPiece(panel, Piece.WHITE.image);
                }
                this.board.add(panel);
            }
        }
    }
    public void drawPiece(JPanel panel,ImageIcon image)
    {
         JLabel label = new JLabel("", image, JLabel.CENTER);
         panel.add( label, BorderLayout.CENTER );
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        board = new javax.swing.JPanel();
        newGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        playButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        blackFile = new javax.swing.JButton();
        whiteFile = new javax.swing.JButton();
        blackFileTextFiled = new javax.swing.JTextField();
        whiteFileTextField = new javax.swing.JTextField();
        timeLimitTextField = new javax.swing.JTextField();
        sleepTimeTextField = new javax.swing.JTextField();
        movesLogScrollPane = new javax.swing.JScrollPane();
        movesLogList = new javax.swing.JList<>();
        moveLogLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        board.setBackground(new java.awt.Color(241, 244, 238));
        board.setPreferredSize(new java.awt.Dimension(980, 867));

        javax.swing.GroupLayout boardLayout = new javax.swing.GroupLayout(board);
        board.setLayout(boardLayout);
        boardLayout.setHorizontalGroup(
            boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boardLayout.setVerticalGroup(
            boardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 640, Short.MAX_VALUE)
        );

        newGameButton.setText("New Game");
        newGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });

        jLabel1.setText(" Time Limit");

        playButton.setText("Play");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Sleep time");

        blackFile.setText("Black");
        blackFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blackFileActionPerformed(evt);
            }
        });

        whiteFile.setText("White");
        whiteFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                whiteFileActionPerformed(evt);
            }
        });

        movesLogList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        movesLogScrollPane.setViewportView(movesLogList);

        moveLogLabel.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        moveLogLabel.setText("Move Log");
        moveLogLabel.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(moveLogLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(movesLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newGameButton)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeLimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(sleepTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(blackFile, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(blackFileTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(whiteFile, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(whiteFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(board, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newGameButton)
                    .addComponent(playButton)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeLimitTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(sleepTimeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(blackFile)
                    .addComponent(blackFileTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(whiteFile)
                    .addComponent(whiteFileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(moveLogLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(movesLogScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(board, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void setMovesLogSelectionListener() {
        JFrame thisJFrame = this;
        movesLogList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent movesLogSelectionEvent) {
                int selectionIndex = movesLogList.getSelectedIndex();
                
                if(selectionIndex == -1 || isGameThreadActive) {
                    return;
                }
                
                for(int i=0;i<boardConfig.HEIGHT;i++)
                {
                    for(int j=0;j<boardConfig.WIDTH;j++)
                    {
                         boardCells[i][j].removeAll();
                         boardCells[i][j].revalidate();
                         boardCells[i][j].repaint();
                         if(stateLogs[selectionIndex][i][j]==Piece.BLACK.intmap)
                             drawPiece(boardCells[i][j], Piece.BLACK.image);
                         if(stateLogs[selectionIndex][i][j]==Piece.WHITE.intmap)
                             drawPiece(boardCells[i][j],Piece.WHITE.image);
                    }
                }
                thisJFrame.pack();
                thisJFrame.setVisible(true);
           }
        });
    }
    
    private void stopGame() {
        isStopActive = false;
        isGameThreadActive = false;
        playButton.setText("Play");
        playButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }
    
    private void newGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameButtonActionPerformed
        
        movesLogList.addListSelectionListener(null);
        System.out.println("##############################NEW GAME#####################################");
        state=executionManager.generateStateMatrix(boardConfig.HEIGHT, boardConfig.WIDTH);
        moveIndex = 0;
        for(int i=0;i<boardConfig.HEIGHT;i++)
        {
            for(int j=0;j<boardConfig.WIDTH;j++)
            {
                 boardCells[i][j].removeAll();
                 boardCells[i][j].revalidate();
                 boardCells[i][j].repaint();
                 if(state[i][j]==Piece.BLACK.intmap)
                     drawPiece(boardCells[i][j], Piece.BLACK.image);
                 if(state[i][j]==Piece.WHITE.intmap)
                     drawPiece(boardCells[i][j],Piece.WHITE.image);
            }
        }
        this.pack();
        this.setVisible(true);
        
        // Clearing the moves log list before starting new game
        movesLogListModel = new DefaultListModel();
        movesLogList.setModel(movesLogListModel);
        movesLogList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
         playButton.setEnabled(true);
         blackFile.setEnabled(true);
         whiteFile.setEnabled(true);
//         timeLimitSelector.setEnabled(true);
//         sleepSelector.setEnabled(true);
         timeLimitTextField.setEditable(true);
         sleepTimeTextField.setEditable(true);
         newGameButton.setEnabled(false);
    //    System.out.println("New Game pressed");
    }//GEN-LAST:event_newGameButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        // TODO add your handling code here:
        if(isStopActive) {
            isStopActive = false;
            stopGame();
            return;
        }
     //   System.out.println(Thread.currentThread());
     //   System.out.println("Game has commenced");
      //  System.out.println("Sleep time is "+sleepTime);
     //   System.out.println("Time Limit is "+timeLimit);
        blackFile.setEnabled(false);
        whiteFile.setEnabled(false);
        playButton.setEnabled(false);
        timeLimit = (int) (Double.parseDouble(timeLimitTextField.getText())*1000);
        sleepTime =(int)(Double.parseDouble(sleepTimeTextField.getText())*1000);
        timeLimitTextField.setEditable(false);
        sleepTimeTextField.setEditable(false);
 //       timeLimitSelector.setEnabled(false);
//        sleepSelector.setEnabled(false);
       //timeLimitSelector.setEditable(false);
        int exitCode=executionManager.compile(white, Piece.WHITE.intmap);
        if(exitCode!=0)
        {
            return;
        }
        exitCode=executionManager.compile(black, Piece.BLACK.intmap);
        if(exitCode!=0){
            return;
        }
        gameThread = new Thread(new Runnable(){
          public void run(){
              playButton.setText("Stop");
              isStopActive = true;
              isGameThreadActive = true;
              playButton.setEnabled(true);
             int turn=0;
                while(isGameThreadActive)
                {
                //    System.out.println("Here is the  Move");
                    Move outMove;
                    if(turn%2==0){
                        displayInput("White",Piece.WHITE.intmap);
                  //      System.out.println("White turn "+turn);
                        outMove= executionManager.startExecution(state, boardConfig.HEIGHT, boardConfig.WIDTH,white,timeLimit,Piece.WHITE.intmap);
                       if(outMove == null){
                           newGameButton.setEnabled(true);
                           playButton.setEnabled(true);
                           blackFile.setEnabled(true);
                           whiteFile.setEnabled(true);
                           break;
                       }
                        System.out.println("White move: "+outMove.getInitialx()+" "+outMove.getInitialy()+" "+outMove.getFinalx()+" "+outMove.getFinaly()+"\n");
                        movesLogListModel.addElement("White: "+outMove.getInitialx()+" "+outMove.getInitialy()+" "+outMove.getFinalx()+" "+outMove.getFinaly()+"\n");
                        addStateInLog();
                    }
                    else
                    {
                        displayInput("Black",Piece.BLACK.intmap);
                         outMove=executionManager.startExecution(state, boardConfig.HEIGHT,  boardConfig.WIDTH, black, timeLimit,Piece.BLACK.intmap);
                        if(outMove == null){
                          newGameButton.setEnabled(true);
                            break;
                        }
                        System.out.println("Black move: "+outMove.getInitialx()+" "+outMove.getInitialy()+" "+outMove.getFinalx()+" "+outMove.getFinaly()+"\n");
                        movesLogListModel.addElement("Black: "+outMove.getInitialx()+" "+outMove.getInitialy()+" "+outMove.getFinalx()+" "+outMove.getFinaly()+"\n");
                        addStateInLog();
                    }
                      //  moves.append("fuckn\n");
                    turn++;
                    
                    try {
                        updateBoard(outMove);
                      //    break;
                        if(executionManager.gameHasEnded(state,boardConfig.HEIGHT,boardConfig.WIDTH)){
                            isStopActive = false;
                            newGameButton.setEnabled(true);
                             break;
                        }
                                
                    } catch (Exception ex) {
                        isStopActive = false;
                        newGameButton.setEnabled(true);
                        break;
                      //  Logger.getLogger(BoardUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        //TODO manage input/output to the different players.
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BoardUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    moveIndex++;
               //     turn=(turn+1)%2;
                }
     //         System.err.println(Thread.currentThread());     
          }
      });
        gameThread.start();
      //  System.out.println("Play thread satrt "+Thread.currentThread());
    }//GEN-LAST:event_playButtonActionPerformed
    private void updateBoard(Move move) throws Exception
    {
       // board.removeAll();
        try{
              boardCells[move.getInitialx()][move.getInitialy()].removeAll();
              boardCells[move.getInitialx()][move.getInitialy()].revalidate();
              boardCells[move.getInitialx()][move.getInitialy()].repaint();
              int finalx=move.getFinalx();
              int finaly=move.getFinaly();
              boardCells[finalx][finaly].removeAll();
              boardCells[finalx][finaly].revalidate();
              boardCells[finalx][finaly].repaint();
              if(state[finalx][finaly]==Piece.WHITE.intmap)
                  drawPiece(boardCells[finalx][finaly],Piece.WHITE.image);
              if(state[finalx][finaly]==Piece.BLACK.intmap)
                  drawPiece(boardCells[finalx][finaly],Piece.BLACK.image );
        this.pack();
        this.setVisible(true);
        }
        catch(Exception e)
        {
            throw new Exception("Some Exception in updating the board");
        }
        
    }
    private void displayInput(String player,int playerIntMap)
    {
            System.out.println(player+" turn#");
            System.out.println(playerIntMap);
//            System.out.println(BoardConfig.HEIGHT + " " + BoardConfig.WIDTH);
            for(int i=0;i<boardConfig.HEIGHT;i++)
            {
                for(int j=0;j<boardConfig.WIDTH;j++)
                {
                    System.out.print(state[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
    }
    private void blackFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blackFileActionPerformed
        // TODO add your handling code here:
        
        fileChooser.showOpenDialog(this);
        black =fileChooser.getSelectedFile();
        if(black == null)
        {
            DialogBox.showDialog("Please choose File for Black");
            return;
        }
        blackFileTextFiled.setText(black.getName());
        if(black != null && white != null)
            playButton.setEnabled(true);
       // System.out.println(black.getName());
    }//GEN-LAST:event_blackFileActionPerformed

    private void whiteFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_whiteFileActionPerformed
        // TODO add your handling code here:
        fileChooser.showOpenDialog(this);
        white =fileChooser.getSelectedFile();
        if(white == null)
        {
            DialogBox.showDialog("Please choose File for White");
            return;
        }
        whiteFileTextField.setText(white.getName());
        if(black != null && white != null)
            playButton.setEnabled(true);
    }//GEN-LAST:event_whiteFileActionPerformed

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
            java.util.logging.Logger.getLogger(BoardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BoardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BoardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BoardUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
      //  System.err.println(Thread.currentThread());
            java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BoardUI frame = new BoardUI();
                frame.setTitle("BreakThrough");
           //     frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //    frame.setSize(new Dimension(900,900));
            frame.setResizable(false);
            frame.setVisible(true);
            }
        });
          //System.out.println(Thread.currentThread());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blackFile;
    private javax.swing.JTextField blackFileTextFiled;
    private javax.swing.JPanel board;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel moveLogLabel;
    private javax.swing.JList<String> movesLogList;
    private javax.swing.JScrollPane movesLogScrollPane;
    private javax.swing.JButton newGameButton;
    private javax.swing.JButton playButton;
    private javax.swing.JTextField sleepTimeTextField;
    private javax.swing.JTextField timeLimitTextField;
    private javax.swing.JButton whiteFile;
    private javax.swing.JTextField whiteFileTextField;
    // End of variables declaration//GEN-END:variables
}
