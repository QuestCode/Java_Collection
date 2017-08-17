/*
3. Extend the class LinkedListClass by adding the following operations:
	a. Find and delete the node with the smallest info in the list. (Delete only the first
	occurrence.Traverse the list only once.) Call the method implementing this operation
	deleteSmallest and include it as an abstract method in the class
	LinkedListClass.Write the definition of this method for the class
	UnorderedLinkedList.Also, write a program to test your method.
	
	b. Find and delete all the occurrences of a given info from the list. (Traverse the list
	only once.) Call the method implementing this operation deleteAll and include
	it as an abstract method in the class LinkedListClass.Write the definition
	of this method for the class UnorderedLinkedList.Also, write a program to
	test your method.
*/

// Devontae Reid

public UnorderedLinkedList extends LinkedList {
	// Precondition:
   // Postcondition: first = null, last = null,count = 0
   public UnorderedLinkedList()
	{
		super();
	}
   
   // Precondition: otherList references a valid UnorderedArrayList object instance
   // Postcondition: the data members of otherList are copied
   public UnorderedLinkedList(UnorderedLinkedList otherList)
   {
      super(otherList);
   }
	
	// Precondition: searchItem references a valid DataElement instance
	// Postcondition: Returns true if searchItem is found in the list; false otherwise.
   // O(n)
	public boolean search(DataElement searchItem)
	{
		LinkedListNode current;
	   boolean found;
	
	   current = first; 
	
	   found = false;
	
	   while(current != null && !found) 
			if(current.info.equals(searchItem))
				found = true;
			else
				current = current.link;
	  	return found;
	}
	
	// Precondition: valid DataElement instances
	// Postcondition: If found, the node containing deleteItem is deleted from the
	//                list. Also first points to the first node, last points to the last
	//                node of the updated list, and count is decremented by 1.
   // O(n)
	public void deleteNode(DataElement deleteItem)
	{
      
		LinkedListNode current;
		LinkedListNode trailCurrent; 
		boolean found;
      
	  	if(first == null)
			System.err.println("Cannot delete from an empty list.");
	  	else
	  	{  
			if(first.info.equals(deleteItem))
		  	{
				first = first.link; 
			  	if(first == null) 
				  	last = null;
			  	count--;
			}
			else{
			  	found = false;
			  	trailCurrent = first; 
			  	current = first.link;
            
				while(current != null && !found)
			  	{
					if(current.info.equals(deleteItem))
					{		
						found = true;
					}
					else
					{
						 trailCurrent = current; 
						 current = current.link; 
					}
				}
            
			  	if(found)
			  	{
					trailCurrent.link = current.link; 
               count--; 
					if(last == current) 
					{							   
						last = trailCurrent;
			 		}
			 	}
			 	else
				  	System.out.println("Item to be deleted is "
										 + "not in the list.");
			}
	  	}
	}
	
	
	// Postcondition: Deletes the first occurrence of the node with the smallest DataElement stored in info
   // O(n)
	public void deleteSmallest()
	{
		LinkedListNode current; 
		LinkedListNode trailCurrent; 
		LinkedListNode smallest; 
		LinkedListNode trailSmallest; 
		
	  	if(first == null) 
			System.err.println("Cannot delete from an empty list.");
		else 
		{	
			if( first.link == null) 
			{
				first = null;
				last = null;
			   count--;
			}
			else 
			{		
				trailCurrent = first;
			  	current = first.link;  
			  	
			   if(trailCurrent.info.compareTo(current.info) > 0)//Case 2
		  		{	
		  			trailSmallest = first;
		  			smallest = first.link; 
		  		}
		  		else 
		  		{
		  		   trailSmallest = first;
		  			smallest = first; 
		  			}
		  		
		  		
		  		trailCurrent = current; 
			  	current = current.link; 
			  	
		  		
		  		while(current != null)
		  		{
		  			if(smallest.info.compareTo(current.info) > 0)
		  			{
		  				trailSmallest = trailCurrent; 
		  				smallest = current; 
		  			} // end if
		  			
               
		  			trailCurrent = current;
			  		current = current.link;
		  		}
		  		
		  		
		  		if(smallest.link == null)
		  		{ 
		  			trailSmallest.link = smallest.link; 
                                                  
					if(last == smallest)
					{							    
						last = trailSmallest;
			 		}
		  			smallest = null; 
		  			trailCurrent = null;
		  			count--; 
			 	}
			 	
			 	else if( first == smallest )
			 	{
			 		first = first.link; 
			 		smallest = null; 
			 		trailSmallest = null; 
			 		count--; 
			 	}
			 	else 
			 	{
		  			trailSmallest.link = smallest.link; 
			 		smallest = null; 
			 		count--; 
			 	} 
			} 		
		} 
	} 
	
	// Precondition: Valid DataElement type
	// Postcondition: all occurrences found of DataElement are removed
   // O(n)
	public void deleteAll(DataElement deleteItem)
	{
		
		LinkedListNode current;
		LinkedListNode trailCurrent; 
		boolean found = false;
		
	  	if(first == null)   
		{
			System.err.println("Cannot delete from an empty list.");
		}
		else
		{
			if(first.link == null) 
			{					
				if(first.info.equals(deleteItem)) 
				{												
					first = first.link; 
               last = null;
					count--; 
				}
			} 
			else 
			{
				while(first.info.equals(deleteItem))
				{
					first = first.link; 
					count--;
					if( first == null)
						break;
				} 
				
				if(first != null)
				{
					trailCurrent = first; 
					current = first.link;
					
					while(current != null)
					{
						if(current.info.equals(deleteItem))
								found = true;
						else
						{
							 trailCurrent = current; 
							 current = current.link; 
							 
						}
						if(found)
						{
							trailCurrent.link = current.link; 
							
							if(first == current) 
								first = trailCurrent; 
							if(last == current) 
								last = trailCurrent; 
							current = trailCurrent.link;
							found = false;
                     count--;
						}
					}
				}
			}
		}
	} 
}