package controler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationController {
    public boolean phoneNumberCheck(String userPhoneNumber) {
        Pattern pattern = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher matcher = pattern.matcher(userPhoneNumber);
        if (matcher.find() && matcher.group().equals(userPhoneNumber)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean mailCheck(String userMail) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(userMail);
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }
}
