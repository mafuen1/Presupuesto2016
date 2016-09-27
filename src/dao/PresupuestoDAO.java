package dao;

import java.sql.Array;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import org.joda.time.DateTime;
import presupuesto.DialogError;
import presupuesto.Utilities;
import java.sql.CallableStatement;


public class PresupuestoDAO
{
    
  private static PresupuestoDAO instance = null;  
  
  Connection con;    
  Utilities utils=new Utilities();
  
  
  
    String idusuario_logueado;

    public String getIdusuario_logueado() {
        return idusuario_logueado;
    }

    public void setIdusuario_logueado(String idusuario_logueado) {
        this.idusuario_logueado = idusuario_logueado;
    }

      
    public static PresupuestoDAO getInstance() throws SQLException 
    {
            if(instance == null) {
                    instance = new PresupuestoDAO();
            }
            return instance;
    }    
      
  
  private PresupuestoDAO() throws SQLException
  {   
        
  }

    
  public void connect (String base_datos) throws SQLException{
         DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
 
        con=DriverManager.getConnection(  
            "jdbc:oracle:thin:@"+base_datos+":xe","presupuesto","smp240383");           
            //    "jdbc:oracle:thin:@186.15.11.42:1522:xe","PRESUPUESTO","200125433");           
      //  "jdbc:oracle:thin:@localhost:1521:xe","PRESUPUESTO","200125433");     
  
  }
        

