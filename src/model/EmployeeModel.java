package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public EmployeeModel(int id, int companyId, String firstName, String lastName, String email, String position,
            int managerId, String departmentName, String username, String password,double leaveBalance,double baseSalary) {
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
        this.leaveBalance=leaveBalance;
        this.baseSalary=baseSalary;
    }

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
        if (joiningDate == null) {
            return LocalDate.now();
        }
        return joiningDate;
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
        skills.remove(skill);
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

    public boolean hasSkill(String skill) {
        return skills.contains(skill);
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
        return String.format("Employee[ID: %d, %s %s, Position: %s, Department ID: %d, Skills: %s]",
                id, firstName, lastName, position, departmentName, skills.toString());
    }

    public String getHierarchyString(String managerName) {
        if (managerName == null || managerName.isEmpty()) {
            return getFullName();
        }
        return managerName + " -> " + getFullName();
    }
}