package matrices2.alumnos;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/*
    En el siguiente ejercicio se le mostrara al docente las notas  
    de sus alumnos de la siguiente manera, en un JTextArea.
    Se le preguntara la cantidad de alumnos que maneja

    CARNET NOMBRE APELLIDO NOTA1 NOTA2 NOTA3 NOTA4 PROMEDIO

    Hacer un menu el cual de las opciones
        1. Agregar un alumno (Recuerde hacer las validaciones estrictas para el carnet y las notas)
        2. Ver todos los alumnos
        3. Ver notas solo de un alumno, buscando por su carnet
        4. Modificar una nota de un alumno, buscando por carnet solicitar revision (no puede bajar la nota) o correccion.

 */
public class Matrices2Alumnos {

    public static void main(String[] args) {
        JTextArea hoja = new JTextArea();
        String alumnos[][];
        String salida = "", carnet, nombre, apellido;
        int cuantos, fila, columna, opcion, alumno = 0, cualFila = -1, opcion2, cualNota;
        double nota, promedio = 0.0;
        boolean salir = false;

        do {
            cuantos = Integer.parseInt(JOptionPane.showInputDialog("Cuantos alumnos manejara (Entre 1 y 50)"));
        } while (cuantos < 0 || cuantos > 50);
        alumnos = new String[cuantos][8];

        for (fila = 0; fila < cuantos; fila++) {
            for (columna = 0; columna < 8; columna++) {
                if (columna >= 3) {
                    alumnos[fila][columna] = "0.0";
                } else {
                    alumnos[fila][columna] = "SIN DATOS";
                }

            }
        }

        while (!salir) {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("Bienvenido, presione la opcion que desee\n"
                    + "1. Agregar un alumno \n"
                    + "2. Ver todos los alumnos\n"
                    + "3. Ver notas solo de un alumno\n"
                    + "4. Modificar una nota de un alumno.\n"
                    + "Cualquier otro para salir"));

            if (opcion == 1) { //Agregar
                do {
                    carnet = JOptionPane.showInputDialog("Ingrese el carnet del alumno #" + (alumno + 1));
                    if (carnet.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error. No se permiten campos vacios");
                    }
                } while (carnet.isEmpty());

                do {
                    nombre = JOptionPane.showInputDialog("Ingrese el nombre del alumno #" + (alumno + 1));
                    if (nombre.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error. No se permiten campos vacios");
                    }
                } while (nombre.isEmpty());

                do {
                    apellido = JOptionPane.showInputDialog("Ingrese el apellido del alumno #" + (alumno + 1));
                    if (apellido.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error. No se permiten campos vacios");
                    }
                } while (apellido.isEmpty());

                alumnos[alumno][0] = carnet.toUpperCase();
                alumnos[alumno][1] = nombre.toUpperCase();
                alumnos[alumno][2] = apellido.toUpperCase();

                for (columna = 3; columna < 7; columna++) {
                    do {
                        nota = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota #" + (columna - 2) + " del alumno:" + (alumnos[alumno][0])));
                        if (nota < 0 || nota > 10) {
                            JOptionPane.showMessageDialog(null, "Error. Ingrese una nota valida");
                        }
                    } while (nota < 0 || nota > 10);
                    alumnos[alumno][columna] = String.valueOf(nota);
                }

                alumno++;

            } else if (opcion == 2) { // Ver Matriz
                salida += "CARNET\tNOMBRE\tAPELLIDO\tNOTA1\tNOTA2\tNOTA3\tNOTA4\tPROMEDIO\n";
                for (fila = 0; fila < cuantos; fila++) {
                    for (columna = 0; columna < 7; columna++) {
                        salida += alumnos[fila][columna] + "\t";
                        if (columna >= 3 && columna <= 7) {
                            promedio += Double.parseDouble(alumnos[fila][columna]);
                        }
                    }
                    promedio /= 4;
                    alumnos[fila][7] = String.valueOf(promedio);
                    salida += alumnos[fila][7] + "\n";
                    promedio = 0.0;
                }
                hoja.setText(salida);
                JOptionPane.showMessageDialog(null, hoja);
                salida = "";
            } else if (opcion == 3) { // Ver solo un alumno
                carnet = JOptionPane.showInputDialog("Ingrese el carnet del alumno a ver");
                if (carnet.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error. No se permiten campos vacios");
                } else {
                    carnet = carnet.toUpperCase();
                    for (fila = 0; fila < cuantos; fila++) {
                        if (carnet.equals(alumnos[fila][0])) {
                            cualFila = fila;
                        }
                    }
                    System.out.print(cualFila);
                    if (cualFila == -1) { //no hay
                        JOptionPane.showMessageDialog(null, "Error. No hay un alumno registrado con ese carnet");
                    } else {
                        salida += "CARNET\tNOMBRE\tAPELLIDO\tNOTA1\tNOTA2\tNOTA3\tNOTA4\tPROMEDIO\n";
                        for (columna = 0; columna < 7; columna++) {
                            salida += alumnos[cualFila][columna] + "\t";
                            if (columna >= 3 && columna <= 7) {
                                promedio += Double.parseDouble(alumnos[cualFila][columna]);
                            }
                        }
                        promedio /= 4;
                        alumnos[cualFila][7] = String.valueOf(promedio);
                        salida += alumnos[cualFila][7] + "\n";
                        promedio = 0.0;
                        hoja.setText(salida);
                        JOptionPane.showMessageDialog(null, hoja);
                        salida = "";
                    }
                }
            } else if (opcion == 4) { // Modificar notas
                carnet = JOptionPane.showInputDialog("Ingrese el carnet del alumno a ver");
                if (carnet.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error. No se permiten campos vacios");
                } else {
                    carnet = carnet.toUpperCase();
                    for (fila = 0; fila < cuantos; fila++) {
                        if (carnet.equals(alumnos[fila][0])) {
                            cualFila = fila;
                        }
                    }
                    System.out.print(cualFila);
                    if (cualFila == -1) { //no hay
                        JOptionPane.showMessageDialog(null, "Error. No hay un alumno registrado con ese carnet");
                    } else {
                        opcion2 = Integer.parseInt(JOptionPane.showInputDialog("Bienvenido al sistema de revision de nota\n1. Revision\n2. Correcion\nCualquier otro para salir"));
                        if (opcion2 == 1) { //revision (no puede bajar la nota)
                            do {
                                cualNota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de nota a cambiar"));
                                if (cualNota < 0 || cualNota > 4) {
                                    JOptionPane.showMessageDialog(null, "Error. Ingrese un valor valido");
                                }
                            } while (cualNota < 0 || cualNota > 10);
                            do {
                                nota = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota #" + cualNota));
                                if (nota < 0 || nota > 10) {
                                    JOptionPane.showMessageDialog(null, "Error. Ingrese una nota valida");
                                }
                            } while (nota < 0 || nota > 10);

                            if (nota < Double.parseDouble(alumnos[cualFila][cualNota + 2])) { //es menor
                                JOptionPane.showMessageDialog(null, "La nota no puede bajar");
                            } else {
                                alumnos[cualFila][cualNota + 2] = String.valueOf(nota);
                                JOptionPane.showMessageDialog(null, "Nota modificada con exito");
                            }
                        } else if (opcion2 == 2) { // correccion (puede bajar la nota)
                            do {
                                cualNota = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de nota a cambiar"));
                                if (cualNota < 0 || cualNota > 4) {
                                    JOptionPane.showMessageDialog(null, "Error. Ingrese un valor valido");
                                }
                            } while (cualNota < 0 || cualNota > 10);
                            do {
                                nota = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la nota #" + cualNota));
                                if (nota < 0 || nota > 10) {
                                    JOptionPane.showMessageDialog(null, "Error. Ingrese una nota valida");
                                }
                            } while (nota < 0 || nota > 10);

                            alumnos[cualFila][cualNota + 2] = String.valueOf(nota);
                            JOptionPane.showMessageDialog(null, "Nota modificada con exito");

                        }
                    }
                }

            } else { // salir del menu
                salir = true;
            }

        }

    }

}
