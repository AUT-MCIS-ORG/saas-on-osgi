package com.sa.osgi.ui;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import com.sa.osgi.system.UIService;
import org.osgi.framework.ServiceRegistration;

public class Activator
implements BundleActivator
{
    private ServiceRegistration<?> serviceReg;
    
    public void start( final BundleContext ctx ) throws Exception
    {       
        serviceReg = ctx.registerService(UIService.class.getName(), new DefaultUIService(), null);
    }

    public void stop( BundleContext ctx ) throws Exception
    {
        if( serviceReg != null){
            serviceReg.unregister();
        }
    }
    
}
