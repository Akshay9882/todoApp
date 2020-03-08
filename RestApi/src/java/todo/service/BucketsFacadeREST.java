/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.service;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.Static;
import javax.ejb.EJB;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.log4j.Logger;
import todo.Bean.Buckets;
import todo.Bean.ResponseStatus;
import todo.BusinessLogic.BusinessLogic;

/**
 *
 * @author Akshay
 */
@Stateless
@Path("todo.buckets")
public class BucketsFacadeREST extends AbstractFacade<Buckets> {

    @EJB
    BusinessLogic bsl;

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(BucketsFacadeREST.class);

    public BucketsFacadeREST() {
        super(Buckets.class);
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("addbuckets/{userid}/{buckettitle}")
    public ResponseStatus create(@PathParam("userid") int userid, @PathParam("buckettitle") String buckettitle) {
        ResponseStatus rs = new ResponseStatus();
        try {
            bsl.addBuckets(userid, buckettitle);
            rs.setResult("success");
        } catch (Exception e) {
            logger.error("Exception in adding buckets", e);
            rs.setResult("error");
        }
        return rs;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Buckets entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("deleteBuckets/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseStatus remove(@PathParam("id") Integer id) {
        ResponseStatus rs = new ResponseStatus();
        try {
            rs.setDeletedCount(bsl.deleteBucket(id));
            rs.setResult("success");
        } catch (Exception e) {
            logger.error("Exception in deleting buckets", e);
            rs.setResult("error");
        }
        return rs;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Buckets find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Buckets> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Buckets> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
