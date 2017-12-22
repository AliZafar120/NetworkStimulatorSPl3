
Distributed System Events Querying through Provenance
========
This project was done as part of academic reseach in Institute of Information Technology. This academic project was based on paper "Diagnosing missing events in Distributed System through negative provanance".

The main mentionable achievements in this project is:
a) Graphical Representation of provenance graph.
b) Improvement of Response time
c) Implementation of base paper.

# Manual
Please use ubuntu 14.04 LTS and install necessary requirements for rapidnet http://netdb.cis.upenn.edu/rapidnet/ and ns3 https://www.nsnam.org/. Then run the following commands in the downloaded directory:
1) ./waf 
2) ./waf --run scratch/program_file_name --cwd any_directory > output.txt 2>&1
(please remove the .cc portion of program file name, cwd is changing directory of output in case you need it, here the output is written to output.txt by 2>&1 )
3)get the log file of and then use it using the tool which can be opened by running the Workspace/Java/Spl3/src/GraphTest/Graph.java
4) using the tool explore the graph and analyze and query events in the system
