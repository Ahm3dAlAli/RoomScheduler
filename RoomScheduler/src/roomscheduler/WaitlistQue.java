package com.roomscheduler;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;


public class WaitlistQue {
    
    
    private static Connection connection;
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static ResultSet resultSet;
    
    
    public static ArrayList<WaitlistEntry> getWaitlistByDate(Date date)
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitingList = new ArrayList<>();
        
        try {
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            getWaitlistByDate = connection.prepareStatement("Select * FROM WAITLIST WHERE DATE=?");
            getWaitlistByDate.setDate(1, date);
            resultSet = getWaitlistByDate.executeQuery();

            while (resultSet.next()) {
                waitingList.add(new WaitlistEntry(resultSet.getString(1),resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
         
            }
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return waitingList;
    }
    
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty)
    {
        connection = DBConnection.getConnection();
        
        ArrayList<WaitlistEntry> waitingList = new ArrayList<WaitlistEntry>();
       
        try {
            getWaitlistByFaculty = connection.prepareStatement("SELECT FACULTY,DATE,SEATS,TIMESTAMP FROM WAITLIST WHERE FACULTY=? order by FACULTY");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();

            while (resultSet.next()) {
                waitingList.add(new WaitlistEntry(
                        resultSet.getString("FACULTY"),
                        resultSet.getDate("DATE"),
                        resultSet.getInt("SEATS"),
                        resultSet.getTimestamp("TIMESTAMP")));

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return waitingList;
    }
    
    public static void addWaitlistEntry(String faculty, Date date, int seats, Timestamp currentTimestamp)
    {
        connection = DBConnection.getConnection();
        try {
            addWaitlistEntry = connection.prepareStatement("insert into WAITLIST(FACULTY,DATE,SEATS,TIMESTAMP)values(?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, currentTimestamp);
            addWaitlistEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteWaitlistEntry(String name)
    {
        connection = DBConnection.getConnection();
        try {
            deleteWaitlistEntry = connection.prepareStatement("DELETE FROM WAITLIST WHERE FACULTY=?");
            deleteWaitlistEntry.setString(1, name);
            deleteWaitlistEntry.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    
     public static ArrayList<WaitlistEntry> getWaitlist()
    {
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitingList = new ArrayList<>();
        
        try {
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            getWaitlistByDate = connection.prepareStatement("SELECT * FROM WAITLIST ORDER BY DATE,TIMESTAMP ASC ");
            
            resultSet = getWaitlistByDate.executeQuery();

            while (resultSet.next()) {
                waitingList.add(new WaitlistEntry(resultSet.getString(1),resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
         
            }
            
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return waitingList;
    }
    
}





//Faculty Rooms Dates Reservations Waitlist
