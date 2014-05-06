#!/usr/bin/python3

# Fuck this I don't need to comment it

from subprocess import *
from sys import exit

testFolder = "test"
testFiles = ["example.mw"]





def pPrint(ab): 
    # print(ab)
    ab = ab.replace(b'\r',b'')
    ab = ab.decode("utf-8")
    ab = '\t' + ab
    ab = ab.replace('\n', '\n\t')
    print(ab)

print("**** Compiling... ****")
# pPrint(check_output("ant clean", shell=True, stderr=STDOUT))
# check_output("ant clean", shell=True, stderr=STDOUT)
try:
    pPrint(check_output("ant grammar", shell=True, stderr=STDOUT))
except CalledProcessError as e:
    pPrint(e.output)
    exit("Javacc il est cassé")

try:
    pPrint(check_output("ant compile", shell=True))
except CalledProcessError as e:
    pPrint(e.output)
    exit("Javac il est cassé")


print()



print("**** Launching tests ****")

for file in testFiles:
    print(">>> " + file)
    try:
        pPrint(check_output("java -cp class Mittelwerk {0}/{1}".format(testFolder, file), \
                            shell=True, stderr=STDOUT))
    except CalledProcessError as e:
        pPrint(e.output)
    
print()

    
print("**** Cleaning Up ****")
# pPrint(check_output("del" +  " *.asm *.yvm", shell=True,stderr=STDOUT))
