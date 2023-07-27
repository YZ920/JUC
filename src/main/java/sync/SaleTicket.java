package sync;

////第一步 创建一个资源类，定义属性和方法
//class Ticket {
//    //票数
//    private int num = 30;
//
//    //买票方法
//    public synchronized void sale() {
//        //判断是否有票
//        if (num > 0) {
//            System.out.println(Thread.currentThread().getName() + "卖出：" + (num--) + "剩下：" + num);
//        }
//    }
//}
//
//public class SaleTicket {
//    //创建多个线程
//    public static void main(String[] args) {
//        Ticket ticket = new Ticket();
//        //创建三个线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++){
//                    ticket.sale();
//                }
//            }
//        }, "aa").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 20; i++){
//                    ticket.sale();
//                }
//            }
//        }, "bb").start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++){
//                    ticket.sale();
//                }
//            }
//        }, "cc").start();
//    }
//}

import java.util.concurrent.locks.ReentrantLock;

/**
 * 方法2：使用lock
 */
//第一步 创建一个资源类，定义属性和方法
class Ticket {
    //票数
    private int num = 30;

    //创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);

    //买票方法
    public void sale() {
        //上锁
        lock.lock();
        try {
            //判断是否有票
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出：" + (num--) + "剩下：" + num);
            }
        }finally {
            //解锁
            lock.unlock();
        }
    }
}

public class SaleTicket {
    //创建多个线程
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //创建三个线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++){
                    ticket.sale();
                }
            }
        }, "aa").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++){
                    ticket.sale();
                }
            }
        }, "bb").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 30; i++){
                    ticket.sale();
                }
            }
        }, "cc").start();
    }
}