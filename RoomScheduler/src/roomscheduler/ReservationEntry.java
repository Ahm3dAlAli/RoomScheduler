package com.roomscheduler;


import java.sql.Date;
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohammedal-ali
 */
public class ReservationEntry {
    private  String Faculty1;
    private  String Room1;
    private  Date Date1;
    private  int Seats1;
    private  Timestamp currentTimestamp1;

    public ReservationEntry(String Faculty, String Room, Date Date, int Seats, Timestamp currentTimestamp) 
    {
        setFaculty(Faculty);
        setRoom(Room);
        setDate(Date);
        setSeats(Seats);
        setTimestamp(currentTimestamp);
    }
    
  
    public  void setFaculty(String Faculty){
        this.Faculty1=Faculty;    
    }
    public  String getFaculty(){
        return this.Faculty1;
    }
    public  void setRoom(String Room){
        this.Room1=Room;    
    }
    public  String getRoom(){
        return this.Room1;
    }
    public  void setTimestamp(Timestamp currentTimestamp){
        this.currentTimestamp1=currentTimestamp;
    }
    public  Timestamp getTimestamp(){
        return currentTimestamp1;
    }
    public  void setDate(Date Date){
        this.Date1=Date;
    }
    public  Date getDate(){
        return this.Date1;
    }
    public  void setSeats(int Seats){
        this.Seats1=Seats;
    }
    public  int getSeats(){
        return this.Seats1;
    }
    
    }
    

