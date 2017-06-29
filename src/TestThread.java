import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Waraporn on 8/18/2016.
 */
public class TestThread {

    interface TestCallback<CCCC> {
        void callDone(CCCC cccc);
    }

    static class MyCallDone implements TestThread.TestCallback<Integer> {

        final List<Integer> completeList = new ArrayList<>();
        @Override
        public void callDone(Integer integer) {
            String threadName = Thread.currentThread().getName();
            completeList.add(integer);
            System.out.println("| " + threadName + " | I am done. " + integer + " rounds. | done -- " + completeList.size());
        }
    }

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName() + " Hello");

        HelloThread bleRun = new HelloThread("Ble", 18);
        HelloThread offRun = new HelloThread("Off", 19);

        MyCallDone cd = new MyCallDone();

        bleRun.setCallBack(cd);
        offRun.setCallBack(cd);

        Thread t2 = new Thread(bleRun, "T1");
        Thread t3 = new Thread(offRun, "T3");
        t2.start();
        t3.start();

        System.out.println("Good bye");
    }

    static class HelloThread implements Runnable {

        private final int mRound;
        private final String mName;
        private TestCallback<Integer> mCallback;

        void setCallBack(TestCallback<Integer> a){
            mCallback = a;
        }

        HelloThread(String name, int round) {
            mName = name;
            mRound = round;
        }
        int i;
        static Random mRandom = new Random(220);

        @Override
        public void run() {

            for (int i = 0 ; i <= mRound ; i++) {
                System.out.println("Run " + mName + "Run i = " + (i+1));

                try {
                    int randomSec = mRandom.nextInt() % 9;
                    System.out.println(randomSec);
                    Thread.sleep(Math.abs(randomSec) * 100);
                } catch (InterruptedException e) {

                }
            }

        }
    }
}
