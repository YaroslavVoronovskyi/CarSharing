package carsharing.model;

import carsharing.Constants;

import java.util.Objects;

public class Company {

    private int id;
    private String name;

    public Company() {
    }

    public Company(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return id + Constants.DOT_SEPARATOR + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
