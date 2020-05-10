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

package com.jalasoft.practice;

import com.jalasoft.practice.loop.Task;

/**
 * Main class for practice 2.
 */
public class LoopMain {
    public static void main(String[] args) {
        Task loop = new Task();

        // Task 1.
        loop.filterStartWithA();

        // Task 2.
        loop.filterCountriesLengthMajorThanFive();

        // Task 3.
        loop.filterCountriesLengthAreOdd();
    }
}
