package org.Grupp4;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class UserService {

    @Inject
    EntityManager entitymanager;

    public List<ApiUser> findAll() {
        List<ApiUser> users = entitymanager.createQuery("SELECT u FROM ApiUser u", ApiUser.class).getResultList();
        return users;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public UUID getApiKey(UUID apiKey) {
        ApiUser user = entitymanager.find(ApiUser.class, apiKey);
        return user.getApiKey();
    }

}