  public void sp_limpiar (String idpresupuesto) throws SQLException 
  {    

        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call LIMPIAR(?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idpresupuesto));
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();                
  } 
  
  
  public String  sp_insertar_presupuesto (String quincena) throws SQLException 
  {    

        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ ? = call  INSERTAR_PRESUPUESTO(?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.registerOutParameter(1,  java.sql.Types.NUMERIC);
        prcProcedimientoAlmacenado.setString  (2,quincena);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        
        
        String idpre = String.valueOf(  prcProcedimientoAlmacenado.getInt(1) );
        
        // confirmar si se ejecuto sin errores
        con.commit();   
        
        return idpre;
  }   
  
  
            public void sp_cargar (String idpresupuesto) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call CARGAR(?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idpresupuesto));
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
          
  } 

    public void sp_eliminar_rubro (String idrubro) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call ELIMINAR_RUBRO(?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idrubro));
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
          
  } 
    

    public void sp_modificar_rubro (String idrubro,String nombre, double monto, String tipo_pago) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call MODIFICAR_RUBRO(?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idrubro));
        prcProcedimientoAlmacenado.setString  (2,nombre);
        prcProcedimientoAlmacenado.setDouble(3,monto);
        prcProcedimientoAlmacenado.setString  (4,tipo_pago);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
          
  }     
    

    public void sp_insertar_rubro (String idpresupuesto,String nombre, double monto, String tipo_pago) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call INSERTAR_RUBRO(?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idpresupuesto));
        prcProcedimientoAlmacenado.setString  (2,nombre);
        prcProcedimientoAlmacenado.setDouble(3,monto);
        prcProcedimientoAlmacenado.setString  (4,tipo_pago);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
          
  }       
    
    public void sp_insertar_gasto_as_rubro (String idpresupuesto,String idgasto) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call INSERTAR_GASTO_AS_RUBRO(?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idpresupuesto));
        prcProcedimientoAlmacenado.setString  (2,idgasto);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
          
  }         
  

    public void sp_eliminar_gasto (String idgasto, String idrubro) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call ELIMINAR_GASTO(?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idgasto));
        prcProcedimientoAlmacenado.setInt   (2,Integer.valueOf(idrubro));
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();      
      
    
  } 

    
    public void sp_pagar_rubro (String idrubro, String idpresupuesto, String lugar,String tipo_pago) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call PAGAR_RUBRO(?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idrubro));
        prcProcedimientoAlmacenado.setInt   (2,Integer.valueOf(idpresupuesto));
        prcProcedimientoAlmacenado.setString  (3,lugar);
        //prcProcedimientoAlmacenado.setDouble   (4, monto);
        prcProcedimientoAlmacenado.setString   (4,tipo_pago);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();            
    
  } 
    
  
  public void sp_modificar_gasto (String idgasto, String lugar, double monto, String tipo_pago,String fecha) throws SQLException 
  {    

try {
        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call MODIFICAR_GASTO(?,?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idgasto));        
        prcProcedimientoAlmacenado.setString  (2,lugar);
        prcProcedimientoAlmacenado.setDouble   (3, monto);
        prcProcedimientoAlmacenado.setString   (4,tipo_pago);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                 
        Date date1;

            date1 = formatter.parse(fecha);

        java.sql.Date datetmp = new java.sql.Date(date1.getTime());  

        prcProcedimientoAlmacenado.setDate (5,datetmp);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();
      } catch (ParseException ex) {
          Logger.getLogger(PresupuestoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }        
    
  }       
    
  
    public void sp_insertar_gasto (String idrubro, String idpresupuesto, String lugar, double monto, String tipo_pago,String fecha) throws SQLException 
  {    

try {
        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call INSERTAR_GASTO(?,?,?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idrubro));
        prcProcedimientoAlmacenado.setInt   (2,Integer.valueOf(idpresupuesto));
        prcProcedimientoAlmacenado.setString  (3,lugar);
        prcProcedimientoAlmacenado.setDouble   (4, monto);
        prcProcedimientoAlmacenado.setString   (5,tipo_pago);
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                 
        Date date1;

            date1 = formatter.parse(fecha);

        java.sql.Date datetmp = new java.sql.Date(date1.getTime());  

        prcProcedimientoAlmacenado.setDate (6,datetmp);
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();
      } catch (ParseException ex) {
          Logger.getLogger(PresupuestoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }        
    
  }     
    
    
    

    public void sp_insertar_pendiente_presupuesto (String idfinanciado, String idpresupuesto, String lugar, String tipo_pago) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call INSERTAR_PENDIENTE_PRESUPUESTO(?,?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idfinanciado));        
        prcProcedimientoAlmacenado.setInt   (2,Integer.valueOf(idpresupuesto));        
        prcProcedimientoAlmacenado.setString  (3,lugar);                    
        prcProcedimientoAlmacenado.setString   (4,tipo_pago);
        
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();

  }     
    

    public void sp_asignar_gasto_a_rubro (String idgasto,  String tipo_pago, String idrubro) throws SQLException 
  {    


        CallableStatement prcProcedimientoAlmacenado = con.prepareCall("{ call ASIGNAR_GASTO_A_RUBRO(?,?,?) }");
        // cargar parametros al SP
        prcProcedimientoAlmacenado.setInt   (1,Integer.valueOf(idgasto));
        prcProcedimientoAlmacenado.setString  (2,tipo_pago);
        prcProcedimientoAlmacenado.setInt   (3,Integer.valueOf(idrubro));
        
        // ejecutar el SP
        prcProcedimientoAlmacenado.execute();
        // confirmar si se ejecuto sin errores
        con.commit();            
    
  }         
  
 
  public String getMontoPresupuestoDisponibleQuincena1 () throws SQLException 
  {    
      String monto="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(QUINCENA1) AS TOTAL FROM USUARIOS";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      while(rs.next())
        monto = utils.priceWithDecimal(rs.getDouble("TOTAL"));

      rs.close();
      stmt.close();
      
    return monto;
  } 
  /*
    public double getMontoPresupuestoDisponibleQuincena1XTIPOPAGO (String tipopago) throws SQLException 
  {    
      double monto=0;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(QUINCENA1) AS TOTAL FROM USUARIOS  WHERE TIPO_PAGO = '"+tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      while(rs.next())
        monto = rs.getDouble("TOTAL");

      rs.close();
      stmt.close();
      
    return monto;
  } 
  
  
    public double getMontoPresupuestoDisponibleQuincena2XTIPOPAGO (String tipopago) throws SQLException 
  {    
      double monto=0;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(QUINCENA2) AS TOTAL FROM USUARIOS  WHERE TIPO_PAGO = '"+tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      while(rs.next())
        monto = rs.getDouble("TOTAL");

    return monto;
  }     */
    
    
  public String getMontoPresupuestoDisponibleQuincena2 () throws SQLException 
  {    
    String monto="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(QUINCENA2) AS TOTAL FROM USUARIOS";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        monto =   utils.priceWithDecimal(rs.getDouble("TOTAL"));

           rs.close();
      stmt.close();
      
    return monto;
  } 
  
 
  public String getPresupuestoActivo () throws SQLException 
  {    
    String pres;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT PRESUPUESTO FROM PRESUPUESTO WHERE ESTADO='ACTIVO'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      rs.next();
      pres =   rs.getString("PRESUPUESTO");

            rs.close();
      stmt.close();
      
    return pres;
  } 
   

  
  
    public String getRubroFinanciadoMontoPagado (String idfinanciado) throws SQLException 
  {    
    String monto="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO) AS TOTAL FROM FINANCIADO_PAGOS WHERE IDFINANCIADO="+idfinanciado;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        monto = utils.priceWithDecimal(rs.getDouble("TOTAL"));

            rs.close();
      stmt.close();
      
    return monto;
  } 
    
   

 public double totalGastado (String idpresupuesto) throws SQLException 
  {    
    double n=0;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO) AS TOTAL FROM GASTOS WHERE IDPRESUPUESTO=" + idpresupuesto ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      rs.next();
      n =  rs.getDouble("TOTAL");

    return n;
  }   


  public int getIDPresupuesto(String nombre) throws SQLException 
  {    

    int idpresupuesto=0;

    Statement stmt = con.createStatement();
    String strSQL = "SELECT IDPRESUPUESTO FROM PRESUPUESTO WHERE PRESUPUESTO='" + nombre +"'";

    ResultSet rs = stmt.executeQuery(strSQL);      

    while (rs.next())
      idpresupuesto =  rs.getInt("IDPRESUPUESTO");

          rs.close();
      stmt.close();
    
    return idpresupuesto;
  }  
  

  public String getEstadoPresupuesto(int idpresupuesto) throws SQLException 
  { 
    String estado=null;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT ESTADO FROM PRESUPUESTO WHERE IDPRESUPUESTO=" + idpresupuesto;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while(rs.next())      

      estado =  rs.getString("ESTADO");
   
            rs.close();
      stmt.close();
      
    return estado;
  }  
  
   public String getDiaFechaCorte2 (String tipopago) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
     
      String strSQL = "SELECT FECHA_CORTE FROM TIPO_PAGO  WHERE TIPO_PAGO='" + tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_CORTE");
   
            rs.close();
      stmt.close();
      
    return dia;
  } 
   
  public String getDiasPagoMinimo2(String tipopago) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
      
      String strSQL = "SELECT FECHA_PAGO_MINIMO FROM TIPO_PAGO  WHERE TIPO_PAGO='" + tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_PAGO_MINIMO");
        
      rs.close();
      stmt.close();
      
    return dia;
  }    

  
    public String getDiasPagoContado2(String tipopago) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
     
      String strSQL = "SELECT FECHA_PAGO_CONTADO FROM TIPO_PAGO  WHERE TIPO_PAGO='" + tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_PAGO_CONTADO");
   
            rs.close();
      stmt.close();
      
    return dia;
  }    
  
    public String getDiaFechaCorte(String idrubro) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
      
      String strSQL = "SELECT FECHA_CORTE FROM RUBROS R, TIPO_PAGO T  WHERE T.TIPO_PAGO=R.TIPO_PAGO AND R.IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_CORTE");
      
      rs.close();
      stmt.close();
      
    return dia;
  }    
  
  public String getDiasPagoMinimo(String idrubro) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
      
      String strSQL = "SELECT FECHA_PAGO_MINIMO FROM RUBROS R, TIPO_PAGO T  WHERE T.TIPO_PAGO=R.TIPO_PAGO AND R.IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_PAGO_MINIMO");
   
      
      rs.close();
      stmt.close();
      
    return dia;
  }    

  
    public String getDiasPagoContado(String idrubro) throws SQLException 
  { 
    String dia=null;

      Statement stmt = con.createStatement();
      
      String strSQL = "SELECT FECHA_PAGO_CONTADO FROM RUBROS R, TIPO_PAGO T  WHERE T.TIPO_PAGO=R.TIPO_PAGO AND R.IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        dia =  rs.getString("FECHA_PAGO_CONTADO");
   
            rs.close();
      stmt.close();
      
    return dia;
  }    

    
  public String getTotalGastadoxRubro(int idrubro) throws SQLException 
  { 
    String total="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO)AS TOTAL FROM GASTOS WHERE IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
        total = String.valueOf(rs.getDouble("TOTAL")) ;   
      
      rs.close();
      stmt.close();      
      
    return total;
  }

  public String getTotalGastado(int indpresupuesto) throws SQLException 
  { 
    String total="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO)AS TOTAL FROM GASTOS WHERE IDPRESUPUESTO=" + indpresupuesto ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
        total = String.valueOf(rs.getDouble("TOTAL")) ;
   
            rs.close();
      stmt.close();

    return total;
  }
    
    
  public String getTotalGastadoxTipoPago(int indpresupuesto, String tipo_pago) throws SQLException 
  { 
      String total="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO)AS TOTAL FROM GASTOS WHERE IDPRESUPUESTO=" + indpresupuesto + " AND TIPO_PAGO='"+tipo_pago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
           total = String.valueOf(rs.getDouble("TOTAL")) ;
   
            rs.close();
      stmt.close();
      
    return total;
  }

  
  public String getMonto(String idpresupuesto) throws SQLException 
  { 
    String total="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(MONTO)AS TOTAL FROM RUBROS WHERE IDPRESUPUESTO=" + idpresupuesto;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
        total = String.valueOf(rs.getDouble("TOTAL"));
   
            rs.close();
      stmt.close();
      
    return total;
  }
  
   public String getSaldoRubro(int idrubro) throws SQLException 
  { 
    String saldo="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SALDO FROM RUBROS WHERE IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while(rs.next())
        saldo = String.valueOf(rs.getDouble("SALDO"));

            rs.close();
      stmt.close();
      
    return saldo;
  }
   
   

  
  public String getSaldo(String idpresupuesto) throws SQLException 
  { 
    String saldo="0";

      Statement stmt = con.createStatement();
      String strSQL = "SELECT SUM(SALDO)AS TOTAL FROM RUBROS WHERE IDPRESUPUESTO=" + idpresupuesto;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
        saldo = String.valueOf(rs.getDouble("TOTAL"));

            rs.close();
      stmt.close();
      
    return saldo;
  }
  
  
  public int getIdFinanciado(int idrubro) throws SQLException 
  { 
    int n=0;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT IDFINANCIADO FROM RUBROS_FINANCIADO WHERE IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while (rs.next())
        n =  rs.getInt("IDFINANCIADO");
      
            rs.close();
      stmt.close();
      
    return n;
  }  
  
  public TipoPagoObject getTipoPago (String idtipopago) throws SQLException 
  {    
    TipoPagoObject obj = new TipoPagoObject();
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM TIPO_PAGO WHERE IDTIPOPAGO="+idtipopago;
      
      ResultSet rs = stmt.executeQuery(strSQL);             
     
      while(rs.next()){                   
        obj.setIdtipopago(rs.getString("IDTIPOPAGO"));
        obj.setTipo_pago(rs.getString("TIPO_PAGO"));
        obj.setFecha_corte(rs.getString("FECHA_CORTE"));
        obj.setFecha_pago_minimo(rs.getString("FECHA_PAGO_MINIMO"));
        obj.setFecha_pago_contado(rs.getString("FECHA_PAGO_CONTADO"));
        obj.setTipo(rs.getString("TIPO"));
        
      }
      
            rs.close();
      stmt.close();
      
        return obj;    
  }   

  
    public TipoPagoObject getTipoPago2 (String tipopago) throws SQLException 
  {    
    TipoPagoObject obj = new TipoPagoObject();
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM TIPO_PAGO WHERE TIPO_PAGO='"+tipopago+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);             
     
      while(rs.next()){                   
        obj.setIdtipopago(rs.getString("IDTIPOPAGO"));
        obj.setTipo_pago(rs.getString("TIPO_PAGO"));
        obj.setFecha_corte(rs.getString("FECHA_CORTE"));
        obj.setFecha_pago_minimo(rs.getString("FECHA_PAGO_MINIMO"));
        obj.setFecha_pago_contado(rs.getString("FECHA_PAGO_CONTADO"));
        obj.setTipo(rs.getString("TIPO"));
        
      }
      
            rs.close();
      stmt.close();
        return obj;    
  }  
    
  public PendientesObject getPendientesXidrubro (String idrubro) throws SQLException 
  {    
    PendientesObject pen = new PendientesObject();
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO P, RUBROS_FINANCIADO R WHERE R.IDFINANCIADO=P.IDFINANCIADO AND R.IDRUBRO="+idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
     
      while(rs.next()){                   
        pen.setIdentificador(rs.getString("IDFINANCIADO"));
        pen.setNombre(rs.getString("NOMBRE"));
        pen.setPlan(rs.getString("PLAN"));
        pen.setCuotas_pagadas(rs.getString("CUOTAS_PAGADAS"));
        pen.setMonto_total(String.valueOf(rs.getDouble("MONTO_TOTAL")) );
        pen.setMonto_cuota(String.valueOf(rs.getDouble("MONTO_CUOTA")));
        pen.setMonto_pagado(String.valueOf(rs.getDouble("MONTO_PAGADO")));
        pen.setTipo_pago(rs.getString("TIPO_PAGO"));
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");                
        pen.setFecha(df.format(rs.getDate("FECHA"))); 
        
        pen.setEstado(rs.getString("ESTADO")); 
        pen.setQuincena(rs.getString("QUINCENA"));         
        double saldo = rs.getDouble("MONTO_TOTAL")-rs.getDouble("MONTO_PAGADO");        
        pen.setSaldo(String.valueOf(saldo));
      }
      
            rs.close();
      stmt.close();
        return pen;
    
  }    
  
  public double getMontoPagado(int idfinanciado) throws SQLException 
  { 
    double n;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT MONTO_PAGADO FROM FINANCIADO WHERE IDFINANCIADO=" + idfinanciado;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      rs.next();

      n =  rs.getDouble("MONTO_PAGADO");
      
      rs.close();
      stmt.close();
    return n;
  }   
  
   public CuotaObject getNextCuota (String idfinanciado) throws SQLException 
  {    
    CuotaObject object = new CuotaObject();

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO_PAGOS WHERE ESTADO='"+CuotaObject.SIN_ASIGNAR+"' AND IDFINANCIADO=" + idfinanciado +" ORDER BY NUMERO_CUOTA" ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        object.setIdcuota(rs.getString("IDCUOTA"));
        object.setDescripcion(rs.getString("NUMERO_CUOTA"));
        object.setMonto(String.valueOf(rs.getDouble("MONTO")));
      }      
      
      rs.close();
      stmt.close();
      
 
    return object;
  }    
 
   public boolean hayCuotasPagadas (String idfinanciado) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO_PAGOS WHERE  ESTADO='"+CuotaObject.PAGADO+"'  AND IDFINANCIADO=" + idfinanciado  ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
            rs.close();
      stmt.close();
 
    return hay;
  }  
   
   
   
   public boolean hayGastosenPresupuesto (String idpresupuesto) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM GASTOS WHERE IDPRESUPUESTO=" + idpresupuesto  ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
            rs.close();
      stmt.close();
 
    return hay;
  }  
   
   
   public boolean hayPagosAsignadosoPagados (String idfinanciado) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO_PAGOS WHERE ( ESTADO='"+CuotaObject.ASIGNADO+"' OR ESTADO='"+CuotaObject.PAGADO+"'  ) AND IDFINANCIADO=" + idfinanciado +" ORDER BY NUMERO_CUOTA" ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
 
            rs.close();
      stmt.close();
    return hay;
  }  
   
  public boolean sehapagadoelPendiente (String idfinanciado) throws SQLException 
  {    
    boolean hay=true;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO_PAGOS WHERE ( ESTADO='"+CuotaObject.ASIGNADO+"' OR ESTADO='"+CuotaObject.SIN_ASIGNAR+"'  ) AND IDFINANCIADO=" + idfinanciado +" ORDER BY NUMERO_CUOTA" ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=false;
      }      
       rs.close();
      stmt.close();
    return hay;
  }     
    
   
  public GastoObject getGasto(String idgasto) throws SQLException 
  {    
    GastoObject object = new GastoObject();

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM GASTOS WHERE IDGASTO=" + idgasto;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      while (rs.next())
      {                  
        object.setIdgasto(rs.getString("IDGASTO"));
        object.setIdpresupuesto(rs.getString("IDPRESUPUESTO"));
        object.setIdrubro(rs.getString("IDRUBRO"));
        object.setLugar(rs.getString("LUGAR"));
        object.setMonto(String.valueOf(rs.getDouble("MONTO")) );
        object.setTipo_pago(rs.getString("TIPO_PAGO"));
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        object.setFecha(df.format(rs.getDate("FECHA")));  
      }      
       rs.close();
      stmt.close();
    return object;
  }    
    
  public PresupuestoObject getPresupuesto(String quincena) throws SQLException 
  {    
    PresupuestoObject object = new PresupuestoObject();
    object.setIdpresupuesto("0");

    Statement stmt = con.createStatement();
    String strSQL = "SELECT * FROM PRESUPUESTO WHERE PRESUPUESTO='" +quincena+"'";

    ResultSet rs = stmt.executeQuery(strSQL);      

    while (rs.next())
    {                       
      object.setIdpresupuesto(rs.getString("IDPRESUPUESTO"));
      object.setPresupuesto(rs.getString("PRESUPUESTO"));
      object.setEstado(rs.getString("ESTADO"));
      object.setMonto_disponible(utils.priceWithDecimal(rs.getDouble("MONTO_DISPONIBLE")));
    }   
          rs.close();
      stmt.close();
    
    return object;
  }
  
  
  
  public RubrosObject getRubro2(String idgasto) throws SQLException 
  {    
    RubrosObject object = new RubrosObject();

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM RUBROS WHERE IDRUBRO = (SELECT IDRUBRO FROM GASTOS WHERE IDGASTO="+idgasto+")" ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      while (rs.next())
      {                  
        object.setIdentificador(rs.getString("IDRUBRO"));
        object.setNombre(rs.getString("NOMBRE").trim());
        object.setMonto(String.valueOf(rs.getDouble("MONTO")));
        object.setSaldo(String.valueOf(rs.getDouble("SALDO")));
        object.setIdpresupuesto(rs.getString("IDPRESUPUESTO"));
       /* object.setQuincena(rs.getString("QUINCENA"));
        object.setMes(rs.getString("MES"));
        object.setAno(rs.getString("ANO"));*/
        object.setTipo_pago(rs.getString("TIPO_PAGO"));
        
      }      

            rs.close();
      stmt.close();
    return object;
  }
  
  public RubrosObject getRubro(String idrubro) throws SQLException 
  {    
    RubrosObject object = new RubrosObject();

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM RUBROS WHERE IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      while (rs.next())
      {                  
        object.setIdentificador(rs.getString("IDRUBRO"));
        object.setNombre(rs.getString("NOMBRE").trim());
        object.setMonto(String.valueOf(rs.getDouble("MONTO")));
        object.setSaldo(String.valueOf(rs.getDouble("SALDO")));
        object.setIdpresupuesto(rs.getString("IDPRESUPUESTO"));
        /*object.setQuincena(rs.getString("QUINCENA"));
        object.setMes(rs.getString("MES"));
        object.setAno(rs.getString("ANO"));*/
        object.setTipo_pago(rs.getString("TIPO_PAGO"));
        
      }      

            rs.close();
      stmt.close();
    return object;
  }
  
  
  public Object [][] getRubrosRecurrentes() throws SQLException 
  {    
 

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM RUBROS_RECURRENTES ORDER BY QUINCENA";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
                   String registro[];
            ArrayList arrayList = new ArrayList();
      int index=0;
      
      while (rs.next())
      {                  
        registro = new String[5];
        registro[0] =  rs.getString("IDRUBRO");
        registro[1] =  rs.getString("NOMBRE").trim();
        registro[2] =  utils.priceWithDecimal(rs.getDouble("MONTO"));          
        registro[3] =  rs.getString("QUINCENA").trim();
        registro[4] =  rs.getString("TIPO_PAGO").trim();
        arrayList.add(registro);
        index++;
      }      


    Object[][] rowData = new Object[index][5];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;
        j++;
    }        
    
          rs.close();
      stmt.close();
        return rowData;

  }

  public Object [][] getTiposPago() throws SQLException 
  {    

           Statement stmt = con.createStatement();
           String strSQL = "SELECT * FROM TIPO_PAGO ORDER BY TIPO_PAGO";

           ResultSet rs = stmt.executeQuery(strSQL);      
            String registro[];
            ArrayList arrayList = new ArrayList();
           int index=0;

           while (rs.next())
           {                  
             registro = new String[3];
            registro[0] =  rs.getString("IDTIPOPAGO");
           registro[1] =  rs.getString("TIPO_PAGO").trim();
            registro[2] =  rs.getString("TIPO").trim();        
            arrayList.add(registro);
             index++;
           }      

           

        Object[][] rowData = new Object[index][3];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            j++;
        }        
    
              rs.close();
      stmt.close();
         return rowData; 

  }
  
  
  public Object [][] getRubrosPresupuesto(String presupuesto) throws SQLException 
  {    
    //int n =countRows (presupuesto);      

    Statement stmt = con.createStatement();
    String strSQL = "SELECT * FROM RUBROS WHERE IDPRESUPUESTO="+ presupuesto+" ORDER BY TIPO_PAGO";

    ResultSet rs = stmt.executeQuery(strSQL);    
    
    String registro[];
    ArrayList arrayList = new ArrayList();

    int index=0;
    Utilities utils = new Utilities();
    while (rs.next())
    {              
      registro = new String[5];
      registro [0] =  rs.getString("IDRUBRO");
      registro [1] =  rs.getString("TIPO_PAGO");
      registro [2] =  rs.getString("NOMBRE").trim();
      registro [3] =  utils.priceWithDecimal(rs.getDouble("MONTO"));          
      registro [4] =  utils.priceWithDecimal(rs.getDouble("SALDO"));      
      arrayList.add(registro);
      
      index++;
    }      
    
    Object[][] rowData = new Object[index][5];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;       
        j++;
    }
      rs.close();
      stmt.close();
      
    return rowData;  
  }
  

  
  public Object [][] getRubrosPresupuesto2(String presupuesto) throws SQLException 
  {    
    //int n =countRows (presupuesto);      

    Statement stmt = con.createStatement();
    String strSQL = "SELECT idrubro,nombre,saldo,tipo_pago FROM RUBROS WHERE IDPRESUPUESTO="+ presupuesto+" ORDER BY TIPO_PAGO";

    ResultSet rs = stmt.executeQuery(strSQL);    
    
    String registro[];
    ArrayList arrayList = new ArrayList();

    int index=0;
    Utilities utils = new Utilities();
    while (rs.next())
    {              
      registro = new String[5];
      registro [0] =  rs.getString("IDRUBRO");
      registro [1] =  rs.getString("TIPO_PAGO");
      registro [2] =  rs.getString("NOMBRE").trim();
      registro [3] =  utils.priceWithDecimal(rs.getDouble("SALDO"));                
      arrayList.add(registro);
      
      index++;
    }      
    
    Object[][] rowData = new Object[index][4];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;            
        j++;
    }
      rs.close();
      stmt.close();
      
    return rowData;  
  }
  
  

    public Object [][] getRubrosPresupuestoSinPagar(String presupuesto) throws SQLException 
  {     

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM RUBROS WHERE SALDO>0 AND IDPRESUPUESTO=" + presupuesto+" ORDER BY NOMBRE";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
                   String registro[];
            ArrayList arrayList = new ArrayList();
      int index=0;
      Utilities utils = new Utilities();
      while (rs.next())
      {                  
        registro = new String[5];
        registro[0] =  rs.getString("IDRUBRO");
        registro[1] =  rs.getString("NOMBRE").trim();
       registro[2] =  utils.priceWithDecimal(rs.getDouble("MONTO"));          
        registro[3] =  utils.priceWithDecimal(rs.getDouble("SALDO"));
        registro[4] =   "";// new JButton("Click aquí");
         arrayList.add(registro);
        index++;
      }      
      
      
    Object[][] rowData = new Object[index][5];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;        
        j++;
    }         
            rs.close();
      stmt.close();
      
        return rowData;

  }

  /*
  
  public String [][] getGastosPresupuestoxTipoPago(int presupuesto) throws SQLException 
  {    

      

      Statement stmt = con.createStatement();
    //  String strSQL = "SELECT TIPO_PAGO, SUM (MONTO) AS MONTO FROM GASTOS WHERE IDPRESUPUESTO=" + presupuesto+"  GROUP BY TIPO_PAGO";
      
    String strSQL ="SELECT TIPO_PAGO, SUM (MONTO) AS MONTO FROM ("
   +"SELECT  G.LUGAR, G.TIPO_PAGO, G.MONTO FROM GASTOS G, TIPO_PAGO T WHERE  T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO'  AND G.IDPRESUPUESTO=" + presupuesto+" "
   +"UNION ALL "
   +"SELECT  R.NOMBRE, R.TIPO_PAGO, R.MONTO FROM RUBROS R, TIPO_PAGO T WHERE  T.TIPO_PAGO=R.TIPO_PAGO AND T.TIPO='CREDITO'  AND R.IDPRESUPUESTO=" + presupuesto+" "
   +")GROUP BY TIPO_PAGO";
      
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       String registro[];
       ArrayList arrayList = new ArrayList();
      int index=0;
      
      while (rs.next())
      {                          
        registro = new String[3];
        registro[0] =  rs.getString("TIPO_PAGO");
        registro[1] =  utils.priceWithDecimal(rs.getDouble("MONTO"));

        
        if(this.depositoAplicado(String.valueOf( presupuesto), registro[0]))
        {
         registro[2] =  "DEPOSITO APLICADO";   
        }
            else
        {
         registro[2] =  "*** PENDIENTE ***";                           
        }
           
        arrayList.add(registro);
        index++;
      }      
      

    String[][] rowData = new String[index][3];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
         rowData [j][2] = reg[2] ;
        j++;
    }           
            
      rs.close();
      stmt.close();
        return rowData;

  }
  */
    
    
  
  public String [][] getGastosPresupuestoxTipoPago(int presupuesto) throws SQLException 
  {    

      
      Statement stmt = con.createStatement();
   
      
    String strSQL =
 "SELECT  TIPO_PAGO, "
+"SUM (MONTO) AS MONTO, "
+"nvl((SELECT ESTADO FROM DEPOSITOS WHERE IDPRESUPUESTO=TABLA.IDPRESUPUESTO AND TIPO_PAGO=TABLA.TIPO_PAGO),'PENDIENTE') AS ESTADO "
+"FROM "
+"(SELECT G.IDPRESUPUESTO,  G.LUGAR, G.TIPO_PAGO, G.MONTO FROM GASTOS G, TIPO_PAGO T WHERE  T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO'  AND G.IDRUBRO<>0  AND  G.IDPRESUPUESTO='"+presupuesto+"'   "
+"UNION ALL    "
+"SELECT  R.IDPRESUPUESTO, R.NOMBRE, R.TIPO_PAGO, R.MONTO FROM RUBROS R, TIPO_PAGO T WHERE  T.TIPO_PAGO=R.TIPO_PAGO  AND T.TIPO='CREDITO'  AND R.IDPRESUPUESTO='"+presupuesto+"') TABLA GROUP BY TIPO_PAGO,IDPRESUPUESTO   ";
           
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       String registro[];
       ArrayList arrayList = new ArrayList();
      int index=0;
      
      while (rs.next())
      {                          
        registro = new String[3];
        registro[0] =  rs.getString("TIPO_PAGO");
        registro[1] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
        registro[2] =  rs.getString("ESTADO");
        
           
        arrayList.add(registro);
        index++;
      }      
      

    String[][] rowData = new String[index][3];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
         rowData [j][2] = reg[2] ;
        j++;
    }           
            
      rs.close();
      stmt.close();
        return rowData;

  }
  
  

  public String [][] getDepositoPendientes(String idpresupuesto) throws SQLException 
  {    
     
      Statement stmt = con.createStatement();    
      
    String strSQL ="select  TIPO_PAGO, MONTO, ESTADO,QUINCENA from depositos where  estado='PENDIENTE' AND IDPRESUPUESTO<>"+idpresupuesto;
      
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       String registro[];
       ArrayList arrayList = new ArrayList();
      int index=0;
      
      while (rs.next())
      {                          
        registro = new String[4];
        registro[0] =  rs.getString("TIPO_PAGO");
        registro[1] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
        registro[2] =  rs.getString("ESTADO");
        registro[3] =  rs.getString("QUINCENA");
      
        arrayList.add(registro);
        index++;
      }      
      

    String[][] rowData = new String[index][4];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
         rowData [j][2] = reg[2] ;
         rowData [j][3] = reg[3] ;
        j++;
    }           
            
      rs.close();
      stmt.close();
        return rowData;

  }  

  
  public String [][] getGastosPresupuestoxTipoPago(int presupuesto,String tipo_pago) throws SQLException 
  {    
  
      Statement stmt = con.createStatement();

      String strSQL =
                                "SELECT * FROM "
                                +"( "
                                +"SELECT G.IDGASTO AS ID, G.LUGAR, G.TIPO_PAGO, G.MONTO,nvl((SELECT  TIPO_PAGO FROM GASTOS_DETALLE WHERE G.IDGASTO=IDGASTO),'POR ASIGNAR') AS PAGADO  FROM GASTOS G, TIPO_PAGO T WHERE  T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO' AND G.IDRUBRO<>0  AND G.IDPRESUPUESTO="+presupuesto+" "
                                +"UNION ALL "
                                +"SELECT R.IDRUBRO AS ID, R.NOMBRE, R.TIPO_PAGO, R.MONTO,G.TIPO_PAGO AS PAGADO FROM GASTOS G, RUBROS R, TIPO_PAGO T WHERE G.IDRUBRO=R.IDRUBRO AND T.TIPO_PAGO=R.TIPO_PAGO AND T.TIPO='CREDITO'  AND R.IDPRESUPUESTO="+presupuesto+" " 
                                +")  WHERE TIPO_PAGO='"+tipo_pago+"' ";

      System.out.println(strSQL);
      
      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       String registro[];
       ArrayList arrayList = new ArrayList();
      int index=0;
      
      while (rs.next())
      {                  
        
        registro = new String[5];
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                       
        registro[0] =  rs.getString("ID"); 
        registro[1] =  rs.getString("LUGAR"); 
        registro[2] =  rs.getString("TIPO_PAGO");
        registro[3] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
        registro[4] =  rs.getString("PAGADO");
        arrayList.add(registro);
        index++;
      }      

      
    String[][] rowData = new String[index][5];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;
        j++;
    }           
      
            rs.close();
      stmt.close();
        return rowData;
  
  }


  
  public String [][] getFinanciadoPagos(String idfinanciado) throws SQLException 
  {    
      


      Statement stmt = con.createStatement();

      String strSQL = "SELECT F.TIPO_PAGO AS TIPO_PAGO,  F.FECHA AS FECHA_COMPRA, FP.IDCUOTA AS IDCUOTA, FP.MONTO AS MONTO, FP.ESTADO AS ESTADO, FP.NUMERO_CUOTA  AS NUMERO_CUOTA,"
              + "( SELECT PRESUPUESTO FROM PRESUPUESTO P WHERE P.IDPRESUPUESTO=FP.IDPRESUPUESTO) "
              + "AS QUINCENA, "
              + "FP.FECHA_PAGO_MINIMO AS FECHA_PAGO_MINIMO, FP.FECHA_PAGO_CONTADO AS FECHA_PAGO_CONTADO "
              + "FROM FINANCIADO F ,FINANCIADO_PAGOS FP  "
              + "WHERE F.IDFINANCIADO=FP.IDFINANCIADO AND FP.IDFINANCIADO=" + idfinanciado+"   ORDER BY FP.FECHA_PAGO_CONTADO";
      ResultSet rs = stmt.executeQuery(strSQL);      
        
        
            String registro[];
            ArrayList arrayList = new ArrayList();
            
      int index=0;
     
      while (rs.next())
      {                  
        registro = new String[7];
        
            registro[0] =  rs.getString("IDCUOTA");
           registro[1] =  rs.getString("QUINCENA");
            registro[2] =  rs.getString("NUMERO_CUOTA");
            registro[3] =  rs.getString("ESTADO");
            registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
            
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            
            registro[5] =  df.format(rs.getDate("FECHA_PAGO_MINIMO"));
           registro[6] = df.format(rs.getDate("FECHA_PAGO_CONTADO"));
         arrayList.add(registro);                
            index++;
        
      }      

    String[][] rowData = new String[index][7];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;
        rowData [j][5] = reg[5] ;
        rowData [j][6] = reg[6] ;       
        j++;
    }
                rs.close();
      stmt.close();
        return rowData;

  }
   
  
  public String [] getTiposdePago() throws SQLException 
  {                
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM TIPO_PAGO ORDER BY TIPO_PAGO";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      ArrayList<String> rowData = new ArrayList<String>();
      
      int index=0;
      
      while (rs.next())
      {                          
        rowData.add(rs.getString("TIPO_PAGO").trim());
        index++;
      }    
      
            rs.close();
      stmt.close();
      return utils.getArrayString(rowData);    
  }
  
  
  
  public String [] getTiposdePagoDEBITO() throws SQLException 
  {                
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM TIPO_PAGO WHERE TIPO='DEBITO' ORDER BY TIPO_PAGO";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      ArrayList<String> rowData = new ArrayList<String>();
      
      int index=0;
      
      while (rs.next())
      {                          
        rowData.add(rs.getString("TIPO_PAGO").trim());
        index++;
      }    
      
            rs.close();
      stmt.close();
      return utils.getArrayString(rowData);    
  }
  
  
  
  

  public String [][] resultadoBusqueda(String detalle) throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     
      String strSQL =  " select * from ( "
    +"select 'RUBRO: '||R.idrubro as id, R.nombre, R.monto,R.tipo_pago, TO_DATE((select presupuesto from presupuesto where idpresupuesto=R.idpresupuesto), 'dd/mm/yyyy')   as fecha from rubros R where upper(R.nombre) like '%"+detalle+"%' "
    +"union all "
    +"select 'PENDIENTE: '||idfinanciado as id,nombre,monto_total as monto,tipo_pago,fecha from financiado where upper(nombre) like '%"+detalle+"%' "
    +"union all "
    +"select 'GASTO: '||idgasto as id, lugar as nombre, monto,tipo_pago,fecha from gastos where upper(lugar) like  '%"+detalle+"%')";

      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[6]; 
        registro[0] =  rs.getString("ID");        
        registro[1] =  rs.getString("NOMBRE"); 
        registro[2] =  utils.priceWithDecimal(rs.getDouble("MONTO"));                
        registro[3] =  rs.getString("TIPO_PAGO");                        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        registro[4] =  df.format(rs.getDate("FECHA"));
        
        
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][6];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;                 
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }   
  


  public String [][] resultadoBusquedaMonto(String detalle) throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     
      String strSQL =  " select * from ( "
    +"select 'RUBRO: '||R.idrubro as id, R.nombre, R.monto,R.tipo_pago, TO_DATE((select presupuesto from presupuesto where idpresupuesto=R.idpresupuesto), 'dd/mm/yyyy')   as fecha from rubros R where R.monto="+detalle+" "
    +"union all "
    +"select 'PENDIENTE: '||idfinanciado as id,nombre,monto_total as monto,tipo_pago,fecha from financiado where monto_total="+detalle+" "
    +"union all "
    +"select 'GASTO: '||idgasto as id, lugar as nombre, monto,tipo_pago,fecha from gastos where monto= "+detalle+")";

      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[6]; 
        registro[0] =  rs.getString("ID");        
        registro[1] =  rs.getString("NOMBRE"); 
        registro[2] =  utils.priceWithDecimal(rs.getDouble("MONTO"));                
        registro[3] =  rs.getString("TIPO_PAGO");                        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        registro[4] =  df.format(rs.getDate("FECHA"));
        
        
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][6];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;                 
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }   
    

  public String [][] getGastosxPresupuesto(int idpresupuesto) throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     // String strSQL = "SELECT * FROM GASTOS WHERE IDPRESUPUESTO=" + idpresupuesto+" ORDER BY FECHA ";
      String strSQL = "SELECT R.NOMBRE, G.IDGASTO,G.IDRUBRO,G.IDPRESUPUESTO,G.MONTO AS MONTO,G.LUGAR,G.FECHA, G.TIPO_PAGO FROM GASTOS G, RUBROS R WHERE  R.IDRUBRO=G.IDRUBRO AND G.IDPRESUPUESTO=" + idpresupuesto+" ORDER BY G.FECHA";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[6]; 
        registro[0] =  rs.getString("IDGASTO");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");        
        registro[1] =  df.format(rs.getDate("FECHA"));
        registro[2] =  rs.getString("NOMBRE"); 
        registro[3] =  rs.getString("LUGAR"); 
        registro[4] =  rs.getString("TIPO_PAGO");
        registro[5] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][6];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;     
            rowData [j][5] = reg[5] ;
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }  
  /*
  
  
  public String [][] getSaldoCuentasBancarias(int idpresupuesto) throws SQLException 
  {    
    QuincenaActual quincenaActual = QuincenaActual.getInstance();
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     
      String strSQL = "select u2.nombre as NOMBRE, u2.tipo_pago as TIPO_PAGO, salario  - ("
              + "SELECT    SUM(G.MONTO) AS MONTO   FROM GASTOS G, RUBROS R, USUARIOS U WHERE "
              + "U.TIPO_PAGO = G.TIPO_PAGO AND U.IDUSUARIO=u2.idusuario and"
              + " R.IDRUBRO=G.IDRUBRO AND G.IDPRESUPUESTO="+idpresupuesto+" AND G.TIPO_PAGO IN (SELECT TIPO_PAGO FROM USUARIOS) GROUP  BY  G.TIPO_PAGO "
              + ") as MONTO"
              + " from presupuesto_salarios ps, usuarios u2 where  ps.IDPRESUPUESTO="+idpresupuesto+" and ps.idusuario=u2.idusuario";
      
      System.out.println(strSQL);
      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[3]; 
        registro[0] =  rs.getString("NOMBRE");
        registro[1] =  rs.getString("TIPO_PAGO");
        registro[2] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][3];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;            
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }  
  */
  
       

  
  public String [][] getSalariosQuincenaActual(int idpresupuesto) throws SQLException 
  {    
    QuincenaActual quincenaActual = QuincenaActual.getInstance();
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     /*
      String strSQL = "select u2.idusuario as id , u2.nombre as NOMBRE, u2.tipo_pago as TIPO_PAGO, salario as Salario,   " +
                                "cast( nvl( " +
                                "salario - (SELECT    SUM(G.MONTO) AS MONTO   FROM GASTOS G, RUBROS R, USUARIOS U WHERE U.TIPO_PAGO = G.TIPO_PAGO  " +
                                "AND U.IDUSUARIO=u2.idusuario and R.IDRUBRO=G.IDRUBRO AND G.IDPRESUPUESTO="+idpresupuesto+" AND G.TIPO_PAGO IN (SELECT TIPO_PAGO FROM USUARIOS) GROUP  BY  G.TIPO_PAGO )  "+
                                " -  cast (nvl((SELECT SUM(GD.MONTO) AS MONTO FROM GASTOS_DETALLE GD WHERE GD.IDPRESUPUESTO = "+idpresupuesto+"  AND GD.TIPO_PAGO=u2.TIPO_PAGO),'0') as integer) " +
                                ", salario) as float) " +
                                "as saldo " +
                                "from presupuesto_salarios ps, usuarios u2 where  ps.IDPRESUPUESTO="+idpresupuesto+" and ps.idusuario=u2.idusuario";
*/
      
      
      
String strSQL = 
        
                    "select tipo_pago,nombre, salario, 																			     "+
                    "cast( nvl( salario - (SELECT    SUM(G.MONTO) AS MONTO FROM GASTOS G, RUBROS R, USUARIOS U 											     "+
                    " WHERE U.TIPO_PAGO = G.TIPO_PAGO  AND U.IDUSUARIO=id and R.IDRUBRO=G.IDRUBRO AND G.IDPRESUPUESTO="+idpresupuesto+" AND  G.TIPO_PAGO IN (SELECT TIPO_PAGO FROM USUARIOS) GROUP  BY  G.TIPO_PAGO )  "+ 
                    "-  																						     "+
                    "cast (nvl((SELECT SUM(GD.MONTO) AS MONTO FROM GASTOS_DETALLE GD WHERE GD.IDPRESUPUESTO = "+idpresupuesto+"  AND GD.TIPO_PAGO=tabla.tipo_pago),'0') as integer) , salario) as float) 		     "+
                    "as saldo 																					     "+
                    "																						     "+
                    "from (																						     "+
                    "select 																					     "+
                    "ps.idusuario as id, 																				     "+
                    "sum(salario) as salario,																			     "+
                    "(select nombre from usuarios where idusuario=ps.idusuario) as nombre,														     "+
                    "(select tipo_pago from usuarios where idusuario=ps.idusuario) as tipo_pago													     "+
                    "from presupuesto_salarios ps where  ps.IDPRESUPUESTO="+idpresupuesto+" group by ps.idusuario) tabla                                                                                                    ";
      
      System.out.println(strSQL);
      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[4]; 
        registro[0] =  rs.getString("NOMBRE");
        registro[1] =  rs.getString("TIPO_PAGO");
        registro[2] =  utils.priceWithDecimal(rs.getDouble("SALARIO"));
        registro[3] =  utils.priceWithDecimal(rs.getDouble("SALDO"));
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][4];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;            
            rowData [j][3] = reg[3] ;       
            j++;
        }
        
        rs.close();
        stmt.close();
        return rowData;    
    
  }  
  
  

       

  
  public String [][] getSalariosQuincena (int idpresupuesto) throws SQLException 
  {    
    QuincenaActual quincenaActual = QuincenaActual.getInstance();
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();
     
      String strSQL = "select * from presupuesto_salarios where idpresupuesto= " +idpresupuesto;

      System.out.println(strSQL);
      
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[3]; 
        registro[0] =  rs.getString("IDUSUARIO");        
        registro[1] =  utils.priceWithDecimal(rs.getDouble("SALARIO"));
        registro[2] =  rs.getString("MOTIVO");
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][3];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;                        
            j++;
        }
        
        rs.close();
        stmt.close();
        return rowData;    
    
  }  
    
  

  public UsuarioObject getUsuario(String idusuario) throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();     
      String strSQL = "SELECT * FROM USUARIOS WHERE IDUSUARIO='"+idusuario.trim()+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      UsuarioObject usuario = new UsuarioObject();
      
      int index=0;      
      while (rs.next())
      {                          
          usuario.setIdUsuario( rs.getString("IDUSUARIO"));
          usuario.setNombre(rs.getString("NOMBRE"));
          usuario.setClave(rs.getString("CLAVE"));
          usuario.setPago(rs.getString("PAGO"));
          usuario.setQuincena1(rs.getDouble("QUINCENA1"));
          usuario.setQuincena2(rs.getDouble("QUINCENA2"));
          usuario.setMensual(rs.getDouble("MENSUAL"));
          usuario.setPerfil(rs.getString("ESTADO"));
      }      
            rs.close();
      stmt.close();
        return usuario;    
    
  }    
  
  
  

  public boolean validarUsuario (String idusuario, String clave) throws SQLException 
  {    
    
      boolean valido=false;
      Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();     
      String strSQL = "SELECT * FROM USUARIOS WHERE IDUSUARIO='"+idusuario.trim()+"'  AND CLAVE='"+clave.trim()+"'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      UsuarioObject usuario = new UsuarioObject();
      
      int index=0;      
      while (rs.next())
      {                          
          valido=true;
          
      }     
        rs.close();
        stmt.close();
        return valido;        
  }    
  
  
  

  public String [][] getUsuarios() throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();     
      String strSQL = "SELECT * FROM USUARIOS";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[7]; 
        registro[0] =  rs.getString("IDUSUARIO");        
        registro[1] =  rs.getString("NOMBRE"); 
        registro[2] =  rs.getString("CLAVE"); 
        registro[3] =  rs.getString("PAGO");
        registro[4] =  utils.priceWithDecimal(rs.getDouble("QUINCENA1"));
        registro[5] =  utils.priceWithDecimal(rs.getDouble("QUINCENA2"));
        registro[6] =  utils.priceWithDecimal(rs.getDouble("MENSUAL"));
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][7];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;     
            rowData [j][5] = reg[5] ;
            rowData [j][6] = reg[6] ;
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }    
  


  public String [][] getUsuarios2() throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();     
      String strSQL = "SELECT * FROM USUARIOS";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[2]; 
        registro[0] =  rs.getString("IDUSUARIO");        
        registro[1] =  rs.getString("NOMBRE"); 
        
         arrayList.add(registro);
        index++;
      }      

       String[][] rowData = new String[index][2];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }      
  


  public String [] getUsuarios3() throws SQLException 
  {    
    
    Utilities utils = new Utilities();  

      Statement stmt = con.createStatement();     
      String strSQL = "SELECT * FROM USUARIOS";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      String registro[];
      ArrayList arrayList = new ArrayList();
      
      int index=0;      
      while (rs.next())
      {                  
        registro = new String[1]; 
        registro[0] =  rs.getString("IDUSUARIO");                
        
         arrayList.add(registro);
        index++;
      }      

       String[] rowData = new String[index];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j]= reg[0] ;            
            
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;    
    
  }        
  
  
  public String [][] getGastosxRubro(int idrubro) throws SQLException 
  {    
    
    Utilities utils = new Utilities();  
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM GASTOS WHERE IDRUBRO=" + idrubro;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
      String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {                  
        registro = new String[5];  
        registro[0] =  rs.getString("IDGASTO");
        
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          registro[1] = df.format(rs.getDate("FECHA")); 
        
       
        registro[2] =  rs.getString("LUGAR"); 
        registro[3] =  rs.getString("TIPO_PAGO");
        registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
        arrayList.add(registro);
        index++;
      }      

          String[][] rowData = new String[index][5];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;       
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;
 
  }
  
  
