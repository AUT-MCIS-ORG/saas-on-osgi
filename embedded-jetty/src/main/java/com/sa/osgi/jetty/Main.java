/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.jetty;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author mao
 */
public class Main {

    public static void main(String[] args) {
        //1. Creating the server on port 8080
		Server server = new Server(8080);

		//2. Creating the WebAppContext for the created content
//		WebAppContext ctx = new WebAppContext();
//		ctx.setResourceBase("src/main/webapp");

                WebAppContext webapp = new WebAppContext();
                webapp.setContextPath("/");
                webapp.setWar("target/saas.war");
                
            
                
                
		//3. Creating the LoginService for the realm
		HashLoginService loginService = new HashLoginService("UserRealm");
		
		//4. Setting the realm configuration there the users, passwords and roles reside
		loginService.setConfig("db.realm.txt");

		//5. Appending the loginService to the Server
		server.addBean(loginService);
		
		//6. Setting the handler
		server.setHandler(webapp);

        try {
            //7. Starting the Server
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
		

    }
}
