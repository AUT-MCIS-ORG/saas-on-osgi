package com.sa.osgi.proxy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

import com.sa.osgi.system.MaoService;
import org.apache.felix.bundlerepository.RepositoryAdmin;
import org.osgi.framework.ServiceRegistration;

public class Activator
        implements BundleActivator {

    ServiceReference m_reference;
    RepositoryAdmin repoService = null;
    ServiceRegistration<?> maoServiceReg;
    MaoServiceImpl maoService = new MaoServiceImpl(null);

    public void start(final BundleContext ctx) throws Exception {
//        new ServiceTracker(ctx, RepositoryAdmin.class.getName(), null)
//        {
//            public Object addingService( ServiceReference reference )
//            {
//                repoService= (RepositoryAdmin)super.addingService( reference );
////                maoService.setRepoService(repoService);
//                return repoService;
//            }
//        }.open();

        maoService.setContext(ctx);
        System.out.println("Register MaoService.........");
        maoServiceReg = ctx.registerService(MaoService.class.getName(), maoService, null);
    }

//    void registerServlets(HttpService httpService) 
//    {
//        try
//        {
//            httpService.registerServlet("/", new HelloWorldServlet("1of2"), null, null);
//            httpService.registerServlet("/2of2", new HelloWorldServlet("2of2"), null, null);
//        }
//        catch ( Throwable e )
//        {
//            e.printStackTrace();
//        }
//    }
    public void stop(BundleContext ctx) throws Exception {
        if (m_reference != null) {
            ctx.ungetService(m_reference);
        }
        if (maoServiceReg != null) {
            maoServiceReg.unregister();
        }
    }

}
