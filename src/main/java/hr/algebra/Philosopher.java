package hr.algebra;

public class Philosopher extends Thread{
    private final Object right;
    private final Object left;

    Philosopher(Object right, Object left, int number){
        this.right = right;
        this.left = left;
        this.setName("Philosopher #" + number);
    }

    @Override
    public void run() {
        try{
            while(true){
                act(" thinking");
                synchronized (left){
                    act(" picking up the left fork");
                    synchronized (right){
                        act(" picking up the right fork");
                    }
                    act(" putting both forks down, stopped eating and is thinking");
                }

            }
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }


    private void act(String message) throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + message);
        Thread.sleep(((int)Math.random()*1000));
    }
}
