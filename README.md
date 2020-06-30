# Thomas O'Gara's Data Structures Portfolio

This repository is intended as an exhibition of the Data Structures developed during the COMP20280 module in UCD.

## Getting Started

To begin, please `git clone` the repository onto your local machine.

### Prerequisutes

There are a small number of prerequisites if you aim to **build and/or run** this project on your local machine.

* A Java Development Kit (JDK) for Java version 8.

#### JDK

The official Oracle JDK can be found at [oracle.com](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html).
  
**Please ensure that the JDK installed on your system is added to the PATH environment variable.
You must be able to run the 'java' and 'javac' tools from the terminal in order to build and/or run this project.**

### Installation

Now that you have a JDK installed and available via the terminal, you are ready to install the project from source.

#### Building the project from source

The following steps will allow you to build the project from source:

1. If there exists a directory named `/out/`, remove its contents. Otherwise, create an empty directory named `/out/`.
2. Run the following command from the terminal

        javac -cp src projectCode20280/*.java -d out

The build process is now complete.

## Deployment

### Running the program locally

Execute the following command from the terminal:

    java -cp out projectCode20280.<Data Structure>

where \<Data Structure\> is replaced with the name of the Data Structure to be tested.
This will begin the execution the project code. A sample of tests will be run for the selected Data Structure.