public boolean isLimpio(String idpresupuesto) throws SQLException 
  {    
    boolean hay=false;
    Utilities utils = new Utilities();  
    
      Statement stmt = con.createStatement();
     
       String strSQL = "select * from presupuesto  where estado='LIMPIO' and idpresupuesto="+idpresupuesto ;
       
       
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
      String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {                  
        hay=true;
      }      

        
      rs.close();
      stmt.close();
       return hay;
 
  }    
  
public boolean HAYGastosSinAsignar() throws SQLException 
  {    
    boolean hay=false;
    Utilities utils = new Utilities();  
    
      Statement stmt = con.createStatement();
     // String strSQL = "select * from gastos where idrubro=0 and idpresupuesto=" + idpresupuesto;
       String strSQL = "select * from gastos where idrubro=0 order by lugar" ;
       
       
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
      String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {                  
        hay=true;
      }      

        
              rs.close();
      stmt.close();
        return hay;
 
  }  
  
  
  
  public String [][] getGastosSinAsignar() throws SQLException 
  {    
    
    Utilities utils = new Utilities();  
    
      Statement stmt = con.createStatement();
     // String strSQL = "select * from gastos where idrubro=0 and idpresupuesto=" + idpresupuesto;
       String strSQL = "select * from gastos where idrubro=0 order by lugar" ;
       
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
      String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {                  
        registro = new String[5];  
        registro[0] =  rs.getString("IDGASTO");
        
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
          registro[1] = df.format(rs.getDate("FECHA")); 
        
       
        registro[2] =  rs.getString("LUGAR"); 
        registro[3] =  rs.getString("TIPO_PAGO");
        registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO"));
        arrayList.add(registro);
        index++;
      }      

          String[][] rowData = new String[index][5];    
        int j=0;
        while (j<index)
        {
            arrayList.iterator().hasNext();
            String reg[] = (String[]) arrayList.get(j);
            rowData [j][0] = reg[0] ;
            rowData [j][1] = reg[1] ;
            rowData [j][2] = reg[2] ;
            rowData [j][3] = reg[3] ;
            rowData [j][4] = reg[4] ;       
            j++;
        }
        
              rs.close();
      stmt.close();
        return rowData;
 
  }
  
  
  public String [][] getGastosPendientesdePago(String quincena) throws SQLException 
  {    

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO WHERE PLAN<>'MANUEL' AND PLAN<>'SILVIA' AND ESTADO='ACTIVO' AND (QUINCENA=0 OR QUINCENA="+quincena+") ORDER BY TIPO_PAGO";
      
    System.out.println("--- PENDIENTES DE PAGO");
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
            String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {                          
         registro = new String[8]; 
        registro[0] =  rs.getString("IDFINANCIADO");
        registro[1] =  rs.getString("NOMBRE");        
        registro[2] =  rs.getString("PLAN");       
        registro[3] =  rs.getString("CUOTAS_PAGADAS");
        registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO_TOTAL"));
        registro[5] =  utils.priceWithDecimal(rs.getDouble("MONTO_CUOTA"));
        registro[6] =  utils.priceWithDecimal(rs.getDouble("MONTO_PAGADO"));
        registro[7] =  rs.getString("TIPO_PAGO");
        arrayList.add(registro);
        index++;
      }      
    String[][] rowData = new String[index][8];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;       
        rowData [j][5] = reg[5] ;
        rowData [j][6] = reg[6] ;
         rowData [j][7] = reg[7] ;
        j++;
    }	    
    
          rs.close();
      stmt.close();
        return rowData;

  }  

  public PendientesObject getPendientes(String idfinanciado) throws SQLException 
  {    
    PendientesObject pen = new PendientesObject();
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM FINANCIADO WHERE IDFINANCIADO="+idfinanciado;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
     
      while(rs.next()){             
        pen.setIdentificador(rs.getString("IDFINANCIADO"));
        pen.setNombre(rs.getString("NOMBRE"));
        pen.setPlan(rs.getString("PLAN"));
        pen.setCuotas_pagadas(rs.getString("CUOTAS_PAGADAS"));
        pen.setMonto_total(String.valueOf(rs.getDouble("MONTO_TOTAL")) );
        pen.setMonto_cuota(String.valueOf(rs.getDouble("MONTO_CUOTA")));
        pen.setMonto_pagado(String.valueOf(rs.getDouble("MONTO_PAGADO")));
        pen.setTipo_pago(rs.getString("TIPO_PAGO"));
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        pen.setFecha( df.format(rs.getDate("FECHA"))); 
        pen.setEstado(rs.getString("ESTADO")); 
        pen.setQuincena(rs.getString("QUINCENA")); 
      }
      
            rs.close();
      stmt.close();
        return pen;    
  }  
  
