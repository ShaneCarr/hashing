Algorithm hashing

notes/scenario: 
I am working on a project where i need to pack ids into partitions using paging.

Create jobs to move files. Jobs are made of partitions

Files can be removed but none can be added from the set being migrated. to create partitiosn i have a simple paging algorithm on ids basically
select top P where id> lastId

Tasks run on partitions 

The architecture i design has jobs that operate on data in sets of partitions partitions, also each job has tasks T1-t3.
 
physical model of the app:

azure queue per tasks pull on queue, run in background thread/manage timeout. that aspect was copied from dropWizard.

Problem:
When a task runs it has side effects. I only want to pick one side effect so i created a bit mask on teh partition to "pick" an execution/completion of a tasks in the case
 of race conditions.  
then i use a bitmask for each tasks to make sure a task only executes ones time in a partition, since each task in a part

here was the problem posed. used bitmask for tasks to create one output for the tasks (the next stask or final tasks claims one result). that is stored/synchroned in partition, but we need a safe way to create teh partition
to avoid teh same file showing up in multiple partitions. 

option one i did this
partition(id, claimBitMask, lowId, highId)

when i create the partitions i do the following

lastid,
pagesize

createPartition(lowid, highId, claimmask, id)
enqueueNexttask()

if there is any intersection where any ids show up in the range of a previouslly injected partition we use that one. 

here is an example

partitions are generated on increasing ids.

say the following scenario
N=9
P=3

P1: 123 P2: 456  P3:789


lets say 1 is deleted

now i would get the question is how to match on this sliding range. 
234, 567 89

in the existing model, i suggested, you can take the existing partition and fit to that. so for example if any file id matches 
take that partition, and drop off the end for we sort by id asc.


remember we found partition with (1,3)
but we only loaded files 2-4, so we can use 
23, 456, 789

this feels not that determinsitic, and seemed like a load ballancing problem. attempt at
complete hashing.

If i can a priori create a partition number (save the partition number from the beginning) i can hash files to a partition just like files to a server or anything else. (loading the range.)
this is just a thought experiment. ti does make the querying more complex, but i wanted to try this and see where it goes.

The problem with this approach is i need to load the file ids ahead of time, and save them for a partition for large groups that won't wokr, unless i load in "sets"

ANyway teh goal isn't to really make it work but try it out and learn something. I can think about how to plug this in later on if it works.

one thing that could work is to set a range of ids, and create parittions in that range and move on after it.  
so let' say we load three partitions at a time.

P=250
partitionIndex
CI => 0
FileRange=> 

l-750 (create three partitions of these three)
move to 

751-1500 

etc







 


 





