# SportRadar
The program consists of a sports scoreboard that shows the results of various world cup matches.

It is based on two objects (Team and Match) to be able to show all the necessary information of the sports scoreboard.

In order to test them I have created the following test:

Create a list of soccer teams manually.

Create a list of matches by going through the list of teams randomly and initializing each match with the result from zero to zero.

Once I have initialized a match, a stopwatch is initialized and the teams can now score goals, which are added from time to time marked by a random variable and in a random team from the list of matches.

When there is a goal and a condition based on a combination of numbers that are randomly generated is met, VAR is called and the goal can be annulled if it meets the final condition.

When the timer reaches 90, (which for this example increases by 5 by 5), the match ends and goes to a new summary list, and disappears from the sports scoreboard.

When all the matches are finished, their result is shown in the summary list and the score remains empty and the program ends.