/*

  public ConfiguracionObject getConfiguracionManuel () throws SQLException 
  {    
    ConfiguracionObject conf = new ConfiguracionObject();
    
    Statement stmt = con.createStatement();
    String strSQL = "SELECT * FROM GENERAL WHERE NOMBRE='Manuel'";

    ResultSet rs = stmt.executeQuery(strSQL);      

    while (rs.next())
    {
        conf.setManuel_quincena1(utils.priceWithDecimal(rs.getDouble("QUINCENA1")));
        conf.setManuel_quincena2(utils.priceWithDecimal(rs.getDouble("QUINCENA2")));
        conf.setManuel_total(utils.priceWithDecimal(rs.getDouble("MONTO")));
    }
          rs.close();
      stmt.close();
    return conf;
  } 
  
public double getTotalDisponibleQuincena1 () throws SQLException 
  {   
    double total = 0;
    ConfiguracionObject conf = new ConfiguracionObject();
    
    Statement stmt = con.createStatement();
    String strSQL = "SELECT SUM(QUINCENA1)AS TOTAL FROM GENERAL";

    ResultSet rs = stmt.executeQuery(strSQL);      
    
    while (rs.next())
        total = rs.getDouble("TOTAL");
     
          rs.close();
      stmt.close();
    return total;
  } 

public double getTotalDisponibleQuincena2 () throws SQLException 
  {   
    double total = 0;
    ConfiguracionObject conf = new ConfiguracionObject();
    
    Statement stmt = con.createStatement();
    String strSQL = "SELECT SUM(QUINCENA2)AS TOTAL FROM GENERAL";

    ResultSet rs = stmt.executeQuery(strSQL);      
    
    while (rs.next())
        total = rs.getDouble("TOTAL");
     
          rs.close();
      stmt.close();
    return total;
  } 


  
public ConfiguracionObject getConfiguracionSilvia () throws SQLException 
  {    
    ConfiguracionObject conf = new ConfiguracionObject();
    
    Statement stmt = con.createStatement();
    String strSQL = "SELECT * FROM GENERAL WHERE NOMBRE='Silvia'";

    ResultSet rs = stmt.executeQuery(strSQL);      

    while (rs.next())
    {           
        conf.setSilvia_quincena1(utils.priceWithDecimal(rs.getDouble("QUINCENA1")));
        conf.setSilvia_quincena2(utils.priceWithDecimal(rs.getDouble("QUINCENA2")));
        conf.setSilvia_total(utils.priceWithDecimal(rs.getDouble("MONTO")));
    }
    
          rs.close();
      stmt.close();
    return conf;
  }  
  
  */
  
  public String [][] getGastosFinanciado() throws SQLException 
  {    
    Utilities utils = new Utilities();   
    String fechaCompra="",diaCorte="",diasPagoContado="",diasPagoMinimo="";
    
      Statement stmt = con.createStatement();
      String strSQL = "SELECT F.IDFINANCIADO AS IDFINANCIADO,F.NOMBRE AS NOMBRE,F.MONTO_TOTAL AS MONTO_TOTAL,"
              + "F.TIPO_PAGO AS TIPO_PAGO,F.FECHA_PAGO_MINIMO AS FECHA_PAGO_MINIMO,F.FECHA_PAGO_CONTADO AS FECHA_PAGO_CONTADO,F.FECHA AS FECHA,F.PLAN AS PLAN"
              + " FROM FINANCIADO F WHERE   F.ESTADO='ACTIVO' "
              + "  ORDER BY FECHA_PAGO_CONTADO ";
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;
      String registro[];
      ArrayList arrayList = new ArrayList();
      while (rs.next())
      {   
        registro = new String[8];  
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        registro[0] =  rs.getString("IDFINANCIADO");        
        registro[1] =  rs.getString("PLAN");
        
        registro[2] = df.format(rs.getDate("FECHA")); 
                        
        registro[3] =  rs.getString("NOMBRE");       
        registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO_TOTAL"));
        registro[5] =  rs.getString("TIPO_PAGO");                    
        registro[6] = df.format(rs.getDate("FECHA_PAGO_MINIMO")); 
        registro[7] = df.format(rs.getDate("FECHA_PAGO_CONTADO"));         
             
        arrayList.add(registro);
        index++;
      }      


    String[][] rowData = new String[index][8];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;       
        rowData [j][5] = reg[5] ;
        rowData [j][6] = reg[6] ;
        rowData [j][7] = reg[7] ;
        
        j++;
    }	      

          rs.close();
      stmt.close();
        return rowData;
  
  } 
  
  public String [][] getGastosFinanciadoxPLAN(String plan) throws SQLException 
  { 
      Statement stmt = con.createStatement();
      String strSQL = "SELECT IDFINANCIADO,FECHA,NOMBRE,PLAN,MONTO_TOTAL-MONTO_PAGADO AS SALDO,MONTO_TOTAL, TIPO_PAGO FROM FINANCIADO WHERE ESTADO='ACTIVO' AND PLAN='"+plan+"' ORDER BY PLAN";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      String registro[];
      ArrayList arrayList = new ArrayList();
        
      int index=0;
      
      while (rs.next())
      {                          
        registro = new String[7];  
        registro[0] =  rs.getString("IDFINANCIADO");
        
         DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        registro[1] =   df.format(rs.getDate("FECHA"));
        registro[2] =  rs.getString("NOMBRE");
        registro[3] =  rs.getString("PLAN");          
        registro[4] =  utils.priceWithDecimal(rs.getDouble("MONTO_TOTAL"));
        registro[5] =  rs.getString("SALDO");
        registro[6] =  rs.getString("TIPO_PAGO");
        arrayList.add(registro);
        
        index++;
      }      

    String[][] rowData = new String[index][7];    
    int j=0;
    while (j<index)
    {
        arrayList.iterator().hasNext();
        String reg[] = (String[]) arrayList.get(j);
        rowData [j][0] = reg[0] ;
        rowData [j][1] = reg[1] ;
        rowData [j][2] = reg[2] ;
        rowData [j][3] = reg[3] ;
        rowData [j][4] = reg[4] ;       
        rowData [j][5] = reg[5] ;
        rowData [j][6] = reg[6] ;
        j++;
    }	          
      
          rs.close();
      stmt.close();
      return rowData;
    
  } 
    
 
  public List <RubrosObject> getRubrosRecurrentesPresupuesto(int quincena) throws SQLException 
  {    
    List <RubrosObject>  list = new ArrayList<RubrosObject>(); 

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM RUBROS_RECURRENTES WHERE QUINCENA=" + quincena;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
 
      while (rs.next())
      {                  
        RubrosObject object = new RubrosObject();  
        object.setNombre(rs.getString("NOMBRE")); 
        object.setMonto(String.valueOf(rs.getDouble("MONTO")));         
        object.setTipo_pago(rs.getString("TIPO_PAGO"));
        list.add(object);
      }      

            rs.close();
      stmt.close();
        return list;

  }

  
  
  public void insertarRubro(RubrosObject rubro) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO RUBROS (IDRUBRO,NOMBRE, MONTO, IDPRESUPUESTO,SALDO,TIPO_PAGO) VALUES (IDRUBRO_SEQ.nextval,?,?,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, rubro.getNombre());
      prepStmt.setDouble(2, Double.parseDouble(rubro.getMonto()) );
      prepStmt.setInt(3, Integer.parseInt(rubro.getIdpresupuesto()));
      prepStmt.setDouble(4, Double.parseDouble(rubro.getMonto()));//EL SALDO SE INICIA IGUAL AL MONTO
      prepStmt.setString(5, rubro.getTipo_pago().trim());
      
      prepStmt.executeUpdate();

        if (prepStmt != null) {
          prepStmt.close();
        }
  }
  

  public void insertarTipoPago(TipoPagoObject obj) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO TIPO_PAGO (IDTIPOPAGO, TIPO_PAGO, FECHA_CORTE,FECHA_PAGO_MINIMO,FECHA_PAGO_CONTADO, TIPO) VALUES (IDTIPOPAGO_SEQ.nextval ,?, ?,?,? ,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getTipo_pago());
      prepStmt.setString(2, obj.getFecha_corte());
      prepStmt.setString(3, obj.getFecha_pago_minimo());
      prepStmt.setString(4, obj.getFecha_pago_contado());
      prepStmt.setString(5, obj.getTipo());
      
      prepStmt.executeUpdate();

        if (prepStmt != null) {
          prepStmt.close();
        }
  }
    

  public void insertarRubroRecurrente(RubroRecurrenteObject obj) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO RUBROS_RECURRENTES (IDRUBRO, ESTADO, NOMBRE, MONTO, QUINCENA,TIPO_PAGO) VALUES (IDRUBRO_SEQ.nextval, ?,?,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getEstado());
      prepStmt.setString(2, obj.getNombre());
      prepStmt.setDouble(3,Double.parseDouble( obj.getMonto()));
      prepStmt.setString(4, obj.getQuincena());
      prepStmt.setString(5, obj.getTipo_pago());
      
      prepStmt.executeUpdate();

        if (prepStmt != null) {
          prepStmt.close();
        }
  }  
  

  public void insertarFinanciadoPagos (FinanciadoPagosObject obj) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO FINANCIADO_PAGOS (IDCUOTA, IDFINANCIADO,IDPRESUPUESTO, "
              + "MONTO, FECHA, IDRUBRO,NUMERO_CUOTA,ESTADO,FECHA_PAGO_MINIMO, FECHA_PAGO_CONTADO) "
              + "VALUES (IDCUOTA_SEQ.nextval, ?,?,?,?,?,?,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      
      prepStmt.setString(1, obj.getIdfinanciado());
      prepStmt.setString(2, obj.getIdpresupuesto());
      prepStmt.setDouble(3,Double.parseDouble(obj.getMonto()));
      
      Date date2 = formatter.parse(obj.getFecha());
      java.sql.Date sqlDate2 = new java.sql.Date(date2.getTime());
      prepStmt.setDate(4,sqlDate2 );
      
      
      prepStmt.setString(5, obj.getIdrubro());
      prepStmt.setInt(6, Integer.parseInt(obj.getNcuota()));
      prepStmt.setString(7, obj.getEstado());      

      
      Date date1 = formatter.parse(obj.getFecha_pago_minimo());
      java.sql.Date sqlDate_pago_minimo = new java.sql.Date(date1.getTime());
      
      date1 = formatter.parse(obj.getFecha_pago_contado());
      java.sql.Date sqlDate_pago_contado = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(8, sqlDate_pago_minimo);
      prepStmt.setDate(9, sqlDate_pago_contado);
      
      
      prepStmt.executeUpdate();
   
      if (prepStmt != null) {
        prepStmt.close();
      }
     
  }    
     /*
  El idRubro se da.
  */
  public void  insertarRubro2(RubrosObject rubro) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO RUBROS (IDRUBRO, NOMBRE, MONTO, IDPRESUPUESTO,SALDO,TIPO_PAGO) VALUES (?,?,?,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
       prepStmt.setInt(1, Integer.parseInt(rubro.getIdentificador()));
      prepStmt.setString(2, rubro.getNombre());
      prepStmt.setDouble(3, Double.parseDouble(rubro.getMonto()));
      prepStmt.setInt(4, Integer.parseInt(rubro.getIdpresupuesto()));
      prepStmt.setDouble(5, Double.parseDouble(rubro.getMonto()));//EL SALDO SE INICIA IGUAL AL MONTO
      prepStmt.setString(6, rubro.getTipo_pago().trim());
      
      prepStmt.executeUpdate();
      
     
      if (prepStmt != null) {
        prepStmt.close();
      }

  }
  
      public int getIDRUBRO_SEQ() throws SQLException 
  { 
      int id=0;

      Statement stmt = con.createStatement();
     
      String strSQL = "SELECT  IDRUBRO_SEQ.nextval AS VAL FROM DUAL";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
      
      while (rs.next())
        id =  rs.getInt("VAL");
   
    return id;
  }    
  
    
   
            

  public long getIDFINANCIADO_SEQ () throws SQLException 
  {   
    long val = 0;    
    
    Statement stmt = con.createStatement();
    String strSQL = "select IDFINANCIADO_SEQ.nextval as NEXTVAL  from dual";

    ResultSet rs = stmt.executeQuery(strSQL);      
    
    while (rs.next())
        val = rs.getLong("NEXTVAL");
     
    return val;
  } 

  
   public long insertarPendientes(PendientesObject pendientes) throws SQLException, ParseException 
  {       
    PreparedStatement prepStmt = null;
    long llave=getIDFINANCIADO_SEQ();
    
      String insertStatement = "INSERT INTO FINANCIADO (IDFINANCIADO, NOMBRE, PLAN, "
              + "CUOTAS_PAGADAS,MONTO_CUOTA, MONTO_TOTAL,MONTO_PAGADO,TIPO_PAGO,FECHA,"
              + "ESTADO,QUINCENA,FECHA_PAGO_MINIMO,FECHA_PAGO_CONTADO)"              
              + " VALUES ("+llave+",?,?,?,?,?,?,?,?,?,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, pendientes.getNombre());
      prepStmt.setString(2, pendientes.getPlan());
      prepStmt.setInt(3, 0);
      prepStmt.setDouble(4, Double.parseDouble(pendientes.getMonto_cuota()));
      prepStmt.setDouble(5, Double.parseDouble(pendientes.getMonto_total()));
      prepStmt.setDouble(6, 0);
      prepStmt.setString(7, pendientes.getTipo_pago());
      
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = formatter.parse(pendientes.getFecha());
         java.sql.Date datetmp = new java.sql.Date(date1.getTime());
      
      prepStmt.setDate(8, datetmp);
      
      
      prepStmt.setString(9, "ACTIVO");
      prepStmt.setString(10,pendientes.getQuincena());                  
      
      
      
      Date date2 = formatter.parse(pendientes.getFecha_pago_minimo());
      java.sql.Date sqlDate_pago_minimo = new java.sql.Date(date2.getTime());
      
      Date date3 = formatter.parse(pendientes.getFecha_pago_contado());
      java.sql.Date sqlDate_pago_contado = new java.sql.Date(date3.getTime());
            
      prepStmt.setDate(11, sqlDate_pago_minimo);
      prepStmt.setDate(12, sqlDate_pago_contado);
      
      prepStmt.executeUpdate();
          
      
      if (prepStmt != null) {
        prepStmt.close();
      }
      
      return llave;
  }
  
  public void insertarPresupuesto( String presupuesto,String monto) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO PRESUPUESTO (IDPRESUPUESTO, PRESUPUESTO,"
              + " ESTADO,MONTO_DISPONIBLE)"
              + " values (IDPRESUPUESTO_SEQ.nextval, ?, ?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, presupuesto);
      prepStmt.setString(2, "ABIERTO");
      prepStmt.setDouble(3, Double.parseDouble(monto));
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

    
  }
  
  public void insertarGasto(GastoObject gasto) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO GASTOS (IDGASTO, IDRUBRO, IDPRESUPUESTO,"
              + " LUGAR, MONTO, FECHA, TIPO_PAGO) values (IDGASTO_SEQ.nextval, ?, ?, ? ,?,?, ?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, gasto.getIdrubro());
      prepStmt.setString(2, gasto.getIdpresupuesto());
      prepStmt.setString(3, gasto.getLugar());
      prepStmt.setDouble(4, Double.valueOf(gasto.getMonto()));
      
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                 
      Date date1 = formatter.parse(gasto.getFecha());
      java.sql.Date datetmp = new java.sql.Date(date1.getTime());                  
      prepStmt.setDate(5, datetmp);
      
      
      
      prepStmt.setString(6, gasto.getTipo_pago().trim());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }  
  }
  
