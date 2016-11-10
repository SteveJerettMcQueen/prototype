/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sjm.financialapplication.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.stereotype.Component;

/**
 *
 * @author sm6668
 */
@Component
public class AppExecutorService {

    private final ExecutorService pool
            = Executors.newSingleThreadExecutor();

    public final ExecutorService getExecutorServicePool() {
        return pool;
    }

}
