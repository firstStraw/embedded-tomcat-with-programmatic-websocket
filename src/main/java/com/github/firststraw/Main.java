package com.github.firststraw;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public class Main {

    public static void main(final String[] args) throws LifecycleException {
        // Using the folder containing the JAR as the base directory.  In the source repository,
        // this will be the "target" folder.
        final String baseDir =
                new File(System.getProperty("java.class.path")).getAbsoluteFile().getParentFile()
                        .getPath();

        final Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(baseDir);

        final Context context = tomcat.addContext("", baseDir);
        context.addApplicationListener(ContextListener.class.getName());

        final Wrapper defaultServlet = Tomcat.addServlet(context, "default", new DefaultServlet());
        defaultServlet.addMapping("/");

        // As of Tomcat 9, the HTTP connector won't start without this call.
        tomcat.getConnector();

        // Stop the Tomcat server when the JVM is shutting down.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    tomcat.stop();
                } catch (LifecycleException ex) {
                    Logger.getLogger(Main.class.getName())
                            .log(Level.SEVERE, "Error while stopping Tomcat server.", ex);
                }
            }
        });

        tomcat.start();
        tomcat.getServer().await();
    }
}
