import java.io.*;
import java.util.*;       
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class Cash_Withdraw
{
    static void withdrawal(String ab)
    {
       System.out.println("\n`````````````````````````````````````\n");
       Scanner sc=new Scanner(System.in);
        try
        {
         
          String balanceofFile=Files.readAllLines(Path.of(ab)).get(4).substring(17);

          int integerBalance=Integer.valueOf(balanceofFile);
          System.out.print("Amount to be withdraw: ");
          int wcash=sc.nextInt();

          if(integerBalance>=wcash)
          {
            integerBalance=integerBalance-wcash;
            System.out.println("Amount Rs"+wcash+"/-withdrawn successfully");
            
            List<String> all=Files.readAllLines(Path.of(ab));
            
            all.set(4,"Account Balance :"+integerBalance);
            
            all.add(all.size(),"Amount Rs"+wcash+"/-withdrawal");
            Files.write(Path.of(ab),all);
          }
          else
          {
              System.out.println("Not Having Sufficient Balance To Withdraw");
          }
        }
        catch(IOException e){}
       System.out.println("`````````````````````````````````````\n");
       AtmInterface.prompt(ab);
       sc.close();
    }
}

class Cash_Deposit
{
    static void deposit(String ab)
    {
       Scanner sc=new Scanner(System.in);
       System.out.println("`````````````````````````````````````\n");
       try
       {
         String balanceofFile=Files.readAllLines(Path.of(ab)).get(4).substring(17);
         int integerBalance=Integer.valueOf(balanceofFile);
       
         System.out.println("Amount to be deposited :");
         int dcash=sc.nextInt();
         integerBalance=integerBalance+dcash;
         System.out.println("Amount Rs"+dcash+"/-deposited successfully");
      
         
         List<String> all=Files.readAllLines(Path.of(ab));
         
         all.set(4,"Account Balance :"+integerBalance);
         
         all.add(all.size(),"Amount Rs"+dcash+"/-deposited");
         Files.write(Paths.get(ab),all);

         System.out.println("`````````````````````````````````````\n");
         AtmInterface.prompt(ab);
         sc.close();
       }
       catch(Exception e){}
    }
}

class Money_Transfer
{
    static void transfer(String source)
    {
       System.out.println("`````````````````````````````````````");
       Scanner sc=new Scanner(System.in);
       System.out.println("Enter the receiver user id :");
       String fName=sc.nextLine();
       System.out.println("Enter the receiver account number : ");
       int fAccNumber=sc.nextInt();
       System.out.println("Enter the amount to be transferred :");
       int tcash=sc.nextInt();

       try
       {
         String destination ="C:\\Users\\DELL\\Desktop\\Oasis Intern\\oibsip_3\\"+fName+".txt";
         File fi=new File(destination);

         if(fi.exists())
         {
          
           String accnumberFromFile=Files.readAllLines(Path.of(destination)).get(1).substring(16);
           int integerAccNumber=Integer.valueOf(accnumberFromFile);

           if(integerAccNumber==fAccNumber)
           {
              String balanceFromSource=Files.readAllLines(Path.of(source)).get(4).substring(17);
              int integerSBalance=Integer.valueOf(balanceFromSource);
              String balanceFromDestination=Files.readAllLines(Paths.get(destination)).get(4).substring(17);
              int integerDBalance=Integer.valueOf(balanceFromDestination);

              if(tcash<=integerSBalance)
              {
                 integerSBalance=integerSBalance-tcash;
                 System.out.println("Amount Rs."+tcash+"/-tansferred successfully");
             
                 List<String> all1=Files.readAllLines(Path.of(source));
                 all1.set(4,"Account Balance :"+integerSBalance);
                 all1.add(all1.size(),"Amount Rs"+tcash+"/-Transferred to "+fAccNumber+" account number");
                 Files.write(Path.of(source),all1);

                 integerDBalance=integerDBalance+tcash;
             
                 List<String> all2=Files.readAllLines(Paths.get(destination));
                 all2.set(4,"Account Balance :"+integerDBalance);
                 all2.add(all2.size(),"Amount Rs"+tcash+"/-Deposited by "+fName);
                 Files.write(Paths.get(destination),all2);
              }
              else 
              System.out.println("Not Having Sufficient Balance To Withdraw");
              
            }
            else
              System.out.println("Entered account number is wrong");
          }
         else
             System.out.println("Not An User! Access Denied!!");
       }
       catch(Exception e){}

       System.out.println("`````````````````````````````````````\n");
       AtmInterface.prompt(source);
       sc.close();
    }
}

class Account_Balance
{
  static void checkBalance(String ab)
  {
    System.out.println("`````````````````````````````````````\n");
    try
    {
         String balanceFromFile=Files.readAllLines(Paths.get(ab)).get(4).substring(17);
         int integerBalance=Integer.valueOf(balanceFromFile);
         System.out.println(" Availabel Balance :"+integerBalance);
    }
    catch(Exception e){}
    System.out.println("`````````````````````````````````````\n");
    AtmInterface.prompt(ab);
  }
}

