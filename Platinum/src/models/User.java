package models;

import java.util.Objects;
public class User {
    String nombre;
    String password;
    public User(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(nombre, user.nombre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}