/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.jetty;

import com.sa.osgi.system.Credential;
import com.sa.osgi.system.MaoService;
import com.sa.osgi.system.ServiceFactory;
import com.sa.osgi.system.UIService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mao
 */
public class WelcomeServlet extends HttpServlet {

    @Override
    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setBufferSize(8192);

        PrintWriter out = response.getWriter();

        // then write the data of the response
        out.println("<html>" + "<head><title>Welcome to SaaS on OSGi+Jetty</title></head>");

        ServiceFactory factory = ServiceFactory.INSTANCE;
//        
        String userName = request.getRemoteUser();
        Credential cred = new Credential();
        cred.setTennantName(userName);
        cred.setUserName(userName);
        
        MaoService maoService = factory.getMaoService();
        UIService uiService = factory.getUIService(cred);
        String color = uiService.getBackgroundColor();
//        System.out.println("Color: " + color);
        String format = uiService.getDateFormat();
        System.out.println("format is: "+format);
        String nowS = new SimpleDateFormat(format).format(new Date());
//        MaoService maoService = factory.getMaoService();
//        String allBundles = maoService.getAllBundles();
//        
//        System.out.println("all bundles: "+allBundles);
        
        // then write the data of the response
        StringBuilder builder = new StringBuilder();
        builder.append("<body  bgcolor=" + color + ">"
                + "<img src=\"duke.waving.gif\" alt=\"Duke waving\">"
                + "<h2>Hello " + userName +"</h2>");
        
        builder.append("<h2>Background colour is: "+color+"</h2>");
        builder.append("<h2>Today is: "+nowS+" in format: "+format+"</h2>");
        builder.append("<h3>All My Own Bundles</h2>");
        builder.append("<table>");
        
        List<String> allBundles = maoService.getAllBundles(cred);
        if(allBundles.size() > 0){
        for(String s:allBundles){
            builder.append("<tr>");
            builder.append("<td>"+s+"</td>");
            builder.append("</tr>");
        }            
        }else{
            builder.append("<tr>");
            builder.append("<td>None, you don't have any customization bundle yet!</td>");
            builder.append("</tr>");
        }

        
        
        builder.append("</table>");
        builder.append("<br><br><a href=\"upload.html\">Upload Bundle</a>");
        builder.append("<br><br><a href=\"logout\">Log Out</a>");
        
        out.println(
                builder.toString()
                );

        out.println("</body></html>");
        out.close();
    }

    @Override
    public String getServletInfo() {
        return "The Hello servlet says hello.";
    }

}
