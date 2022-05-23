public class Process {
    String name;
    int burstTime;
    int virtualRunTime;

    // Getters and setters
    public String getName() { return name; }
    public int getBurstTime() { return burstTime; }
    public int getVirtualRunTime() { return virtualRunTime; }
    public void setName(String name) { this.name = name; }
    public void setBurstTime(int burstTime) { this.burstTime = burstTime; }
    public void setVirtualRunTime(int virtualRunTime) { this.virtualRunTime = virtualRunTime; }

    // Methods for adjusting burst time and run time
    public void incrementRuntime() { virtualRunTime++; }
    public void decrementBursttime() { burstTime--; }

    // Empty constructor
    public Process() { }

    // Parameterized Constructor
    public Process(String[] parameters)
    {
        setName(parameters[0]);
        setBurstTime(Integer.parseInt(parameters[1]));
        setVirtualRunTime(Integer.parseInt(parameters[3]));
    }

    // For printing the information about the process
    public void printInfo()
    {
        System.out.println(name + "\tBurst Time: " + burstTime + "\tVirtual Runtime: " + virtualRunTime);
    }
}
