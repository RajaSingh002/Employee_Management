package utils;

public enum Role {
    
    CEO,MANAGER, TECHLEAD, DEVELOPER, HR;

    public static boolean isValidRole(String role) {
        for (Role r : Role.values()) {
            if (r.name().equalsIgnoreCase(role)) return true;
        }
        return false;
    }
}
