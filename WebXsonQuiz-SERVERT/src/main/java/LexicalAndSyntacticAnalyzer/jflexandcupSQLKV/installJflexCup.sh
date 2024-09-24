#!/bin/bash

clear

nameClassCup='MyParseSQLKV'
nameSymCup='MySymSQLKV'
nameLexema='LexemaMySQLKV'

rm -f "$nameLexema.java" 

java -jar ../jflexandcup/jflex-full-1.9.1.jar code.jflex 

rm -f "$nameClassCup.java" 
rm -f "$nameSymCup.java" 

java -jar ../jflexandcup/java-cup-11b.jar -parser $nameClassCup -symbols $nameSymCup code.cup