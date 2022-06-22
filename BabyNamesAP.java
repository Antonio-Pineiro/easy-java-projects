import java.util.*;

import java.io.*;

//define the class

public class BabyNamesAP

{

     //define the main method

     public static void main(String[] args)

     {

          //create a scanner object

          Scanner sc = new Scanner(System.in);

          //prompt and in the year

          System.out.print("Enter the year: ");

          String year = sc.next();

          //prompt and in the gender

          System.out.print("Enter the gender: ");

          String gender = sc.next();

          //prompt and in the name

          System.out.print("Enter the name: ");

          String name = sc.next();

          //close the scanner object

          sc.close();

          //create a file object and open the input file

          File infile = new File("babynameranking" + year + ".txt");

          //iffile does not open properly, then exit

          if (!infile.exists())

          {

              System.out.println("No record for " + year);

              System.exit(1);

          }



          int rank = 0;

          try

          {

              //create a scanner object and pass infile

              Scanner in = new Scanner(infile);

              //read the input text file

              while (in.hasNext())

              {

                   //read the line

                   String line = in.nextLine();

                   //split the line with tab space value and store in an array

                   String[] details = line.split("\t");

                   //if the gender is male and the name is value of details at index 1,

                   //then the rank is value of details at index 0

                   if (gender.equalsIgnoreCase("M") && details[1].contains(name))

                        rank = new Integer(details[0]);

                   //if the name is value of details at index 3,

                   //then the rank is value of details at index 0

                   else if (details[3].contains(name))

                        rank = new Integer(details[0]);

              }

          }

          //catch the exception

          catch (FileNotFoundException e)

          {

              e.printStackTrace();

          }

          //if the rank is 0, then print the statement

          if (rank == 0)

          {

              System.out.println("The name " + name + " is not ranked in year " + year);

          }

          //otherwise, print the rank of the name.

          else

          {

              System.out.println(name + " is ranked #" + rank + " in year " + year);

          }

     }

}