package de.ibapp.ghostnet.moduls;

public class Person {

    private String name;
    private String nummer;
    private boolean licensed;

    public Person(){}

    public Person(String name, String nummer) {
        this.name = name;
        this.nummer = nummer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNummer() {
        return nummer;
    }

    public void setNummer(String nummer) {
        this.nummer = nummer;
    }

    public void  setLicensed(boolean licensed){
        this.licensed = licensed;
    }

    public boolean islicensed() {
        return licensed;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Telefonnummer: " + nummer;
    }
}
