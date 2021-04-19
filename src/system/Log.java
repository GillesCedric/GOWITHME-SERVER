package system;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/*
 * @since 28/11/2020
 * @authors ANOUMEDEM NGUEFACK Gilles Cédric, NSIA FOTUE Rene, TCHITAKE GNIZE Alain, WELEHELA Patricia
 * @class Log
 * @description cette classe représente les logs du système
 * @public
 */
public class Log {
    private String className;
    private String text;
    private Date date;

    /*
     * @constructor
     * @description constructeur par défaut
     */
    public Log() {
    }

    /*
     * @constructor
     * @param int id l'id du log
     * @param String text le message du log
     * @param Date date la date du log
     * @description constructeur permettant d'initialiser un log avec un id, un texte et la date du log
     */
    public Log(String className,String text) {
        this.className = className;
        this.text = text;
        this.date = new Date();
    }

    //Getters and Setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*
     * @public
     * @method equals
     * @override
     * @returns boolean
     * @param Object o l'objet avec lequel on veut faire la comparaison
     * @description cette méthode permet de tester si l'bject actuel et l'objet passé en paramètre sont égaux
     */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Log other = (Log) obj;
        if (!Objects.equals(this.className, other.className)) {
            return false;
        }
        if (!Objects.equals(this.text, other.text)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    /*
     * @public
     * @method hashCode
     * @override
     * @returns int
     * @description cette méthode permet de retourner le hashcode de l'objet actuel
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.className);
        hash = 71 * hash + Objects.hashCode(this.text);
        hash = 71 * hash + Objects.hashCode(this.date);
        return hash;
    }
    


    /*
     * @public
     * @method toString
     * @override
     * @returns String
     * @description cette méthode permet d'afficher les attributs de la classe sous forme de String
     */
    @Override
    public String toString() {
        return "Log{" + "className=" + className + ", text=" + text + ", date=" + date + '}';
    }

    /*
     * @public
     * @method listAlllogs
     * @returns void
     * @static
     * @throws IOException
     * @description cette méthode permet d'afficher tous les logs du système
     */
    public static ArrayList<String> getLogs() {
        return  GestionFile.readChars();
    }

    /*
     * @public
     * @method addlog
     * @returns void
     * @static
     * @throws IOException
     * @description cette méthode permet d'ajouter un log
     */
    public static void addLog(Log log) {
        GestionFile.writeChars(log.toString());
    }

}