
public class Process{

	private int active = 0;
	private int arrivalTime, cpuTime, remainingTime, turnaroundTime;
	
	
	public Process(int active, int arrivalTime, int cpuTime, int remainingTime) {
		this.active = active;
		this.arrivalTime = arrivalTime;
		this.cpuTime= cpuTime;
		this.remainingTime = remainingTime;
	}
	
	
	public Process() {}
	
	
	public void setActive(int active) {
		this.active = active;
	}
	
	
	public int getActive() {
		return this.active;
	}
	
	
	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}
	
	
	public int getRemainingTime() {
		return this.remainingTime;
	}
	
	
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
	public int getArrivalTime() {
		return this.arrivalTime;
	}
	
	
	public void setCPUTime(int cpuTime) {
		this.cpuTime = cpuTime;
	}
	
	
	public int getCPUTime() {
		return this.cpuTime;
	}
	
	
	public void setTurnaroundTime(int turnaroundTime) {
		this.turnaroundTime = turnaroundTime;
	}
	
	
	public int getTurnaroundTime() {
		return this.turnaroundTime;
	}
	
}
