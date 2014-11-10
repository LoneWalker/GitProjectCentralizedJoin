package com.company;

import centjoin.CJoiner;

public class Main {

    private String jmp;
    public static void main(String[] args) {
	// write your code here



        StringBuilder sb = new StringBuilder();
        //sb.append("Hello").append("\n").append("are").append("\n").append("you");
        //System.out.println(sb.toString());
        CJoiner obj = new CJoiner("A.txt","B.txt","Out.txt");
        Long start= System.currentTimeMillis();
        obj.CJoin();
        System.out.println("Total execution time in milli second is:"+(System.currentTimeMillis()-start));
    }
}