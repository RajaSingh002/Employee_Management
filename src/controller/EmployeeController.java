package controller;

import java.sql.Connection;
import java.time.*;
import java.util.*;
import model.*;
import repository.EmployeeRepo.DuplicateFieldException;
import service.*;
import utils.*;
import view.*;

/*
 *******************************************************************************************************
 *  @Class Name         :   EmployeeController
 *  @Author             :   <Raja Kumar>(raja.kumar@antrazal.com)
 *  @Company            :   Antrazal
 *  @Date               :   12-05-2025
 *  @Description        :   Controller class handling employee-related operations including CRUD,
 *                         hierarchy viewing, salary slip generation, skills management, and 
 *                         delegating leave management to LeaveController.
 *******************************************************************************************************
 */

public class EmployeeController {
    protected final EmployeeModel user;
    private final EmployeeService eService;
    protected final AttendanceService aService;
    protected final LeaveController leaveController;

    public EmployeeController(EmployeeModel user, Connection conn) {
        this.user = user;
        this.eService = new EmployeeService(conn);
        this.aService = new AttendanceService(conn);
        this.leaveController = new LeaveController(user, new LeaveService(conn));

    }

    Scanner sc = ScannerSingleton.getInstance();

    /*
     *********************************************************
     * @Method Name : addEmployee
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Collects input for a new employee, validates uniqueness of
     * email,
     * assigns manager based on position, sets initial leave balance,
     * and delegates creation to EmployeeService.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    public void addEmployee() {
        String firstName, lastName, email, position, departmentName, username, password;
        int managerID;

        while ((firstName = EmployeeView.FirstName()) == null)
            return;
        while ((lastName = EmployeeView.LastName()) == null)
            return;

        while (true) {
            email = EmployeeView.Email();
            if (email == null)
                return;

            if (eService.isEmailUsedByFiredEmployee(email, user.getCompanyId())) {
                EmployeeView.showMessage(Constant.EMAIL_USED_BY_FIRED);
            } else if (eService.isEmailAlreadyUsed(email, user.getCompanyId())) {
                EmployeeView.showMessage(Constant.EMAIL_USED_BY_ACTIVE);
            } else {
                break;
            }
        }

        double baseSalary = EmployeeView.Salary();
        if (baseSalary == -1)
            return;

        position = EmployeeView.Position();
        if (position == null)
            return;

        if (position.equalsIgnoreCase("Manager") || position.equalsIgnoreCase("HR")) {
            managerID = user.getId();
        } else {
            while (true) {
                List<EmployeeModel> managers = eService.getManagers(user.getCompanyId());

                if (managers.isEmpty()) {
                    EmployeeView.showMessage(Constant.NO_MANAGERS_AVAILABLE);
                    return;
                }

                EmployeeView.displayManagers(managers);
                managerID = EmployeeView.ManagerId();
                if (managerID == -1)
                    return;

                EmployeeModel manager = eService.getEmployeeById(managerID, user.getCompanyId());
                if (manager == null || !manager.getPosition().equalsIgnoreCase("Manager")) {
                    EmployeeView.showMessage(Constant.INVALID_MANAGER);
                    continue;
                }

                if (EmployeeView.confirm(Constant.CONFIRM_MANAGER_SELECTION)) {
                    break;
                }
            }
        }

        while ((departmentName = EmployeeView.Department()) == null)
            return;

        LocalDate joiningDate = LocalDate.now();

        while ((username = EmployeeView.Username()) == null)
            return;

        while ((password = EmployeeView.Password()) == null)
            return;

        EmployeeModel employee = new EmployeeModel();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setPosition(position);
        employee.setManagerId(managerID);
        employee.setDepartmentName(departmentName);
        employee.setJoiningDate(joiningDate);
        employee.setActive(true);
        employee.setCompanyId(user.getCompanyId());
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setBaseSalary(baseSalary);
        employee.setLeaveBalance(15);

        eService.createEmployee(employee);
        EmployeeView.showMessage(Constant.EMPLOYEE_ADDED_SUCCESSFULLY);
    }

    /*
     *********************************************************
     * @Method Name : ViewAll
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Fetches and displays all employees of the user's company.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */

