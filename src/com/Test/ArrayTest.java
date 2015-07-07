package com.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ArrayTest {
	private static final int[][] cellNum = { { 0, 0, 0, 0, 4, 0, 5, 0, 6, 7, 0, 8, 0, 0, 0, 0, 0, 9, 10, 11, 12 }, { 0, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 7,0 ,0, 0, 0, 0, 0, 8, 9, 10 },
		{ 0, 0, 0, 0, 4, 0, 5, 0, 6, 7, 0, 8, 0, 0, 0, 0, 0, 9, 0, 10, 11 }, { 0, 0, 0, 0, 4, 0, 5, 0, 6, 7, 0, 8, 0, 9, 0, 10, 0, 11, 12, 13, 14 },
		{ 0, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 7, 0, 8, 0, 9, 0, 0, 10, 11 }, { 0, 0, 0, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 10, 0, 11, 0, 12},
		{ 0, 0, 0, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 0, 13, 14, 15, 16, 17,18,19 }, { 0, 0, 0, 0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
    
	private static final int[][] arr = { { 0, 0, 0, 0, 4, 0, 5, 0, 6, 7, 0, 8, 0, 0, 0, 0, 0, 9, 10, 11, 12 }, { 0, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 7,0 ,0, 0, 0, 0, 0, 8, 9, 10 },
		{ 0, 0, 0, 0, 4, 0, 5, 0, 6, 7, 0, 8, 0, 0, 0, 0, 0, 9, 0, 10, 11 }, { 0, 0, 0, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 10, 0, 11, 12, 13 },
		{ 0, 0, 0, 0, 4, 0, 5, 0, 0, 0, 6, 0, 7, 0, 8, 0, 9, 0, 0, 10, 11,12}, { 0, 0, 0, 0, 4, 0, 5, 0, 6, 0, 7, 0, 8, 0, 9, 0, 10, 0, 11, 0, 12 ,13},
		{ 0, 0, 0, 0, 4, 5, 6, 0, 7, 8, 9, 10, 11, 12, 0, 13, 0, 14, 15, 16, 17 }, { 0, 0, 0, 0, 4, 5, 6, 0, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 } };
	static void arrang(int i ,String[] string){
		String str="";
		switch(i){
		case 4:
			string[i]="Fixed Billings";
			break;
		case 5:
			string[i]="Fixed Escalation Rate";
			break;
		
		case 6:
			string[i]="Variable Billings"; 
			break;
		case 7:
			string[i]="Variable True up";
			break;
		case 8:
			string[i]="Variable Escalation";
			break;
		
		case 9:
			string[i]="Adjustments";
			break;
		
		case 10:
		
			string[i]="Adjustments Escalation";
			break;
		
		case 11:
			string[i]="Milestone Billings";
			break;
		
		
		case 12:
		
			string[i]="Milestone Escalation";
			break;
		
		case 13:
			string[i]="Bonus Billings";
			break;
		case 14:
		
			string[i]="Bonus Escalation";
			break;
		
		case 15:
			string[i]="LD Billings";
			
			break;
		case 16:
		
			string[i]="LD Escalation";
			break;
			
		case 17:
			string[i]="Weight Escalation";
			break;
		case 18:
			string[i]=" Escalation Rate";
			break;
		case 19:
			string[i]=" Extra work";
			break;
		
		case 20:
			string[i]="Comments";
			break;
		default:break;
		}
	}
	public static void main(String[] args) throws Exception {
		//outPrintArray();
		/*int len=21;
		PrintWriter pw=new PrintWriter(new FileOutputStream("C:\\Users\\920643.TCSGEGDC\\Documents\\Billing stream\\excel item.txt"));
		String[] s=new String[len];
		for(int i=0;i<len;i++){
			arrang(i,s);
		}
		for(int i=0;i<len;i++){
			pw.print(i+"  ");
			pw.print(s[i]==null?0:s[i]);
			pw.println();
		}
		pw.flush();
		pw.close();*/
		
		int arr[]={0, 0, 0, 0, 4, 5, 6, 0, 8, 9, 10, 11, 12, 0, 14, 0, 16, 17, 18, 19, 20};
//		int arr[]={0, 0, 0, 0, 4, 5, 6, 0, 7, 8, 9, 10, 11, 0, 12, 0, 13, 14, 15, 16, 17};
		//changeArr(arr);
		String s="adjustments is equal to 46164.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000036+ outstanding billing of 211.442,24ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃ";
		System.out.print(s.length());
	}
	
	
	public static void changeArr(int[] in){
		int len=in.length;
		int j=0;
		for(int i=0;i<len;i++){
			if(in[i]!=0){
				j=in[i];
				break;
			}
		}
		for(int i=0;i<len;i++){
			if(in[i]!=0){
				in[i]=j++;
				
			}
		}
		for(int i=0;i<len;i++){
			System.out.print(in[i]+ " , ");
		}
	}
	public static void outPrintArray() {
		for(int ii=0;ii<arr.length;ii++){
			System.out.print(arr[ii]+" ");
		}
		String s="adjustments is equal to 46164.00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000036+ outstanding billing of 211.442,24ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ¢ÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂ000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000ÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃÂÃ";
		System.out.print(s.length());
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for(int i=0;i<cellNum.length;i++){
			if(i!=0){
				sb.append(", ");
			}
			sb.append("{");
			
			for(int j=0;j<cellNum[i].length;j++){
				if(j!=0){
					sb.append(", ");
				}
				if(cellNum[i][j]!=0){
					cellNum[i][j]=j;
				}
				
				sb.append(cellNum[i][j]);
			}
			sb.append("}");
		}
		sb.append("}");
		System.out.print(sb.toString());
	}

}