class Transaction_History
{
    static void acc_history(String ab)
    {
       System.out.println("`````````````````````````````````````\n");
       System.out.println("Transaction History.....");
       try
       {
           List<String> history=Files.readAllLines(Paths.get(ab));
           for(int i=history.size()-1;i>=7;i--)
           {
              System.out.print(history.get(i)+"______\n");
              
           }
       }
       catch(Exception e){}
       System.out.println("\n`````````````````````````````````````\n");
       AtmInterface.prompt(ab);
    }
}

public class AtmInterface {
   static String user_name;
   static String user_id;
   static int pin;
   static int accnumber;
   public static int accbalance=1000;

   public static void prompt(String ab)
   {
      Scanner sc=new Scanner(System.in);
      System.out.println("WELCOME TO BANKING SYSTEM");
      System.out.println("-----------------------------\n");
      System.out.println("Select Option:");
      System.out.println("1. Cash Withdrawal\n2. Cash Deposit\n3. Money Transfer\n4. Account balance\n5. Transaction History\n6. Exit");
      System.out.print("Enter your choice :");
      int choice=sc.nextInt();
    
        switch (choice) {
            case 1:
                Cash_Withdraw.withdrawal(ab);
            case 2:
                Cash_Deposit.deposit(ab);
            case 3:
                Money_Transfer.transfer(ab);
            case 4:
                Account_Balance.checkBalance(ab);
            case 5:
                Transaction_History.acc_history(ab);
            case 6:
                System.out.println("Thankyou For Banking......");
                System.exit(0);
            default:
             System.out.println("Incorrect Option");
             prompt(ab);  
        }
        sc.close();


   }
   
   public static void home()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("\nWELCOME TO ATM INTERFACE");
       System.out.println("`````````````````````````````````````\n");
       System.out.println("Select Below Option :");
       System.out.println("1 Register(New User)\n2 for Login\n3 for Exit");
       System.out.print("Your choice:");
       int choice=sc.nextInt();
       switch(choice)
       {
        case 1:
        System.out.println("`````````````````````````````````````");
        System.out.print("Enter account holder name:");
        sc.nextLine();
        user_name=sc.nextLine();
        System.out.print("Enter the user id(8 characters):");
        user_id=sc.nextLine();
        System.out.print("Please set your pin:");
        pin=sc.nextInt();
        System.out.print("Enter your Account Number:");
        accnumber=sc.nextInt();
        System.out.println("\nREGISTRATION SUCCESSFULLY COMPLETED!!");
        System.out.println("`````````````````````````````````````\n");
         try
         {
           File f=new File("C:\\Users\\DELL\\Desktop\\Oasis Intern\\oibsip_3\\"+user_id+".txt");
            Writer w=new FileWriter(f);
            w.write(("Atm PIN :"+pin));
            w.write(("\nAccount Number :"+accnumber));
            w.write(("\nAccount Holder :"+user_name));
            w.write(("\nUser Id:"+user_id));
            w.write("\nAccount Balance :"+accbalance);
            w.write("\n\n.....Transaction History.....");
            w.close();
         }
         catch(Exception e){}
          login();
          break;
        
        case 2:
              login();
              break;

         case 3:
                System.out.println("Thankyou For Banking......");
                System.exit(0);
                break;
         
         default:
                System.out.println("Incorrect Option\n");
                home();

       }
       sc.close();
   }

   public static void login()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("`````````````````````````````````````\n");
       System.out.print("\nLOGIN.....\nEnter the user id:");
       String verify_user_id=sc.nextLine();
       System.out.print("Enter your secret number:");
       int verify_password=sc.nextInt();
       
       String ab="C:\\Users\\DELL\\Desktop\\Oasis Intern\\oibsip_3\\"+verify_user_id+".txt";
       File fi=new File(ab);
       if(fi.exists())
       {
          try
          {
            BufferedReader br=new BufferedReader(new FileReader(fi));
            int getPin=Integer.valueOf(br.readLine().substring(9));
            br.close();
            if(getPin==verify_password)
            {
              System.out.println(".....LOGIN SUCCESSFULL.....\n");
              prompt(ab);
           }
           else
           {
             System.out.println("\nINVALID SECRET NUMBER");
             System.out.print("\nWant To Login Again:");
             sc.nextLine();
             String againlogin=sc.nextLine();
              if(againlogin.equalsIgnoreCase("yes"))
                login();
              else
              {
                System.out.println("Incorrect Option\n");
                System.exit(0);
              }
           }
          }
          catch(Exception e){}
        }
        else 
        {
        System.out.println("Not An User! Access Denied!!");
        login();
        }
        sc.close();
   }

    public static void main(String[] args) {
        home();
    }
}