public void insertarRubroFinanciado(int idrubro, int idfinanciado) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO RUBROS_FINANCIADO ( IDRUBRO, IDFINANCIADO)"
              + " values ( ?, ?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setInt(1, idrubro);
      prepStmt.setInt(2, idfinanciado);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
     
  }




public void insertarUsuario (UsuarioObject obj) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO USUARIOS (IDUSUARIO, NOMBRE, CLAVE, PAGO, QUINCENA1, QUINCENA2, MENSUAL,ESTADO)"
              + " values (  ?, ?, ?, ?, ? ,?, ?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      
      prepStmt.setString(1, obj.getIdUsuario());
      prepStmt.setString(2, obj.getNombre());
      prepStmt.setString(3, obj.getClave());
      prepStmt.setString(4, obj.getPago());
      
      prepStmt.setDouble(5, obj.getQuincena1());
      prepStmt.setDouble(6, obj.getQuincena2());
      prepStmt.setDouble(7, obj.getMensual());
      prepStmt.setString(8, obj.getPerfil());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }


public void insertarPresupuestoSalarios (int quincena) throws SQLException 
  {   
    PreparedStatement prepStmt = null;
    String insertStatement="";
 
    if (quincena==1)  
        insertStatement = "INSERT INTO PRESUPUESTO_SALARIOS select (select IDPRESUPUESTO from presupuesto where estado='ABIERTO')as idpresupuesto,  idusuario, quincena1 as monto, 'Salario' AS MOTIVO from usuarios where estado='ADMINISTRADOR'";
    else
        insertStatement = "INSERT INTO PRESUPUESTO_SALARIOS select (select IDPRESUPUESTO from presupuesto where estado='ABIERTO')as idpresupuesto, idusuario, quincena2 as monto , 'Salario' AS MOTIVO from usuarios where estado='ADMINISTRADOR'";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }




  public void cerrarPresupuesto(int idpresupuesto) throws SQLException 
  {
    
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO set ESTADO='CERRADO' where IDPRESUPUESTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setInt(1, idpresupuesto);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }


  }
