package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
public class Student {
    Random random = new Random();
    String matricula;
    String  lastName;
    String name;
    double prom = 0.0;
    //final ya que nunca cambia de valores
    private final ArrayList<String> materias = new ArrayList<>(Arrays.asList("Ingles", "Poo", "PDS", "Calculo"));
    private double suma = 0.0;
    public Student() {
    }
    public Student(String yearIngreso, String lastName, String name, double prom) {
        this.matricula = yearIngreso;
        this.lastName = lastName;
        this.name = name;
        this.prom = prom;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setProm(double prom) {
        this.prom = prom;
    }
    public double getProm() {
        return prom;
    }
    public String getMatricula() {
        return matricula;
    }
    public ArrayList<String> getMaterias() {
        return materias;
    }
    public void generarNuevaMatricula(String yearIngreso){
        this.matricula = yearIngreso;
        for (int i = 0; i < 4; i++) {
            this.matricula += String.valueOf(random.nextInt(10));
        }
    }
    public void sumarCalificacion(double calificacion) {
        this.suma += calificacion;
    }
    public double calcularPromedio() {
        prom = suma / 4;
        return prom;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(matricula, student.matricula);
    }
    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }
    @Override
    public String toString() {
        return  matricula + " - " + lastName + " - " +
                name + " - " +
                 prom;
    }
}