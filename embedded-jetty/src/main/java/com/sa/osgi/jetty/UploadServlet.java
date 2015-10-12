/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.jetty;

import com.sa.osgi.system.MaoService;
import com.sa.osgi.system.ServiceFactory;
import java.io.*;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author maochuanli
 */
@WebServlet(name = "UploadServlet", urlPatterns = {"/upload"})
public class UploadServlet extends HttpServlet {
    private final static Logger LOGGER = 
            Logger.getLogger(UploadServlet.class.getCanonicalName());
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        
        if(isMultipart){
            // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        String ss = servletContext.getInitParameter("javax.servlet.context.tempdir");
        File repository = new File("/tmp");//(File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        System.out.println("context var: "+ss);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        try {
            // Parse the request
            List<FileItem> items = upload.parseRequest(request);
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();

                if (item.isFormField()) {
//                    processFormField(item);
                } else {
                    processUploadedFile(item);
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        response.sendRedirect("/welcome"); 
        
//        RequestDispatcher dis = request.getRequestDispatcher("/welcome");
//        dis.forward(request, response);

}



@Override
    public void doPost(
        HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processUploadedFile(FileItem item) {
        String fieldName = item.getFieldName();
        String fileName = item.getName();
        String contentType = item.getContentType();
        boolean isInMemory = item.isInMemory();
        long sizeInBytes = item.getSize();
        
        System.out.println("file name: "+fileName);
        try {
//            InputStream inputStream = item.getInputStream();
//            FileOutputStream fout = new FileOutputStream("/tmp/aa");
//            fout.write(inputStream.rea);
            String newFileName = "/tmp/"+fileName;
            item.write(new File(newFileName));
            ServiceFactory factory = ServiceFactory.INSTANCE;
            MaoService maoService = factory.getMaoService();
            boolean b = maoService.installBundle(newFileName);
            System.out.println("Installation Result: "+b);
            
        } catch (IOException ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