public void modificarSaldoRubro(String idrubro, String saldo) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS set SALDO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);  
      
      prepStmt.setDouble(1, Double.parseDouble(saldo));
      prepStmt.setString(2, idrubro);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

  }

public void modificarNombreRubro(String idrubro, String nombre) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS set NOMBRE=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);  
      
      prepStmt.setString(1, nombre);
      prepStmt.setString(2, idrubro);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

  }

public void modificarMontoRubro(String idrubro, String monto) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS set MONTO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);  
      
      prepStmt.setDouble(1,Double.parseDouble(monto) );
      prepStmt.setString(2, idrubro);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

  }


public void modificarMontosRubro(String idrubro, String monto, String saldo) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE RUBROS SET MONTO=?,SALDO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);  
      
      prepStmt.setDouble(1, Double.parseDouble(monto));
      prepStmt.setDouble(2,Double.parseDouble(saldo));
      prepStmt.setString(3, idrubro);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

  }


public void modificarTipoPago(TipoPagoObject obj) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE TIPO_PAGO SET TIPO_PAGO=?,FECHA_CORTE=?,"
              + "FECHA_PAGO_MINIMO=?, FECHA_PAGO_CONTADO=?, TIPO=? WHERE IDTIPOPAGO=?";
      
      prepStmt = con.prepareStatement(insertStatement);  
      
      prepStmt.setString(1, obj.getTipo_pago());
      prepStmt.setString(2, obj.getFecha_corte());
      prepStmt.setString(3, obj.getFecha_pago_minimo());
      prepStmt.setString(4, obj.getFecha_pago_contado());
      prepStmt.setString(5, obj.getTipo());
      prepStmt.setString(6, obj.getIdtipopago());
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

  }


  public void modificarPendiente(PendientesObject pendientes) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set NOMBRE=? where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, pendientes.getNombre());
      prepStmt.setInt(2, Integer.parseInt(pendientes.getIdentificador()));
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  
  public void setPendienteINACTIVO(String idfinanciado) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set ESTADO=? where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, "INACTIVO");
      prepStmt.setString(2,idfinanciado);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
    

   public void setPendienteACTIVO(String idfinanciado) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set ESTADO=? where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, "ACTIVO");
      prepStmt.setString(2,idfinanciado);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
    
  public void abrirPresupuesto(String idpresupuesto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO SET ESTADO=? where IDPRESUPUESTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, "ACTIVO");
      prepStmt.setString(2,idpresupuesto);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }

  public void cerrarPresupuesto(String idpresupuesto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO SET ESTADO=? where IDPRESUPUESTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, "INACTIVO");
      prepStmt.setString(2,idpresupuesto);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }  
   
   
  public void modificarPendiente2(PendientesObject pendientes) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set NOMBRE=?,PLAN=?,MONTO_TOTAL=?,TIPO_PAGO=?, FECHA=?,MONTO_CUOTA=?,QUINCENA=? where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, pendientes.getNombre());
      prepStmt.setString(2, pendientes.getPlan());
      prepStmt.setDouble(3,Double.parseDouble(pendientes.getMonto_total()));
      prepStmt.setString(4, pendientes.getTipo_pago());
      
      
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
              Date date1 = formatter.parse(pendientes.getFecha());
               java.sql.Date datetmp = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(5, datetmp);
      
      
      prepStmt.setDouble(6,Double.parseDouble(pendientes.getMonto_cuota()));
      prepStmt.setString(7, pendientes.getQuincena());
      prepStmt.setString(8, pendientes.getIdentificador());
          
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }

  
  public void modificarEstadoCuota(CuotaObject cuota) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO_PAGOS SET ESTADO=?,FECHA=?, IDPRESUPUESTO=?,IDRUBRO=? where IDCUOTA=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, cuota.getEstado());
      
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
              Date date1 = formatter.parse(cuota.getFecha());
               java.sql.Date datetmp = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(2, datetmp);      
      
      
      prepStmt.setString(3, cuota.getIdpresupuesto());
      prepStmt.setString(4, cuota.getIdrubro());
      prepStmt.setString(5, cuota.getIdcuota());
          
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
/*Cuando no se cuenta con el IDCUOTA*/
  public void modificarEstadoCuota2(CuotaObject cuota) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO_PAGOS SET ESTADO=?,FECHA=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, cuota.getEstado());
      
      
   SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
              Date date1 = formatter.parse(cuota.getFecha());
               java.sql.Date datetmp = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(2, datetmp);
      
            
      prepStmt.setString(3, cuota.getIdrubro());
          
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
    public void modificarPendienteIncrementarCuotaPagada(int idfinanciado) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set CUOTAS_PAGADAS=CUOTAS_PAGADAS+1 where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);      
     
      prepStmt.setInt(1, idfinanciado);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
    public void modificarPendienteDecrementarCuotaPagada(int idfinanciado) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set CUOTAS_PAGADAS=CUOTAS_PAGADAS-1 where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);      
     
      prepStmt.setInt(1, idfinanciado);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }    
  
  public void modificarSaldodePendiente(int id, String monto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE FINANCIADO set MONTO_PAGADO=? where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setDouble(1,Double.parseDouble(monto) );
      prepStmt.setInt(2, id);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  
  
  public void modificarRubro(String idrubro, String nombre, String monto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS set NOMBRE=?, MONTO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, nombre);
      prepStmt.setDouble(2,Double.parseDouble(monto));
      prepStmt.setString(3, idrubro);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  public void modificarRubro(RubrosObject obj) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS set NOMBRE=?, MONTO=?,TIPO_PAGO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getNombre());
      prepStmt.setDouble(2,Double.parseDouble(obj.getMonto()));
      prepStmt.setString(3, obj.getTipo_pago().trim());
      prepStmt.setString(4, obj.getIdentificador());
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }  
  
  
  public void modificarUsuario(UsuarioObject obj) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "update USUARIOS set NOMBRE=?, CLAVE=?,PAGO=?, QUINCENA1=?, QUINCENA2=?, MENSUAL=?, ESTADO=?"
                                                + "where IDUSUARIO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getNombre());
      prepStmt.setString(2, obj.getClave());
      prepStmt.setString(3, obj.getPago());
      
      prepStmt.setDouble(4,obj.getQuincena1());
      prepStmt.setDouble(5,obj.getQuincena2());
      prepStmt.setDouble(6,obj.getMensual());
       prepStmt.setString(7, obj.getPerfil());
      
      prepStmt.setString(8, obj.getIdUsuario());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }  
  

 public void modificarRubroRecurrente(RubroRecurrenteObject obj) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS_RECURRENTES set ESTADO=?, NOMBRE=?, MONTO=?, QUINCENA=?,TIPO_PAGO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getEstado());
      prepStmt.setString(2, obj.getNombre());
      prepStmt.setDouble(3,Double.parseDouble( obj.getMonto()));
      prepStmt.setString(4, obj.getQuincena());
      prepStmt.setString(5, obj.getTipo_pago());
      prepStmt.setString(6, obj.getIdrubro());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
 

 public void modificarMontoDisponiblePresupuesto(String presupuesto,String monto_disponible) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO SET MONTO_DISPONIBLE=? where IDPRESUPUESTO="+presupuesto;
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setDouble(1,Double.parseDouble(monto_disponible));
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
 
 
 public void modificarRubroRecurrenteNOMBREYMONTO(String nombre,String monto,String idrubrorecurrente) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "update RUBROS_RECURRENTES set NOMBRE=?, MONTO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, nombre);      
      prepStmt.setDouble(2,Double.parseDouble( monto));
      prepStmt.setString(3, idrubrorecurrente);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  } 
 
   public void modificarConfiguracionPresupuestoGeneral(String idpresupuesto,String nuevoMonto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO SET MONTO_DISPONIBLE=? "
              + " WHERE IDPRESUPUESTO="+idpresupuesto;
      
      System.out.println(insertStatement);
      System.out.println(nuevoMonto);
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setDouble(1,Double.parseDouble(nuevoMonto));
      
                  
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  } 
 
   /*
  public void modificarConfiguracionManuel(ConfiguracionObject conf) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GENERAL SET MONTO=?, QUINCENA1=?, "
              + "QUINCENA2=? WHERE NOMBRE='Manuel'";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setDouble(1,Double.parseDouble(conf.getManuel_total()));
      prepStmt.setDouble(2,Double.parseDouble( conf.getManuel_quincena1()));
      prepStmt.setDouble(3,Double.parseDouble( conf.getManuel_quincena2()));
      
                  
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  } 
 
  public void modificarConfiguracionSilvia(ConfiguracionObject conf) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GENERAL SET MONTO=?, QUINCENA1=?, "
              + "QUINCENA2=? WHERE NOMBRE='Silvia'";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setDouble(1,Double.parseDouble( conf.getSilvia_total()));
      prepStmt.setDouble(2,Double.parseDouble( conf.getSilvia_quincena1()));
      prepStmt.setDouble(3,Double.parseDouble( conf.getSilvia_quincena2()));
      
                  
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  } */
  
  public void modificarFechasPago(String idfinanciado,String pagominimo, String pagocontado) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    try {
      String insertStatement = "UPDATE FINANCIADO SET FECHA_PAGO_MINIMO=?, FECHA_PAGO_CONTADO=? "
              + " where IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
                
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date1;
      
      date1 = formatter.parse(pagominimo);
      java.sql.Date sqlDate_pago_minimo = new java.sql.Date(date1.getTime());      
      date1 = formatter.parse(pagocontado);
      java.sql.Date sqlDate_pago_contado = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(1, sqlDate_pago_minimo);
      prepStmt.setDate(2, sqlDate_pago_contado);
      prepStmt.setString(3, idfinanciado);
      
    } catch (ParseException ex) {
          Logger.getLogger(PresupuestoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  public void modificarFechasPago2(String idcuota,String pagominimo, String pagocontado) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    try {
      String insertStatement = "UPDATE FINANCIADO_PAGOS SET FECHA_PAGO_MINIMO=?, FECHA_PAGO_CONTADO=? "
              + " where IDCUOTA=?";
      
      prepStmt = con.prepareStatement(insertStatement);
                
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date1;
      
      date1 = formatter.parse(pagominimo);
      java.sql.Date sqlDate_pago_minimo = new java.sql.Date(date1.getTime());      
      date1 = formatter.parse(pagocontado);
      java.sql.Date sqlDate_pago_contado = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(1, sqlDate_pago_minimo);
      prepStmt.setDate(2, sqlDate_pago_contado);
      prepStmt.setString(3, idcuota);
      
    } catch (ParseException ex) {
          Logger.getLogger(PresupuestoDAO.class.getName()).log(Level.SEVERE, null, ex);
      }      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
    
  public void modificarGasto(GastoObject gasto) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GASTOS SET FECHA=?, LUGAR=?, "
              + "MONTO=?, TIPO_PAGO=? where IDGASTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
              Date date1 = formatter.parse( gasto.getFecha());
               java.sql.Date datetmp = new java.sql.Date(date1.getTime());
            
      prepStmt.setDate(1, datetmp);      
      
      
      prepStmt.setString(2, gasto.getLugar());
      prepStmt.setDouble(3,Double.parseDouble( gasto.getMonto()));
      prepStmt.setString(4, gasto.getTipo_pago());
      prepStmt.setString(5, gasto.getIdgasto());
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }

  
  public void modificarIDRUBRODELGasto(GastoObject gasto) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GASTOS SET IDRUBRO=?, TIPO_PAGO=? where IDGASTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      

      
      prepStmt.setString(1, gasto.getIdrubro());
      prepStmt.setString(2, gasto.getTipo_pago());
      prepStmt.setString(3, gasto.getIdgasto());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }  
  
    public void modificarGastoNombre(GastoObject gasto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GASTOS SET LUGAR=? where IDGASTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, gasto.getLugar());
      prepStmt.setString(2, gasto.getIdgasto());
 
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }

  
  public void borrarPendientes(String idpendiente) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM FINANCIADO WHERE IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idpendiente);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
   
  public void borrarTipoPago(String idtipopago) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM TIPO_PAGO WHERE IDTIPOPAGO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idtipopago);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  
    public void borrarUsuario(UsuarioObject obj) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM USUARIOS WHERE IDUSUARIO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, obj.getIdUsuario());
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
    
  
  public void borrarRubro(String idrubro) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM RUBROS WHERE IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idrubro);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  public void borrarRubroRecurrente(String idrubro) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM RUBROS_RECURRENTES WHERE IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idrubro);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }  

  public void borrarRubroFinanciado(String idrubro) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM RUBROS_FINANCIADO WHERE IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idrubro);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
    public void borrarFinanciadoPagos(String idfinanciado) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM FINANCIADO_PAGOS WHERE IDFINANCIADO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idfinanciado);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
    
   public void borrarFinanciadoPagos(String idfinanciado,String idpresupuesto,String idrubro) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM FINANCIADO_PAGOS WHERE IDFINANCIADO=? AND IDPRESUPUESTO=? AND IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idfinanciado);
      prepStmt.setString(2, idpresupuesto);
      prepStmt.setString(3, idrubro);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
   
   public void borrarGasto(String idgasto) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM GASTOS WHERE IDGASTO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idgasto);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
   

   
   
   
   
   
  public void modificarIDPRESUPUESTOenGastos(String idrubro,String idpresupuesto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GASTOS set IDPRESUPUESTO=? where IDRUBRO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idpresupuesto);
      prepStmt.setString(2, idrubro);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  
  
   public void modificarIDPRESUPUESTO_de_GASTOXidGASTO(String idpresupuesto) throws SQLException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE GASTOS set IDPRESUPUESTO=? where IDRUBRO=0";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idpresupuesto);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
  
  public void corregirGastos () throws SQLException 
  {    
   
    Utilities utils = new Utilities();  
    String idrubro,idpresupuesto;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT * FROM GASTOS WHERE IDPRESUPUESTO=0";      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      int index=0;      
      while (rs.next())
      {                          
        idrubro =  rs.getString("IDRUBRO");
        RubrosObject obj = this.getRubro(idrubro);                
        this.modificarIDPRESUPUESTOenGastos(idrubro, obj.getIdpresupuesto());
                
        index++;
      }      

      
  }  
  
  
  
  public void  getQuincenaPrevia () throws SQLException 
  {    

      Statement stmt = con.createStatement();
      String strSQL = "select * from presupuesto ORDER BY IDPRESUPUESTO DESC" ;
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        
        this.abrirPresupuesto(rs.getString("IDPRESUPUESTO"));
      }
      
      stmt.close();
      rs.close();

  }    
  
  
   
   public void LIMPIAR_QUINCENA(String IDPRE) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
    
    
    
    
      String insertStatement = "DELETE FROM PRESUPUESTO WHERE IDPRESUPUESTO="+IDPRE;      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate();
      
      insertStatement = "DELETE FROM RUBROS WHERE IDPRESUPUESTO="+IDPRE;      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate();
     
    /*
      insertStatement = "DELETE FROM GASTOS WHERE IDPRESUPUESTO="+IDPRE;      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate();*/

      insertStatement = "UPDATE FINANCIADO_PAGOS SET ESTADO = 'SIN ASIGNAR' WHERE IDPRESUPUESTO = "+IDPRE;      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate(); 
      
      insertStatement = " DELETE FROM RUBROS_FINANCIADO WHERE IDRUBRO IN (SELECT IDRUBRO FROM RUBROS WHERE IDPRESUPUESTO="+IDPRE+")";      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate();      
      
      
     insertStatement = " DELETE FROM PRESUPUESTO_SALARIOS WHERE IDPRESUPUESTO="+IDPRE;      
      prepStmt = con.prepareStatement(insertStatement);            
      prepStmt.executeUpdate();   
     
  
  }
   
     
   
  public void insertarGastoDetalle( String idpresupuesto,String idgasto, String tipo_pago, double monto) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

      String insertStatement = "INSERT INTO GASTOS_DETALLE (IDGASTODETALLE, IDGASTO,TIPO_PAGO,IDPRESUPUESTO,MONTO)"              
                                            + " values (IDGASTODETALLE_SEQ.nextval, ?, ?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idgasto);
      prepStmt.setString(2, tipo_pago);
      prepStmt.setString(3, idpresupuesto);
      prepStmt.setDouble(4,monto);
      
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }

    
  }
   
   
   
   
  


