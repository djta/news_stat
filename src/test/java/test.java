import org.apache.ibatis.io.Resources;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.ClassLoadHelper;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by hzyuyongmao on 2018/2/23.
 *
 * @Description
 */
public class test {
    public static void main(String args[]) throws InterruptedException, ExecutionException, TimeoutException {

//        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
//
//        scheduler.start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue(100));
//        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < 100; i++) {
            Future<String> future = threadPoolExecutor.submit(new TestTask());
//            futureList.add(future);
//            System.out.println(future.get(10, TimeUnit.SECONDS));
        }
        threadPoolExecutor.shutdown();
//        System.out.println("threadPool");
//        for (Future<String> future : futureList) {
//            try {
//                System.out.println(future.get(10, TimeUnit.SECONDS));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (TimeoutException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(future.get(10, TimeUnit.SECONDS));
    }


    static class TestTask implements Callable<String> {

        public String call() throws Exception {
            Thread.sleep(5000);
            System.out.println("thread OK");
            return "OK";
        }
    }
}
