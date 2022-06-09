# Prefix Miner with Trie Data Structure
#### Developer: Amirhossein Farmad
#### Date: 05/16/2022

---

### Environment

* Java 11
* Spring boot 2.6.7


---
### Question

Matching Prefixes
The task is to develop a method that accepts a string as a parameter and returns the longest prefix that matches the input string, given a list of variable-length string prefixes. If the input string begins with that prefix, the prefix matches.
Your solution should use the list of prefixes to match as a configuration.

---
### How the application works?

This is a console application using the Trie data structure to store prefixes and find the longest existing matched prefix in your input word.

* Prepare a text file of prefixes. Prefixes are separated by the Newline character.
* Put the text file of your prefixes in
    *      src/main/resources/data
* You can either set its file name in 
    *      application.properties
* You can also input the file name each time application runs.
* First the file is read and loaded into a Trie data structure.
* Input the word you want to search for the longest matched prefix in it.
* You can search several times and if you want to quit insert :q.

---

### Why Trie Data Structure?

A trie is an ordered data structure, a type of search tree used to store associative data structures. It is also referred to as a Radix tree or Prefix tree.
Its performance is better than Binary Search Tree and Ternary Search Tree in terms of time complexity.
Although Ternary Search Tree spends less space.
There is no prebuilt implementation in Java.
We can implement it in two general ways in Java:
* Array (ex 26 letters): It is fast because there is no pointer and it uses less space because the array size is fixed.
* Map: It can support all the symbols.


### Where Can I Learn More About Tree Data Structure?
Thanks  Geekific Youtube channel creating lots of super great contents about data structures and algorithms. 

You can check this link to learn more about Tree data structure:

https://www.youtube.com/watch?v=bZMNBTkiUoM&list=PLlsmxlJgn1HJRYU7YIf8DSEg8_DGwSV29