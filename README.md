#Verions

The program contains two versions, V1.0 implepements the basic version with few 
errors. V1.2 Contains fixed code with some additional features

#Request for Quering specific metar detail

The request parameters for "{airport}/METAR/specific") to get specific request are as follows:

- TEMPERATURE,
- WIND_INFORMATION,
- CLOUD_CEILING,
- RUNWAY_VISIBILITY,
- WIND_CHANGE,
- PRECIPITATION

you can send those together or one at a time

#Automated Task.
There is an automated job in the root.
The job runs every 30 minutes to retrieve the data and send it our service. Please run the following commands to fire the job

first enable the system to execute the bash script

- sudo chmod 755 [your path here]/METAR/cronJob.sh


- nano crontab -e   (    to open cron editor and add the following command to have your system run the job every 30 minute(you can give it your own time))--->


- 30 * * * * [your path here]/METAR/cronJob.sh