package org.example.problems.javastream;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CompleteablFutureTest {
    public static void main(String[] args) {
        CompleteablFutureTest completeablFutureTest = new CompleteablFutureTest();
        completeablFutureTest.testComplete();
    }


    private void callCompleSolution() throws InterruptedException, ExecutionException, TimeoutException {
        Set<Integer> prices = Collections.synchronizedSet(new HashSet<Integer>());
        CompletableFuture<Void> return1 = CompletableFuture.runAsync(callMethod());
        CompletableFuture<Void> return2 = CompletableFuture.runAsync(callMethod());
        CompletableFuture<Void> return3 = CompletableFuture.runAsync(callMethod());
        CompletableFuture<Void> combineTask = CompletableFuture.allOf(return1, return2, return1);
        combineTask.get(3, TimeUnit.DAYS);
    }

    private Runnable callMethod() {
        return null;
    }

    private void testComplete() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture.supplyAsync(() -> getOrder(finalI), Executors.newCachedThreadPool())
                    .thenApplyAsync((order) -> getOrderDetail(order), Executors.newFixedThreadPool(4))
//                    .exceptionally(t->new Exception("failed to ex"))
                    .thenAccept((detail) -> sendMail(detail));
        }
    }

    private void sendMail(String detail) {
        System.out.println("[mail sending]=" + detail);
    }

    private String getOrderDetail(Map<String, String> order) {
        try {
            System.out.println("inside order details =" + order);
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
        }
        return order.getOrDefault("rest", "detault");
    }

    private Map<String, String> getOrder(int index) {
        System.out.println("inside order =" + index);
        return Map.of("rest", index + "value");
    }

    interface SemaphoreBeh {
        void acquire() throws InterruptedException;

        void release();
    }

    class SemaphoreCustom implements SemaphoreBeh {
        AtomicInteger permit;
        int maxSize;
        ReentrantLock lock = new ReentrantLock();
        Condition isLocked = lock.newCondition();

        public SemaphoreCustom(int permit) {
            this.maxSize = permit;
            this.permit = new AtomicInteger(0);
        }

        @Override
        public void acquire() throws InterruptedException {
            if (permit.get() == maxSize) {
                isLocked.await();
            }
            permit.incrementAndGet();
        }

        @Override
        public void release() {
            permit.decrementAndGet();
            isLocked.signal();
        }
    }
}
