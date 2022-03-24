#!/bin/bash


#This is the automated task that repeatedely calls an external service for metar data and sends it to our storage service

curl -o subscription.txt -X GET localhost:8080/subscriptions # call to retrieve all subsribed airports and store response in text file

cut -d '[' -f2 subscription.txt > subscription2.txt

sed -i -e 's/\(\"[^",]\+\),\([^",]*\)/\1 \2/g' -e 's/\"//g' -e 's/\(\[\|\]\)\}//g' subscription2.txt


sed -i -e $'s/,/\\\n/g' subscription2.txt  # get only a list of airports by stripping of other irrelevant characters


airports=`cat subscription2.txt`


#loop throught the airport list to get metar data for them and convert the response to json format to send to our metar retrieval service
for aiport in $airports
do
curl -o metar.txt https://tgftp.nws.noaa.gov/data/observations/metar/stations/$aiport.TXT
sed -i '1d' metar.txt
sed -i -e 's/^/{"metarData":"/' metar.txt
sed -i s/$/\"}/ metar.txt
curl -H "Content-Type:application/json" -X POST -d @metar.txt localhost:8080/airport/addMetar
done
    





