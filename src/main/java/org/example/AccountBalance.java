package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class AccountBalance {

   public static int[] Calcualte(int[] balances, String[] requests)
   {
      // String[] cashback = new String[]{};
       ArrayList<String> cashback = new ArrayList<>();
       int count = 0;
       int read = 0, write = 0;
       for(String s: requests)
       {
           count++;
           String func = s.split(" ")[0];
           long timestp = Long.parseLong(s.split(" ")[1]);
           int acc = Integer.parseInt(s.split(" ")[2]);
           int amt = Integer.parseInt(s.split(" ")[3]);

           if(!cashback.isEmpty())
           {
              String str =   cashback.getFirst();
               long timestp_cb = Long.parseLong(str.split(" ")[1]);
               int acc_cb = Integer.parseInt(str.split(" ")[2]);
               int amt_cb = Integer.parseInt(str.split(" ")[3]);
               if(timestp_cb < timestp)
               {
                   balances[acc_cb-1] += amt_cb;
                   cashback.removeFirst();
               }
           }
           if(func.equals("withdraw"))
           {
               balances[acc-1] -= amt;
               if(balances[acc-1] < 0)
               {
                   return new int[]{-count};
               }
               cashback.add( "withdraw " + (timestp + (3600 * 24)) +" "+ acc+" " + ((int)(amt * .02))+" ");
           }
           else if(func.equals("deposit"))
           {
               balances[acc-1] += amt;
           }
       }


       return balances;
   }

    public static void main(String[] args)
    {
        int[] balances = new int[]{1000, 1500};
        String[] requests = new String[]{
        "withdraw 1613327630 2 480",
                "withdraw 1613327644 2 800",
                "withdraw 1614105244 1 100",
                "deposit 1614108844 2 200",
                "withdraw 1614108845 2 150"};

        int[] res = Calcualte(balances, requests);

        System.out.println(Arrays.toString(res));
    }
}
