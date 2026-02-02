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
@Named
public class CarsService {
    
    @Inject
    EntityManager entityManager;

    public List<Cars> findAll() {
        List<Cars> cars = entityManager.createQuery("SELECT c FROM Cars c", Cars.class).getResultList();
        return cars;
    }

    @Transactional(Transactional.TxType.REQUIRED)
        public Cars createCar(Cars cars) {
            cars.setVinNumber(UUID.randomUUID());
            entityManager.persist(cars);
            return cars;
        }
}
