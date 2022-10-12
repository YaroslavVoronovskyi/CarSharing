package carsharing.model;

import carsharing.Constants;

import java.util.Objects;

public class Car {
    private int id;
    private String name;
    private int companyId;
    private boolean isRented;

    public Car() {
    }

    public Car(String name, int companyId) {
        this.name = name;
        this.companyId = companyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    @Override
    public String toString() {
        return id + Constants.DOT_SEPARATOR + name + companyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && name.equals(car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
