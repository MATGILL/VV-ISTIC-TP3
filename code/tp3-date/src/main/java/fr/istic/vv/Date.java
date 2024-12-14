package fr.istic.vv;

import java.util.Objects;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) { //TESTED
        boolean validDate = isValidDate(day, month, year);
        if (!validDate) {
            throw new RuntimeException("The date is not valid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) { //TESTED
        if (year < 1) {
            return false;
        }
        if (month <= 0 || month > 12) {
            return false;
        }
        int maxDay = maxDay(month, year);
        return day > 0 && day <= maxDay;
    }


    public static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0));
    }

    public Date nextDate() { //TESTED
        // Incrémenter le jour
        int newDay = day + 1;
        int maxDay = maxDay(month, year);

        if (newDay > maxDay) {
            newDay = 1; // Réinitialiser le jour au 1er
            int newMonth = month + 1;
            if (newMonth > 12) {
                newMonth = 1; // Réinitialiser le mois au janvier
                year++; // Incrémenter l'année
            }
            return new Date(newDay, newMonth, year);
        }
        return new Date(newDay, month, year);
    }

    public Date previousDate() {
        // Décrémenter le jour
        int newDay = day - 1;
        if (newDay < 1) {
            int newMonth = month - 1;
            if (newMonth < 1) {
                newMonth = 12; // Réinitialiser le mois à décembre
                year--; // Décrémenter l'année
            }
            newDay = maxDay(newMonth, year); // Aller au dernier jour du mois précédent
            return new Date(newDay, newMonth, year);
        }
        return new Date(newDay, month, year);
    }

    @Override
    public int compareTo(Date other) { //TESTED
        Objects.requireNonNull(other);
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        }
        if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        }
        return Integer.compare(this.day, other.day);
    }


    public static int maxDay(int month, int year) {
        if(month <1 || month > 12){throw new IllegalArgumentException("invalid month");}
        if(year < 1){throw new IllegalArgumentException("invalid year");}
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            return 30;
        } else {
            return 31;
        }
    }
}