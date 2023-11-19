package de.ibapp.ghostnet.validation;

public class Validation {
    private String namePattern = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private String phonePattern = "^(\\+[0-9]{1,3}[- ]?)?\\(?[0-9]{3}\\)?[- ]?[0-9]{3}[- ]?[0-9]{4}$";

    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(this.phonePattern);
    }
    public boolean isValidName(String name) {
        return name.matches(this.namePattern);
    }
    public  boolean isvalidateNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isvalidateCoordinates(String latitude, String longitude) {
        if (Double.parseDouble(latitude) < -90 || Double.parseDouble(latitude) > 90) {
            return false;
        }
        if (Double.parseDouble(longitude) < -180 || Double.parseDouble(longitude) > 180) {
            return false;
        }
        return true;
    }
}
