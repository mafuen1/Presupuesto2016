/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.security.MessageDigest;

/**
 *
 * @author MaFuen1
 */
public class Utilities {
    
    public  String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }
    
    public String priceWithDecimal (Double price) {
        NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
        //Si se desea forzar el formato español:
        //formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es","ES"));
        String result=formatoImporte.format(price);
        
        //Muestra -1.234,56 €         
        return result;
    }

    
    

    public String priceWithoutCommas (String s) throws ParseException {
      NumberFormat cf = NumberFormat.getCurrencyInstance();
      Number number = null;

      number = cf.parse(s);
 
      double dClone = number.doubleValue();    
      
      return String.valueOf(dClone);
    }       
    
        
    
   public String getMes(){
       String mes=""; 
       Calendar cal = Calendar.getInstance();            
        
        int month = cal.get(Calendar.MONTH)+1;
         
            if (month == 1)
                mes = "ENERO";
            else
                if (month == 2)
                    mes = "FEBRERO";
                else
                    if (month == 3)
                        mes = "MARZO";
                    else
                        if (month == 4)
                            mes = "ABRIL";
                        else
                            if (month == 5)
                               mes = "MAYO";
                            else
                                if (month == 6)
                                    mes = "JUNIO";
                                else
                                    if (month == 7)
                                       mes = "JULIO";
                                    else
                                        if (month == 8)
                                           mes = "AGOSTO";
                                        else
                                            if (month == 9)
                                                mes = "SETIEMBRE";
                                            else
                                                if (month == 10)
                                                    mes = "OCTUBRE";
                                                else
                                                    if (month == 11)
                                                        mes = "NOVIEMBRE";
                                                    else
                                                        if (month == 12)
                                                            mes = "DICIEMBRE";
                    
        
        return mes;
    }  
   
   
    public String getDate(){
        Calendar cal = Calendar.getInstance();
            
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
            
        String dia = String.valueOf(day);
        String mes = String.valueOf(month);
        String ano = String.valueOf(year);
        
        if (dia.length()==1)
            dia = "0"+dia;
        if (mes.length()==1)
            mes = "0"+mes;
        
        return dia+"/"+mes+"/"+ano;
    }  
    

    public int getDay(){
        Calendar cal = Calendar.getInstance();
                   
        int day = cal.get(Calendar.DAY_OF_MONTH);
                  
        return day;
    } 
    
    public int getMonth(){
        Calendar cal = Calendar.getInstance();
                   
        int valor = cal.get(Calendar.MONTH)+1;
                  
        return valor;
    }     
    
    public int getYear(){
        Calendar cal = Calendar.getInstance();
                   
        int valor = cal.get(Calendar.YEAR);
                  
        return valor;
    }     
    
    //DD/MM/YYYY
    public String getDay(String fecha){
        String temp = fecha;
        String arr[]= temp.split("/");
        
        
        return arr[0].trim();
    }
    public String getMonth(String fecha){
        String temp = fecha;
        String arr[]= temp.split("/");
        
        
        return arr[1].trim();
    }   
    public String getYear(String fecha){
        String temp = fecha;
        String arr[]= temp.split("/");
        
        
        return arr[2].trim();
    }      
    
    
   public String [] getArrayString (ArrayList<String> rowData){
       
       Object obj [] = rowData.toArray();
       
       String va [] = new String[obj.length];
       
       for (int i=0; i<obj.length; i++)
       {
           va[i] = String.valueOf(obj [i]);
       }
       return va;
   }
    
   
public String encriptar (String valor) 
    {
        String passwordToHash = valor;
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        return generatedPassword;
    }   
   
   /*

 public String [][]  UnirVectores (String A[][], String B[][]){
 
     int largoA = A.length;
     int largoB = B.length;
     
     
     int newlargo = largoA+largoB;
     
     
     
     for (int i=0; i<largoA; i++)
     
     
     
     
 
 }
    */
    
}
