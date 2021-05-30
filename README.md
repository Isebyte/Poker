# Poker!

"I suck at poker." ~ Me

This simple Java project includes several files that takes in a multi-line input of 
player id's and hands of cards and outputs the winner(s) of the poker game. I have split
up the implementation between five files, one being a wrapper for the game for the sake
of modularity. It was originally coded in Eclipse (Mac), then exported as a runnable jar file.

## Instructions on running in terminal/command line:
**Note**: the Java Runtime Environment must be installed before running this program. 
1. cd to the directory containing Poker.jar and the tests folder (in the downloaded Poker folder).
2. To run the program with the testing script type: ./run_tests "java -jar Poker.jar"
3. To run by itself with manual user input type: java -jar Poker.jar

## Installing Java: https://www.java.com/en/download/help/download_options.xml


### Additional Notes
1. Assumed that the input will always be in the correct format,
   although system exits have been added if card format/number/num players is incorrect.
2. Users can input card letter formats as capital or lower case letters.
3. User ids do not have to be sequential, but have to be 2-digit numbers.
4. Suits are all considered equal.
5. I included additional tests within the tests folder.
