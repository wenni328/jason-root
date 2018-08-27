package org;

import lombok.extern.slf4j.Slf4j;

/**
 * @auther: xieyong
 * @date: 2018/8/22 15:50
 * @Description:
 */
@Slf4j
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        log.debug("我要一个教练");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       //while (true){
            log.info("教练来了： " + Thread.currentThread().getName());
            log.error("教我游泳,交完后，教练回到了游泳池");
        //}

    }
}
