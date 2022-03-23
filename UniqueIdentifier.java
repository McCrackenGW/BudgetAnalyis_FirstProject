package budgetaryinfoXXYN;


public class UniqueIdentifier {
	
	
	public static String createUniqueKey() {
		
     	double num = Math.random();
        String numOne = Double.toString(num);
        String uniqueNum = numOne.substring(2) + "4" + numOne.substring(2) + "2" + numOne.substring(2) + "394930";
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        char[] characters = "BmXtYzYYMDKSMVEO5382MSZlloilxSv82NMV".toCharArray();      

        
         String[] uniqueID = new String[10];
                
         for (int x = 0; x < 10; x++) {
            int i = (int)(Math.random() * 18 + 1);
            uniqueID[x] = uniqueNum.substring(i,i+4) + alphabet[i] + uniqueNum.substring(i, i+2) + characters[i] + uniqueNum.substring(i, i+3) + characters[i+3];
        }
       String totallyUnique = uniqueID[4];
       return totallyUnique;
    }

}
