#$1 == arquivo a ser lido

javac *.java
cat $1 | java Main
rm *.class
