import java.util.*;
public class Groceries {
	private static Scanner scr;
	private static Map<String,Double> items =new TreeMap<>();
	private static List<String>orderNames = new ArrayList<>();
	private static List<Double>orderPrices = new ArrayList<>();
	private static List<Integer>orderItemQty = new ArrayList<>();

	
	public static void main(String[] args) {
		System.out.println("Welcome to the Guenther's Market!");
	 Scanner scr = new Scanner(System.in);
	 String decission="";
	 try {

	do {
	
		fillItemMap();
		 printMenu();
		// scr.nextLine();
		 System.out.println("What item would you like to order?");
		 String itemName = scr.nextLine().toLowerCase();
		// System.out.println(itemName);
		 while(!isItemExists(itemName)) {
			 System.out.println("Sorry, We don't have those. Please try again. ");
			 printMenu();
			 System.out.println("What item would you like to order?");
			 
			itemName = scr.nextLine().toLowerCase();
			
		 }
		
		 addOrderItem(itemName);
		 //Double itemPrice= items.get(itemName);
		 //System.out.println("Adding "+itemName+" to cart at $"+itemPrice);
        System.out.println("Would you like to order any thing else (y/n)?");
		  decission = scr.nextLine();
			if (decission.equalsIgnoreCase("n")) {
				
				System.out.println("Thank you for your order!");
				System.out.println("Here's what you got.");
				
				displayOrderItems();
				System.out.println("Average price per item in order was "+findAverage(orderPrices));
				int maxIdx=findMax(orderPrices);
				System.out.println("Highest index of cost item : "+maxIdx);
				int minIdx=findMin(orderPrices);
				System.out.println("Lowest index of cost item : "+minIdx);
				System.out.println("Most expensive item ordered : "+orderNames.get(maxIdx));
				System.out.println("Least expensive item ordered : "+orderNames.get(minIdx));

				
				
				System.exit(0);
			} else if (decission.equalsIgnoreCase("y")) {
				//studentDetails();
			}

			else {
				throw new Exception();

			}

		} while (decission.equalsIgnoreCase("y") || decission.equalsIgnoreCase("n"));
	 } 
		 catch (InputMismatchException e) {
			System.out.println("input mismatch");
		} catch (Exception e) {
			System.out.println("invalid input");
			System.exit(0);

		}

		//scr.close();
	 

	}
	

private static void addOrderItem(String itemName) {
	Double itemPrice= items.get(itemName);
	 System.out.println("Adding "+itemName+" to cart at $"+itemPrice);	 
	 orderNames.add(itemName);
	 orderPrices.add(itemPrice);
	 addOrderQty(itemName);
	 
}

private static void addOrderQty(String itemName) {
	
	/*
	for(String orderItemName:orderNames)
	{
		
	 Double itemPrice=	items.get(orderItemName); 
	 System.out.printf("%-20s%20.2f%s%n",orderItemName,itemPrice,"$");
	}*/
	int itemIdx= orderNames.indexOf(itemName); 
	int count=Collections.frequency(orderNames, itemName); 
	
	//System.out.println(itemIdx);
	//System.out.println(count);
	//System.out.println(orderItemQty);
	
	try {
		orderItemQty.get(itemIdx);
		 orderItemQty.set(itemIdx, count);
	} catch ( IndexOutOfBoundsException e ) { 
		 //orderItemQty.add(itemIdx, count);
		 orderItemQty.add(count);
	}
	
	 
	//System.out.println(count);
	//System.out.println(orderItemQty);

}

private static void displayOrderItems() {
	ArrayList<String> distinctOrderNames = new ArrayList<String>();
	
	for (String orderItemName:orderNames) { 
		  
        // If this element is not present in distinctOrderNames 
        // then add it 
        if (!distinctOrderNames.contains(orderItemName)) { 

        	distinctOrderNames.add(orderItemName); 
        } 
    } 

	
	for(String orderItemName:distinctOrderNames)
	{
	 Double itemPrice=	items.get(orderItemName);
	 int itemIdx= distinctOrderNames.indexOf(orderItemName);
	 int itemQty=	orderItemQty.get(itemIdx);
	 //System.out.println(orderItemName+"\t\t\t\t"+ "$"+itemPrice);
	 //String item = "$"+itemPrice;
	 System.out.printf("%-20s%20.2f%s%n",orderItemName,itemPrice,"$");
	 System.out.println("ItemQty "+itemQty);
	}
}
/*private static void displayOrderItems1() {
	for(int i=0;i<orderNames.size();i++)
	{
		String s = orderNames.get(i);
	 Double itemPrice=	items.get(s);
	
	 System.out.println(s+"\t\t"+ "$"+itemPrice);
	}
}*/
	
	
private static boolean isItemExists(String itemName) {
	boolean isExists=false;
	//System.out.println(items.containsKey(itemName));
	
	isExists=items.containsKey(itemName);
	return isExists;
}
private static void fillItemMap() {
	items.put("apple",.99);
	items.put("bananna",.59);
	items.put("cauliflower",1.59);
	items.put("dragonfruit", 2.19);
	items.put("elderberry",1.79);
	items.put("figs",2.09);
	items.put("grape fruit",1.99);
	items.put("honeydew",3.49);
	
}
private static void printMenu() {
	//System.out.println("Items\t\t\t\tPrice");
	System.out.printf("%-20s%20s%n","Items","Price");
	System.out.println("==============================================");
	
	
	for(Map.Entry<String,Double> entry : items.entrySet()) {
		//System.out.println(entry.getKey()+"\t\t\t\t"+entry.getValue());
		System.out.printf("%-20s%20.2f%n",entry.getKey(),entry.getValue());
	}
	
	}
public static int findMax(List<Double>nums) {
	double max = nums.get(0);
	int maxIdx=0;
	for(Double num:nums) {
		if(num>max) {
			max=num;
			maxIdx=nums.indexOf(max);
			
		}
	}
	return maxIdx;
}

private static int findMin(List<Double> nums) {
	double min = nums.get(0);//nums.get(nums.size() - 1);
	int minIdx=0;
	for (Double num : nums) {
		if (num < min) {
			min = num;
			minIdx=nums.indexOf(num);

		}
	}
	return minIdx;
}

private static double findAverage(List<Double> prices) {
	double total = 0;
	double average = 0;
	for (double price : prices) {
		total = total + price;
	}
	average = (total / prices.size());
	return average;
}



}

