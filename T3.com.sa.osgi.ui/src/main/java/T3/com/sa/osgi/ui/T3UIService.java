/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T3.com.sa.osgi.ui;

import com.sa.osgi.system.*;

/**
 *
 * @author mao
 */
public class T3UIService implements UIService{

    public String getBackgroundColor() {
        return "DarkOrange";
    }

    public String getDateFormat() {
        return "yyyy-MM-dd hh:mm:ss z";
    }
    
}

