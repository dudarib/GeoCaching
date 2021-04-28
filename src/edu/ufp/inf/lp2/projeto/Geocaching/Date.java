package edu.ufp.inf.lp2.projeto.Geocaching;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date> {

    private int day;

    private int month;

    private int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date() {
        Calendar gregCalendar = new GregorianCalendar();
        this.day = gregCalendar.get(Calendar.DAY_OF_MONTH);
        this.month = gregCalendar.get(Calendar.MONTH) + 1;    // retorna de 0 a 11 , etao acrescentamos sempre +1 para ficar equivalente
        this.year = gregCalendar.get(Calendar.YEAR);
    }

    /**
     * Funçao que testa se a data , é maior do que a data dada
     *
     * @param d
     * @return false, if data é menor, true if data é maior
     */
    public boolean beforeDate(Date d) {
        if (this.year < d.year) {
            return true;
        } else if (this.year == d.year) {
            if (this.month < d.month) {
                return true;
            } else if (this.month == d.month) {
                return this.day < d.day;
            }

        }
        return false;
    }

    public int daysMonth(int month, int year) {
        //testar se mês está entre 1 e 12, se estiver retornar (-1)
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear(year)) // return(isLeap(Year) ? 29 : 28);
                {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 31;
        }
    }

    public boolean afterDate(Date d) {
        return false; //! beforeDate
    }

    public void incrementDate() {
    }

    public int differenceYears(Date d) {
        return 0;
    }

    public boolean isLeapYear() {
        return ((this.year % 4 == 0) && ((this.year % 100 != 0) || (this.year % 400 == 0)));
    }

    public static boolean isLeapYear(Integer year) {
        return ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0)));
    }

    // retorna 0 se a data for igual e retorna -1 se nao for
    public int compareTo(Date d) {
        if (this.day == d.day && this.month == d.month && this.year == d.year)
            return 0;
        return beforeDate(d)?-1:1;      // se o before date for verdadeiro retorna -1, senao retorna 1
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month the month to set
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

}
