package system;

/*
 * @since 28/11/2020
 * @authors ANOUMEDEM NGUEFACK Gilles Cédric, NSIA FOTUE Rene, TCHITAKE GNIZE Alain, WELEHELA Patricia
 * @class Parameter
 * @description Cette est utilisé pour gérer tous les paramètres de  l'application
 * @public
 */
public class Data {
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String SYSTEMNAME = System.getProperty("user.name");
    public static final String LOCATION = "C:"+SEPARATOR+"Users"+SEPARATOR+""+SYSTEMNAME+""+SEPARATOR;
    //public static final String LOCATION = "E:"+SEPARATOR;
    public static final String APPNAME = "GOWITHME-SERVER";
    public static final String LOG = SYSTEMNAME+".log";
    public static final String USERS = "users";
    public static final String PROFILES = USERS+SEPARATOR+"profiles";
    public static final String CARS = USERS+SEPARATOR+"cars";
    public static final String LOGS = USERS+SEPARATOR+"logs";
    public static final int PORT = 3000;
    public static final String HOST = "127.0.0.1";
}
