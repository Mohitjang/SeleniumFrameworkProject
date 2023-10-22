package cisco.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	
//	After failing test Listener failed test will to this code and asks do i need to run again
//	maybe i'm a flaky test:- 
	
	int count = 0;
	int maxTry = 1;
	
	
	@Override
	public boolean retry(ITestResult result) {
		
		if (count<maxTry) {
//			to tell the rerun the code:-
			count++;
			return true;
			
			
		}
//		if condition is not true then it will nnot rerun the code:-
		return false;
	}

//	To activate rerun test we have to give attributr to the testmethod:-
	
}
