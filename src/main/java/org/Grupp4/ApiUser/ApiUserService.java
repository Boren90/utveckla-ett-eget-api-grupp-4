package org.Grupp4.ApiUser;

import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class ApiUserService {

    @Inject
    EntityManager entitymanager;

    @Transactional(Transactional.TxType.REQUIRED)
    public ApiUser findByUsernameAndPassword(ApiUser apiUser) {
        try {
        return entitymanager.createQuery("SELECT u FROM ApiUser u WHERE u.userName = :userName AND u.userPassword = :userPassword", ApiUser.class)
        .setParameter("userName", apiUser.getUserName())
        .setParameter("userPassword", apiUser.getUserPassword())
        .getSingleResult();
        }catch (NoResultException e) {
            
        return null;
        }
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public ApiUser createNewUser(ApiUser apiUser) {
        apiUser.setApiKey(UUID.randomUUID());
        entitymanager.persist(apiUser);
        return apiUser;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public UUID getApiKey(UUID apiKey) {
        ApiUser user = entitymanager.find(ApiUser.class, apiKey);
        return user.getApiKey();
    }

}
