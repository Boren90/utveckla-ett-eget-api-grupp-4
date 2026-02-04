package org.Grupp4.Car;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional(Transactional.TxType.SUPPORTS)
@ApplicationScoped
public class CarService {

    @Inject
    EntityManager entityManager;

    public List<Car> findAll() {
        List<Car> cars = entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        return cars;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Car updateCarInfo(CarDTO carsDTO, Long id) {
        Car car = entityManager.find(Car.class, id);
        car.setTrivia(carsDTO.getNewTrivia());
        car.setValue(carsDTO.getNewValue());
        return car;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Car createCar(Car car) {
        car.setVinNumber(UUID.randomUUID());
        entityManager.persist(car);
        return car;
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteCar(Long id) {
        entityManager.remove(entityManager.getReference(Car.class, id));
    }
}
