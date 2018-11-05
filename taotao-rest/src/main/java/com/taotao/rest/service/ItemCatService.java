package com.taotao.rest.service;

import com.taotao.rest.pojo.ItemCatResult;
import org.apache.cxf.rs.security.cors.CrossOriginResourceSharing;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: keen
 * Date: 2018-10-23
 * Time: 22:45
 */
@Path("/itemCat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@CrossOriginResourceSharing(allowOrigins = {"http://localhost:8082/"}, allowCredentials = true,
        allowAllOrigins = true, maxAge = 3600)
public interface ItemCatService {
    @Path("/getItemCat")
    @GET
    ItemCatResult getItemCat();
}
