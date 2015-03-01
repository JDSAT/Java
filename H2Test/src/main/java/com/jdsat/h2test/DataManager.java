package com.jdsat.h2test;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**Creates object to manage a single DB table and
 * perform basic CRUD operations.
 * 
 * @author Ronald
 * @param <T> 
 */
public class DataManager<T> {
    private String tableName;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;
    private Class objClass;
    
    public DataManager(){}
    
    public DataManager(String pUnit,String tableName,Class objClass){
        this.tableName=tableName;
        this.objClass=objClass;
        entityManagerFactory=Persistence.createEntityManagerFactory(pUnit);
        entityManager=entityManagerFactory.createEntityManager();
        entityTransaction=entityManager.getTransaction();
        entityTransaction.begin();
    }
    
    public boolean insertEntity(T toPersist){           
        try{
            entityManager.persist(toPersist); 
            return true;
        }catch(RuntimeException e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
                throw e;
            }
        }
        return false;
    }
    
    public void insertEntities(List<T> entityList){        
        for(T t:entityList){
            try{
                entityManager.persist(t); 
            }catch(RuntimeException e){
                if(entityTransaction.isActive()){
                    entityTransaction.rollback();
                    throw e;
                }
            }
        }
    }
    
    public T retrieveEntity(int id,Class objClass){        
        T result=null;            
        try{
            result=(T)entityManager.find(objClass,id);
        }catch(RuntimeException e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
                throw e;
            }
        }
        return result;
    }
    
    public List<T> getAllEntities(){     
        TypedQuery<T> query =
            entityManager.createQuery("SELECT p FROM "+tableName+" p ", objClass);
        List<T> results = query.getResultList();
        return results;
    }
    
    public boolean removeEntity(int id){         
        T toDelete=retrieveEntity(id,objClass);
        try{
            if(toDelete!=null){
                entityManager.remove(entityManager.merge(toDelete));
                return true;
            }
            return false;
        }catch(RuntimeException e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
                throw e;
            }
        }
        return false;
    }
    
    public void removeAllEntities(){
        Query q1 = entityManager.createQuery("DELETE FROM "+tableName);
        try{
            q1.executeUpdate();
        }catch(RuntimeException e){
            if(entityTransaction.isActive()){
                entityTransaction.rollback();
                throw e;
            }
        }
    }
    
    public void shutDown(){
        entityTransaction.commit();
        entityManagerFactory.close();
    }
}
