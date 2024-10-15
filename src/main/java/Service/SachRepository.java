package Service;

import Entity.Sach;
import Utils.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SachRepository {
    private List<Sach> saches = new ArrayList<>();
    public List<Sach> getAll(){
        String sql = "SELECT ma,ten,so_trang,don_gia FROM sach";
        try(Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Sach s = new Sach();
                s.setMa(rs.getString(1));
                s.setTen(rs.getString(2));
                s.setSoTrang(rs.getInt(3));
                s.setDonGia(rs.getInt(4));
                saches.add(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return saches;
    }
    public Sach getOne(String ma){
        String sql = "SELECT ma,ten,so_trang,don_gia FROM sach WHERE ma =?";
        try(Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,ma);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Sach s = new Sach();
                s.setMa(rs.getString(1));
                s.setTen(rs.getString(2));
                s.setSoTrang(rs.getInt(3));
                s.setDonGia(rs.getInt(4));
                return s;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Sach update(String ma,Sach s){
        String sql = "UPDATE sach SET ten=?,so_trang=?,don_gia=? WHERE ma =?";
        try(Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,s.getTen());
            ps.setInt(2,s.getSoTrang());
            ps.setInt(3,s.getDonGia());
            ps.setString(4,ma);
            ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }
    public void delete(String ma){
        String sql = "DELETE FROM sach WHERE ma =?";
        try(Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1,ma);
            ps.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Sach> timkiem(String ten){
        ArrayList<Sach> listSach = new ArrayList<>();
        String sql = "SELECT ma,ten,so_trang,don_gia FROM sach WHERE ten like ?";
        try(Connection conn = DBConnect.getConnection();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1,'%'+ten+'%');
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String ma,tens;
                int soTrang,donGia;
                ma = rs.getString(1);
                tens = rs.getString(2);
                soTrang = rs.getInt(3);
                donGia = rs.getInt(4);
                listSach.add(new Sach(ma,tens,soTrang,donGia));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSach;
    }
    public static void main(String[] args){
        SachRepository sachRepository = new SachRepository();
        List<Sach> list = sachRepository.getAll();
        for(Sach sach: list){
            System.out.println(sach.toString());
        }
        Sach s = sachRepository.getOne("20");
        System.out.println("getOne"+s.toString());
    }
}
