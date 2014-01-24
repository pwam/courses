/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package router0;

import java.io.*;
import java.net.*;

public class router0 {
    public static void main(String[] args) throws IOException {
      
         String host = "Router 0";
         
         int[][] rt = new int[4][4];
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
         
         rt[2][0] = 99;
         rt[2][1] = 99;
         rt[2][2] = 99;
         rt[2][3] = 99;
         
         rt[3][0] = 99;
         rt[3][1] = 99;
         rt[3][2] = 99;
         rt[3][3] = 99;
         
         System.out.println(host);
         System.out.println("Inital Routing Table: ");
         System.out.println("--------");
         
         for(i=0;i<n;i++) {
            for(j=0;j<n;j++) 
               System.out.print(rt[i][j]+ " ");
            System.out.println();
         }
                       
         ServerSocket listen = new ServerSocket(7777);
         ServerSocket listen2 = new ServerSocket(7778);
         ServerSocket listen3 = new ServerSocket(7779);
         Socket sock = listen.accept();
         Socket sock2 = listen2.accept();
         Socket sock3 = listen3.accept();
         InputStreamReader ir = new InputStreamReader(sock.getInputStream());
         InputStreamReader ir2 = new InputStreamReader(sock2.getInputStream());
         InputStreamReader ir3 = new InputStreamReader(sock3.getInputStream());
         BufferedReader br = new BufferedReader(ir);
         BufferedReader br2 = new BufferedReader(ir2);
         BufferedReader br3 = new BufferedReader(ir3);
         
         String str = "99";
         while(str != null) {
            for(k=0; k<n; k++) {
               for(i=0; i<n; i++) {
                  str = br.readLine();
                  if(str != null) {
                     int num = Integer.parseInt(str);
                     if (num < rt[k][i])
                        rt[k][i] = num;
                  }
               }
            }
         }
         
         
                  
         str = "99";
         while(str != null) {
            for(k=0; k<n; k++) {
               for(i=0; i<n; i++) {
                  str = br2.readLine();
                  if(str != null) {
                     int num = Integer.parseInt(str);
                     if (num < rt[k][i])
                        rt[k][i] = num;
                  }
               }
            }
         }
         
         str = "99";
         while(str != null) {
            for(k=0; k<n; k++) {
               for(i=0; i<n; i++) {
                  str = br3.readLine();
                  if(str != null) {
                     int num = Integer.parseInt(str);
                     if (num < rt[k][i])
                        rt[k][i] = num;
                  }
               }
            }
         }
         
         str = "99";
         for(k=0; k<n; k++) {
            for(i=0; i<n; i++) {
               PrintStream ps = new PrintStream(sock.getOutputStream());
               int num = rt[k][i];
               str = Integer.toString(num);
               ps.print(str);
               ps.println();
            }
         }
         
         for(int a=0; a<10; a++) {
         for(k=0; k<n; k++) {
            for(i=0; i<n; i++) {
               for(j=0; j<n; j++) {
                  if(rt[i][j]>rt[i][k]+rt[k][j])
                     rt[i][j]=rt[i][k]+rt[k][j];
               }
            }
         }
         }
         
         System.out.println();
         System.out.println("Updated Routing Table: ");
         System.out.println("--------");
         
         for(i=0;i<n;i++) {
            for(j=0;j<n;j++) 
               System.out.print(rt[i][j]+ " ");
            System.out.println();
         }   
    }
}

