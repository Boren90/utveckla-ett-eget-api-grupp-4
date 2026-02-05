package org.Grupp4.Car;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID vinNumber;
    @Size(min= 2, max = 99, message = "Car name is too long or too short")
    private String carBrand;
    @Size(min= 2, max = 99, message = "Every car has a name, and it's missing here!")
    private String carModel;
    private int year;
    @Size(min = 0)
    private String value;
    private String img;
    @Column(length = 300)
    private String trivia;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(UUID vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTrivia() {
        return trivia;
    }

    public void setTrivia(String trivia) {
        this.trivia = trivia;
    }

}
