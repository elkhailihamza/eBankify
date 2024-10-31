package org.project.Util;

import org.mindrot.jbcrypt.BCrypt;

public class HashKeyword {
    public static String hash(String keyword, int log_rounds) {
        return BCrypt.hashpw(keyword, BCrypt.gensalt(log_rounds));
    }

    public static boolean check(String keyword, String hashedKeyword) {
        return BCrypt.checkpw(keyword, hashedKeyword);
    }
}
