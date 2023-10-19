- jrsuleim_2125 
- HW2

1. List the advantages and disadvantages of lazy deletion. 

Disadvantages:
- The list occupies extra spaces during lazy deletion, which is unnecessary. The node that was removed after a lazy deletion procedure takes up unnecessary disc space.

- The deleted nodes are just marked, therefore traversing the list requires extra time.

- Because each node needs an additional bit, it takes more space.

   
Advantages:
- It takes less time to delete a node with lazy deletion since it simply marks the node as deleted instead of deleting it from the list. Thus, it takes less time than a typical deletion.

- It is simple to code.

- Possible saving if deleted keys reinserted 



 




