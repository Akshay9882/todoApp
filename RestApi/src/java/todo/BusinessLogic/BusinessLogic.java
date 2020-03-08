/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.BusinessLogic;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.apache.log4j.Logger;
import todo.Bean.Buckets;
import todo.Bean.Todos;
import todo.Bean.Users;
import todo.service.BucketsFacadeREST;

/**
 *
 * @author Akshay
 */
@Stateless
public class BusinessLogic {

    private static final Logger logger = Logger.getLogger(BusinessLogic.class);

    @PersistenceContext(unitName = "WebApplication2PU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Users> getData(int id) {
        List<Users> bucketslist = null;
        try {
            Query q = getEntityManager().createQuery("SELECT c FROM Users c WHERE c.userId= :getbyUserId");
            q.setParameter("getbyUserId", id);
            bucketslist = q.getResultList();
        } catch (Exception e) {
            logger.info("Exception in getdata");
        }
        return bucketslist;
    }

    public int deleteBucket(int id) {
        int count = 0;
        try {
            Query q = getEntityManager().createQuery("DELETE FROM Buckets c WHERE c.bucketId= :deleteBucketById");
            q.setParameter("deleteBucketById", id);
            count = q.executeUpdate();
        } catch (Exception e) {
            logger.error("Exception in deleting buckets", e);
        }
        return count;
    }

    public int deleteTodo(int id) {
        int count = 0;
        try {
            Query q = getEntityManager().createQuery("DELETE FROM Todos c WHERE c.todid= :deleteTodoById");
            q.setParameter("deleteTodoById", id);
            count = q.executeUpdate();
        } catch (Exception e) {
            logger.error("Exception in deleting Todo", e);
        }
        return count;
    }

    public void addBuckets(int userid, String buckettitle) {
        try {
            Buckets b = new Buckets();
            Users u = new Users();
            u.setUserId(userid);
            b.setUserId(u);
            b.setBucketTitle(buckettitle);
            getEntityManager().persist(b);
        } catch (Exception e) {
            logger.error("Exception in adding buckets", e);
        }
    }

    public void addTodo(int bucketid, String todotitle) {
        try {
            Todos t1 = new Todos();
            Buckets b1 = new Buckets();
            b1.setBucketId(bucketid);
            t1.setBucketid(b1);
            t1.setTitle(todotitle);
            Short status = 1;
            t1.setStatus(status);
            getEntityManager().persist(t1);
        } catch (Exception e) {
            logger.error("Exception in adding todo", e);
        }
    }

    public void updateTodoStatus(int todoid, short status) {
        try {
            logger.info("testing logs");
//            Todos t1 = new Todos();
//            Buckets b1 = new Buckets();
//            b1.setBucketId(bucketid);
//            t1.setBucketid(b1);
//            t1.setTodid(todoid);
//            t1.setStatus(status);
//            getEntityManager().merge(t1);
            Query q = getEntityManager().createQuery("Update Todos t SET t.status=:setStatus WHERE t.todid=:updatebyTodoID");
            q.setParameter("setStatus", status);
            q.setParameter("updatebyTodoID", todoid);
            q.executeUpdate();
        } catch (ConstraintViolationException e) {
            logger.info("Exception in adding todo", e);
        }
    }
}
