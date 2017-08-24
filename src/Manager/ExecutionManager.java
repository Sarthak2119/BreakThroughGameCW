/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manager;

import POJO.Move;
import UI.Piece;
import configuration.BoardConfig;
import java.io.File;
import javax.swing.JPanel;

/**
 *
 * @author saksham
 */
public abstract class ExecutionManager {
    public  int[][] generateStateMatrix(int height,int width)
    {
        int state[][] = new int[height][width];
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                    if(i==height-2 || i==height-1){
                        state[i][j]=Piece.BLACK.intmap;
                    }
                    else
                        if(i==0 || i==1){
                            state[i][j]=Piece.WHITE.intmap;
                        }
                        else
                            state[i][j]=-1;
            }
        }
        return state;
    }
    public abstract Move startExecution(int state[][],int height,int width,File file,int timelimit,int player);
    public abstract int[][] generateFinalStateMatrix(int state[][],int height,int width,Move move);
    public abstract boolean isValidMove(Move move,int state[][],int height,int width,int player);
    public abstract boolean gameHasEnded(int state[][],int height,int width);
    public abstract int compile(File file,int player);
}
