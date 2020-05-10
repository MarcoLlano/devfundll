/*
 *
 *   Copyright (c) 2020 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the
 *  license agreement you entered into with Jalasoft.
 *
 */

package com.jalasoft.practice.loop;

import java.util.ArrayList;
import java.util.List;

/**
 * Adding list filter task by using foreach and stream foreach-filter:
 *
 * 1: Get countries that start with 'A' letter.
 * 2: Get countries with length > 5.
 * 3: Get countries that length is odd.
 */
public class Task {

    private List<String> setCountries() {
        List<String> countries = new ArrayList<>();
        countries.add("Bolivia");
        countries.add("Argentina");
        countries.add("Argelia");
        countries.add("Portugal");
        countries.add("Italia");
        countries.add("Australia");
        countries.add("Peru");
        countries.add("Korea");

        return countries;
    }

    /**
     * Task 1: Get countries that start with 'A' letter.
     * Filter should use foreach and stream foreach-filter.
     */
    public void filterStartWithA() {
        List<String> countries = setCountries();

        // Task 1 using foreach.
        System.out.println("Filter countries that starts with 'A' by using foreach.");
        for (String country : countries) {
            if (country.startsWith("A")) {
                System.out.println(country);
            }
        }

        // Task 1 using stream foreach-filter
        System.out.println("*********************************************************************");
        System.out.println("Filter countries that starts with 'A' by using stream foreach filter.");
        countries.stream()
                .filter(value -> value.startsWith("A"))
                .forEach(System.out::println);
    }

    /**
     * 2: Get countries with length >= 5.
     * Filter should use foreach and stream foreach-filter.
     */
    public void filterCountriesLengthMajorThanFive() {
        List<String> countries = setCountries();

        // Task 2 using foreach.
        System.out.println("Filter countries that have length 5 by using foreach.");
        for (String country : countries) {
            if (country.length() > 5) {
                System.out.println(country);
            }
        }

        // Task 2 using stream foreach-filter
        System.out.println("*********************************************************************");
        System.out.println("Filter countries that have length 5 by using stream foreach filter.");
        countries.stream()
                .filter(value -> value.length() > 5)
                .forEach(System.out::println);
    }

    /**
     * 3: Get countries that length is odd.
     * Filter should use foreach and stream foreach-filter.
     */
    public void filterCountriesLengthAreOdd() {
        List<String> countries = setCountries();

        // Task 3 using foreach.
        System.out.println("Filter countries that length is odd by using foreach.");
        for (String country : countries) {
            if (country.length() % 2 == 0) {
                System.out.println(country);
            }
        }

        // Task 3 using stream foreach-filter
        System.out.println("*********************************************************************");
        System.out.println("Filter countries that length is odd using stream foreach filter.");
        countries.stream()
                .filter(value -> value.length() % 2 == 0)
                .forEach(System.out::println);
    }
}
