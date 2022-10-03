/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loanntk.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import loanntk.entity.Category;
import loanntk.entity.Product;

import loanntk.utils.DBUtils;


/**
 *
 * @author Acer
 */
public class RegistrationDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2.create SQL String               
                String sql = "select username from registration"
                        + " where username = ? and password = ?";
                //3.create sql statement
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.execute query
                rs = stm.executeQuery();
                //5.process result
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;

    }

    private List<RegistrationDTO> listAccounts;

    public List<RegistrationDTO> getListAccounts() {
        return listAccounts;
    }

    public void searchLastname(String searchValue) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2.create SQL String 
                String sql = "select * from registration"
                        + " where lastname like ?";
                //3.create sql statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4.execute query
                rs = stm.executeQuery();
                //5.process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");

                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, isAdmin);

                    if (this.listAccounts == null) {
                        this.listAccounts = new ArrayList<RegistrationDTO>();
                    }
                    this.listAccounts.add(dto);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean deteleRecord(String pk) throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2.create SQL String 
                String sql = "Delete from Registration"
                        + " where username = ?";
                //3.create sql statement
                stm = con.prepareStatement(sql);
                stm.setString(1, pk);
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
                //4.execute query

                //5.process result
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean updatePassRole(String username, String password, String lastname, boolean isAdmin) throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            //1. Connect DB
            con = DBUtils.makeConnection();
            if (con != null) {
                //2.create SQL String 
                String sql = "update Registration set password = ?,lastname = ?, isAdmin = ? where username = ?";

                //3.create sql statement
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setString(2, lastname);
                stm.setBoolean(3, isAdmin);
                stm.setString(4, username);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
                //4.execute query

                //5.process result
            }
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean insertRecord(String username, String password, String lastname, boolean isAdmin) throws SQLException, NamingException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement stm = null;

        int affectionRows = 0;
        try {
            //1. Make Connecttion
            conn = DBUtils.makeConnection();
            if (conn != null) {
                //2. Create SQL String
                String sql = "Insert into Registration(username, password, lastname, isAdmin)"
                        + " Values(?, ?, ?, ?)";
                //3. Create Statement obj
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                stm.setString(3, lastname);
                stm.setBoolean(4, isAdmin);
                //4. Execute Query
                affectionRows = stm.executeUpdate();
                // Nếu preparedStatement đã truyền tham số thì executeQery không truyền tham số và ngược lại
            }
            //5. Process result
            if (affectionRows > 0) {
                return true;
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return false;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String query = "select id, name, image, price, title, description from product";
        try {
            Connection conn = null;
            PreparedStatement stm = null;
            ResultSet rs = null;

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    //đếm số lượng trong db

    public int getTotalProduct() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "select count(*) from product";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {

        }

        return 0;
    }
        public int getTotalAccount() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "select count(*) from registration";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {

        }

        return 0;
    }

    public List<Product> pagingProduct(int intdex) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM product \n"
                + "ORDER BY ID \n"
                + "OFFSET ? ROW FETCH NEXT 6 ROW ONLY;";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setInt(1, (intdex - 1) * 6);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
    
    
    public List<Product> pagingAccount(int intdex) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM registration \n"
                + "ORDER BY username \n"
                + "OFFSET ? ROW FETCH NEXT 4 ROW ONLY;";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setInt(1, (intdex - 1) * 4);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
 


//    public static void main(String[] args) {
//        RegistrationDAO dao = new RegistrationDAO();
//        List<Product> list = dao.getProductBycID("cid");
//        for (Product o : list) {
//            System.out.println(o);
//        }
//    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String query = "select * from Category";
        try {
            Connection conn = null;
            PreparedStatement stm = null;
            ResultSet rs = null;
            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public Product getLast() {
        String query = "select top 1 * from product\n"
                + "order by id desc";
        try {
            Connection conn = null;

            PreparedStatement stm = null;
            ResultSet rs = null;

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
        }
        return null;
    }
    // search by name

    public List<Product> searchByName(String txtSearch) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = " Select * from product\n"
                + " where [name] like ?";
                
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setString(1,"%"+ txtSearch+"%");
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
    
     public List<Product> getAllProduct(String cid) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM product ";
              
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setString(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
      public List<Product> getProductBycID(String cid) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM product \n"
                + "where cateID = ?";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setString(1, cid);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
       public List<Product> getProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM product \n"
                + "where sell_ID = ?";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setInt(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }

        } catch (Exception e) {

        }
        return list;

    }
        public Product getProductByID(String id) {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM product \n"
                + "where id = ?";
        try {

            conn = new DBUtils().makeConnection();//mo ket noi voi sql
            stm = conn.prepareStatement(query);
            stm.setString(1, id);
            rs = stm.executeQuery();
            while (rs.next()) {
                return new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }

        } catch (Exception e) {

        }
        return null;
       

    }
}
