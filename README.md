# CSX42: Assignment 3
**Name:** Priyanka Prakash Tanpure

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [studentskill/src](./studentskill/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile studentskills/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile studentskill/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile studentskills/src/build.xml run -Dinput="input.txt" -Dmodify="modify.txt" -Doutput1="output1.txt" -Doutput2="output2.txt" -Doutput3="output3.txt" -Derror="error.txt" -Ddebug="2"
```
Note: Arguments accept the absolute path of the files and file names can be anything


## Description:
BST : I use Binary serach tree for storing the nodes because Average case time complexity for insert/serach/delete is O(log n) and
Worst case time complexity for insert/serach/delete is O(n). 
At the end we have to display the nodes in ascending order so it's easy to traverse nodes in BST in inorder manner (Left->Root->Right).

Logic for BST insert/update/search code is taken from assignment done in course PST(in C language).

Assumption :
If number of skills are greater than 10 in input, I am truncating the exatra skills for single line.

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 11 July 2020

"I am using one slack day for this Assignmnet 3"