    public void ViewAll() {
        List<EmployeeModel> employees = eService.getEmployeesByCompany(user.getCompanyId());
        Constant.printEmployeeTable(employees);
    }

    /*
     *********************************************************
     * @Method Name : UpdateEmployee
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Updates employee details after validating inputs and
     * handling duplicate fields. Allows skipping fields.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */

    public void UpdateEmployee() {
        String input = EmployeeView.askEmployeeIdToUpdate();
        if (input.equalsIgnoreCase(Constant.BACK))
            return;

        int empId;
        try {
            empId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            EmployeeView.showInvalidIdMessage();
            return;
        }

        EmployeeModel emp = eService.getIdAndComapny(empId, user.getCompanyId());
        if (emp == null) {
            EmployeeView.showEmployeeNotFound();
            return;
        }

        String firstName = EmployeeView.askFirstName(emp.getFirstName());
        if (firstName.equalsIgnoreCase(Constant.BACK))
            return;
        if (!firstName.isBlank() && firstName.matches("[a-zA-Z]+")) {
            emp.setFirstName(firstName);
        } else if (!firstName.isBlank()) {
            EmployeeView.showInvalidFirstName();
        }

        String lastName = EmployeeView.askLastName(emp.getLastName());
        if (lastName.equalsIgnoreCase(Constant.BACK))
            return;
        if (!lastName.isBlank() && lastName.matches("[a-zA-Z]+")) {
            emp.setLastName(lastName);
        } else if (!lastName.isBlank()) {
            EmployeeView.showInvalidLastName();
        }

        String email = EmployeeView.askEmail(emp.getEmail());
        if (email.equalsIgnoreCase(Constant.BACK))
            return;
        if (!email.isBlank() && email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            emp.setEmail(email);
        } else if (!email.isBlank()) {
            EmployeeView.showInvalidEmail();
        }

        String newPosition = EmployeeView.askPosition();
        if (newPosition == null)
            return;
        emp.setPosition(newPosition);

        while (true) {
            String managerInput = EmployeeView.askManagerId(emp.getManagerId());
            if (managerInput.equalsIgnoreCase(Constant.BACK))
                return;
            if (managerInput.isBlank())
                break;

            try {
                int managerId = Integer.parseInt(managerInput);
                EmployeeModel manager = eService.getIdAndComapny(managerId, user.getCompanyId());
                if (manager != null && manager.getId() != emp.getId()) {
                    emp.setManagerId(managerId);
                    break;
                } else {
                    EmployeeView.showInvalidManagerId();
                    EmployeeView.showAvailableManagers(eService.getManagers(user.getCompanyId()));
                }
            } catch (NumberFormatException e) {
                EmployeeView.showNonNumericManagerId();
            }
        }

        String deptName = EmployeeView.askDepartmentName(emp.getDepartmentName());
        if (deptName.equalsIgnoreCase(Constant.BACK))
            return;
        if (!deptName.isBlank()) {
            emp.setDepartmentName(deptName);
        }

        String skillInput = EmployeeView.askSkills(emp.getSkills());
        if (skillInput.equalsIgnoreCase(Constant.BACK))
            return;
        if (!skillInput.isBlank()) {
            emp.addSkill(List.of(skillInput.split(",")));
        }

        String username = EmployeeView.askUsername(emp.getUsername());
        if (username.equalsIgnoreCase(Constant.BACK))
            return;
        if (!username.isBlank()) {
            emp.setUsername(username);
        }

        String password = EmployeeView.askPassword();
        if (password.equalsIgnoreCase(Constant.BACK))
            return;
        if (!password.isBlank()) {
            emp.setPassword(password);
        }

        try {
            eService.updateEmployee(empId, emp);
            EmployeeView.showEmployeeUpdateSuccess();
        } catch (DuplicateFieldException e) {
            EmployeeView.showDuplicateFieldMessage(e.getMessage());
        }

    }

    /*
     *********************************************************
     * @Method Name : DeleteEmployee
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Deletes (soft delete) an employee by ID after input
     * validation.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */

