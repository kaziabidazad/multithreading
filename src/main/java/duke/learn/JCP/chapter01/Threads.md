 

##Liveness Hazard:
A liveness failure occurs when an activity gets into a state such that it is permanently unable to
make forward progress. One form of liveness failure that can occur in sequential
programs is an inadvertent infinite loop, where the code that follows the loop
never gets executed. The use of threads introduces additional liveness risks. For
example, if thread A is waiting for a resource that thread B holds exclusively, and
B never releases it, A will wait forever.

##Performance Hazards:
In well designed concurrent applications the use of threads is a net performance
gain, but threads nevertheless carry some degree of runtime overhead.
Context switches—when the scheduler suspends the active thread temporarily so
another thread can run—are more frequent in applications with many threads,
and have significant costs: saving and restoring execution context, loss of locality,
and CPU time spent scheduling threads instead of running them. When
threads share data, they must use synchronization mechanisms that can inhibit
compiler optimizations, flush or invalidate memory caches, and create synchronization
traffic on the shared memory bus. All these factors introduce additional
performance costs;

##Synchronization 
Whenever more than one thread accesses a given state variable, and one of them might
write to it, they all must coordinate their access to it using synchronization. The primary
mechanism for synchronization in Java is the synchronized keyword, which provides
exclusive locking, but the term “synchronization” also includes the use of
volatile variables, explicit locks, and atomic variables.

##What is Thread Safety
A class is thread-safe if it behaves correctly when accessed from multiple
threads, regardless of the scheduling or interleaving of the execution of
those threads by the runtime environment, and with no additional synchronization
or other coordination on the part of the calling code.

Thread-safe classes encapsulate any needed synchronization so that
clients need not provide their own.

## Atomicity
Operations A and B are atomic with respect to each other if, from the
perspective of a thread executing A, when another thread executes B,
either all of B has executed or none of it has. An atomic operation is one
that is atomic with respect to all operations, including itself, that operate
on the same state.

## Volatile

Locking can guarantee both visibility and atomicity; volatile variables can
only guarantee visibility.