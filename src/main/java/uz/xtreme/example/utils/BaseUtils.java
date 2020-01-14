package uz.xtreme.example.utils;

import org.springframework.stereotype.Component;
import uz.xtreme.example.config.ApplicationContextProvider;
import uz.xtreme.example.dao.FunctionParam;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Author: Rustambekov Avazbek
 * Date: 23/10/19
 * Time: 15:47
 */

@Component
public class BaseUtils {

    private static final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR"};

    public String encodeToMd5(String data) {

        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes(), 0, data.length());
            return toHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getBean(String name) {
        return ApplicationContextProvider.applicationContext.getBean(name);
    }

    public String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    private static String toHex(byte[] data) {
        char[] chars = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            chars[i * 2] = HEX_DIGITS[(data[i] >> 4) & 0xf];
            chars[i * 2 + 1] = HEX_DIGITS[data[i] & 0xf];
        }
        return new String(chars);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public boolean isEmpty(Object l) {
        return l == null;
    }

    public String generateParamText(List<FunctionParam> params) {
        StringBuilder builder = new StringBuilder();
        builder.append(" ( ");
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                builder.append(" ? ");
            } else {
                builder.append(" ,? ");
            }
        }
        builder.append(" ) ");
        return builder.toString();
    }

}
