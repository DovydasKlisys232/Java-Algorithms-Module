// simple java program that prints out different N values

public class EX14{
	static void addition(int n) {
		System.out.print("N = " + n + ", ");
	}

	static void square(int n) {
		System.out.print(n*n + ", ");
	}

	static void LogTwo(int n) {
		System.out.print(Math.log(n) + ", ");
	}

	static void NLogTwo(int n) {
		System.out.println(n * Math.log(n));
	}

    public static void main(String[] args){
    	for(int i = 0; i <= 10; i++) {
    		int N = Math.pow(2, i);
		    addition(N);
		   	square(N);
		   	LogTwo(N);
		   	NLogTwo(N);	
    	}
    }
}
