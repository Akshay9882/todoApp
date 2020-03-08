/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.crossOrigin;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


@Provider
public class CrossOriginResourceSharingFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*");
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "OPTIONS, GET, POST, PUT, DELETE");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "accept, access-control-allow-methods, access-control-allow-origin, authorization, content-type, userSession, sessionId, requestId, user, Message,restrict, uniqueId");
        response.getHeaders().putSingle("Access-Control-Expose-Headers", "accept, access-control-allow-methods, access-control-allow-origin, authorization, content-type, userSession, sessionId, requestId, user, Message,restrict, uniqueId");
    }
}