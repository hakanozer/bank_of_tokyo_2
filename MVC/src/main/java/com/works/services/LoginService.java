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
    final TinkEncDec tinkEncDec;

    public boolean login(Admin admin) {
        boolean status = false;
        try {
            String sql = "select * from ADMIN where EMAIL = ?";
            PreparedStatement st = db.dataSource().getConnection().prepareStatement(sql);
            st.setString(1, admin.getEmail());
            ResultSet rs = st.executeQuery();
            status = rs.next();
            if (status) {
                String dbPassword = rs.getString("password");
                String plainPassword = tinkEncDec.decrypt(dbPassword);
                if ( admin.getPassword().equals(plainPassword) ) {
                    admin.setAid( rs.getLong("aid") );
                    req.getSession().setAttribute("admin", admin);
                }
            }
        }catch (Exception ex) {
            System.err.println("Login Error : " + ex);
        }
        return status;
    }

    public void logout() {
        req.getSession().removeAttribute("admin");
    }


}
