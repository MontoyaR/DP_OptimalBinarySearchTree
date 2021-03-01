# Assignment-02

## Instructions

You are to implement the dynamic programming algorithm we discussed in class for the ***optimal binary search tree*** problem.

Save the input and output files for each configuration.
-   You must provide a feature to store the three matrices e, w, and r, and also the optimal binary search tree in files. Use appropriate file names.  No GUI is required for displaying the tree.  A textual structure (display) is sufficient.
-   You may use the algorithms provided in the class (and in Chapter 15); however, you must not reuse the implementations from other sources. The implementation must be yours. You cannot use code written by others, including seeking free and/or paid services from others (e.g., Course Hero). It is an individual assignment, and no group work is expected or will be accepted.
-   There is no specific programming-language requirement for this assignment.
-   You must chart the performance of the algorithm for n=10, 100, 1000, 10000, and 100000 randomly generated input keys (then sorted) and their probabilities. Remember you would need n+1 dummy keys and the sum of probabilities of real and dummy keys is one. You may reuse the code from Assignment 01.
-   Compare the recorded performances (time measurements) with their theoretical growth rates, i.e., “Big-oh” complexities.
-   If you run out of resources for a specific configuration (e.g., out of memory for 1,000,000 keys), it should be noted and submitted.
-   Provide a README file explaining the structure of your implementation, decision designs, and how to execute your program.

## Optimal Binary Search Tree Performance
| Run | Time (sec) | File                             |
|-----|------------|----------------------------------|
| 1   | 0.0115226  | OBST.10.keys.txt                 |
| 2   | 0.0436667  | OBST.100.keys.txt                |
| 3   | 1.3347323  | OBST.1000.keys.txt               |
| 4   | 2148       | OBST.10000.keys.txt              |
| 5   |   N/A      |N/A                               |

*The run for 100,000 keys was unable to complete because it took a long time. The Root, Weight, and Cost Matrices for the 10,000 keys were unable to be uploaded to Github due to Github's 100MB commit limit.*

![](https://github.com/MontoyaR/DP_OptimalBinarySearchTree/blob/master/src/Optimal%20Binary%20Search%20Tree/Optimal%20Binary%20Search%20Tree.png)