/*hoy*/
public void insertarSalario (String idpresupuesto, String idusuario, double monto, String motivo) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO PRESUPUESTO_SALARIOS (IDPRESUPUESTO, IDUSUARIO, SALARIO, MOTIVO)"
              + " values (  ?, ?, ?, ?)";
      
      prepStmt = con.prepareStatement(insertStatement);
            
      prepStmt.setString(1, idpresupuesto);
      prepStmt.setString(2, idusuario);    
      prepStmt.setDouble(3, monto);      
      prepStmt.setString(4, motivo);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }



  public long getIDDEPOSITO_NEXTVAL() throws SQLException 
  {   
    long val = 0;    
    
    Statement stmt = con.createStatement();
    String strSQL = "select IDDEPOSITO_SEQ.nextval as NEXTVAL  from dual";

    ResultSet rs = stmt.executeQuery(strSQL);      
    
    while (rs.next())
        val = rs.getLong("NEXTVAL");
     
    return val;
  } 


    public long getIDCOMPROBANTE_NEXTVAL() throws SQLException 
  {   
    long val = 0;    
    
    Statement stmt = con.createStatement();
    String strSQL = "select IDCOMPROBANTE_SEQ.nextval as NEXTVAL  from dual";

    ResultSet rs = stmt.executeQuery(strSQL);      
    
    while (rs.next())
        val = rs.getLong("NEXTVAL");
     
    return val;
  } 

  
