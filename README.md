This repository presents a library of classes and methods used to solve a game of Boggle. For more about boggle, please visit here: http://en.wikipedia.org/wiki/Boggle

The code uses a simple depth first search strategy to explore all combinations of words possible. The BoggleGraph class maintains a vocabulary hash set, that it builds using a dictionary file. The file used can be found under the project directory, on this path: boggle/src/test/resources/dictionary.txt

For the main class, visit the BoggleGraph.java class file at main.org.nkelkar.boggle.helper
Very soon, I plan to port all the final methods to do the graph solving to a separate package, probably under main.org.nkelkar.boggle.run

As I develop/work more on this project, I hope to come up with faster algorithms that solve larger board sizes. Stay tuned! 

Note: There are classes in this project that can also be extended to fit your graph algorithms needs. However, I don't assume an edge weight for my graphs in this project, and if you'd need a weighted graph, you'd probably have to implement a custom Edge class or something alike :)

Enjoy!

UPDATE-02/16/2014: Replaced old HashSet dictionary with the more advanced Trie data structure. The solver can solve a 4x4 board much much faster now! (~5 seconds of savings) I'm sure it will be faster in magnitudes for larger board sizes.

UPDATE-02/16/2014: Please note, for the dictionary boggle/src/test/resources/dictionary.txt, the following copyright notice:

The collective work is Copyright 2000-2011 by Kevin Atkinson as well
as any of the copyrights mentioned below:

  Copyright 2000-2011 by Kevin Atkinson

  Permission to use, copy, modify, distribute and sell these word
  lists, the associated scripts, the output created from the scripts,
  and its documentation for any purpose is hereby granted without fee,
  provided that the above copyright notice appears in all copies and
  that both that copyright notice and this permission notice appear in
  supporting documentation. Kevin Atkinson makes no representations
  about the suitability of this array for any purpose. It is provided
  "as is" without express or implied warranty.

Alan Beale <biljir@pobox.com> also deserves special credit as he has,
in addition to providing the 12Dicts package and being a major
contributor to the ENABLE word list, given me an incredible amount of
feedback and created a number of special lists (those found in the
Supplement) in order to help improve the overall quality of SCOWL.
 
UPDATE-02/16/2014: Upgraded to using the SOWPODS dictionary that is also the de-facto standard dictionary for Scrabble(TM). The dictionary file boggle/src/test/resources/dictionary.txt is no longer used, but the above copyright notice for it is maintained none-the-less (since the dictionary did help me during debugging). The file will also be maintained.
