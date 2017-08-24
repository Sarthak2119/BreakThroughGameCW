/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import POJO.Move;
import UI.DialogBox;
import UI.Piece;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saksham
 */ 
public class CplusplusExecutionDaoImpl implements ExecutionDao{

    /*
        contains actual implementation for extraction of move
    */
    private Process p;
    @Override
    public Move startExecution(int[][] state, int height, int width, File file, int timelimit,int player) {
                    //Dummy implementation
            try{
           File outputFile= generateOutputFile(file,state,height,width,timelimit,player);
           if(outputFile == null)
               return null;
      //      File outputFile = new File(file.getParent()+"/WHITEOUT");
            FileInputStream fis = new FileInputStream(outputFile);
            BufferedReader bis = new BufferedReader(new InputStreamReader(fis));
            String line=bis.readLine();
            String integers[]=line.split("\\s+");
            String actualPlayer;
            if(player == Piece.WHITE.intmap)
                actualPlayer = "White";
            else
                actualPlayer = "Black";
            if(integers.length !=4)
            {
                DialogBox.showDialog("Invalid move by "+actualPlayer);
                return null;
            }
            Move move = new Move();
            move.setInitialx(Integer.parseInt(integers[0]));
            move.setInitialy(Integer.parseInt(integers[1]));
            move.setFinalx(Integer.parseInt(integers[2]));
            move.setFinaly(Integer.parseInt(integers[3]));
            return move;
            
            //return null;
        } catch (IOException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
            return null;
    }
    @Override
    public int compile(File file,int player)
    {
         int processExitCode=0;
        try {
           //System.out.println(cmd + " \t"+file.getParent());
            File parent=new File(file.getParent());
            Runtime rt=Runtime.getRuntime();
            String exName;
            if(player == Piece.WHITE.intmap)
                exName=Piece.WHITE.executableName;
            else
                exName=Piece.BLACK.executableName;
            p=rt.exec("g++ "+ file.getName()+" -o "+exName, null,parent);
            p.waitFor();
                processExitCode =p.exitValue();
            if(processExitCode!=0){
                //TODO Display JFrame and exit with error
                DialogBox.showDialog("Compilation Error "+exName);
               return p.exitValue();
            //        System.out.println(p.exitValue() +" Compilation error");
            }
        } catch (IOException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return processExitCode;
    }
    public File generateOutputFile(File file,int state[][],int height,int width,int timelimit,int player) throws IOException, InterruptedException
    {
         String exName;
            if(player == Piece.WHITE.intmap)
                exName=Piece.WHITE.executableName;
            else
                exName=Piece.BLACK.executableName;
        File outputFile = new File(file.getParent()+"/OUT");
        File inputFile=generateInputFile(state, height, width, player, file);
        ProcessBuilder ps= new ProcessBuilder(file.getParent()+"/"+exName);
        ps.redirectInput(inputFile);
        ps.redirectOutput(outputFile);
        ps.redirectErrorStream(true);
        Process outProcess=ps.start();
            //Thread.sleep(timelimit*1000);   //add time limit here
    //    System.err.println(timelimit);
            if(!outProcess.waitFor(timelimit, TimeUnit.MILLISECONDS))
            {
                outProcess.destroy();
                if(player == Piece.BLACK.intmap)
                      DialogBox.showDialog("Time limit Exceeded by Black");
                else
                      DialogBox.showDialog("Time limit Excedded by White");
                return null;
            }
            int exitCode=outProcess.exitValue();
            if(exitCode != 0)
            {
                  if(player == Piece.BLACK.intmap)
                      DialogBox.showDialog("Runtime Error Occurred on Black");
                else
                      DialogBox.showDialog("Runtime Error Occurred on White");
            //    DialogBox.showDialog("Runtime Error Occurred");
                
              //  System.out.println("Runtime error encountered");
                return null;
                //TERMINATE
            }
        
        FileReader fis = new FileReader(outputFile);
        BufferedReader br = new BufferedReader(fis);
     /*   String line="";
        while((line=br.readLine())!=null)
        {
            System.out.println(line);
        }*/
        return outputFile;
    }
    public File generateInputFile(int state[][],int height,int width,int turn,File file)
    {
        File inputFile = new File(file.getParent()+"/IN");
        PrintWriter writer=null;
        try {
            writer = new PrintWriter(inputFile.getPath(), "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        writer.println(turn);
        //writer.println(height +" "+ width);
        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                writer.print(state[i][j]+" ");
            }
            writer.println();
        }
          writer.close();
    /*      System.err.println("Input file###->");
           FileReader fis=null;
        try {
            fis = new FileReader(inputFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fis);
        String line="";
        try {
            while((line=br.readLine())!=null)
            {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(CplusplusExecutionDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
          return inputFile;
    }
    
}
