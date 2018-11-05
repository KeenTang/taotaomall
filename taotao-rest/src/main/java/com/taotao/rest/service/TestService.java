package com.taotao.rest.service;

import com.taotao.model.Test;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-21
 * Time: 16:34
 */
@Path("/testService")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@CrossOriginResourceSharing(allowAllOrigins = true, allowCredentials = true, allowOrigins = {"*"})
public interface TestService {
    @Path("/test/{id}")
    @GET
    Test test(@PathParam("id") String id);

    /**
     * testPost
     * @param map
     * @return
     */
    @Path("/testPost")
    @POST
    String testPost(Map<String,Object> map);


//    @POST
  //  String getData();
}