package edu.ufp.inf.lp2.projeto.Geocaching;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Date implements Comparable<Date> {

    private int day;

    private int month;

    private int year;

    private int hour;

    private int minute;
    private int second;
    private int milisecond;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public Date(int day, int month, int year, int hour, int minute, int second, int milisecond) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.milisecond = milisecond;
    }
/*
    public Date() {
        Calendar gregCalendar = new GregorianCalendar();
        this.day = gregCalendar.get(Calendar.DAY_OF_MONTH);
        this.month = gregCalendar.get(Calendar.MONTH) + 1;    // retorna de 0 a 11 , entao acrescentamos sempre +1 para ficar equivalente
        this.year = gregCalendar.get(Calendar.YEAR);
    }

 */

    public Date() {
        GregorianCalendar gregCalendar = new GregorianCalendar();
        this.day = gregCalendar.get(Calendar.DAY_OF_MONTH);
        this.month = gregCalendar.get(Calendar.MONTH) + 1;
        this.year = gregCalendar.get(Calendar.YEAR);
        this.hour = gregCalendar.get(Calendar.HOUR_OF_DAY);
        this.minute = gregCalendar.get(Calendar.MINUTE);
        this.second = gregCalendar.get(Calendar.SECOND);
        this.milisecond = gregCalendar.get(Calendar.MILLISECOND);
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

    public int compareTo(Date d) {
        if (this.year == d.year && this.month == d.month && this.day == d.day && this.hour == d.hour && this.minute == d.minute && this.second == d.second && this.milisecond == d.milisecond) {
            return 0;
        } else if (this.year == d.year) {
            if (this.month == d.month) {
                if (this.day == d.day) {
                    if (this.hour == d.hour) {
                        if (this.minute == d.minute) {
                            if (this.second == d.second) {
                                return this.milisecond < d.milisecond ? -1 : 1;
                            }
                            return this.second < d.second ? -1 : 1;
                        }

                        return this.minute < d.minute ? -1 : 1;
                    }
                    return this.hour < d.hour ? -1 : 1;
                }
                return this.day < d.day ? -1 : 1;
            } else {
                return this.month < d.month ? -1 : 1;
            }
        } else {
            return this.year < d.year ? -1 : 1;
        }
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

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMilisecond() {
        return milisecond;
    }

    @Override
    public String toString() {
        return this.day + "/" + this.month + "/" + this.year + " " + this.hour + ":" + this.minute;
    }
}
