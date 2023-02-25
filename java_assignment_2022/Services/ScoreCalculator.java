package Services;

import java.util.HashMap;

public class ScoreCalculator {
	public int caculateScore(HashMap<String, String> data) {
		
		//get first key
		String firstKey = data.keySet().iterator().next();
		
		//get first value, store in the variable called "firstValue"
		String firstValue = data.get(firstKey);
		
		//first sequence length
		int m = firstValue.length();
		
		int alignmentScore = 0;
		
		//iterate all the sequence
		for (String key : data.keySet()) {
			String value = data.get(key);
			
			//to check if genome has the same length with reference sequence
			//if not, the genome will not be taken account to calculate the score
			if (value.length()==m) {
				for (int i = 0; i < m; i++) {
					
					//if the character is not the same, add2
					if (value.charAt(i) != firstValue.charAt(i)) {
						alignmentScore += 2;		
					}
				}
			}
		}
		System.out.println("alignment score = " + alignmentScore);
		return alignmentScore;
	}
}