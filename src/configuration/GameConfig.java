/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package configuration;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author saksham
 */
public class GameConfig {
 
    public static ArrayList time_limits;
    public static ArrayList sleep_times;
    public static int defaultTimeLimit;
    public static int defaultSleepTime;
    public GameConfig()
    {
        time_limits = new ArrayList<Integer>();
        sleep_times = new ArrayList<Integer>();
        time_limits.add(1);
        time_limits.add(2);
        sleep_times.add(0);
        sleep_times.add(1);
        sleep_times.add(2);
        defaultSleepTime=1;
        defaultTimeLimit=2;
    }
}
