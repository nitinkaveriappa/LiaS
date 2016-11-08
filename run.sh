#!/bin/bash

rm output.txt

         COUNTER=0
         while [  $COUNTER -lt 100 ]; do
             java AVLTreeTest $COUNTER >> output.txt
             let COUNTER=COUNTER+1 
         done
		 
