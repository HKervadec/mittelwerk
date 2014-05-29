# The Mittelwerk Compiler

This is a school project for the 3info from the [INSA-Rennes](http://www.insa-rennes.fr/en.html).

Our goal is to provide a tool chain for the [Orbiter Space Flight Simulator](http://orbit.medphys.ucl.ac.uk/), allowing us to write basic automata without technical knowledge.

## STATUS
Currently, the compiler is made for and support only one ship, the **ShuttleA**.


## TODO (Ordered by importance)
* More api functions
* Tool to automagically paste the generated code into the vessel
* Compiler errors management
  * Type verification for expression and function call
  * Void does not have returns statements.
* And some warning
  * When two or more state got the START/FINAL token
  * Trying to gotogoto when in the FINAL state.
* Refactor
  * Extrn declaration at the beginning of file, to use function in the vessel code, not in the api
    * It would allow us to change more easily the ship
  * Use of a beautifier third party script
    * This way, we will not have to manage the code indentation, etc.

* Suppress the visual studio requirement

* Generate a nice Mittelwerk.jar


## Grammar
TODO
See the example.mw



## Requirements

### Mittelwerk Compiler
* [Jdk](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) (7 or later)
* [Javacc 5.0](https://java.net/projects/javacc/downloads)
* [Ant](https://ant.apache.org/manual/index.html)
  * [Ant plug-in for doxygen](http://ant-doxygen.blogspot.fr/)
* [Python 3](https://www.python.org/downloads/) or later

### Orbiter compilation
* Visual Studio (we use the 2013 pro version)
* Orbiter 2010 sdk (currently in the git repository)


## Howto
### Recompile the compiler
* Mv into the grammar directory
* > ant grammar
* > ant compile

### Compile an automata
* Write your automata in a file, for example, t800.mw
* > java -cp class Core.Mittelwerk t800.mw <optional_output.cpp> <optional_output.h>
* <optional_output.cpp> contains the code of the automata, in a separate file.
* <optional_output.h> contains the code that must be pasted in the vessel header.

### Recompile the Ship dll
We suppose the code is generated in *Otto.cpp* and *header.h*.
* Open the ship project with Visual Studio
* Add the Otto.cpp file into it
* Find the clbkPostStep function into the ship code (ShuttleA.cpp)
  * Add the following line at the beginning of it
  * postStep(simt, simdt, mjd);
* Go into the ship header (ShuttleA.h), into the private section.
  * Paste the content of header.h into it.

