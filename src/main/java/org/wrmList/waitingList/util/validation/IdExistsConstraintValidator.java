package org.wrmList.waitingList.util.validation;

import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.wrmList.waitingList.common.controller.BaseController;
import org.wrmList.waitingList.util.annotation.IdExists;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@Component
public class IdExistsConstraintValidator implements ConstraintValidator<IdExists, Object> {
    private final EntityManager entityManager;
    private final WebApplicationContext applicationContext;
    private Class<?> entityClass;
    private String field;

    @Override
    public void initialize(IdExists idExists) {
        this.entityClass = idExists.entityClass();
        this.field = idExists.field();
    }

    @Override
    public boolean isValid(Object id, ConstraintValidatorContext context) {
        if(id == null) {
            return false;
        }

        if (Object.class.equals(entityClass)) {
            try {
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
                if (attributes != null) {
                    String requestURI = attributes.getRequest().getRequestURI();
                    log.info("Request URI: {}", requestURI);
                    
                    Map<String, BaseController> controllers = applicationContext.getBeansOfType(BaseController.class);
                    log.info("Found {} controllers", controllers.size());
                    
                    for (BaseController<?, ?, ?, ?, ?> controller : controllers.values()) {
                        // Check class-level RequestMapping
                        RequestMapping classMapping = controller.getClass().getAnnotation(RequestMapping.class);
                        if (classMapping == null) {
                            // Try to get mapping from superclass
                            classMapping = controller.getClass().getSuperclass().getAnnotation(RequestMapping.class);
                        }
                        
                        if (classMapping != null) {
                            String[] paths = classMapping.value();
                            for (String path : paths) {
                                // Remove /api/v1 prefix for comparison
                                String normalizedRequestURI = requestURI.replace("/api/v1", "");
                                if (normalizedRequestURI.startsWith(path)) {
                                    entityClass = controller.getEntityClass();
                                    log.info("Found matching controller: {} with entity class: {}", 
                                        controller.getClass().getSimpleName(),
                                        entityClass.getSimpleName());
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.error("Error getting entity class from controller: {}", e.getMessage(), e);
                return false;
            }
        }

        if (Object.class.equals(entityClass)) {
            log.error("Entity class is still Object.class");
            return false;
        }

        try {
            String jpql = String.format("SELECT COUNT(e) FROM %s e WHERE e.%s = :id", entityClass.getSimpleName(), field);
            log.info("Executing JPQL: {}", jpql);
            Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("id", id)
                .getSingleResult();
            
            if(count <= 0) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        String.format("%s with id %s not found",
                                entityClass.getSimpleName(), id)
                ).addConstraintViolation();
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("Error checking entity existence: {}", e.getMessage(), e);
            return false;
        }
    }
}
