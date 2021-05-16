package com.roomscheduler;


import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Connection;
import java.sql.Date;
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
 * @author mohammedal-ali
 */
 public class Dates {
        private static Connection connection;
        private static ArrayList<Date> dates = new ArrayList<>();
        private static PreparedStatement addDate;
        private static PreparedStatement getAllDates;
        private static ResultSet resultSet;
   
        public static ArrayList<Date> getAllDates(){
            connection = DBConnection.getConnection();
            //ArrayList<String> dates = new ArrayList<>();
            ArrayList<Date> date1 =new ArrayList<Date>();
        try
        {
            getAllDates = connection.prepareStatement("select DATE from DATES order by DATE");
            resultSet = getAllDates.executeQuery();
            
            while(resultSet.next())
            {
                //dates.add(resultSet.getDate(1).toString());
                date1.add(resultSet.getDate(1));
            }
        }
        catch(SQLException sqlException)
        {
        }
        return date1; 
            
        }
        public static void addDate(Date date){

            connection = DBConnection.getConnection();
            
            try{
               
                addDate = connection.prepareStatement("INSERT INTO DATES(DATE) VALUES (?)");
                addDate.setDate(1,date);
                addDate.executeUpdate();

            }
            catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
 }
 }