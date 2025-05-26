package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 *******************************************************************************************************
 *   @Class Name         :   EmployeeModel
 *   @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *   @Company            :   Antrazal
 *   @Date               :   12/05/2025
 *   @Description        :   This class models an employee entity with fields such as name, email,
 *                           department, position, manager, skill list, joining date, active status,
 *                           authentication credentials, leave balance and salary. Includes logic for
 *                           skill manipulation and hierarchy representation.
 *******************************************************************************************************
 */
public class EmployeeModel {

    private int id;
    private int companyId;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Integer managerId;
    private String departmentName;
    private LocalDate joiningDate;
    private boolean isActive;
    private List<String> skills;
    private String username;
    private String password;
    private double leaveBalance;
    private double baseSalary;

    public EmployeeModel() {
    }

    /*
     ***********************************************************************************************
     * @Constructor : EmployeeModel(...)
     * 
     * @Description : Initializes employee with provided values and sets joining
     * date to now,
     * active status to true, and initializes skill list.
     ***********************************************************************************************
     */
    public EmployeeModel(int id, int companyId, String firstName, String lastName, String email, String position,
            int managerId, String departmentName, String username, String password, double leaveBalance,
            double baseSalary) {
        this.id = id;
        this.companyId = companyId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.position = position;
        this.managerId = managerId;
        this.departmentName = departmentName;
        this.isActive = true;
        this.joiningDate = LocalDate.now();
        this.skills = new ArrayList<>();
        this.username = username;
        this.password = password;
        this.leaveBalance = leaveBalance;
        this.baseSalary = baseSalary;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public LocalDate getJoiningDate() {
        return (joiningDate == null) ? LocalDate.now() : joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<String> getSkills() {
        return skills == null ? new ArrayList<>() : new ArrayList<>(skills);
    }

    public void addSkill(List<String> newSkills) {
        if (skills == null) {
            skills = new ArrayList<>();
        }
        for (String s : newSkills) {
            if (!skills.contains(s)) {
                skills.add(s);
            }
        }
    }

    public void removeSkill(String skill) {
        if (skills != null) {
            skills.remove(skill);
        }
    }

    public boolean hasSkill(String skill) {
        return skills != null && skills.contains(skill);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public double getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(double leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     ***********************************************************************************************
     * @Method Name : CeoSkills
     * 
     * @Description : Returns a predefined list of essential CEO skills.
     ***********************************************************************************************
     */
    public List<String> CeoSkills() {
        return Arrays.asList(
                "Leadership",
                "Strategic Thinking",
                "Decision Making",
                "Communication",
                "Problem Solving",
                "Visionary Planning");
    }

    @Override
    public String toString() {
        return String.format("Employee[ID: %d, %s %s, Position: %s, Department ID: %s, Skills: %s]",
                id, firstName, lastName, position, departmentName, skills);
    }

    /*
     ***********************************************************************************************
     * @Method Name : getHierarchyString
     * 
     * @Description : Returns employee name prepended by manager name in hierarchy
     * format.
     ***********************************************************************************************
     */
    public String getHierarchyString(String managerName) {
        return (managerName == null || managerName.isEmpty()) ? getFullName() : managerName + " -> " + getFullName();
    }
}
