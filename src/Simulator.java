import java.util.LinkedList;
import java.util.Random;

public class Simulator{

	private int n, k, d, v;
	
	private Random random = new Random();
	
	public Simulator(int numberOfProcess, int timeInterval, int avgCPUTime, int standardDeviation) {
		this.n = numberOfProcess;
		this.k = timeInterval;
		this.d = avgCPUTime;
		this.v = standardDeviation;
	}
	
	
	public void start() {
		 
		 int[] A = generateArrivalTimes();
		 
		 int[] T = generateCPUTimes();
		 
		 int[] R = T;
		 
		 Process[] processLists = generateProcesses(A, T, R);
		 
		 LinkedList<Process> activeProcesses = new LinkedList<>();
		
		 int t = 0;
		 while(!isRemainingTimeZero(processLists)) {
			 
			 checkProcessArrival(processLists, t);
			 
			 t++;
			 
			 if(isThereActiveProcess(processLists)) {
				 
				 activeProcesses = getActiveProcesses(processLists, activeProcesses);				 
				 Process nextExecutingProcess = activeProcesses.peek();
				 
				 nextExecutingProcess.setRemainingTime(nextExecutingProcess.getRemainingTime()-1); 
				 
				 if(nextExecutingProcess.getRemainingTime() == 0) {
					 activeProcesses.remove();
					 nextExecutingProcess.setActive(0);
					 nextExecutingProcess.setTurnaroundTime(t - nextExecutingProcess.getArrivalTime());
				 }
				 
			 }	
		 }
		 
		 System.out.println("\nProcess  \tArrival Time\tBurst Length\tTurn-around Time");
		 System.out.println("-----------------------------------------------------------------");
		 
		 double avgTurnaroundTime = 0;
		 int count = 1;
		 for(Process p: processLists){
			 avgTurnaroundTime += p.getTurnaroundTime();
			 System.out.println("P"+ count++ + "\t\t" + p.getArrivalTime() + "\t\t" + p.getCPUTime() + "\t\t" + p.getTurnaroundTime());
		 }
		 avgTurnaroundTime /= n;
		 
		 System.out.println("\nThe Average Turnaround Time using FCFS scheduling algorithm is " + avgTurnaroundTime);
		 
	 }
	 
	 
	 private int[] generateArrivalTimes() {
		 int[] arrivalTimes = new int[n];
		 
		 for(int i=0; i<n; i++) {
			 arrivalTimes[i] =  (int) Math.round(random.nextDouble()*k);
		 }
		 
		 return arrivalTimes;
	 }
	 
	 
	 private int[] generateCPUTimes() {
		 int[] cpuTimes = new int[n];
		 
		 for(int i=0; i<n; i++) {
			 cpuTimes[i] = (int) Math.round((random.nextGaussian()*v) + d);
		 }
		 
		 return cpuTimes;
	 }
	 
	 
	 private Process[] generateProcesses(int[] arrivalTimes, int[] cpuTimes, int[] remainingTimes) {
		 Process[] processLists = new Process[n];
		 for(int i=0; i<n; i++) {
			 Process process = new Process(0, arrivalTimes[i], cpuTimes[i], remainingTimes[i]);
			 if(arrivalTimes[i] == 0) process.setActive(1);
			 processLists[i] = process;
		 }
		 
		 return processLists;
	 }
	 
	 
	 private boolean isRemainingTimeZero(Process[] processLists) {
		 int totalSum = 0;
		 
		 for(Process p: processLists) {
			 totalSum += p.getRemainingTime();
			 if(totalSum > 0) {
				 return false;
			 }
		 }
		 return true;
	 }
	 
	 
	 private boolean isThereActiveProcess(Process[] processes) {
		 
		 for(Process p: processes) {
			 if(p.getActive() == 1) return true;
		 }
		 
		 return false;
	 }
	
	 
	 private LinkedList<Process> getActiveProcesses(Process[] processes, LinkedList<Process> activeP){
		 LinkedList<Process> activeProcesses = activeP;
		 for(Process p: processes) {
			 
			 if(p.getActive() == 1 && !activeProcesses.contains(p)) {
				 activeProcesses.add(p);
			 }
			 
		 }
		 
		 return activeProcesses;
	 }
	 
	 
	 private void checkProcessArrival(Process[] processes, int time) {
		 
		 for(Process p: processes) {
			 if(p.getArrivalTime() == time) {
				 p.setActive(1);
			 }
		 }
	 }
}
