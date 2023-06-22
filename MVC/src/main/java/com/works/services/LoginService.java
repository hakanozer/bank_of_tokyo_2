package com.works.services;

import com.works.entities.Admin;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
@RequiredArgsConstructor
public class LoginService {

    final DB db;
    final HttpServletRequest req;

    public boolean login(Admin admin) {
        boolean status = false;
        try {
            String sql = "select * from ADMIN where EMAIL = ? and PASSWORD = ?";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, admin.getEmail());
            st.setString(2, admin.getPassword());
            ResultSet rs = st.executeQuery();
            status = rs.next();
            if (status) {
                admin.setAid( rs.getLong("aid") );
                req.getSession().setAttribute("admin", admin);
            }
        }catch (Exception ex) {
            System.err.println("Login Error : " + ex);
        }
        return status;
    }


}
