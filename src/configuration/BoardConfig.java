/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.awt.Color;



/**
 *
 * @author saksham
 */
public class BoardConfig {
    
    public static final  int WIDTH=8;
    public static final  int HEIGHT=8;
    private static Color firstColor;
    private static Color secondColor;
    public static final int totalMoves=5000;
    public BoardConfig()
    {
        firstColor=new java.awt.Color(201,111,47);
        secondColor=new java.awt.Color(248,175,124);
    }
    
    //public static final Color BLACK= Color.BLACK;
   // public static final Color WHITE = Color.WHITE;

    public static Color getFirstColor() {
        return firstColor;
    }

    public static Color getSecondColor() {
        return secondColor;
    }


    
}
