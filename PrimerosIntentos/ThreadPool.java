public class ThreadPool {
    /**
     * Set of threads in the threadpool
     */
    protected Thread threads[] = null;

    
    public ThreadPool(int size)
    {
        // 
    }

   
    public void addToQueue(Runnable r) throws InterruptedException
    {
        // 
    }
    
   
    public synchronized Runnable getJob() throws InterruptedException {
        // implementar
        return null;
    }
}

/**
 * The worker threads that make up the thread pool.
 */
class WorkerThread extends Thread {
    /**
     * The constructor.
     *
     * 
     */
    WorkerThread(ThreadPool o)
    {
        // 
    }

 
    public void run()
    {
        // implementar
    }
}
