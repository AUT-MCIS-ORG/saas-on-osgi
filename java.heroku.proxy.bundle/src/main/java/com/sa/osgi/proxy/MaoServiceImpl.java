/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.proxy;

import com.sa.osgi.system.Credential;
import com.sa.osgi.system.MaoService;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 *
 * @author mao
 */
public class MaoServiceImpl implements MaoService {

//    RepositoryAdmin repoService;
    BundleContext ctx;

    MaoServiceImpl(BundleContext ctx) {
        this.ctx = ctx;
    }

    public List<String> getAllBundles(Credential cred) {
        List<String> allMyBundles = new ArrayList<String>();
        if (ctx == null) {
            return allMyBundles;
        }
//        StringBuilder builder = new StringBuilder();

//        Repository[] listRepositories = repoService.listRepositories();
//        for(Repository r:listRepositories){
//            String name = r.getName();
//            String uri = r.getURI();
//            builder.append("Repository Name: "+name + ", URI: "+uri +"<br>");
//            Resource[] resources = r.getResources();
//            for(Resource res:resources){
//                String nameX = res.getSymbolicName() +","+res.getURI() + "," + res.getVersion();
//                builder.append(nameX+"<br>");
//                
//            }
//        }
        Bundle[] bundles = ctx.getBundles();
        for (Bundle b : bundles) {
            String name = b.getSymbolicName();
            String location = b.getLocation();
//            builder.append("Symbolic Name: " + name + ", Location: " + location + "<br>");
            if (name.contains(cred.getTennantName())) {
                allMyBundles.add(name + "," + location);
            }
        }
        return allMyBundles;
    }

    public boolean installBundle(String fileName) {
        if (ctx == null) {
            return false;
        }
        File f = new File(fileName);
        if (f.exists()) {
            try {
                System.out.println("file path: " + f.getAbsolutePath());
                Bundle b = this.ctx.installBundle("file:" + f.getAbsolutePath());
                if (b != null) {
                    System.out.println("start new installed bundle: " + b.getSymbolicName());
                    b.start();
                    return true;
                }
            } catch (BundleException ex) {
                Logger.getLogger(MaoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean installBundle(FileInputStream fin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        this.ctx.installBundle(null, fin);
//        return false;
    }

    void setContext(BundleContext ctx) {
        this.ctx = ctx;
    }

}
