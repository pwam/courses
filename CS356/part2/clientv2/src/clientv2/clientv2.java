/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package clientv2;

import java.io.*;
 import java.net.*;
 
 public class clientv2 {
     public static void main(String[] args) throws IOException {
       
          String host = "Router 1";
          int[][] rt = new int[2][4];
          int n  = 4;
          int i, j, k;
          
          rt[0][0] = 99;
          rt[0][1] = 99;
          rt[0][2] = 99;
          rt[0][3] = 99;
          
          rt[1][0] = 1;
          rt[1][1] = 0;
          rt[1][2] = 1;
          rt[1][3] = 99;
          
          System.out.println(host);
          System.out.println("Inital Routing Table: ");
          System.out.println("--------");
          
          for(i=0;i<n-2;i++) {
             for(j=0;j<n;j++) 
                System.out.print(rt[i][j]+ " ");
             System.out.println();
          }
 
          Socket sock = new Socket("192.168.56.1", 7777);
          InputStreamReader ir = new InputStreamReader(sock.getInputStream());
          BufferedReader br = new BufferedReader(ir);
          
          for(k=1; k<n-2; k++) {
             for(i=0; i<n; i++) {
                PrintStream ps = new PrintStream(sock.getOutputStream());
                int num = rt[k][i];
                String str = Integer.toString(num);
                ps.print(str);
                ps.println();
             }
             
          }
 
          String str = "99";
          while(str != null) {
             for(k=0; k<n-3; k++) {
                for(i=0; i<n; i++) {
                   str = br.readLine();
                   if(str != null) {
                      int num = Integer.parseInt(str);
                      rt[k][i] = num;
                   }
                }
             }
          }
          
          for(k=0; k<n-2; k++) {
             for(i=0; i<n-2; i++) {
                for(j=0; j<n; j++) {
                   if(rt[i][j]>rt[i][k]+rt[k][j])
                      rt[i][j]=rt[i][k]+rt[k][j];
                }
             }
          }
     }
 }

