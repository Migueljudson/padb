/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package miguel;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author 20221074010048
 */
public class CustomerDAO {
    

    private Connection con;

    public CustomerDAO() throws SQLException {
        this.con = new ConnectionFactory().getConnection();
        System.out.println("Connection OK!");
    }

    public void insertCustomer(Customer c) throws SQLException {
        String insertSql = "INSERT INTO customer (store_id, first_name, last_name, email, address_id, active) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(insertSql);
        pst.setInt(1, c.getStore_id());
        pst.setString(2, c.getFirst_name());
        pst.setString(3, c.getLast_name());
        pst.setString(4, c.getEmail());
        pst.setInt(5, c.getAddress_id());
        pst.setInt(6, c.getActive());

        pst.execute();
        pst.close();
        System.out.println(" Customer ADDED!");

    }

    public void deleteCustomer(int id) throws SQLException {
        String sql = "delete from customer"
                + "where customer_id=?";
    }

    public void updateCustomer(int id) throws SQLException {
        String sql = "UPDATE customer"
                + "SET store_id = ?, address_id = ?, last_update = NOW() WHERE first_name = ? AND last_name = ?"
                + "where customer_id=?";
    }

    public void showCustomer(int id) throws SQLException {
        Statement st = con.createStatement();

        String query = "select * from customer"
                + "order by customer_id desc"
                + " limit 5";

        ResultSet rs = st.executeQuery(query);

        ResultSetMetaData md = rs.getMetaData();
        int col = md.getColumnCount();

        System.out.println("Tabela: " + md.getTableName(1));
        for (int i = 1; i <= col; i++) {
            System.out.print(md.getColumnName(i) + "\t");
        }
        System.out.println("");

        while (rs.next()) {
            for (int i = 1; i <= col; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println("");
        }
        st.close();
    }

    void showCustomer() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   }
