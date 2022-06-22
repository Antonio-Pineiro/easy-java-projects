

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Project2 {

	public static void main(String[] args) throws IOException {
		
		
        String filename1 ,filename2 ;
        filename1 = args [0];
        filename2 = args[1];
	//System.out.println(filename1+" "+filename2);
		ReadData(filename1,filename2);

	}
	public static  void ReadData(String  File1,String File2)throws IOException {
		COPYZipcode02  zipcod;
		int zipcodew =0;
		List<COPYZipcode02> aaw =new ArrayList<>();
		String line =null,line1 = null;
	    int  count =0;
        String  State2 =null,city =null;
         double  avger =0;
         long popuation =0;
	    HashMap<Integer  , String> ZipCodeAndCity =new HashMap<>();
	    HashMap< Integer , String> ZIPCODEState =new HashMap<>();



		HashMap<String,String > mapz = new HashMap<String,String>();

		// file reader
		
		BufferedReader file1 = new BufferedReader(new FileReader(File1));//zipcode_file//arr.csv
		//aazer= aazer+ arzgsd[2];

		BufferedReader az = new BufferedReader(new FileReader(File2));//State_file//State.csv

				az.readLine();//header of State file
				String ar1[] ;
				file1.readLine();//header of zipcode_file
				while((line = az.readLine()) != null)//loop1 reading State_file
				{

		    	    String oo =line.trim();//trim trailing spaces

					String roo =line.replaceAll(",(?=[^\"]*\"[^\"]*(?:\"[^\"]*\"[^\"]*)*$)", "");
				     roo=roo.replaceAll("\\\"", "");
				     String aoo = roo.replaceAll("[^\"]$", ",0.00,");



					 		ar1= aoo.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");
					 		String  state =ar1[1];
				     		String stateName =ar1[3];
				     	    mapz.put(state, stateName);




				}
				az.close();
		while((line1 =file1.readLine())!=null)// loop0 reading ZipCode_file
		{

				line1=line1.trim();//trim trailing spaces
				line1 =line1.replaceAll(",,",",");//fixing file data
				line1 =line1.replaceAll("$","0.0,");//normalizing the array
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0.0,");//fixing rowLenght
				line1 =line1.replaceAll("$", "0,");//fixing rowLenght

				
				String [] ifo =line1.split(",(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))");//split using commas
				    zipcodew =Integer.valueOf(ifo[1]);
				    city = ifo[3];
				    State2= ifo[4];
				    String stateNamez;
                    		avger = Double.valueOf(ifo[19]);
				if((Long.valueOf(ifo[17])) ==0.0)
				{
				 popuation =0;
				}else{
                    		popuation = Long.valueOf(ifo[17]);
				}
                    		ZipCodeAndCity.put(zipcodew, city);
					ZIPCODEState.put(zipcodew, State2);
                     if(mapz.containsKey(State2))  {
                    	 stateNamez =mapz.get(State2);
        				zipcod =  new COPYZipcode02(zipcodew,city,State2,stateNamez,avger, popuation);
        				aaw.add(zipcod);

        			}
                     else
                     {
         				zipcod =  new COPYZipcode02(zipcodew,city,State2,"null",avger, popuation);
        				aaw.add(zipcod);

                     }






		}//loop reading zipcodeFile



		file1.close();

	      PrintWriter output = new PrintWriter("../CPS3525/Project2_results.html/");

			Map<String, Double> AvgWageByState=aaw.stream()
		    .collect(Collectors.groupingBy(COPYZipcode02::getState, Collectors.averagingDouble(COPYZipcode02::getAvgWages)));

			  Map<Integer, Double> AvgWagCity=aaw.stream()
				    .collect(Collectors.groupingBy(COPYZipcode02::getZipcode, Collectors.averagingDouble(COPYZipcode02::getAvgWages)));

			Map<String, Long> avgPoplutaion=aaw.stream()
		    .collect(Collectors.groupingBy(COPYZipcode02::getState, Collectors.summingLong(COPYZipcode02::getPopluations)))
		    ;


			Map<Integer, Long> sumCitypopulation=aaw.stream()
				    .collect(Collectors.groupingBy(COPYZipcode02::getZipcode, Collectors.summingLong(COPYZipcode02::getPopluations)))
				     ;




	LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
	AvgWageByState.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))

    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

	LinkedHashMap<Integer, Long> MaxBYCity = new LinkedHashMap<>();
	sumCitypopulation.entrySet().stream().max(Map.Entry.comparingByValue())
    .ifPresent(x -> MaxBYCity.put(x.getKey(), x.getValue()));

	LinkedHashMap<Integer, Long> MinBYCity = new LinkedHashMap<>();
	sumCitypopulation.entrySet().stream().min(Map.Entry.comparingByValue())
    .ifPresent(x -> MinBYCity.put(x.getKey(), x.getValue()));


	LinkedHashMap<Integer , Double> maxCitywage = new LinkedHashMap<>();
	AvgWagCity.entrySet().stream().max(Map.Entry.comparingByValue())
    .ifPresent(x -> maxCitywage.put(x.getKey(), x.getValue()));

	LinkedHashMap<Integer , Double> minCitywage = new LinkedHashMap<>();
	AvgWagCity.entrySet().stream().filter(AvgWagCi ->AvgWagCi.getValue()>0).min(Map.Entry.comparingByValue())
    .ifPresent(x -> minCitywage.put(x.getKey(), x.getValue()));










				Map<String, Long> counted = aaw.stream()

	           .collect(Collectors.groupingBy(COPYZipcode02::getState, Collectors.counting()));


				output.println("<html>");
				System.out.println("<html>");
				output.println("<style>");
				System.out.println("<style>");
				output.println("table {\n\r"+
  "font-family: arial, sans-serif;\n\r"+
  "border-collapse: collapse;\n\r"+
  "width: 75%;\n\r }");
				output.println(" th, td {   border: 2px solid black; text-align: right; padding: 8px; }");
  	output.println("tr:nth-child(even) {background-color: #dddddd;}");
				output.println("</style>");
				output.println("</head>");
				output.println("<body>");
				output.println("<table>");
