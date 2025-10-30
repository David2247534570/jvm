package com.ltx.jmm;

import org.openjdk.jcstress.Main;
import org.openjdk.jcstress.annotations.*;
import org.openjdk.jcstress.infra.results.I_Result;

/**
 * 有序性(Ordering)
 *
 * @author tianxing
 */
@JCStressTest
@Outcome(id = {"1", "4"}, expect = Expect.ACCEPTABLE, desc = "accept")
@Outcome(id = "0", expect = Expect.ACCEPTABLE_INTERESTING, desc = "interesting")
@State
public class Ordering {

    private int num = 0;
    private volatile boolean flag = false;

    public static void main(String[] args) throws Exception {
        Main.main(args);
    }

    @Actor
    public void actor1(I_Result result) {
        if (flag) {
            result.r1 = num + num;
        } else {
            result.r1 = 1;
        }
    }

    @Actor
    public void actor2(I_Result result) {
        num = 2;
        // 写写屏障
        flag = true;
        // 写读屏障
    }
}
