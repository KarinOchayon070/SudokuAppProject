Hi guys,

In the third year of my computer studies degree, as part of an advanced Java development course, I developed a Java fx Sudoku application. This application is developed in a client-server architecture and has three different repositories:
The algorithm repository   - https://github.com/KarinOchayon070/sudokuAlgorithm.
The server side repository - https://github.com/KarinOchayon070/SudokuServer.
The client side repository - https://github.com/KarinOchayon070/SudokuClient.

🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢🔢

The server side has several important layers: 
(1). Algorithm:
The algorithmic part of the application is responsible for solving Sudoku puzzles. I have created two classes that implement the "IBacktrackingAlg" interface -
"BitMaskAlgo" and "DFSAlgo". These classes use backtracking to solve Sudoku puzzles, but use different approaches to the backtracking algorithm. 
(2). Controller:
The SudokuController class is responsible for handling various requests such as solving a Sudoku puzzle, deleting a puzzle, and retrieving a puzzle by ID or difficulty
level.
(3). Service:  
The SudokuService takes in an instance of the algorithmic class as a parameter and uses it to solve the Sudoku puzzles.
It also interacts with the data access layer to retrieve and store Sudoku puzzles.
(4). Data Model:
The SudokuTemplate class represents the structure of a Sudoku puzzle, with an ID, difficulty level, and a 2D integer array representing the puzzle itself.
(5). DAO:
In my DAO folder you can see the interface IDAO and a class that implements this interface and here we have the file "sudokuTemplate.txt"
(where all the sudoku patterns are saved which include - Sudoku board, Sudoku ID card and Sudoku difficulty level).
In this class are all the operations that can be done in the file.
(6). Server:
In my server folder you can see that it is running on port 6000 and listening to the client's requests (also you can see there the respone, request, handleRequest class).

![image](https://user-images.githubusercontent.com/92684210/219621448-925d5bda-14be-464f-b080-a8b5c4332ffd.png)

