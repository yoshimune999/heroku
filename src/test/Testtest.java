package test;

import java.util.Scanner;


public class Testtest {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        // 標準入力から値を取得してinput_lineに入れる
        int type = sc.nextInt();
        int query = sc.nextInt();
        int [][] item = new int[type][2] ;
        int amount = 0;
        int i = 0;
        for( i=0;i<type;i++) {
        	 item[i] = methodA(sc.nextInt(),sc.nextInt());
        }

        for( i=0;i<query;i++) {
        	String order = sc.next();
        	if(order.equals("buy")) {
        		for(int cou=0;cou<type;cou++) {
        			int b = sc.nextInt();
        			if(item[cou][1] < b) {
        				amount = -1;
        				break;
        			} else if(item[cou][1] >= b) {
        				item[cou][1] -= b;
        				for(int n=0;n<b;n++) {
        					amount += item[cou][0];
        				}
        			}
        		}
        		System.out.println(amount);
        		amount =0;
        	}else if(order.equals("bake")) {
        		for(int cou=0;cou<type;cou++) {
        			item[cou][1] += sc.nextInt();
        		}
        	}else {
        		i--;
        	}
        }


    }
    public static int[] methodA(int a, int b) {
    	int [] item = {a, b} ;
    	return item;
    }
}