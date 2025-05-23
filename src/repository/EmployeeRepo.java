package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.EmployeeModel;
import utils.Constant;

public class EmployeeRepo {

    private final Connection conn;

    public EmployeeRepo(Connection conn) {
        this.conn = conn;
    }

public class DuplicateFieldException extends Exception {
    public DuplicateFieldException(String message) {
        super(message);
    }
}

    public void Add(EmployeeModel employee) {
        String checkSql = "SELECT id FROM employees WHERE email = ? AND is_active = false";

        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, employee.getEmail());
            try (ResultSet res = checkStmt.executeQuery()) {
                if (res.next()) {
                    throw new IllegalStateException("Cannot rehire a fired employee..." + employee.getEmail());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        String insertSql = "INSERT INTO employees (first_name, last_name, email, position, manager_id, department_name, joining_date, is_active, skills, company_id,username,password,base_salary,leaveBalance) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?,?)";

        try (PreparedStatement st = conn.prepareStatement(insertSql)) {
            st.setString(1, employee.getFirstName());
            st.setString(2, employee.getLastName());
            st.setString(3, employee.getEmail());
            st.setString(4, employee.getPosition());
            st.setInt(5, employee.getManagerId());
            st.setString(6, employee.getDepartmentName());
            st.setDate(7, Date.valueOf(employee.getJoiningDate()));
            st.setBoolean(8, employee.isActive());
            st.setString(9, String.join(",", employee.getSkills()));
            st.setInt(10, employee.getCompanyId());
            st.setString(11, employee.getUsername());
            st.setString(12, employee.getPassword());
            st.setDouble(13, employee.getBaseSalary());
            st.setDouble(14, employee.getLeaveBalance());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(int empID, EmployeeModel employee) throws DuplicateFieldException {
    String checkSql = "SELECT id FROM employees WHERE id = ? AND company_id = ? AND is_active = true";

    try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
        checkStmt.setInt(1, empID);
        checkStmt.setInt(2, employee.getCompanyId());

        try (ResultSet rs = checkStmt.executeQuery()) {
            if (!rs.next()) {
                return false;
            }
        }

        String updateSql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, position = ?, manager_id = ?, "
                + "department_name = ?, skills = ?, username = ?, password = ? "
                + "WHERE id = ? AND company_id = ? AND is_active = true";

        try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setString(1, employee.getFirstName());
            stmt.setString(2, employee.getLastName());
            stmt.setString(3, employee.getEmail());
            stmt.setString(4, employee.getPosition());
            stmt.setInt(5, employee.getManagerId());
            stmt.setString(6, employee.getDepartmentName());
            stmt.setString(7, String.join(",", employee.getSkills()));
            stmt.setString(8, employee.getUsername());
            stmt.setString(9, employee.getPassword());
            stmt.setInt(10, empID);
            stmt.setInt(11, employee.getCompanyId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }

    } catch (SQLIntegrityConstraintViolationException e) {
        if (e.getMessage().toLowerCase().contains("email")) {
            throw new DuplicateFieldException("Email already exists. Please use a different one.");
        } else if (e.getMessage().toLowerCase().contains("username")) {
            throw new DuplicateFieldException("Username already exists. Please choose a unique username.");
        } else {
            throw new DuplicateFieldException("Duplicate value found. Please ensure all unique fields are correct.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    public boolean delete(int id, int companyID) {
        String sql = "UPDATE employees SET is_active = false WHERE id = ? AND company_id = ? AND is_active = true";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            st.setInt(2, companyID);
            int rows = st.executeUpdate();

            if (rows > 0) {

                return true;
            } else {

                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<EmployeeModel> Read(int companyID) {
        String sql = "SELECT * FROM employees WHERE is_active = true AND company_id = ?";
        List<EmployeeModel> employees = new ArrayList<>();

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, companyID);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    EmployeeModel emp = new EmployeeModel();
                    emp.setId(rs.getInt("id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPosition(rs.getString("position"));
                    emp.setManagerId(rs.getInt("manager_id"));
                    emp.setDepartmentName(rs.getString("department_name"));

                    Date date = rs.getDate("joining_date");
                    emp.setJoiningDate(date != null ? date.toLocalDate() : null);

                    emp.setActive(rs.getBoolean("is_active"));

                    emp.setCompanyId(rs.getInt("company_id"));

                    employees.add(emp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    public EmployeeModel getById(int empId, int companyId) {
        String sql = "SELECT * FROM employees WHERE id = ? AND company_id = ? AND is_active = true";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    EmployeeModel emp = new EmployeeModel();
                    emp.setId(rs.getInt("id"));
                    emp.setFirstName(rs.getString("first_name"));
                    emp.setLastName(rs.getString("last_name"));
                    emp.setEmail(rs.getString("email"));
                    emp.setPosition(rs.getString("position"));

                    Object managerObj = rs.getObject("manager_id");
                    emp.setManagerId(managerObj != null ? (Integer) managerObj : null);

                    emp.setDepartmentName(rs.getString("department_name"));

                    Date date = rs.getDate("joining_date");
                    emp.setJoiningDate(date != null ? date.toLocalDate() : null);

                    emp.setActive(rs.getBoolean("is_active"));
                    emp.setCompanyId(rs.getInt("company_id"));
                    emp.setUsername(rs.getString("username"));
                    emp.setPassword(rs.getString("password"));

                    return emp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isEmailAlreadyUsed(String email, int companyId) {
        String sql = "SELECT * FROM employees WHERE email = ? AND company_id = ? AND is_active = true";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, companyId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailUsedByFiredEmployee(String email, int companyId) {
        String sql = "SELECT * FROM employees WHERE email = ? AND company_id = ? AND is_active = false";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setInt(2, companyId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public EmployeeModel findEmployeeByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM employees WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setId(resultSet.getInt("id"));
                employee.setUsername(resultSet.getString("username"));
                employee.setPassword(resultSet.getString("password"));
                employee.setActive(resultSet.getBoolean("is_active"));
                employee.setCompanyId(resultSet.getInt("company_id"));
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeModel> getManagers(int companyId) {
        List<EmployeeModel> list = new ArrayList<>();
        String sql = "SELECT * FROM employees WHERE company_id = ? AND position = 'Manager' AND is_active = 1";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, companyId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeModel emp = new EmployeeModel();
                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setPosition(rs.getString("position"));
                list.add(emp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Double getBaseSalary(int empId, int companyId) {
        String sql = "SELECT base_salary FROM employees WHERE id = ? AND company_id = ? AND is_active = true";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("base_salary");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEmployeeNameAndPosition(int empId, int companyId) {
        String sql = "SELECT first_name, last_name, position FROM employees WHERE id = ? AND company_id = ? AND is_active = true";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, empId);
            stmt.setInt(2, companyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("first_name") + " " + rs.getString("last_name") + " (" + rs.getString("position")
                        + ")";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel findByUsername(String username, String companyName) {
        String sql = """
                    SELECT e.*, c.id AS company_id
                    FROM companies c
                    JOIN employees e ON e.company_id = c.id
                    WHERE e.username = ? AND c.company_name = ? AND e.is_active = true
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, companyName);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new EmployeeModel(
                        rs.getInt("id"),
                        rs.getInt("company_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getInt("manager_id"),
                        rs.getString("department_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("leaveBalance"),
                        rs.getDouble("base_salary"));
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel findByUsernameAndPassword(String username, String password, String companyName) {
        String sql = """
                    SELECT e.*, c.id AS company_id
                    FROM companies c
                    JOIN employees e ON e.company_id = c.id
                    WHERE e.username = ? AND e.password = ? AND c.company_name = ? AND e.is_active = true
                """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, companyName);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return new EmployeeModel(
                        rs.getInt("id"),
                        rs.getInt("company_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getInt("manager_id"),
                        rs.getString("department_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("leaveBalance"),
                        rs.getDouble("base_salary"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EmployeeModel getActiveEmployeeById(int empId, int comapnyId) {
        String query = "SELECT * FROM employees WHERE id = ? AND company_id = ? AND is_active = true";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, empId);
            ps.setInt(2, comapnyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EmployeeModel(
                        rs.getInt("id"),
                        rs.getInt("company_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getString("position"),
                        rs.getInt("manager_id"),
                        rs.getString("department_name"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getDouble("leaveBalance"),
                        rs.getDouble("base_salary"));
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }
        return null;
    }

    public boolean addSkills(int empId, List<String> newSkills) {
        String fetchQuery = "SELECT skills FROM employees WHERE id = ?";
        String updateQuery = "UPDATE employees SET skills = ? WHERE id = ?";

        try (PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery)) {
            fetchStmt.setInt(1, empId);
            ResultSet rs = fetchStmt.executeQuery();

            if (rs.next()) {
                String existingSkills = rs.getString("skills");
                Set<String> skillSet = new HashSet<>();

                if (existingSkills != null && !existingSkills.isBlank()) {
                    skillSet.addAll(List.of(existingSkills.split(",")));
                }

                for (String skill : newSkills) {
                    skillSet.add(skill);
                }

                String updatedSkills = String.join(",", skillSet);

                try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                    updateStmt.setString(1, updatedSkills);
                    updateStmt.setInt(2, empId);
                    return updateStmt.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
             e.printStackTrace();
        }

        return false;
    }

    public String getSkillsById(int empId) {
        String query = "SELECT skills FROM employees WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("skills");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<EmployeeModel> getAllEmployeesByCompany(int companyId) {
        List<EmployeeModel> employees = new ArrayList<>();

        String sql = "SELECT * FROM employees WHERE company_id = ? AND is_active = true";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, companyId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    private EmployeeModel mapResultSetToEmployee(ResultSet rs) throws SQLException {
        EmployeeModel emp = new EmployeeModel();
        emp.setId(rs.getInt("id"));
        emp.setFirstName(rs.getString("first_name"));
        emp.setLastName(rs.getString("last_name"));
        emp.setEmail(rs.getString("email"));
        emp.setUsername(rs.getString("username"));
        emp.setPassword(rs.getString("password"));
        emp.setPosition(rs.getString("position"));
        emp.setDepartmentName(rs.getString("department_name"));
        emp.setCompanyId(rs.getInt("company_id"));
        emp.setManagerId((Integer) rs.getObject("manager_id"));
        emp.setBaseSalary(rs.getDouble("base_salary"));
        emp.setLeaveBalance(rs.getInt("leaveBalance"));
        emp.setActive(rs.getBoolean("is_active"));
        return emp;
    }

}
