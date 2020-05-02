# Smite_Longest_CC_Chain
The code I used in my Innocentrabbit videos searching for the longest possible CC chains in the video game smite. 

The code is a generally basic recursive search but with many layers of complexity dude to having to model the games mechanics. I do not think this is the optimal solution, but at the time, this version gave me a concrete answer and I could deal with its limitations.

I ran all simulations in the command line, and you can edit the simulation setting in main.java.
The main settings are:
  - Comment in or out the gods you wish to check, the gods currently listed are all that are available however more could be added in Setup.java.
  - True or false for Hard CC, Soft CC, Bracer, Med, Ults
  - in GameSim.java you can find a place to add a time cap on each combo check, which leads to estimation instead of definitive answering.
  - You can also find a place to comment in/out lines that make the code give updates when a new best is found.
  
 God & Ability are the main classes of note.
  
