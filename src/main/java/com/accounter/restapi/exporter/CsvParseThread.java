package com.accounter.restapi.exporter;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CsvParseThread implements Runnable {



    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e);
        }

    }
}
