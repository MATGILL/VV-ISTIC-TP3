package fr.istic.vv;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    // maxDay

    // Test la récupération du dernier jour d'un mois pour une année bissextile
    @Test
    void testMaxDay_TC1() {
        assertEquals(29, Date.maxDay(2, 2024));  // Février 2024 (année bissextile)
    }

    // Test la récupération du dernier jour d'un mois pour une année non bissextile
    @Test
    void testMaxDay_TC2() {
        assertEquals(28, Date.maxDay(2, 2023));  // Février 2023 (année non bissextile)
    }

    // Test la récupération du dernier jour d'un mois de 30 jours
    @Test
    void testMaxDay_TC3() {
        assertEquals(30, Date.maxDay(4, 2024));  // Avril
    }

    // Test la récupération du dernier jour d'un mois de 31 jours
    @Test
    void testMaxDay_TC4() {
        assertEquals(31, Date.maxDay(1, 2024));  // Janvier
    }

    // Test pour un mois invalide (0)
    @Test
    void testMaxDay_TC5() {
        assertThrows(IllegalArgumentException.class, () -> Date.maxDay(0, 2024));  // Mois invalide
    }

    // Test pour un mois invalide (13)
    @Test
    void testMaxDay_TC6() {
        assertThrows(IllegalArgumentException.class, () -> Date.maxDay(13, 2024), "Un mois invalide (13) devrait provoquer une exception.");
    }


    // isValid

    // Test une date invalide avec année négative
    @Test
    void testisValid_TC1() {
        assertFalse(Date.isValidDate(1,1,-1));  // Année négative
    }

    // Test une date invalide avec année négative
    @Test
    void testisValid_TC1_mutation() {
        assertTrue(Date.isValidDate(1,1,1));  // Année == 1
    }

    // Test des valeurs invalides pour le mois et le jour
    @Test
    void testIsValid_TC2() {
        assertFalse(Date.isValidDate(-1, -1, 0));  // Mois et jour invalides
    }

    // Test d'un mois invalide (0)
    @Test
    void testIsValid_TC3() {
        assertFalse(Date.isValidDate(0,4,2020));  // Mois 0
    }

    // Test d'une date invalide avec un mois et un jour incorrects
    @Test
    void testIsValid_TC4() {
        assertFalse(Date.isValidDate(-2, 0, 2019));  // Mois et jour invalides
    }

    // Test d'une date invalide pour une année non bissextile
    @Test
    void testIsValid_TC5() {
        assertFalse(Date.isValidDate(29,2,2019));  // 29 février 2019 (année non bissextile)
    }

    // Test un mois invalide (13)
    @Test
    void testIsValid_TC6() {
        assertFalse(Date.isValidDate(0, 13, 2018));  // Mois invalide
    }

    // Test d'une date valide pour une année bissextile avec jours == maxDay
    @Test
    void testIsValid_TC7() {
        assertTrue(Date.isValidDate(29,2,2016));  // 29 février 2016 (année bissextile)
    }
    @Test
    void testIsValid_TC8() {
        assertFalse(Date.isValidDate(30,2,2016));  // 29 février 2016 (année bissextile)
    }

    @Test
    void testIsValid_TC9() {
        assertTrue(Date.isValidDate(28,2,2019));  // 29 février 2019 (année non bissextile)
    }
    @Test
    void testIsValid_TC10() {
        assertFalse(Date.isValidDate(29,2,2019));  // 29 février 2019 (année non bissextile)
    }

    @Test
    void testIsValid_TC11() {
        assertTrue(Date.isValidDate(31,3,2019));  // 31 mars 2019 (31 jours max)
    }
    @Test
    void testIsValid_TC12() {
        assertTrue(Date.isValidDate(30,4,2019));  // 30 avril 2019 (30 jours max)
    }
    @Test
    void testIsValid_TC13() {
        assertFalse(Date.isValidDate(31,4,2019)); // 30 avril 2019 (30 jours max)
    }

    // Constructor

    // Test un constructeur avec un mois invalide (0)
    @Test
    void testConstructor_TC2() {
        assertThrows(RuntimeException.class, () -> new Date(0,2,2014));  // Mois invalide
    }

    // compareTo

    // Test de la comparaison entre deux dates avec des années différentes
    @Test
    void testCompareTo_DifferentYears() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 1, 2020);
        assertTrue(date1.compareTo(date2) < 0);  // date1 avant date2
        assertTrue(date2.compareTo(date1) > 0);  // date2 après date1
    }

    // Test de la comparaison entre deux dates ayant la même année mais des mois différents
    @Test
    void testCompareTo_SameYearDifferentMonths() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 12, 2000);
        assertTrue(date1.compareTo(date2) < 0);  // date1 avant date2
        assertTrue(date2.compareTo(date1) > 0);  // date2 après date1
    }

    // Test de la comparaison entre deux dates avec le même mois mais des jours différents
    @Test
    void testCompareTo_SameYearSameMonthDifferentDays() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(2, 1, 2000);
        assertTrue(date1.compareTo(date2) < 0);  // date1 avant date2
        assertTrue(date2.compareTo(date1) > 0);  // date2 après date1
    }

    // Test de la comparaison entre deux dates identiques
    @Test
    void testCompareTo_SameDates() {
        Date date1 = new Date(1, 1, 2000);
        Date date2 = new Date(1, 1, 2000);
        assertEquals(0, date1.compareTo(date2));  // Les dates sont identiques
    }

    // nextDate

    // Test pour la méthode nextDate avec une date du 15 mai 2024
    @Test
    void testNextDate_TC1() {
        Date date = new Date(15, 5, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(16, 5, 2024)));  // Le jour suivant doit être le 16 mai
    }

    // Test pour la méthode nextDate avec une date du 30 avril 2024
    @Test
    void testNextDate_TC2() {
        Date date = new Date(30, 4, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 5, 2024)));  // Le jour suivant doit être le 1er mai
    }

    // Test pour la méthode nextDate avec une date du 31 décembre 2024
    @Test
    void testNextDate_TC3() {
        Date date = new Date(31, 12, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 1, 2025)));  // Le jour suivant doit être le 1er janvier
    }

    // Test pour la méthode nextDate avec une date du 29 février 2024 (année bissextile)
    @Test
    void testNextDate_TC4() {
        Date date = new Date(29, 2, 2024);  // Année bissextile
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 3, 2024)));  // Le jour suivant doit être le 1er mars
    }

    // Test pour la méthode nextDate avec une date du 28 février 2023 (année non bissextile)
    @Test
    void testNextDate_TC5() {
        Date date = new Date(28, 2, 2023);  // Année non bissextile
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 3, 2023)));  // Le jour suivant doit être le 1er mars
    }

    // Test pour la méthode nextDate avec une date du 31 janvier 2024
    @Test
    void testNextDate_TC6() {
        Date date = new Date(31, 1, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 2, 2024)));  // Le jour suivant doit être le 1er février
    }

    // Test pour la méthode nextDate avec une date du 1er décembre 2024
    @Test
    void testNextDate_TC7() {
        Date date = new Date(1, 12, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(2, 12, 2024)));  // Le jour suivant doit être le 2 décembre
    }

    // Test pour la méthode nextDate avec une date du 30 janvier 2024
    @Test
    void testNextDate_mutation1() {
        Date date = new Date(30, 1, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(31, 1, 2024)));  // Le jour suivant doit être le 31 janvier (newDay == maxDay)
    }

    // Test pour la méthode nextDate avec une date du 28 février 2024
    @Test
    void testNextDate_mutation2() {
        Date date = new Date(28, 2, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(29, 2, 2024)));  // Le jour suivant doit être le 29 février (newDay == maxDay)
    }

    // Test pour la méthode nextDate avec une date du 29 avril 2024
    @Test
    void testNextDate_mutation3() {
        Date date = new Date(29, 4, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(30, 4, 2024)));  // Le jour suivant doit être le 30 avril (newDay == maxDay)
    }

    // Test pour la méthode nextDate avec une date du 30 novembre 2024
    @Test
    void testNextDate_mutation4() {
        Date date = new Date(30, 11, 2024);
        Date nextDate = date.nextDate();
        assertEquals(0, nextDate.compareTo(new Date(1, 12, 2024)));  // Le jour suivant doit être le 1 décembre
    }

    // previousDate
    // Test pour la méthode previousDate avec une date du 15 mai 2024
    @Test
    void testPreviousDate_TC1() {
        Date date = new Date(15, 5, 2024);
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(14, 5, 2024)));  // Le jour précédent doit être le 14 mai
    }

    // Test pour la méthode previousDate avec une date du 1er mai 2024
    @Test
    void testPreviousDate_TC2() {
        Date date = new Date(1, 5, 2024);
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(30, 4, 2024)));  // Le jour précédent doit être le 30 avril
    }

    // Test pour la méthode previousDate avec une date du 31 décembre 2024
    @Test
    void testPreviousDate_TC3() {
        Date date = new Date(31, 12, 2024);
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(30, 12, 2024)));  // Le jour précédent doit être le 30 décembre
    }

    // Test pour la méthode previousDate avec une date du 1er janvier 2024
    @Test
    void testPreviousDate_TC4() {
        Date date = new Date(1, 1, 2024);
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(31, 12, 2023)));  // Le jour précédent doit être le 31 décembre
    }

    // Test pour la méthode previousDate avec une date du 1er mars 2024 (année bissextile)
    @Test
    void testPreviousDate_TC5() {
        Date date = new Date(1, 3, 2024);  // Année bissextile
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(29, 2, 2024)));  // Le jour précédent doit être le 29 février
    }

    // Test pour la méthode previousDate avec une date du 1er mars 2023 (année non bissextile)
    @Test
    void testPreviousDate_TC6() {
        Date date = new Date(1, 3, 2023);  // Année non bissextile
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(28, 2, 2023)));  // Le jour précédent doit être le 28 février
    }

    // Test pour la méthode previousDate avec une date du 2 mars 2023 (année non bissextile)
    @Test
    void testPreviousDate_mutation1() {
        Date date = new Date(2, 3, 2023);  // Année non bissextile
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(1, 3, 2023)));  // Le jour précédent doit être le 1 mars février
    }

    // Test pour la méthode previousDate avec une date du 1er février 2023 (année non bissextile)
    @Test
    void testPreviousDate_mutation2() {
        Date date = new Date(1, 2, 2023);  // Année non bissextile
        Date previousDate = date.previousDate();
        assertEquals(0, previousDate.compareTo(new Date(31, 1, 2023)));  // Le jour précédent doit être le 31 janvier
    }

    //isLeapYear
    // Test 1 : Année divisible par 4 mais pas par 100 (année bissextile)
    @Test
    void testIsLeapYear_DivisibleBy4NotBy100() {
        int year = 2024;  // Année bissextile
        assertTrue(Date.isLeapYear(year), "2024 should be a leap year");
    }

    // Test 2 : Année divisible par 100 mais pas par 400 (non bissextile)
    @Test
    void testIsLeapYear_DivisibleBy100NotBy400() {
        int year = 1900;  // Année non bissextile
        assertFalse(Date.isLeapYear(year), "1900 should not be a leap year");
    }

    // Test 3 : Année divisible par 400 (année bissextile)
    @Test
    void testIsLeapYear_DivisibleBy400() {
        int year = 2000;  // Année bissextile
        assertTrue(Date.isLeapYear(year), "2000 should be a leap year");
    }

    // Test 4 : Année non divisible par 4 (non bissextile)
    @Test
    void testIsLeapYear_NotDivisibleBy4() {
        int year = 2023;  // Année non bissextile
        assertFalse(Date.isLeapYear(year), "2023 should not be a leap year");
    }
}
