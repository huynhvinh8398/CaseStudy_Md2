package utils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class InstantUtils {
    private static final String PATTERN_FOMAT = "HH:mm dd-MM-yyyy";
    public static String instantToString(Instant instant){
return instantToString(instant, null);
    }

    public static String instantToString(Instant instant, String patternFomat) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern
                (patternFomat !=null? patternFomat:PATTERN_FOMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
}
