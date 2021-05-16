package com.roomscheduler;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohammedal-ali
 */
public class RoomQueries {
     
    private static Connection connection;

    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static PreparedStatement getRooms;
    private static PreparedStatement getAvilaRoomForSeats;
    private static ResultSet resultSet;

    public static ArrayList<String> getAllPossibleRooms(int seats) {
        connection = DBConnection.getConnection();
        ArrayList<String> room = new ArrayList<>();
        try {
            getAllPossibleRooms = connection.prepareStatement("select name from ROOMS where seats >= ? order by seats");
            getAllPossibleRooms.setInt(1, seats);
            resultSet = getAllPossibleRooms.executeQuery();
            while (resultSet.next()) {
                room.add(resultSet.getString("NAME"));
            }
        } catch (SQLException sqlException) {
        }
        return room;
    }
    
     public static ArrayList<String> getAllRooms() {
        connection = DBConnection.getConnection();
        ArrayList<String> all_rooms = new ArrayList<>();
        try {
            PreparedStatement getAllRooms = connection.prepareStatement("select name from ROOMS");
            resultSet = getAllRooms.executeQuery();
            while (resultSet.next()) {
                all_rooms.add(resultSet.getString("NAME"));
            }
        } catch (SQLException sqlException) {
        }
        return all_rooms;
    }
    
    public static ArrayList<Integer> getallseats(){
        connection = DBConnection.getConnection();
        ArrayList<Integer> rooms = new ArrayList<>();
        try {
            getRooms = connection.prepareStatement("SELECT SEATS FROM ROOMS");
            resultSet = getRooms.executeQuery();
            while (resultSet.next()) {
                rooms.add(resultSet.getInt("SEATS"));
            }        } catch (SQLException sqlException) {
        }
        return rooms;
        
    }

    public static void addRoom(String RN, int seatTotal) {
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into ROOMS (NAME,SEATS) values (?,?)");
            addRoom.setString(1, RN);
            addRoom.setInt(2, seatTotal);
            addRoom.executeUpdate();
        } catch (SQLException sqlException) {
        }

    }

    public static void dropRoom(String RN) {
        connection = DBConnection.getConnection();
        try {
            dropRoom = connection.prepareStatement("DELETE FROM ROOMS WHERE NAME=?");
            dropRoom.setString(1,RN);
            dropRoom.executeUpdate();
        } catch (SQLException sqlException) {
        }

    }

    public static String getRoomForSeats(int seats) {

        connection = DBConnection.getConnection();
        //ArrayList<String> Roo = new ArrayList<>();
        String result="";
        try {
            PreparedStatement getRoomForSeats = connection.prepareStatement("SELECT NAME FROM ROOMS WHERE SEATS=? ORDER BY SEATS DESC");
            getRoomForSeats.setInt(1, seats);
            ResultSet resultset = getRoomForSeats.executeQuery();
            while (resultset.next() ) {
                result=resultset.getString("NAME");
                
            }
        } catch (SQLException sqlException) {
        }
        
        return result;
    }

    public static int getCapacityForRooms(String roomName) {
        int result=0;
        connection = DBConnection.getConnection();
        try{
            PreparedStatement getCapacityForRooms = connection.prepareStatement("SELECT SEATS FROM ROOMS WHERE NAME=?");
            getCapacityForRooms.setString(1, roomName);
            ResultSet resultSet1 = getCapacityForRooms.executeQuery();
            while (resultSet1.next()){
                result = resultSet1.getInt("SEATS");
            }
        }catch (SQLException sqlException) {
        }
        return result;
    }

    public static ArrayList<String> getRooms() {
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<>();
        try {
            getRooms = connection.prepareStatement("SELECT NAME FROM ROOMS");
            resultSet = getRooms.executeQuery();
            while (resultSet.next()) {
                rooms.add(resultSet.getString("NAME"));
            }
        } catch (SQLException sqlException) {
        }
        return rooms;
    }

    public static ArrayList<Integer> getSeatsForUn(List<String> unreservedList) {
        
        connection = DBConnection.getConnection();
        
        ArrayList<Integer> unseats = new ArrayList<>();
        try {

            for (String i : unreservedList) {
                PreparedStatement getSeatsForUn = connection.prepareStatement("SELECT SEATS FROM ROOMS WHERE NAME=?");
                getSeatsForUn.setString(1, i);
                try (ResultSet resultSet2 = getSeatsForUn.executeQuery();) {
                    resultSet2.next();
                    unseats.add(resultSet2.getInt("SEATS"));
                } catch (SQLException sqlException) {
                }
            }
        } catch (SQLException sqlException) {
        }

        return unseats;

    }
    
        public static ArrayList<String> getAvilaRoomForSeats(Date date,int seats ) {

        connection = DBConnection.getConnection();
        
        ArrayList<String> result11= new ArrayList<String>();
        try {
            getAvilaRoomForSeats = connection.prepareStatement("SELECT NAME FROM ROOMS WHERE NAME NOT IN (SELECT ROOMNUM FROM RESERVATIONS WHERE DATERE = ? ) AND SEATS>= ? ORDER BY SEATS ASC"); 
            getAvilaRoomForSeats.setDate(1, date);
            getAvilaRoomForSeats.setInt(2, seats);
            
            resultSet = getAvilaRoomForSeats.executeQuery();
            
            while (resultSet.next()) {
                result11.add(resultSet.getString("NAME"));
            }
        } catch (SQLException sqlException) {
        }
        System.out.println(result11.isEmpty());
        return result11;
    }
}