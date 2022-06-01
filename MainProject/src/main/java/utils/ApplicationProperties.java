package utils;

// It is better if your properties are in different file and not in a class like this
// We are using class here for simplicity
public class ApplicationProperties {

    public static void _init(Integer urlParameter) {
        if(urlParameter == 1)
            MAINURL = URL1;
        else if(urlParameter == 2)
            MAINURL = URL2;
    }

    int currentPath = 2;
    public static String MAINURL;
    private static final String URL1 = "jdbc:sqlserver://DESKTOP-8UKIHUQ\\SQLEXPRESS;databaseName=ProjectDB;encrypt=true;trustServerCertificate=true;integratedSecurity=true";
    private static final String URL2 = "jdbc:sqlserver://ASUS-DASH-F15\\SQLEXPRESS;databaseName=ProjectDB;encrypt=false;integratedSecurity=true;";
}
