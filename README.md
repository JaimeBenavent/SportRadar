# SportRadar
***DESCRIPTION
The application shows a sports scoreboard with some results of soccer world cup matches

Displays in a main scoreboard the last action occurred in each of the matches in play, using functions such as goal, VAR, disallowed goal, half-time and end of match
Below the main scoreboard, a list of the matches in play is displayed, which are kept on screen until the end of the match
At the end of all matches, this list will show a summary of the results of the matches played


***HOW IT WORKS
1. Creates a frame object where the scoreboard is displayed.
2. A list of matches pending to start is created containing all the home and aways teams
3. An empty list of matches in play is created
4. An empty list of match summaries is created
5. A thread is created to update the actions of each match and display them on the screen
6. A thread is created to start the matches by moving the matches from the list of pending matches to the list of matches in play
7. The thread to start the matches is started 
8. The thread to display the results is started
9. The thread to check goal matches is started


***TEST
A soccer teams list is created manually
A matches list is created based on a random selection from the soccer teams list and every match starts with 0-0 score
Once a match is started, every team can now score goals, which are managed by a random time variable and a random team from the matches list
Every time a team scores a goal, it is calculated a 20% of VAR possibility. If the VAR is called, then it has a 50% of possibility to be a valid goal. This is based on a random function with configurable variables to call VAR action and Valid goal action
When the timer reaches 90', (in this test increases by 5 second), the match ends and is added to the summary list, and is removed from the scoreboard
When all the matches are finished, the result is shown in the summary list and the application ends