public void insertarDeposito (DepositoObject deposito) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO DEPOSITOS (IDDEPOSITO, FECHA, TIPO_PAGO, MONTO, IDUSUARIO, IDCOMPROBANTE,IDPRESUPUESTO,ESTADO, QUINCENA)"
              + " values (?,sysdate, ?, ?, ?,? ,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
            
      

       prepStmt.setString(1, deposito.getIddeposito());                              
       
      prepStmt.setString(2, deposito.getTipo_pago());    
      prepStmt.setDouble(3, deposito.getMonto());      
      
      prepStmt.setString(4, deposito.getIdusuario());      
      prepStmt.setString(5, deposito.getIdcomprobante());      
      prepStmt.setString(6, deposito.getIdpresupuesto());      
      prepStmt.setString(7, deposito.getEstado());      
      prepStmt.setString(8, deposito.getQuincena());      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }


public void insertarComprobante (ComprobanteObject object) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO DEPOSITO_COMPROBANTE (IDCOMPROBANTE, NUMERO_DOCUMENTO, FECHA_PAGO, LUGAR, IDUSUARIO, MONTO)"
              + " values (?, ?, ?, ?,? ,?)";
      
      prepStmt = con.prepareStatement(insertStatement);
            
      prepStmt.setString(1, object.getIdcomprobante());        
      prepStmt.setString(2, object.getDocumento());                    
      
      
      
      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      Date date1 = formatter.parse(object.getFecha());
      java.sql.Date fecha_comp = new java.sql.Date(date1.getTime());
      
      
      prepStmt.setDate(3, fecha_comp);                
      prepStmt.setString(4, object.getLugar());   
      prepStmt.setString(5, object.getIdusuario());         
      prepStmt.setDouble(6, object.getMonto());      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }



public void insertarDepositoDetalleGastos (String idpresupuesto,String iddeposito,String tipo_pago) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = 
      
        "INSERT INTO deposito_detalle (IDPRESUPUESTO,IDDEPOSITO, DETALLE,TIPO_PAGO,MONTO,PAGADO,IDGASTO,IDRUBRO)  "
       + "SELECT "+idpresupuesto+", "+iddeposito+"  ,LUGAR,TIPO_PAGO,MONTO, PAGADO,IDGASTO,0 FROM ( SELECT G.IDGASTO AS IDGASTO, G.LUGAR AS LUGAR"
              + ", G.TIPO_PAGO AS TIPO_PAGO, G.MONTO AS MONTO,"
              + "nvl((SELECT  TIPO_PAGO FROM GASTOS_DETALLE WHERE G.IDGASTO=IDGASTO),'POR ASIGNAR') AS PAGADO  FROM GASTOS G, TIPO_PAGO T WHERE  "
              + "T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO' "
       + "AND G.IDPRESUPUESTO="+idpresupuesto+" )  WHERE TIPO_PAGO='"+tipo_pago+"' ";
     
      prepStmt = con.prepareStatement(insertStatement);                  

      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }



public void insertarDepositoDetalleRubros (String idpresupuesto,String iddeposito,String tipo_pago) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = 
      
        "INSERT INTO deposito_detalle (IDPRESUPUESTO,IDDEPOSITO, DETALLE,TIPO_PAGO,MONTO,PAGADO,IDGASTO,IDRUBRO)  "
       + "SELECT "+idpresupuesto+", "+iddeposito+"  ,LUGAR,TIPO_PAGO,MONTO, PAGADO,0,IDRUBRO FROM ( SELECT R.IDRUBRO AS IDRUBRO, R.NOMBRE AS LUGAR"
              + ", R.TIPO_PAGO AS TIPO_PAGO, R.MONTO AS MONTO,G.TIPO_PAGO AS PAGADO FROM "
              + "GASTOS G, RUBROS R, TIPO_PAGO T WHERE G.IDRUBRO=R.IDRUBRO AND T.TIPO_PAGO=R.TIPO_PAGO AND T.TIPO='CREDITO' "
              + " AND R.IDPRESUPUESTO="+idpresupuesto+" )  WHERE TIPO_PAGO='"+tipo_pago+"' ";
     
      prepStmt = con.prepareStatement(insertStatement);                  

      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }




   public boolean hayDepositosPendientes  () throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
        String strSQL = "select * from depositos where  estado='PENDIENTE' ";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
      rs.close();
      stmt.close();
 
    return hay;
  }  


   public boolean depositoAplicado  (String idpresupuesto, String tipo_pago) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
        String strSQL = "select * from depositos where idpresupuesto="+idpresupuesto+" and tipo_pago='"+tipo_pago+"' and estado='DEPOSITO APLICADO' ";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
      rs.close();
      stmt.close();
 
    return hay;
  }  
   
   
  public boolean esDepositoPendiente  (String idpresupuesto, String tipo_pago) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();
      String strSQL = "SELECT  * FROM DEPOSITOS WHERE IDPRESUPUESTO="+idpresupuesto+" AND TIPO_PAGO='"+tipo_pago+"' AND ESTADO='PENDIENTE' ";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
      rs.close();
      stmt.close();
 
    return hay;
  }  


  

   public boolean hayxAsignar  (String idpresupuesto, String tipo_pago) throws SQLException 
  {    
    boolean hay=false;

      Statement stmt = con.createStatement();

               
      String strSQL =
                                "SELECT * FROM (SELECT * FROM "
                                +"( "
                                +"SELECT G.IDGASTO AS ID, G.LUGAR, G.TIPO_PAGO, G.MONTO,nvl((SELECT  TIPO_PAGO FROM GASTOS_DETALLE WHERE G.IDGASTO=IDGASTO),'POR ASIGNAR') AS PAGADO  FROM GASTOS G, TIPO_PAGO T WHERE  T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO' AND G.IDPRESUPUESTO="+idpresupuesto+" "
                                +"UNION "
                                +"SELECT R.IDRUBRO AS ID, R.NOMBRE, R.TIPO_PAGO, R.MONTO,G.TIPO_PAGO AS PAGADO FROM GASTOS G, RUBROS R, TIPO_PAGO T WHERE G.IDRUBRO=R.IDRUBRO AND T.TIPO_PAGO=R.TIPO_PAGO AND T.TIPO='CREDITO'  AND R.IDPRESUPUESTO="+idpresupuesto+" " 
                                +")  WHERE TIPO_PAGO='"+tipo_pago+"' "
                                   +") where pagado='POR ASIGNAR'";
      
      ResultSet rs = stmt.executeQuery(strSQL);      
       
      if (rs.next())
      {        
        hay=true;
      }      
      
            rs.close();
      stmt.close();
 
    return hay;
  }  


  


   
   
public void modificarSalario (String idpresupuesto, String idusuario, double monto, String motivo) throws SQLException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "UPDATE  PRESUPUESTO_SALARIOS "
              + "SET SALARIO=? , MOTIVO = ? WHERE IDUSUARIO=? ";
      
      prepStmt = con.prepareStatement(insertStatement);                  
      
      prepStmt.setDouble(1, monto);      
      prepStmt.setString(2, motivo);
      prepStmt.setString(3, idusuario);    
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }



public void modificarMontoDisponible(String idpresupuesto ) throws SQLException, ParseException 
  {
    PreparedStatement prepStmt = null;

      String insertStatement = "UPDATE PRESUPUESTO set MONTO_DISPONIBLE= " +
                                            "(select SUM(SALARIO) from presupuesto_salarios where idpresupuesto="+idpresupuesto+") " +
                                            "WHERE IDPRESUPUESTO = "+idpresupuesto+"";
      
      prepStmt = con.prepareStatement(insertStatement);

          
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }

   public void borrarSalario(String idpresupuesto,String motivo, double salario) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM PRESUPUESTO_SALARIOS WHERE IDPRESUPUESTO=? AND MOTIVO=? AND SALARIO=?";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idpresupuesto);
      prepStmt.setString(2, motivo);
      prepStmt.setDouble(3, salario);
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
   
  
   
   

   
  public void insertaTarjetasPendientes_de_Deposito(String idpresupuesto,String quincena,String usuario) throws SQLException, ParseException 
  {         
      Statement stmt = con.createStatement();
          
    String strSQL ="SELECT TIPO_PAGO, SUM (MONTO) AS MONTO FROM ("
   +"SELECT  G.LUGAR, G.TIPO_PAGO, G.MONTO FROM GASTOS G, TIPO_PAGO T WHERE  T.TIPO_PAGO=G.TIPO_PAGO AND T.TIPO='CREDITO'  AND G.IDPRESUPUESTO=" + idpresupuesto+" "
   +"UNION ALL "
   +"SELECT  R.NOMBRE, R.TIPO_PAGO, R.MONTO FROM RUBROS R, TIPO_PAGO T WHERE  T.TIPO_PAGO=R.TIPO_PAGO AND T.TIPO='CREDITO'  AND R.IDPRESUPUESTO=" + idpresupuesto+" "
   +")GROUP BY TIPO_PAGO";
      
      System.out.println(strSQL);
      ResultSet rs = stmt.executeQuery(strSQL);      
       
       
      while (rs.next())
      {                          

        
        if(!this.depositoAplicado(idpresupuesto, rs.getString("TIPO_PAGO")))
        {
           if (!this.esDepositoPendiente(idpresupuesto, rs.getString("TIPO_PAGO"))){
                DepositoObject deposito = new DepositoObject ();

                deposito.setIdpresupuesto(idpresupuesto);
                deposito.setIdusuario( usuario);
                deposito.setMonto(rs.getDouble("MONTO"));
                deposito.setIdcomprobante("0");
                deposito.setTipo_pago(rs.getString("TIPO_PAGO"));
                deposito.setEstado(DepositoObject.DEPOSITO_PENDIENTE);
                deposito.setQuincena(quincena);

                this.insertarDeposito2(deposito);        
           }
        }
                          
      }      
      
      rs.close();
      stmt.close();
        

  }
   
  

  
public void insertarDeposito2 (DepositoObject deposito) throws SQLException, ParseException 
  {   
    PreparedStatement prepStmt = null;

 
      String insertStatement = "INSERT INTO DEPOSITOS (IDDEPOSITO, FECHA, TIPO_PAGO, MONTO, IDUSUARIO, IDCOMPROBANTE,IDPRESUPUESTO,ESTADO, QUINCENA)"
              + " values (IDDEPOSITO_SEQ.nextval,sysdate, ?, ?, ?,? ,?,?,?)";
      
      prepStmt = con.prepareStatement(insertStatement);                  
       
       
      prepStmt.setString(1, deposito.getTipo_pago());    
      prepStmt.setDouble(2, deposito.getMonto());      
      
      prepStmt.setString(3, deposito.getIdusuario());      
      prepStmt.setString(4, deposito.getIdcomprobante());      
      prepStmt.setString(5, deposito.getIdpresupuesto());      
      prepStmt.setString(6, deposito.getEstado());      
      prepStmt.setString(7, deposito.getQuincena());      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }     
  }
  

   public void borrarDepositoPendiente(String idpresupuesto,String tipo_pago) throws SQLException 
  {
    PreparedStatement prepStmt = null;
    
      String insertStatement = "DELETE FROM DEPOSITOS WHERE IDPRESUPUESTO=? AND TIPO_PAGO=? AND ESTADO='PENDIENTE'";
      
      prepStmt = con.prepareStatement(insertStatement);
      
      prepStmt.setString(1, idpresupuesto);
      prepStmt.setString(2, tipo_pago);
      
      
      prepStmt.executeUpdate();
      if (prepStmt != null) {
        prepStmt.close();
      }
  }
   
   
}
