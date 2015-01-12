package springboot.helpers;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class TemplateHelper {

    public static boolean hasAuthority(String authority) {
        for (GrantedAuthority a: SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            if (a.getAuthority().equals(authority)) {
                return true;
            }
        }
        return false;
    }
}
