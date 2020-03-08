/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todo.ApplicationConfig;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Akshay
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) { 
        resources.add(todo.crossOrigin.CrossOriginResourceSharingFilter.class);
        resources.add(todo.service.BucketsFacadeREST.class);
        resources.add(todo.service.TodosFacadeREST.class);
        resources.add(todo.service.UsersFacadeREST.class);
    }

}
