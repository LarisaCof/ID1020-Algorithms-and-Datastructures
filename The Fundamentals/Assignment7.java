package lab1;

import java.util.Scanner;


	public class Assignment7<Item> 
	{ 
		private Item[] a = (Item[]) new Object[1]; // Generic array
		private int N = 0; // Number of elements in the stack

		public boolean isEmpty() { 
			return N == 0;
		}

		public int size() {
			return N;
		}
		// New array created with size max (if the stack is full)
		private void resize(int max) { 
			
			Item[] temp = (Item[]) new Object[max]; 
			for (int i = 0; i < N; i++)
				temp[i] = a[i]; // Copy all the elements in the stack into the new array
			a = temp; 
		}

		public void push(Item item) { // Push elements on to the stack
			if (N == a.length) // Check if the stack is full
				resize(2 * a.length); // If the stack is full, create a new array that is double the size by calling resize.
									
			a[N++] = item;// Add new element to the new array, increase number of elements by 1.
		}

		// Pop elements from the stack
			public Item pop() { 
				Item item = a[--N]; //item is now the second last element in the stack/array
				a[N] = null; // Last element set to null
				if (N > 0 && N == a.length / 4) //If N is array/4 create a new array with half of the length (save memory).
					resize(a.length / 2); 
											
				return item;
			}
		
	   //True if a and b match
	    public static boolean isBalanced(char a, char b) 
	    { 
	       if (a == '(' && b == ')') 
	         return true; 
	       else if (a == '{' && b == '}') 
	         return true; 
	       else if (a == '[' && b == ']') 
	         return true; 
	       else
	         return false; 
	    } 
	      
	    public static boolean checkIfBalanced(String s) 
	    { 
	    	Assignment7<Character> stack = new Assignment7<Character>(); //create a new stack
	    	char chars[] = new char [s.length()]; //Create char array with the same length as the string s
	    	for (int j = 0; j < s.length(); j++) 
	    	{
	    		chars [j] = s.charAt(j);//All characters in the string to char array
	    		
	    	}
	       
	    	int i = 0;
	    	
	       for( i=0; i < chars.length; i++) //Go through all characters to see if the string is balanced
	       { 
	            
	          if (chars[i] == '{' || chars[i] == '(' || chars[i] == '[')  //Push character at position i if it is a starting paranthesis
	            stack.push(chars[i]); 
	       
	         
	          if (chars[i] == '}' || chars[i] == ')' || chars[i] == ']') 
	        	  //If character at position i is an ending parenthesis, 
	        	  // pop it from the stack and check if it matches
	          {																
	              
	             if (stack.isEmpty()) // No parenthesis in the stack, false. 
	               { 
	                   return false; 
	               }  
	       
	             else if ( !isBalanced(stack.pop(), chars[i]) ) // pop the top element from the stack,
	            	 											
	               { 
	                   return false; //if the ending parenthesis does not match the starting parenthesis return false
	               } 
	          } 
	            
	       } 
	         
	       //If there is an element left in the stack this means 
	       //that there is a starting parenthesis with no matching ending parenthesis.
	       if (!stack.isEmpty()) 
	         return false; //if it is not empty, the string is not balanced so return false.
	       else
	         {   
	             return true;  //if the stack is empty, the string is balanced. return true.
	         }  
	    }  
	    
	    
	    //main tests if the function works. 
	    public static void main(String[] args)  
	    { 
	    	System.out.println("Enter a sentence: ");
	    	Scanner scanner = new Scanner (System.in);
	        String s = scanner.nextLine();
	        
	        
	          if (!checkIfBalanced(s)) 
	            System.out.println("The parentheses are not properly balanced."); 
	          else
	            System.out.println("The parentheses are properly balanced.");   
	    } 
	  
	} 
	
	
	
	


