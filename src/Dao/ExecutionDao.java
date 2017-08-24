/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import POJO.Move;
import java.io.File;

/**
 *
 * @author saksham
 */
public interface ExecutionDao {
    public Move startExecution(int[][] state, int height, int width, File file, int timelimit,int player);
    public int compile(File file,int player);
}
