/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package serverv2;

import java.io.*;
 import java.net.*;
 
 public class serverv2 {
     public static void main(String[] args) throws IOException {
       
          String host = "Router 0";
          
          int[][] rt = new int[2][4];
          int n  = 4;
          int i, j, k;
          
          rt[0][0] = 0;
          rt[0][1] = 1;
          rt[0][2] = 3;
          rt[0][3] = 7;
          
          rt[1][0] = 99;
          rt[1][1] = 99;
          rt[1][2] = 99;
          rt[1][3] = 99;
          
          System.out.println(host);
          System.out.println("Inital Routing Table: ");
          System.out.println("--------");
          
          for(i=0;i<n-2;i++) {
             for(j=0;j<n;j++) 
                System.out.print(rt[i][j]+ " ");
             System.out.println();
          }
                        
          ServerSocket listen = new ServerSocket(7777);
          Socket sock = listen.accept();
          InputStreamReader ir = new InputStreamReader(sock.getInputStream());
          BufferedReader br = new BufferedReader(ir);
          
          
          
          String str = "99";
          while(str != null) {
             for(k=1; k<n-2; k++) {
                for(i=0; i<n; i++) {
                   str = br.readLine();
                   if(str != null) {
                      int num = Integer.parseInt(str);
                      rt[k][i] = num;
                   }
                }
             }
          }
          
          str = "99";
          for(k=0; k<n-3; k++) {
             for(i=0; i<n; i++) {
                PrintStream ps = new PrintStream(sock.getOutputStream());
                int num = rt[k][i];
                str = Integer.toString(num);
                ps.print(str);
                ps.println();
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
          
          System.out.println();
          System.out.println("Updated Routing Table: ");
          System.out.println("--------");
          
          for(i=0;i<n-2;i++) {
             for(j=0;j<n;j++) 
                System.out.print(rt[i][j]+ " ");
             System.out.println();
          }   
     }
 }

