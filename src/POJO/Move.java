/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package POJO;

/**
 *
 * @author saksham
 */
public class Move {
    private int initialx;
    private int initialy;

    public void setInitialx(int initialx) {
        this.initialx = initialx;
    }

    public void setInitialy(int initialy) {
        this.initialy = initialy;
    }

    public void setFinalx(int finalx) {
        this.finalx = finalx;
    }

    public void setFinaly(int finaly) {
        this.finaly = finaly;
    }
    private int finalx;
    private int finaly;

    public int getInitialx() {
        return initialx;
    }

    public int getInitialy() {
        return initialy;
    }

    public int getFinalx() {
        return finalx;
    }

    public int getFinaly() {
        return finaly;
    }
    
}
