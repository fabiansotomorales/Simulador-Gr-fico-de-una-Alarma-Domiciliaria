# makefile begins
# define a variable for compiler flags (JFLAGS)
# define a variable for the compiler (JC)
# define a variable for the Java Virtual Machine (JVM)
# define a variable for de JVM options, in the case of JavaFX
# 	these are --module-path and --add-modules. For the first one, make sure
# 	to use the location where you have JavaFX lib directory.
# define a variable for a parameter. When you run make, you could use:
# make run FILE="Algo.csv" to overwrite its value in this file.
#

JFLAGS = -g
JC = javac
JVM= java
JFX_OPTIONS = --module-path C:\Java\javafx-sdk-17.0.7\lib --add-modules javafx.controls
FILE="config.txt"
RM = del

#
# Clear any default targets for building .class files from .java files; next, we
# provide our own target entry to do this in this makefile.
# make has a set of default targets for different suffixes (like .c.o)
# Currently, clearing the default for .java.class is not necessary since
# make does not have a definition for this target, but later versions of
# make may, so it doesn't hurt to make sure that we clear any default
# definitions for these
#

.SUFFIXES: .java .class

#
# Here is our target entry for creating .class files from .java files
# This is a target entry that uses the suffix rule syntax:
#	DSTS:
#		rule
#	DSTS (Dependency Suffix     Target Suffix)  Ej.:.java.class
# 'TS' is the suffix of the target file (ej. .class), 'DS' is the suffix of the dependency (ej. .java)
# file, and 'rule'  is the rule for building a target
# '$*' is a built-in macro that gets the basename of the current target
# Remember that there must be a <tab> before the command line ('rule')
# you may need to check that your editor is not replacing <tab> by spaces
#

.java.class:
	$(JC) $(JFLAGS) $(JFX_OPTIONS) $*.java

#
# CLASSES is a macro consisting of N words (one for each java source file)
# When a single line is too long, use \<return> to split lines that then will be
# considered as a single line. For example:
# NAME = Camilo \
         Juan
# is understood as
# NAME = Camilo        Juan

CLASSES = \
	Stage1.java \
	House.java \
	MagneticSensor.java \
	MagneticSensorView.java \
	Sensor.java \
	State.java \
	SwitchState.java \
	Window.java \
	WindowView.java

#
# MAIN is a variable with the name of the file containing the main method
#

MAIN = Stage1

#
# the default make target entry
# for this example it is the target classes

default: classes

#
# Next line is a target dependency line
# This target entry uses Suffix Replacement within a macro:
# $(macroname:string1=string2)
# In the words in the macro named 'macroname' replace 'string1' with 'string2'
# Below we are replacing the suffix .java of all words in the macro CLASSES
# with the .class suffix
#

classes: $(CLASSES:.java=.class)

#
# Next two lines contain a target for running the program
# Remember the tab in the second line.
# $(JMV) y $(MAIN) are replaced by their values
#

run: $(MAIN).class
	$(JVM) $(JFX_OPTIONS) $(MAIN) $(FILE)

#
# this line is to remove all unneeded files from
# the directory when we are finished executing(saves space)
# and "cleans up" the directory of unneeded .class files
# RM is a predefined macro in make (RM = rm -f)
#

clean:
	$(RM) *.class

