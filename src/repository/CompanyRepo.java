package repository;

import java.sql.*;
import model.CompanyModel;
import model.EmployeeModel;
import utils.Constant;
import utils.DatabaseUtil;

public class CompanyRepo {

    public boolean isCompanyExits(String companyName) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement st = conn.prepareStatement(Constant.CHECK_COMPANY_EXISTS)) {
            st.setString(1, companyName);
            ResultSet res = st.executeQuery();
            return res.next();
        }
    }

    public boolean isUserNameExits(String username) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(Constant.CHECK_USERNAME_EXISTS)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean isEmailExits(String email) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement st = conn.prepareStatement(Constant.CHECK_EMAIL_EXISTS)) {
            st.setString(1, email);
            ResultSet res = st.executeQuery();
            return res.next();
        }
    }

    public void insertCompany(CompanyModel company, EmployeeModel ceo) throws SQLException {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement st = conn.prepareStatement(Constant.INSERT_COMPANY_SQL, Statement.RETURN_GENERATED_KEYS)) {

            st.setString(1, company.getName());
            st.setString(2, company.getEmail());
            st.executeUpdate();

            ResultSet keys = st.getGeneratedKeys();
            if (keys.next()) {
                int companyId = keys.getInt(1);

                try (PreparedStatement stmt = conn.prepareStatement(Constant.INSERT_CEO_SQL)) {
                    stmt.setString(1, ceo.getFirstName());
                    stmt.setString(2, ceo.getLastName());
                    stmt.setString(3, ceo.getEmail());
                    stmt.setString(4, "CEO");
                    stmt.setNull(5, Types.INTEGER); // CEO has no manager
                    stmt.setDate(6, Date.valueOf(ceo.getJoiningDate()));
                    stmt.setBoolean(7, true);
                    stmt.setString(8, String.join(",", ceo.CeoSkills()));
                    stmt.setInt(9, companyId);
                    stmt.setString(10, ceo.getDepartmentName());
                    stmt.setString(11, company.getUsername());
                    stmt.setString(12, company.getPassword());
                    stmt.executeUpdate();
                }
            }
        }
    }
}
