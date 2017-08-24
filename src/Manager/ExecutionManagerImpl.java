/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import Dao.CplusplusExecutionDaoImpl;
import Dao.ExecutionDao;
import POJO.Move;
import UI.DialogBox;
import UI.Piece;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author saksham
 */
public class ExecutionManagerImpl extends ExecutionManager {

    public ExecutionDao executionDao;
    private int dx[]={1,1,1};
    private int dy[]={0,-1,1};
    
    public ExecutionManagerImpl()
    {
        executionDao = new CplusplusExecutionDaoImpl();
    }
    @Override
    public Move startExecution(int[][] state, int height, int width, File file, int timelimit,int player) {
       Move move=executionDao.startExecution(state, height, width, file, timelimit,player);
       if(move == null )
           return null;
    //    System.out.println(move.getInitialx()+" "+move.getInitialy()+" "+move.getFinalx()+" "+move.getFinaly());
       if(isValidMove(move, state, height, width,player)){
           generateFinalStateMatrix(state, height, width, move);
           return move;
       }
       String actualPlayer;
       if(player == Piece.WHITE.intmap)
           actualPlayer="White";
       else
           actualPlayer = "Black";
       DialogBox.showDialog("Invalid move by "+actualPlayer);
       return null;
    }

    /*
        This method generates the final matrix assuming the move is valid.
    */
    @Override
    public int[][] generateFinalStateMatrix(int[][] state, int height, int width, Move move) {
        int temp=state[move.getInitialx()][move.getInitialy()];
        state[move.getFinalx()][move.getFinaly()]=temp;  // moving to final cell
        state[move.getInitialx()][move.getInitialy()]=-1;  //Old cell is now empty
        return state;
    }

    @Override
    public boolean isValidMove(Move move, int[][] state, int height, int width,int player) {
        
        

        int initialx=move.getInitialx();
        int initialy=move.getInitialy();
        int finalx=move.getFinalx();
        int finaly =move.getFinaly();
       if(inBounds(initialx, initialy, height, width) && inBounds(finalx,finaly, height, width))
       {
           if(state[initialx][initialy]==player)
           {
               if(state[finalx][finaly]==player){
                 //  System.out.println("Invalid move");
                   return false;
               }
               if(state[finalx][finaly]==-1)
                    return true;
                 int diffx=finalx-initialx;
                 int diffy=finaly - initialy;
                   if(player == Piece.WHITE.intmap)
                   {
                     
                        if(diffx == 1 && ( diffy == -1 || diffy == 1))
                            return true;
                   }
                   else
                   {
                         if(diffx == -1 && ( diffy == -1 || diffy == 1))
                            return true;
                   }
           }
       }
     //    System.out.println("Invalid move");
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean gameHasEnded(int[][] state, int height, int width) {
        
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                if(i==0)
                    if(state[i][j]==Piece.BLACK.intmap){
                        DialogBox.showDialog("Black Wins");
                        return true;
                    }
                if(i==7)
                    if(state[i][j]==Piece.WHITE.intmap){
                        DialogBox.showDialog("White Wins");

                        return true;
                    }
            }
        }
       return false; //To change body of generated methods, choose Tools | Templates.
    }
    private boolean inBounds(int x,int y,int height,int width)
    {
        if(x>=0 &&x<height && y>=0 && y<width)
            return true;
        return false;
    }

    @Override
    public int  compile(File file, int player) {
       return executionDao.compile(file, player);
    }
    
}
