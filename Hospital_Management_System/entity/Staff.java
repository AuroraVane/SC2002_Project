package entity;

import utils.Color.ColorRole;

public class Staff extends User{
    private String age;
    private String role;
    public Staff(String id, String name, String password, String gender, String role, String age) {
        super(id, name, password,gender);
        this.age = age;
        this.role = role;
    }
    @Override
    public String getRole() {
        return role;
    }
    public String getAge(){
        return age;
    }
    @Override
    public ColorRole getColorRole() {
        switch (role) {
            case "Doctor":
                return ColorRole.DOCTOR;
            case "Administrator":
                return ColorRole.ADMINISTRATOR;
            case "Pharmacist":
                return ColorRole.PHARMACIST;
            default:
                return ColorRole.PATIENT;
        }
    }
}
