package com.github.firststraw;

import javax.servlet.ServletContextEvent;
import javax.websocket.DeploymentException;
import javax.websocket.server.ServerContainer;
import org.apache.tomcat.websocket.server.Constants;
import org.apache.tomcat.websocket.server.WsContextListener;

/**
 * Context listener for registering the websocket endpoint.
 */
public class ContextListener extends WsContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent sce) {
        super.contextInitialized(sce);

        final ServerContainer sc =
                (ServerContainer) sce.getServletContext().getAttribute(
                        Constants.SERVER_CONTAINER_SERVLET_CONTEXT_ATTRIBUTE);
        try {
            sc.addEndpoint(EchoEndpoint.class);
        } catch (final DeploymentException e) {
            throw new IllegalStateException(e);
        }
    }
}
