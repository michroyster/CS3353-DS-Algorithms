public class Process {
    // parameters
    private String name;
    private int handleCount;
    private double otherOperationCount;
    private double otherTransferCount;
    private int pageFaults;
    private int pageFileUsage;
    private int parentProcessID;
    private int processID;
    private int priority;
    private int threadCount;

    public String getName() { return name; }
    public int getHandleCount() { return handleCount; }
    public double getOtherOperationCount() { return otherOperationCount; }
    public double getOtherTransferCount() { return otherTransferCount; }
    public int getPageFaults() { return pageFaults; }
    public int getPageFileUsage() { return pageFileUsage; }
    public int getProcessID() { return processID; }
    public int getParentProcessID() { return parentProcessID; }
    public int getPriority() { return priority; }
    public int getThreadCount() { return threadCount; }
    
    public void setName(String name) { this.name = name; }
    public void setHandleCount(int handleCount) { this.handleCount = handleCount; }
    public void setOtherOperationCount(double otherOperationCount) { this.otherOperationCount = otherOperationCount; }
    public void setOtherTransferCount(double otherTransferCount) { this.otherTransferCount = otherTransferCount; }
    public void setPageFaults(int pageFaults) { this.pageFaults = pageFaults; }
    public void setPageFileUsage(int pageFileUsage) { this.pageFileUsage = pageFileUsage; }
    public void setParentProcessID(int parentProcessID) { this.parentProcessID = parentProcessID; }
    public void setProcessID(int processID) { this.processID = processID; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setThreadCount(int threadCount) { this.threadCount = threadCount; }

    // Empty constructor
    public Process() { };

    // parameterized constructor
    public Process(String[] parameters) {
        this.setName(parameters[1]);
        this.setHandleCount(Integer.parseInt(parameters[0]));
        this.setOtherOperationCount(Double.parseDouble(parameters[2]));
        this.setOtherTransferCount(Double.parseDouble(parameters[3]));
        this.setPageFaults(Integer.parseInt(parameters[4]));
        this.setPageFileUsage(Integer.parseInt(parameters[5]));
        this.setParentProcessID(Integer.parseInt(parameters[6]));
        this.setProcessID(Integer.parseInt(parameters[8]));
        this.setPriority(Integer.parseInt(parameters[7]));
        this.setThreadCount(Integer.parseInt(parameters[9]));
    }

    // For Testing purposes
    public Process(int priority, String name)
    {
        setName(name);
        setPriority(priority);
    }

    @Override
    public String toString()
    {
        return this.getName() + this.getPriority();
    }

    public void printValue()
    {
        System.out.println("Name: \t\t\t" + this.getName() + "\n" +
        "Handle Count: \t\t" + this.getHandleCount() + "\n" +
        "Other Operation Count: \t" + this.getOtherOperationCount() + "\n" +
        "Other Transfer Count: \t" + this.getOtherTransferCount() + "\n" +
        "Page Faults: \t\t" + this.getPageFaults() + "\n" +
        "Page File Usage: \t" + this.getPageFileUsage() + "\n" +
        "Parent Process ID: \t" + this.getParentProcessID() + "\n" +
        "Priority: \t\t" + this.getPriority() + "\n" +
        "Thread Count: \t\t" + this.getThreadCount() + "\n"
        );
    }
}