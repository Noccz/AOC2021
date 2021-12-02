#!/bin/bash

#AOC_1_1
input="input/input_aoc1"
nrOfIncs=0
prevline=-1
currIndex=0
lastLine=$(wc -l $input)

while IFS= read -r line
do
  if [[ $prevline == -1 ]]; then
  	prevline=$line
  	((nrOfIncs=$nrOfIncs+1))
  else
  	if [[ $line > $prevline ]]; then
  		((nrOfIncs=$nrOfIncs+1))
  	fi
  fi
  prevline=$line
done < "$input"
echo "AOC_1_1 - nrOfIncs: $nrOfIncs"

# AOC_1_2
nrOfIncs=0
inputValues=($(cat $input))
inputLength=${#inputValues[@]}
threesumVal=0
prevVal=-1

while IFS= read -r line
do
	if [[ $currIndex+2 -gt $inputLength ]]; then
		break
	fi
	threesumVal=$((inputValues[$currIndex]+inputValues[$((currIndex+1))]+inputValues[$((currIndex+2))]))

	if [[ $prevVal == -1 ]]; then
  	  prevVal=$threesumVal
    else
  	  if [[ $threesumVal -gt $prevVal ]]; then
  		  ((nrOfIncs=$nrOfIncs+1))
  	  fi
  	fi
  	prevVal=$threesumVal
	((currIndex=currIndex+1))
done < "$input"
echo "AOC_1_2 - nrOfIncs: $nrOfIncs"
echo
