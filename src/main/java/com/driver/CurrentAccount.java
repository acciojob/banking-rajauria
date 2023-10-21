package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {

        super (name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        if (balance < 5000){
            throw new Exception("Insufficient Balance");
        }
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if (!isValid(tradeLicenseId)){
            String rearranged = rearrange (tradeLicenseId);
            if (rearranged.isEmpty()) throw new Exception("Valid License can not be generated");
            else this.tradeLicenseId = rearranged;
        }



    }

    public boolean isValid (String licenseId){
        for(int i=0; i<licenseId.length()-1; i++){
            if(licenseId.charAt(i) == licenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }

    public String rearrange (String id){
        int len = id.length();
        int[] hash = new int [26];
        for (int i = 0 ; i < len;i++){
            hash[id.charAt(len) - 'a']++;
        }

        int max = 0;
        int letter = 0;
        for (int i = 0 ; i < 26 ; i++){
            if (hash[i] > max){
                max = hash[i];
                letter = i;
            }
        }

        if (max > ((len + 1) / 2)) return "";

        int idx  = 0;
        char[] arr = new char [len];


        while (hash [letter] -- > 0){
            arr[idx] = (char)(letter + 'a');
            idx += 2;
        }

        for (int i = 0 ; i < 26; i++){
            while (hash[i]-- > 0) {
                if (idx > arr.length) idx = 1;
                arr[idx] = (char) (i + 'a');
                idx += 2;
            }
        }

        return String.valueOf(arr);
    }

}
