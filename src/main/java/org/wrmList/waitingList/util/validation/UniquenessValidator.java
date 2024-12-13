package org.wrmList.waitingList.util.validation;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Add Uniqueness Validator

@Slf4j
@Component
public class UniquenessValidator {
    
    private static EntityManager staticEntityManager;
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public void init() {
        staticEntityManager = entityManager;
    }

    public static boolean isValueUnique(Class<?> entityClass, String fieldName, String value) {
        try {
            if (value == null || staticEntityManager == null) {
                return true;
            }

            String jpql = String.format(
                "SELECT COUNT(e) FROM %s e WHERE LOWER(e.%s) = LOWER(:value)", 
                entityClass.getSimpleName(), fieldName
            );
            
            Long count = staticEntityManager
                .createQuery(jpql, Long.class)
                .setParameter("value", value)
                .getSingleResult();

            return count == 0;
        } catch (NoResultException e) {
            return true;
        } catch (Exception e) {
            log.error("Error checking uniqueness for {} {}: {}", 
                entityClass.getSimpleName(), fieldName, e.getMessage());
            return false; // Changed to return false on errors
        }
    }
}