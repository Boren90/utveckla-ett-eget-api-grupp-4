package org.Grupp4;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Cars {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID vinNumber;
    @NotEmpty(message = "Every car has a name, and it's missing here!")
    private String carBrand;
    @NotEmpty(message = "Every car has a name, and it's missing here!")
    private String carModel;
    @Size(min = 1920, max = 2026)
    private int year;
    @Size(min = 0)
    private String value;
    private String img;
    @Column(length = 300)
    private String trivia;

    public UUID getId() {
        return vinNumber;
    }

    public void setId(UUID vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getModelName() {
        return carModel;
    }

    public void setModelName(String carModel) {
        this.carModel = carModel;
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
