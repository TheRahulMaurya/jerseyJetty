package org.rahul.lattice.jerseyJetty.dao.manager;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


@Provider
public class CORSFilter implements ContainerResponseFilter {
//    public ContainerResponse filter(ContainerRequest request,
//            ContainerResponse response) {
//
//        response.getHttpHeaders().add("Access-Control-Allow-Origin", "*");
//        response.getHttpHeaders().add("Access-Control-Allow-Headers",
//                "origin, content-type, accept, authorization");
//        response.getHttpHeaders().add("Access-Control-Allow-Credentials", "true");
//        response.getHttpHeaders().add("Access-Control-Allow-Methods",
//                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//
//        return response;
//    }

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
			responseContext.getHeaders().add(
	            "Access-Control-Allow-Origin", "*");
	          responseContext.getHeaders().add(
	            "Access-Control-Allow-Credentials", "true");
	          responseContext.getHeaders().add(
	           "Access-Control-Allow-Headers",
	           "origin, content-type, accept, authorization");
	          responseContext.getHeaders().add(
	            "Access-Control-Allow-Methods", 
	            "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		
	}
}