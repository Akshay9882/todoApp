/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.service;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.EJB;
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
import org.codehaus.jackson.map.ObjectMapper;
import todo.Bean.ResponseStatus;
import todo.Bean.Todos;
import todo.BusinessLogic.BusinessLogic;

/**
 *
 * @author Akshay
 */
@Stateless
@Path("todo.todos")
public class TodosFacadeREST extends AbstractFacade<Todos> {

    @EJB
    BusinessLogic bsl;

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;
    private static final Logger logger = Logger.getLogger(TodosFacadeREST.class);

    public TodosFacadeREST() {
        super(Todos.class);
    }

    @GET
    @Path("addTodo/{bucketid}/{todotitle}")
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseStatus create(@PathParam("bucketid") int bucketid, @PathParam("todotitle") String todotitle) {
        ResponseStatus rs = new ResponseStatus();
        try {
            bsl.addTodo(bucketid, todotitle);
            rs.setResult("success");
        } catch (Exception e) {
            logger.error("Exception in adding todos", e);
            rs.setResult("error");
        }
        return rs;
    }

    @PUT
    @Path("updateTodo/{todoid}/{status}/")
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseStatus edit(@PathParam("todoid") Integer todoid,@PathParam("status") Short status) {
        ResponseStatus rs = new ResponseStatus();
        try {
            bsl.updateTodoStatus(todoid, status);
            rs.setResult("success");
        } catch (Exception e) {
            logger.error("Exception in adding todos", e);
            rs.setResult("error");
        }
        return rs;
    }

    @DELETE
    @Path("deleteTodo/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public ResponseStatus remove(@PathParam("id") String id) {
        ResponseStatus rs = new ResponseStatus();
        try {
            rs.setDeletedCount(bsl.deleteTodo(Integer.parseInt(id)));
            rs.setResult("success");
        } catch (Exception e) {
            rs.setResult("error");
            logger.error("Exception in deleting todos", e);
        }
        return rs;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Todos find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Todos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
