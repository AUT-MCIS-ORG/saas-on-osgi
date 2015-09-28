/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.ui;

import com.sa.osgi.system.*;

/**
 *
 * @author mao
 */
public class DefaultUIService implements UIService{

    public String getBackgroundColor() {
        return "blue";
    }

    public String getDateFormat() {
        return "mm/dd/yyyy";
    }
    
}

