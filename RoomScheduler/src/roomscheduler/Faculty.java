package com.roomscheduler;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acv
 */
public class Faculty
{
    private static Connection connection;
    private static final ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addFaculty;
    private static PreparedStatement getFacultyList;
    private static ResultSet resultSet;
    
    public static void addFaculty(String name)
    {   
        connection = DBConnection.getConnection();
        try
        { 
            addFaculty = connection.prepareStatement("insert into FACULTY(FACULTYNAME) values (?)");
            addFaculty.setString(1, name);
            addFaculty.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getFacultyList()
    {
        connection = DBConnection.getConnection();
        ArrayList<String> faculty1 = new ArrayList<>();
        try
        {
            getFacultyList = connection.prepareStatement("select FACULTYNAME from FACULTY order by FACULTYNAME");
            resultSet = getFacultyList.executeQuery();
            
            while(resultSet.next())
            {
                faculty1.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return faculty1;
        
        
    }
    
    
}