    public void DeleteEmployee() {
        while (true) {
            String input = EmployeeView.getEmployeeIdToDelete();

            if (input.equalsIgnoreCase(Constant.BACK))
                return;

            try {
                int id = Integer.parseInt(input);
                boolean success = eService.deleteEmployee(id, user.getCompanyId());

                if (success) {
                    EmployeeView.showEmployeeDeletedSuccess();
                } else {
                    EmployeeView.showEmployeeDeleteFailure();
                }
                break;

            } catch (NumberFormatException e) {
                EmployeeView.showInvalidEmployeeIdMessage();
            }
        }
    }

    /*
     *********************************************************
     * @Method Name : showHierarchy
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays the employee hierarchy starting from the specified
     * employee ID.
     * Prevents CEO from viewing own hierarchy.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */

    public void showHierarchy() {
        String input = HierarchyView.getEmployeeIdForHierarchy();

        if (input.equalsIgnoreCase(Constant.BACK))
            return;

        int empId;
        try {
            empId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            EmployeeView.showInvalidEmployeeIdMessage();
            return;
        }

        if (empId == user.getId() && user.getPosition().equalsIgnoreCase("CEO")) {
            HierarchyView.showCEOHierarchyRestriction();
            return;
        }

        try {
            List<EmployeeModel> hierarchy = eService.getHierarchy(empId, user.getCompanyId());

            if (hierarchy.isEmpty()) {
                HierarchyView.showNoHierarchyFound();
            } else {
                HierarchyView.displayHierarchy(hierarchy);
            }
        } catch (Exception e) {
            HierarchyView.showGenericError(e.getMessage());
            e.printStackTrace();
        }
    }

    /*
     *********************************************************
     * @Method Name : generateSalarySlipForEmployee
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts for an employee ID and generates salary slip by
     * calling service.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */

    public void generateSalarySlipForEmployee() {
        Scanner scanner = ScannerSingleton.getInstance();

        while (true) {
            SalaryView.SalarySlipEmployeeId();
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase(Constant.BACK)) {
                SalaryView.showReturnToMenu();
                return;
            }

            try {
                int empId = Integer.parseInt(input);
                if (empId < 0) {
                    SalaryView.showNegativeEmployeeIdError();
                    continue;
                }

                String message = eService.generateSalarySlip(empId, user.getCompanyId());
                SalaryView.showSalarySlipMessage(message);
                return;

            } catch (NumberFormatException e) {
                SalaryView.showInvalidNumericIdMessage();
            }
        }
    }

    /*
     *********************************************************
     * @Method Name : handleAddSkills
     * 
     * @author : <Raja Kumar>(raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays current and allowed skills, prompts for skill
     * addition,
     * validates inputs, and updates employee skills.
     * 
     * @param : none
     * 
     * @return : void
     ********************************************************
     */
    protected void handleAddSkills() {
        Scanner sc = ScannerSingleton.getInstance();
        int empId = user.getId();

        String currentSkills = eService.getEmployeeSkills(empId);
        SkillView.showCurrentSkillsHeader();

        if (currentSkills == null || currentSkills.isBlank()) {
            SkillView.showNoSkillsMessage();
        } else {
            List<String> skillList = Arrays.asList(currentSkills.split(","));
            SkillView.displaySkillTable(skillList);
        }

        SkillView.showAllowedSkillsHeader();
        SkillView.displaySkillTable(Constant.SKILLS);

        SkillView.promptForSkillsToAdd();
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase(Constant.BACK))
            return;

        List<String> skills = Arrays.stream(input.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();

        if (skills.isEmpty()) {
            SkillView.showInvalidSkillInput();
            return;
        }

        List<String> invalid = eService.getInvalidSkills(skills);
        if (!invalid.isEmpty()) {
            SkillView.showInvalidSkills(invalid, Constant.SKILLS);
            return;
        }

        boolean success = eService.addSkillsToEmployee(empId, skills);
        if (success) {
            SkillView.showSkillAdditionSuccess();
        }
    }

}