//=-=-=-==-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

				System.out.println("table {\n\r"+
  "font-family: arial, sans-serif;\n\r"+
  "border-collapse: collapse;\n\r"+
  "width: 75%;\n\r }");
				System.out.println(" th, td {   border: 2px solid black; text-align: right; padding: 8px; }");
  				System.out.println("tr:nth-child(even) {background-color: #dddddd;}");
				System.out.println("</style>");
				System.out.println("</head>");
                System.out.println("<body>");
				System.out.println("<table>");




//-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=--=-=-=-=-=-=-=
				output.println(" ");
				output.println("<tr>");
				System.out.println(" ");
				System.out.println("<tr>");
		 output.println("<th>State</th><th>Captial</th><th>Number_of_zipcodes</th><th>Total_Estimated_Population</th><th>Average_AvgWages</th>");
 		 	System.out.println("<th>State</th><th>Captial</th><th>Number_of_zipcodes</th><th>Total_Estimated_Population</th><th>Average_AvgWages</th>\n\r");

				output.println("</tr>");
				System.out.println("</tr>");
				output.println(" ");

		 for (Entry<String, Double> entryz : reverseSortedMap.entrySet()) {
			 
				System.out.printf("<tr><td>%s</td><td> %-12s</td><td>%,d</td><td>%,d</td><td>$%,.1f</td></tr>\n\r" , entryz.getKey(),mapz.get(entryz.getKey()),counted.get(entryz.getKey()),avgPoplutaion.get(entryz.getKey()),
						entryz.getValue());
				//System.out.println("</table>\n");
		 }
	   		 for (Entry<String, Double> entyz : reverseSortedMap.entrySet()) {

			output.printf("<tr><td>%s</td><td> %-12s</td><td>%,d</td><td>%,d</td><td>$%,.1f</td></tr>\n\r" , entyz.getKey(),mapz.get(entyz.getKey()),counted.get(entyz.getKey()),avgPoplutaion.get(entyz.getKey()),
                    entyz.getValue());
				//System.out.println("</table>\n");
	   		 }
			System.out.println("</table>\n");
			output.println("</table>\n");
		 for (Entry<Integer, Long> eryz : MaxBYCity.entrySet()) {

		System.out.printf("<p>Zipcode   %d  at  %s,%s has maximum popultion of %,d with AvgWages $%,.1f</p>\n\r",eryz.getKey(),ZipCodeAndCity.get(eryz.getKey()),ZIPCODEState.get(eryz.getKey()),eryz.getValue(),AvgWagCity.get(eryz.getKey()) );
		 output.printf("<p>Zipcode   %d  at  %s,%s has maximum popultion of %,d with AvgWages $%,.1f</p>\n\r",eryz.getKey(),ZipCodeAndCity.get(eryz.getKey()),ZIPCODEState.get(eryz.getKey()),eryz.getValue(),AvgWagCity.get(eryz.getKey()) );
		 }


		 for (Entry<Integer, Long> erzyr : MinBYCity.entrySet()){
		 System.out.printf("<p>Zipcode   %d  at  %s,%s has minimum popultion of %,d with AvgWages $%,.1f</p>\n\r",erzyr.getKey(),ZipCodeAndCity.get(erzyr.getKey()),ZIPCODEState.get(erzyr.getKey()),erzyr.getValue(),AvgWagCity.get(erzyr.getKey()) );

		 output.printf("<p>Zipcode   %d  at  %s,%s has minimum popultion of %,d with AvgWages $%,.1f</p>\n\r",erzyr.getKey(),ZipCodeAndCity.get(erzyr.getKey()),ZIPCODEState.get(erzyr.getKey()),erzyr.getValue(),AvgWagCity.get(erzyr.getKey()) );
		 }
		 for (Entry<Integer, Double> ezy : maxCitywage.entrySet()){
		 System.out.printf("<p>Zipcode   %d  at  %s,%s has maximum AvgWage of $%,.1f with popultion %,d </p>\n\r",ezy.getKey(),ZipCodeAndCity.get(ezy.getKey()),ZIPCODEState.get(ezy.getKey()),ezy.getValue(),sumCitypopulation.get(ezy.getKey()));

		 output.printf("<p>Zipcode   %d  at  %s,%s has maximum AvgWage of $%,.1f with popultion %,d </p>\n\r",ezy.getKey(),ZipCodeAndCity.get(ezy.getKey()),ZIPCODEState.get(ezy.getKey()),ezy.getValue(),sumCitypopulation.get(ezy.getKey()));
		 }
		 for (Entry<Integer, Double> erzy : minCitywage.entrySet()){
			 System.out.printf("<p>Zipcode   %d  at  %s,%s has minimum AvgWage of $%,.3f with popultion %,d </p>\n\r",erzy.getKey(),ZipCodeAndCity.get(erzy.getKey()),ZIPCODEState.get(erzy.getKey()),erzy.getValue(),sumCitypopulation.get(erzy.getKey()));
			output.printf("<p>Zipcode   %d  at  %s,%s has minimum AvgWage of $%,.3f with popultion %,d </p>\n\r",erzy.getKey(),ZipCodeAndCity.get(erzy.getKey()),ZIPCODEState.get(erzy.getKey()),erzy.getValue(),sumCitypopulation.get(erzy.getKey()));
			}
			//System.out.println("\n</body>\n");
			//System.out.println("</html>");
				output.println("\n</body>\n");
				output.println("</html>");

				output.close();

}//end  of Method m1


}
