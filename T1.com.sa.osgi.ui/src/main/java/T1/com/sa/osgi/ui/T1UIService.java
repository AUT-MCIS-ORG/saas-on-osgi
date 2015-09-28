/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T1.com.sa.osgi.ui;

import com.sa.osgi.system.*;

/**
 *
 * @author mao
 */
public class T1UIService implements UIService{

    public String getBackgroundColor() {
        return "black";
    }

    public String getDateFormat() {
        return "yyyy-mm-dd";
    }
    
}

