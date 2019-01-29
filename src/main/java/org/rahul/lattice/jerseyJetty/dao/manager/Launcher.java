package org.rahul.lattice.jerseyJetty.dao.manager;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.startup.Tomcat;

public class Launcher {

    public static void main(String[] args)  {
        Launcher launcher = new Launcher();
        try {
            launcher.startServer();
        } catch (Exception e) {
            System.out.println("Couldn't start the server");
        }
    }

    private void startServer() throws Exception {
        File war = new File(System.getProperty("java.class.path"));
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8888);
        tomcat.setBaseDir(System.getProperty("user.dir"));

        Host host = tomcat.getHost();
        host.setAppBase(System.getProperty("user.dir"));
        host.setAutoDeploy(true);
        host.setDeployOnStartup(true);
        Context appContext = tomcat.addWebapp(host, "/example", war.getAbsolutePath());
        System.out.println("Deployed " + appContext.getBaseName() + " as " + appContext.getDocBase());

        tomcat.start();
        tomcat.getServer().await();
    }
}
