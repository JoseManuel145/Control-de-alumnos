import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import models.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Student> studentsMap = new HashMap<>(); //Hashmap estudiantes
        ArrayList <Student> studentsList = new ArrayList<>(); //ArrayList estudisntes
        ArrayList <User> users = new ArrayList<>(); //ArrayList usuarios
        Scanner scan = new Scanner(System.in);
        boolean continuar = true;
        String eleccion;

        do {
            login();
            eleccion = scan.nextLine();
            switch (eleccion) {
                case "1":
//                  pregunta si el arraylist esta vacio para crear un usuario
                    if (users.isEmpty()) {
                        System.out.println("Ingresa el nombre:");
                        String name = scan.nextLine();

                        boolean esCorrecto = false;
//                      bucle para corroborar el nombre (se puede optimizar con un switch
                        do {
                            System.out.println("¿El nombre es correcto? s/n");
                            String respuesta = scan.nextLine();
//                          si escribo "n" cambia el nombre

                            if (respuesta.equalsIgnoreCase("n")) { //Compara respuesta ya sea n o N
                                esCorrecto = true;
                                System.out.println("Ingresa el nuevo nombre: ");
                                name = scan.nextLine();
                            } else if (respuesta.equalsIgnoreCase("s")){
//                              si escribe otra cosa lo considera como "s"
                                esCorrecto = false;
                            }
                        } while (esCorrecto);

                        System.out.println("Ingresa un password (6 caracteres)");
                        String password = scan.nextLine();
                        while (password.length() != 6 || Character.isDigit(password.charAt(0))) {
                            System.out.println("ERROR");
                            System.out.println("ingresa otro password (6 caracteres):");
                            password = scan.nextLine();
                        }
                        User user = new User(name, password); //Se crea usuario.
                        users.add(user);
                        System.out.println("------------------------");
                        System.out.println("Usuario creado con exito");
                        System.out.println("------------------------");
                    } else if (users.size() == 1) {
                        System.out.println("Solo se permite un usuario");
                    }
                    break;
                case "2":
                    if (users.isEmpty()){
                        System.out.println("-----------------------------------");
                        System.out.println("     Ingresa un usuario primero    ");
                        System.out.println("-----------------------------------");
                    } else {
                        int intentos = 3;
                        boolean acceso = false; //Bandera para menu de estudiantes.
                        do {
                            System.out.println("Ingresa un nombre:");
                            String nameAux = scan.nextLine();
                            for (User i:users){
                                if (nameAux.equals(i.getNombre())){
                                    System.out.println("Ingresa la contraseña:");
                                    String passInicio = scan.nextLine();
                                    System.out.println();
                                    if (passInicio.equals(i.getPassword())){
                                        System.out.println("BIENVENIDO " + " " + i.getNombre()+ " " );
                                        acceso = true;
                                    }else{
                                        intentos--;
                                        System.out.println("Contraseña incorrecta");
                                        System.out.println("Intentos: " + intentos);
                                    }
                                    if (intentos == 0){
                                        continuar = false;
                                    }
                                }else{
                                    System.out.println("-----------------------------------");
                                    System.out.println("         Usuario no existe         ");
                                    System.out.println("-----------------------------------");
                                }
                            }
                        }while (intentos > 0 && !acceso);
                        while(acceso){
                            menu();
                            eleccion = scan.nextLine();
                            switch (eleccion){
                                case "1":
                                    Student student1 = new Student();
                                    System.out.println("-----------------------------------");
                                    System.out.println("         Agregar estudiante        ");
                                    System.out.println("Año de ingreso (ingrese los últimos 2 números): ");
                                    String yearIngreso;
                                    String matriculaFinal = null;
                                    boolean caracteres;
                                    do {
                                        yearIngreso = scan.nextLine();
                                        if (yearIngreso.length() != 2 || !Character.isDigit(yearIngreso.charAt(0)) || !Character.isDigit(yearIngreso.charAt(1))) {
                                            System.out.println("Error al generar la matrícula, ingrésela de nuevo. Deben ser dos dígitos numéricos.");
                                            caracteres = true;
                                        } else {
                                            student1.generarNuevaMatricula(yearIngreso);
                                            matriculaFinal = student1.getMatricula();
                                            caracteres = false;
                                        }
                                    } while (caracteres);

                                    System.out.println("Ingresa Nombre(s):");
                                    String nameStudent = scan.nextLine();

                                    System.out.println("Ingresa sus apellidos:");
                                    String lastName = scan.nextLine();
                                    agregarCalificaciones(student1); //Metodo para generar calificaciones.

                                    // ciclo para buscar matriculas que se repitan al generarlas
                                    for (Student student : studentsList) {
                                        // si encuentra una similitud cambia matricula
                                        if (student.getMatricula().equals(matriculaFinal)) {
                                            //genera una nueva
                                            student1.generarNuevaMatricula(yearIngreso);
                                            // se la asigna de nuevo
                                            matriculaFinal = student1.getMatricula();
                                            break;
                                        }
                                    }
                                    //se crea el estudiante
                                    Student newStudent = new Student(matriculaFinal, lastName, nameStudent, student1.getProm());
                                    studentsList.add(newStudent);
                                    System.out.println("Estudiante agregado con exito.");
                                    System.out.println("Alumno agregado: " + matriculaFinal);
                                    break;
                                case "2":
                                    System.out.println("-----------------------------------");
                                    System.out.println("      Modificar un estudiante      ");
                                    System.out.println();
                                    System.out.print("Ingresa la matrícula del estudiante: ");
                                    String matriculaEdit = scan.next();
                                    scan.nextLine();

                                    boolean estudianteEncontrado = false; //Bandera para encontrar un Estudiante
                                    for (Student estudiante : studentsList) {
                                        if (matriculaEdit.equals(estudiante.getMatricula())) {
                                            estudianteEncontrado = true;
                                            boolean editar = true;
                                            do {
                                            System.out.println("¿Qué desea cambiar?");
                                            System.out.println("|1| Datos del alumno.");
                                            System.out.println("|2| Calificaciones del alumno.");
                                            System.out.println("|x| Salir.");
                                            String eleccionAux = scan.next();
                                            scan.nextLine();
                                                switch (eleccionAux) {
                                                    case "1": //Modifica los datos del alumno
                                                        System.out.println("Modifica el nombre:");
                                                        String nameStudentAux = scan.nextLine();

                                                        System.out.println("Modifica sus apellidos:");
                                                        String lastNameAux = scan.nextLine();

                                                        // Actualizar los datos del estudiante
                                                        estudiante.setName(nameStudentAux);
                                                        estudiante.setLastName(lastNameAux);
                                                        System.out.println("Datos editados.");
                                                        break;
                                                    case "2": // Modificar las calificaciones
                                                        agregarCalificaciones(estudiante);
                                                        System.out.println("Calificaciones editadas.");
                                                        break;
                                                    default:
                                                        editar = false;
                                                        System.out.println("Saliendo");
                                                        break;
                                                }
                                            }while (editar);
                                            break;
                                        }
                                    }
                                    if (!estudianteEncontrado) {
                                        System.out.println("Estudiante no encontrado.");
                                    }
                                    break;
                                case "3":
                                    System.out.println("-----------------------------------");
                                    System.out.println("         Eliminar alumno           ");
                                    System.out.println();
                                    System.out.print("Ingresa la matricula del alumno a eliminar:");
                                    String matriculaDel = scan.nextLine();
                                    boolean encontrado = false;
                                    for (Student student:studentsList){
                                        if (matriculaDel.equals(student.getMatricula())){
                                            studentsList.remove(student);
                                            encontrado = true;
                                        }
                                    }
                                    if (!encontrado){
                                        System.out.println("Estudiante no encontrado.");
                                    }
                                    break;
                                case "4":
                                    System.out.println("-----------------------------------");
                                    System.out.println("            Buscar alumno          ");
                                    System.out.println();
                                    for (Student i:studentsList){
                                        studentsMap.put(i.getMatricula(),i);
                                    }
                                    System.out.print("Ingresa la matricula del alumno a buscar: ");
                                    String matriculaSearch = scan.nextLine();
                                    if (studentsMap.containsKey(matriculaSearch)){
                                        Student studentSearch = studentsMap.get(matriculaSearch);
                                        System.out.println(studentSearch);
                                    }else {
                                        System.out.println("Alumno no encontrado.");
                                    }
                                    for (Student i:studentsList){
                                        studentsMap.remove(i.getMatricula());
                                    }
                                    break;
                                case "5":
                                    System.out.println("-----------------------------------");
                                    System.out.println("         Lista de alumnos          ");
                                    System.out.println();
                                    System.out.println("MATRICULA---APELLIDOS---NOMBRES---PROMEDIO");
                                    for (Student student: studentsList){
                                        System.out.println(student.toString());
                                    }
                                    break;
                                default:
                                    acceso = false;
                            }
                        }
                    }
                    break;
                default:
                    continuar = false;
            }
        }while (continuar);
        scan.close();
    }
    public static void login(){
        System.out.println("------------------------------");
        System.out.println("¿Que desea hacer?");
        System.out.println("|1| Registrarse.");
        System.out.println("|2| Iniciar sesion.");
        System.out.println("|X| Salir.");
        System.out.println("------------------------------");
    }
    public static void menu(){
        System.out.println("------------------------------");
        System.out.println("¿Que desea hacer?");
        System.out.println("|1| Agregar estudiante a la lista.");
        System.out.println("|2| Modificar estudiante de la lista.");
        System.out.println("|3| Eliminar estudiante de la lista.");
        System.out.println("|4| Buscar estudiante en la lista.");
        System.out.println("|5| Imprimir lista completa.");
        System.out.println("|X| Salir.");
        System.out.println("------------------------------");
    }
    public static void agregarCalificaciones(Student student) {
        Scanner scan = new Scanner(System.in);
        boolean mayor = true; //Bandera para comparar si esta dentro del rango las califciaciones.
//        usa getter de Materias para verlas
        for (String materia : student.getMaterias()) {
            System.out.print("Ingresa la calificación de " + materia + ": ");
            do {
//                ciclo do para agregar la calificacion para el arreglo de calificacion
                double nota = scan.nextDouble();
//                pregunta si es menor a 100 y mayor a 10 para agregar la calificacion
                if (nota>9 && nota<101) {
                    student.sumarCalificacion(nota);
                    mayor = false;
                }else {
                    System.out.println("La calificacion debe ser mayor a 10 y menor a 100.");
                    System.out.print("Ingresa la calificacion nuevamente: ");
                }
            }while (mayor);
        }
//        crea variable promedio con el valor del metodo calcular promedio
        double promedio = student.calcularPromedio();
//        le envia el promedio al objeto para que lo guarde
        student.setProm(promedio);
    }
}