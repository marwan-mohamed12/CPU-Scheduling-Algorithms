/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package srt;

import java.util.*;

/**
 *
 * @author Virus
 */


public class Srt {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();
         
        int[] arrivalTime = new int[n];
        int[] burstTime = new int[n];
        int[] completionTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int[] remainingTime = new int[n];
        int[] processNum = new int[n];
        int totalTime = 0;
        int completed = 0;
        float avgWaitingTime = 0, avgTurnaroundTime = 0;
        
        System.out.println("Enter the arrival time and burst time for each process: ");
        for (int i = 0; i < n; i++) {
            processNum[i] = i + 1;
            System.out.println("Process " + (i + 1) + ": ");
            arrivalTime[i] = sc.nextInt();
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
            totalTime += burstTime[i];
        }
        
        
        int currTime = 0;
        while (completed != n) {
            int shortestTime = Integer.MAX_VALUE;
            int shortestProcess = -1;
            for (int i = 0; i < n; i++) {
                if (arrivalTime[i] <= currTime && remainingTime[i] < shortestTime && remainingTime[i] > 0) {
                    shortestTime = remainingTime[i];
                    shortestProcess = i;
                }
            }
            if (shortestProcess == -1) {
                currTime++;
            } else {
                remainingTime[shortestProcess]--;
                currTime++;
                if (remainingTime[shortestProcess] == 0) {
                    completed++;
                    completionTime[shortestProcess] = currTime;
                    waitingTime[shortestProcess] = completionTime[shortestProcess] - arrivalTime[shortestProcess] - burstTime[shortestProcess];
                    turnaroundTime[shortestProcess] = completionTime[shortestProcess] - arrivalTime[shortestProcess];
                }
            }
        }
        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < n; i++) {
            System.out.println(processNum[i] + "\t\t" + arrivalTime[i] + "\t\t" + burstTime[i] + "\t\t" + completionTime[i] + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
            avgWaitingTime += waitingTime[i];
            avgTurnaroundTime += turnaroundTime[i];
        }
        avgWaitingTime /= n;
        avgTurnaroundTime /= n;
        System.out.println("Average Waiting Time: " + avgWaitingTime);
        System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
    }

}
