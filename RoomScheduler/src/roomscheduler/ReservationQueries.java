package com.roomscheduler;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohammedal-ali
 */
public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement cancelReservation;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement deleteReservation;
    
    static private ResultSet resultSet;
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String faculty){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations=new ArrayList<>();
        
        
        try{
            getReservationsByFaculty=connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE FACULTYNAME=? ");
            getReservationsByFaculty.setString(1,faculty);
            resultSet=getReservationsByFaculty.executeQuery();
            
            while(resultSet.next()){
                reservations.add(new ReservationEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
                
            }
        }
        catch(SQLException sqlException)
        {
        }
        return reservations;
        
                
        }
    
   
    
    public static void addReservationEntry(String faculty,String roomna,Date date,int seats,Timestamp currentTimestamp)
    {
        connection = DBConnection.getConnection();
        //currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
            addReservationEntry = connection.prepareStatement("insert into RESERVATIONS(FACULTYNAME,ROOMNUM,DATERE,NUMSEATS,TIMESTAMPRES)values(?,?,?,?,?)");
            addReservationEntry.setString(1,faculty);
            addReservationEntry.setString(2,roomna);
            addReservationEntry.setDate(3,date);
            addReservationEntry.setInt(4,seats);
            addReservationEntry.setTimestamp(5,currentTimestamp);
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
        }
        
    }
    
    public static ArrayList<String> getRoomsReservedByDate(Date date){
 
        connection = DBConnection.getConnection();
        
        ArrayList<String> reservedDates = new ArrayList<>();
        

        try{
            getRoomsReservedByDate=connection.prepareStatement("SELECT ROOMNUM FROM RESERVATIONS WHERE DATERE=? order by DATERE");
            getRoomsReservedByDate.setDate(1,date);
            resultSet=getRoomsReservedByDate.executeQuery();
            while(resultSet.next())
            {
                reservedDates.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
        }
        return reservedDates;
        
        
    }
    public static ArrayList<String> getRoomsReserved(){
         connection = DBConnection.getConnection();
        ArrayList<String> reservedrooms = new ArrayList<>();

        try{
            PreparedStatement getRoomsReserved=connection.prepareStatement("SELECT ROOMNUM FROM RESERVATIONS order by DATERE");
            resultSet=getRoomsReserved.executeQuery();
            while(resultSet.next())
            {
                reservedrooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservedrooms;
        
    }
    public static ArrayList<String> getFacultyOnReservationList(){
         connection = DBConnection.getConnection();
        ArrayList<String> faculty = new ArrayList<>();

        try{
            PreparedStatement getFacultyOnReservationList=connection.prepareStatement("SELECT FACULTYNAME FROM RESERVATIONS");
            resultSet=getFacultyOnReservationList.executeQuery();
            while(resultSet.next())
            {
                faculty.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
        }
        return faculty;
        
    }
    public static ArrayList<String> getFacultybyReservedDates(Date date){
 
        connection = DBConnection.getConnection();
        ArrayList<String> reservedfaculty = new ArrayList<>();

        try{
            getRoomsReservedByDate=connection.prepareStatement("SELECT FACULTYNAME FROM RESERVATIONS WHERE DATERE=? order by DATERE");
            getRoomsReservedByDate.setDate(1,date);
            resultSet=getRoomsReservedByDate.executeQuery();
            while(resultSet.next())
            {
                reservedfaculty.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
        }
        return reservedfaculty;
        
        
    }
    
    public static void cancelReservation(String faculty,Date date){
        connection = DBConnection.getConnection();
        try
        {
            cancelReservation=connection.prepareStatement("DELETE FROM RESERVATIONS WHERE FACULTYNAME=? AND DATERE=?");
            cancelReservation.setString(1,faculty);
            cancelReservation.setDate(2,date);
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
        }
        

    }
    public static void deleteReservation(String roomname){
        connection = DBConnection.getConnection();
        try
        {
            //System.out.println("ROOM: "+roomname);
            deleteReservation=connection.prepareStatement("DELETE FROM AAA6542.RESERVATIONS WHERE ROOMNUM=?");
            deleteReservation.setString(1,roomname);
            deleteReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        

    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(Date date){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationlist=new ArrayList<ReservationEntry>();
        
        
        try{
            java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
            PreparedStatement getReservationsByDate1=connection.prepareStatement("Select * FROM RESERVATIONS WHERE DATERE=?");
            getReservationsByDate1.setDate(1,date);
            resultSet=getReservationsByDate1.executeQuery();
            
            while(resultSet.next()){
            reservationlist.add(new ReservationEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            
            
        }
        }
        catch(SQLException sqlException)
        {
        }
        
        return reservationlist;
        
    
    }
    public static ArrayList<ReservationEntry> getReservationsByRoom(String  roomname1){
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservationlist=new ArrayList<>();
        try{
            PreparedStatement getReservationsByRoom=connection.prepareStatement("SELECT * FROM RESERVATIONS WHERE ROOMNUM=? ");
            getReservationsByRoom.setString(1,roomname1);
            resultSet=getReservationsByRoom.executeQuery();
            
            while(resultSet.next())
            {
                reservationlist.add(new ReservationEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getDate(3),resultSet.getInt(4),resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException)
        {
        }
        
        return reservationlist;
    
    }


    
}