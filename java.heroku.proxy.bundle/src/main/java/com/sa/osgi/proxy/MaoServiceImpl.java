/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sa.osgi.proxy;

import com.sa.osgi.system.MaoService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.felix.bundlerepository.Reason;
import org.apache.felix.bundlerepository.Repository;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.apache.felix.bundlerepository.Resolver;
import org.apache.felix.bundlerepository.Resource;
import org.osgi.framework.InvalidSyntaxException;

/**
 *
 * @author mao
 */
public class MaoServiceImpl implements MaoService{

    RepositoryAdmin repoService;
    
    MaoServiceImpl(RepositoryAdmin repoService) {
        this.repoService = repoService;
    }

    public String getAllBundles() {
        if(repoService==null) return "OSGi RepositoryAdmin Not Available!";
        StringBuilder builder = new StringBuilder();
        
        Repository[] listRepositories = repoService.listRepositories();
        for(Repository r:listRepositories){
            String name = r.getName();
            String uri = r.getURI();
            builder.append("Repository Name: "+name + ", URI: "+uri +"<br>");
            Resource[] resources = r.getResources();
            for(Resource res:resources){
                String nameX = res.getSymbolicName() +","+res.getURI() + "," + res.getVersion();
                builder.append(nameX+"<br>");
                
            }
        }
        return builder.toString();
    }

    void setRepoService(RepositoryAdmin repoService) {
        this.repoService = repoService;
    }

    public boolean installBundle(String symbolicName, String version) {
        try {
            if(repoService == null) {
                System.out.println("Repository Admin service N/A");
                return false;
            }
            Resolver resolver = repoService.resolver();
            final String filter = "(&(symbolicname=" + symbolicName + ")(!(version=" + version
                + "))(version>=" + version + "))";
            Resource[] resource = repoService.discoverResources(filter);
            for(Resource r: resource){
                resolver.add(r);
                System.out.println("resource: "+r.getURI());
            }
//            
            if (resolver.resolve())
            {
                resolver.deploy(Resolver.START);
                return true;
            } else {
                Reason[] reqs = resolver.getUnsatisfiedRequirements();
                for (int i = 0; i < reqs.length; i++)
                {
                    System.out.println("Unable to resolve: " + reqs[i]);
                }
            }            
        } catch (InvalidSyntaxException ex) {
            Logger.getLogger(MaoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
