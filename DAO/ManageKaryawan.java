/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import hibernate.Karyawan;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author USER
 */
public class ManageKaryawan {
    private static SessionFactory factory;
    
    public String AddKaryawan(String id, String depan, String belakang, int gaji){
        String hasil = "gagal";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Karyawan kyw = new Karyawan(id, depan, belakang, gaji);
            session.save(kyw);
            hasil = "sukses";
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
            tx.rollback();
            }
        }
         finally{
            session.close();
        }
        return hasil;
    }
    public String AddKaryawan1(Karyawan kyw){
        String hasil = "gagal";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Karyawan kyw = new Karyawan(id, depan, belakang, gaji);
            session.save(kyw);
            hasil = "sukses";
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
            tx.rollback();
            }
        }
         finally{
            session.close();
        }
        return hasil;
    }
    
    public String deleteKaryawan(String kodeKaryawan){
        String hasil = "gagal";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Karyawan kyw = (Karyawan) session.get(Karyawan.class, kodeKaryawan);
            session.delete(kyw);
            hasil = "sukses";
            tx.commit();
        } catch (Exception e) {
            if(tx != null)tx.rollback();
            
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return hasil;
    }
    
    public String updateKaryawan(Karyawan karyawan){
        String hasil = "gagal";
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Karyawan kyw = (Karyawan) session.get(Karyawan.class, karyawan.getIdKaryawan());
            kyw.setNamaDepan(karyawan.getNamaDepan());
            kyw.setNamaBelakang(karyawan.getNamaBelakang());
            kyw.setGaji(karyawan.getGaji());
            session.update(kyw);
            hasil = "sukses";
            tx.commit();
        } catch (Exception e) {
            if(tx != null)tx.rollback();
            e.printStackTrace();
            
        }
        finally{
            session.close();
        }
        return hasil;
    }
    
    public List<Karyawan> daftarKaryawan(){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("gagal");
            e.printStackTrace();
        }
        Session session = factory.openSession();
        Transaction tx = null;
        List<Karyawan> getKry = new ArrayList<Karyawan>();
        try {
            tx = session.beginTransaction();
            getKry = session.createQuery("FROM Karyawan").list();
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return getKry;
    }
    
    public Karyawan getKaryawanByKode (String Kode){
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("gagal");
            e.printStackTrace();
        }
        Karyawan Kry = new Karyawan();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Kry = (Karyawan) session.get(Karyawan.class, Kode);
            tx.commit();
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            e.printStackTrace();
        }finally{
            session.close();
        }
        return Kry;
    } 
}
