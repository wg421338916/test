import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock(true);//公平锁
        Condition condition = lock.newCondition();

        condition.await();
        condition.signal();;

//        lock.tryLock()
//        lock.lock();
//        lock.unlock();

//
//        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
//        reentrantReadWriteLock.readLock().lock();


//        getHoldCount() 查询当前线程保持此锁的次数，也就是执行此线程执行lock方法的次数
//        getQueueLength（）返回正等待获取此锁的线程估计数，比如启动10个线程，1个线程获得锁，此时返回的是9
//        getWaitQueueLength（Condition condition）返回等待与此锁相关的给定条件的线程估计数。比如10个线程，用同一个condition对象，并且此时这10个线程都执行了condition对象的await方法，那么此时执行此方法返回10
//        hasWaiters(Condition condition)查询是否有线程等待与此锁有关的给定条件(condition)，对于指定contidion对象，有多少线程执行了condition.await方法
//        hasQueuedThread(Thread thread)查询给定线程是否等待获取此锁
//        hasQueuedThreads()是否有线程等待此锁
//        isFair()该锁是否公平锁
//        isHeldByCurrentThread() 当前线程是否保持锁锁定，线程的执行lock方法的前后分别是false和true
//        isLock()此锁是否有任意线程占用
//        lockInterruptibly（）如果当前线程未被中断，获取锁
//        tryLock（）尝试获得锁，仅在调用时锁未被线程占用，获得锁
//        tryLock(long timeout TimeUnit unit)如果锁在给定等待时间内没有被另一个线程保持，则获取该锁
    }
}
