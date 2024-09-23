/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.company.conectarjdbc;

import java.sql.Connection; // Importa la clase Connection para conectarse a la base de datos
import java.sql.DriverManager; // Importa la clase DriverManager para gestionar los drivers de la base de datos
import java.sql.PreparedStatement; // Importa PreparedStatement para ejecutar consultas parametrizadas
import java.sql.ResultSet; // Importa ResultSet para almacenar los resultados de las consultas
import java.sql.SQLException; // Importa SQLException para manejar errores en SQL
import java.sql.Statement; // Importa Statement para ejecutar consultas SQL

/**
 *
 * @author andres
 */
public class ConectarJDBC {

    public static void main(String[] args) {
        System.out.println("Inicio");
        // Información de conexión a la base de datos
        //jdbc:mysql://localhost:3306/mi_base_datos?zeroDateTimeBehavior=CONVERT_TO_NULL
        String url = "jdbc:mysql://localhost:3306/mi_base_de_datos"; // URL de la base de datos
        String user = "mi_usuario"; // Usuario de la base de datos
        String password = "mi_contraseña"; // Contraseña del usuario

        // Variables para gestionar la conexión y consultas
        Connection connection = null; // Variable para almacenar la conexión a la base de datos
        Statement statement = null; // Variable para ejecutar consultas SQL
        ResultSet resultSet = null; // Variable para almacenar los resultados de las consultas

        try {
            // Establece la conexión con la base de datos
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión establecida con éxito.");

            // Crea una consulta SQL para seleccionar todos los clientes
            String sql = "SELECT id, nombre, cuenta FROM clientes"; // Consulta SQL para seleccionar clientes
            statement = connection.createStatement(); // Crea un objeto Statement para ejecutar la consulta
            resultSet = statement.executeQuery(sql); // Ejecuta la consulta y almacena los resultados

            // Itera sobre los resultados de la consulta
            while (resultSet.next()) {
                // Obtiene los datos de cada cliente
                int id = resultSet.getInt("id"); // Obtiene el ID del cliente
                String nombre = resultSet.getString("nombre"); // Obtiene el nombre del cliente
                String cuenta = resultSet.getString("cuenta"); // Obtiene la cuenta del cliente

                // Muestra los datos del cliente
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Cuenta: " + cuenta);
            }

            // Ejemplo de uso de PreparedStatement para una consulta segura
            String insertSQL = "INSERT INTO clientes (id, nombre, cuenta) VALUES (?, ?, ?)"; // Consulta SQL con parámetros
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL); // Crea un PreparedStatement

            // Establece los valores de los parámetros
            preparedStatement.setInt(1, 4); // Establece el ID del nuevo cliente
            preparedStatement.setString(2, "Nuevo Cliente"); // Establece el nombre del nuevo cliente
            preparedStatement.setString(3, "123456"); // Establece la cuenta del nuevo cliente

            // Ejecuta la consulta de inserción
            preparedStatement.executeUpdate(); // Inserta el nuevo cliente en la base de datos
            System.out.println("Nuevo cliente insertado con éxito.");

        } catch (SQLException e) {
            // Maneja cualquier error que ocurra durante la conexión o consulta
            e.printStackTrace(); // Muestra el error en la consola
        } finally {
            // Cierra los recursos en el orden inverso en que fueron abiertos
            try {
                if (resultSet != null) {
                    resultSet.close(); // Cierra el ResultSet
                }
                if (statement != null) {
                    statement.close(); // Cierra el Statement
                }
                if (connection != null) {
                    connection.close(); // Cierra la conexión
                }
            } catch (SQLException e) {
                e.printStackTrace(); // Maneja cualquier error al cerrar los recursos
            }
        }
    }
}
