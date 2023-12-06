# Portfolio project IDATT1003 - 2023
STUDENT NAME = Florian Simon Xavier Leucht  
STUDENT ID = 10055

## Project description
The project is designed to be a train dispatch system. The system is designed to be used on one station, during one day. 
The system is presented to the user as a menu in the terminal. From here the person responsible for managing
train departures can do the following:
- Show a departure table
- Add a departure to the system
- Assign a train to a departure
- Set a departure to delayed
- Search for a departure or departures by train number, or destination
- Update the system clock

## Project structure
The source files are located in the main folder. The system is divided into a model package that handles the data in the
system and a view package that handles the user interface. The system also has a class called TrainDispatchApp that
contains the main method.

The JUnit-test classes are stored in the test folder. Here the tests classes have the same name as the classes they are
testing, with the word test added to end of the name.


## Link to repository
https://github.com/fsleucht/TrainDispatchSystem

## How to run the project
The project can be run by running the method called main in TrainDispatchApp.

When run the program will print a menu i the terminal where the user can enter options with the keyboard.
The program is expected to present different pages when the user inputs numbers corresponding with the different options.
When exiting the program by entering "8" in the main menu the system is expected to print a exit message and terminate
the program.

## How to run the tests
The tests can be run by running the JUnit-test classes in the test folder

