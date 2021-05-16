package com.roomscheduler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acv
 */
public class DBConnection
{

    private static Connection connection;
    private static final String user = "aaa6542";
    private static final String password = "java";
    private static final String database = "jdbc:derby://localhost:1527/RoomSchedulerAhmed9361";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(database, user, password);
            } catch (SQLException e)
            {
                System.out.println("Could not open database.");
                System.exit(1);

            }
        }
        return connection;
    }

}
