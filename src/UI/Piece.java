/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.ImageIcon;

/**
 *
 * @author saksham
 */
public enum Piece {
    WHITE("/images/white.png",1,"whiteEx"),BLACK("/images/black.png",0,"blackEx");
    public ImageIcon image;
    public int intmap;
    public String executableName;
    Piece(String path,int intmap,String executableName)
    {
            this.image=new ImageIcon(this.getClass().getResource(path));
            this.intmap=intmap;
            this.executableName=executableName;
    }
}
