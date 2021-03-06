/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.json;
import com.reservation.Reservation;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import javax.servlet.http.HttpSession;  

import com.reservation.Reservation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class PNRDetailsServlet extends HttpServlet { 
    private static final long serialVersionUID = 1L;
    // This will store all received articles
    
    List<Article> articles = new LinkedList<Article>();
    public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
        System.out.println("##############################%%%%%%%%%%");
        String demo=(String) req.getSession(false).getAttribute("userName");
        System.out.println(demo);
        
        PrintWriter out=res.getWriter();
        int userId=Integer.valueOf(demo);
        System.out.println("com.json.PNRDetailsServlet.doPost()");
        System.out.println(userId);
        ArrayList<PassengerTicketDetails> pnrTable=Reservation.readPNRDetailsByUserId(userId);
       
//        JSONObject jo=new JSONObject();
//        JSONArray ja=new JSONArray();
//        try {
//            jo.put("mohan", "asd");
//        } catch (JSONException ex) {
//            Logger.getLogger(RouteServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        for(int i=0;i<pnrTable.size();i++)
        {
            System.out.println(pnrTable.get(i).getPnrId());
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, pnrTable);
    }
}