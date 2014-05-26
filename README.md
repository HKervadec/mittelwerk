# The Mittelwerk Compiler

This is a school project for the 3info from the [INSA-Rennes](http://www.insa-rennes.fr/en.html).

Our goal is to provide a tool chain for the [Orbiter Space Flight Simulator](http://orbit.medphys.ucl.ac.uk/), allowing us to write basic automata without technical knowledge.

## TODO
- Compiler errors management
- Generate functions header for the ShuttleA.h
- Tool to automagically paste the generated code into the vessel
- Argument to set the output files

- Generate a nice Mittelwerk.jar

- Suppress the visual studio requirement


## Grammar
TODO
See the example.mw



## Requirements

### Mittelwerk Compiler
- Jdk
- Javacc 5.0
- Ant
-- Ant plug-in for doxygen
- Python 3

### Orbiter compilation
- Visual Studio
- Orbiter 2010 sdk


## Howto
### Recompile the compiler
- Mv into the grammar directory
- > ant grammar
- > ant compile

### Compile an automata
- Write your automata in a file, for example, t800.mw
- > java -cp class Core.Mittelwerk t800.mw
- You will have a Otto.cpp generated in the result directory

### Recompile the Ship dll
- Open the ship project with Visual Studio
- Add the Otto.cpp file into it
- Find the clbkPostStep function into the ship code (ShuttleA.cpp)
-- Add the following line at the beginning of it
-- postStep(simt, simdt, mjd);
- Go into the ship header (ShuttleA.h), into the private section
-- Add "int m_state = 0;"
-- Add header for every function in the Otto.cpp
-- This part will be suppressed in future version of the tool chain :)

