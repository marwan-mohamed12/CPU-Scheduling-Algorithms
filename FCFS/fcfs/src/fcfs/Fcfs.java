/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fcfs;

/**
 *
 * @author Virus
 */
import java.util.*;


/*
    We define a Process class to represent a single process with its PID, arrival time, and burst time. 
    We also provide a constructor to create a new process object with the given values.
 */
class Process {

    int pid;
    int arrivalTime;
    int burstTime;

    public Process(int pid, int arrivalTime, int burstTime) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }
}

public class Fcfs {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /*
            We define a FCFS class with a main method to execute the FCFS algorithm. 
            We first create a new Scanner object to read input from the user. 
            We prompt the user to enter the number of processes, and store it in the variable n. 
            We also create an empty List of Process objects to store the processes.

            We then prompt the user to enter the arrival time and burst time for each process, 
            and create a new Process object with the given values. We add each new process object to the processes list.
         */
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter the number of processes: ");
//        int n = sc.nextInt();
//
//        List<Process> processes = new ArrayList<>();
//
//        System.out.println("Enter the arrival time and burst time of each process:");
//        for (int i = 1; i <= n; i++) {
//            int arrivalTime = sc.nextInt();
//            int burstTime = sc.nextInt();
//            processes.add(new Process(i, arrivalTime, burstTime));
//        }
//
//        /*
//            We sort the processes list by their arrival time using the Collections.sort() method and a lambda expression to compare the arrival times of two processes.
//
//            We then initialize three variables to keep track of the current time, total waiting time, and total turnaround time. We also print a header for the output table.
//
//            We iterate over the processes list using a for-each loop, and calculate the waiting time and turnaround time for each process using the FCFS algorithm. We add the waiting time and turnaround time of each process to the corresponding variables. We update the current time to be the sum of the current time and the burst time of the current process.
//
//            Finally, we print the PID, arrival time, burst time, waiting time, and turnaround time of each process in a formatted output table using `System.out.printf()
//         */
//        // Sort the processes by their arrival time
//        Collections.sort(processes, (p1, p2) -> p1.arrivalTime - p2.arrivalTime);
//
//        // Calculate waiting time and turnaround time for each process
//        int currentTime = 0;
//        int totalWaitingTime = 0;
//        int totalTurnaroundTime = 0;
//        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
//        for (Process p : processes) {
//            int waitingTime = currentTime - p.arrivalTime;
//            int turnaroundTime = waitingTime + p.burstTime;
//            totalWaitingTime += waitingTime;
//            totalTurnaroundTime += turnaroundTime;
//            currentTime += p.burstTime;
//
//            System.out.printf("%d\t\t%d\t\t%d\t\t%d\t\t%d\n", p.pid, p.arrivalTime, p.burstTime, waitingTime, turnaroundTime);
//        }
//
//        // Calculate average waiting time and turnaround time
//        double avgWaitingTime = (double) totalWaitingTime / n;
//        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
//        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWaitingTime);
//        System.out.printf("Average Turnaround Time: %.2f\n", avgTurnaroundTime);
        
        /*
            Here's how the program works:

            The user is prompted to enter the number of processes and the arrival time and burst time for each process.
            The program calculates the completion time, waiting time, and turnaround time for each process using the FCFS algorithm.
            The program then calculates the average waiting time and average turnaround time for all processes.
            Finally, the program outputs the results to the console.
            Note that this implementation assumes that the processes are entered in arrival order (i.e., the first process entered has the earliest arrival time). If the processes are not entered in arrival order, you would need to sort the processes by arrival time before applying the FCFS algorithm.
        */
        // Trivial One 
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int totalWaitingTime = 0, totalTurnaroundTime = 0;

        // Input arrival time and burst time for each process
        System.out.println("Enter arrival time and burst time for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Arrival time of process " + (i+1) + ": ");
            arrivalTime[i] = sc.nextInt();
            System.out.print("Burst time of process " + (i+1) + ": ");
            burstTime[i] = sc.nextInt();
        }

        // Calculate completion time, waiting time, and turnaround time for each process
        completionTime[0] = arrivalTime[0] + burstTime[0];
        turnaroundTime[0] = completionTime[0] - arrivalTime[0];
        waitingTime[0] = 0;
        totalWaitingTime = waitingTime[0];
        totalTurnaroundTime = turnaroundTime[0];
        for (int i = 1; i < n; i++) {
            completionTime[i] = completionTime[i-1] + burstTime[i];
            turnaroundTime[i] = completionTime[i] - arrivalTime[i];
            waitingTime[i] = turnaroundTime[i] - burstTime[i];
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        // Calculate average waiting time and average turnaround time
        double avgWaitingTime = (double) totalWaitingTime / n;
        double avgTurnaroundTime = (double) totalTurnaroundTime / n;

        // Output results
        System.out.println("\nProcess\t Arrival Time\t Burst Time\t Completion Time\t Waiting Time\t Turnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
        }
        System.out.println("\nAverage waiting time: " + avgWaitingTime);
        System.out.println("Average turnaround time: " + avgTurnaroundTime);
    
    }

}
