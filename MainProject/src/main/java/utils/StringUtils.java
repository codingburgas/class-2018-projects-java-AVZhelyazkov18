package utils;

public class StringUtils {
    public static String sub(String message, int beginIndex, int endIndex, boolean additionalSpaces) {
        if(beginIndex > endIndex) { return message; }
        String newString = new String();

        if(message == null) {
            for(int i = 0; i < endIndex - 1; i++) {
                newString += " ";
            }
            return newString;
        }

        try {
            for (int i = beginIndex; i < endIndex; i++) {
                newString += message.charAt(i);
            }
        } catch(StringIndexOutOfBoundsException e) {
            if (additionalSpaces == true) {
                for(int i = 0; i < endIndex - message.length(); i++) {
                    newString += " ";
                }
            }
        }

        return newString;
    }
}
