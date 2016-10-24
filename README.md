HR Assistant
===================


The Job of a Human Resources Employee is hard work, especially when it comes to handle hundreds or thousands of Job Applications.
Often, the Cover Letters are coming in unstructured formats.. Keeping these data in order and prioritize interesting job applications can be difficult.

This Bluemix application is leveraging IBM Watson services which are helping:
* Using the Tone Analyzer to get the emotions of the cover letter and job descriptions.
* Using Personality Insights to get more insights of the applicant
* Using Alchemy Language to get the keywords used in the text in jobs and job applications.

The Application is available here: https://hrassistant.eu-gb.mybluemix.net/

The handling of the application should be self-explaining. However, here are some key points:

The application make use of 2 external OSGi plugins to do the heavy lifting of JSON Conversion and connection with Cloudant.
The OSGi plugins, including the update sites can be found in the Bitbucket repositories.

Cloudant connector - https://bitbucket.org/flinden68/cloudant-connector

Jackson Mapper plugin - https://bitbucket.org/flinden68/jackson-json-mapper-plugin 

###Login
The application has no proper login yet. In order to keep spam bots away, we integrated a simple Demo-Login. You can choose whatever use name you like, just enter the correct password which will be provided in the login form.
The Login also lets you choose a role. To access all features of the app, choose the Role “Administrator”.
 

###Job Listing (Role “Job Applicant” and “Administrator”)
The job applicants seeking a job can browse and search for available job offers. By pressing “apply” the job applicant can enter and submit his personal data and most important: the cover letter.
 

###Dashboard (Role “HR / Backoffice” and “Administrator”)
Data is presented to the HR / Backoffice employee in a graphical manner. Only recent data is being considered in the charts. It is used to have a quick overview of recent data.
###Backoffice (Role “HR / Backoffice” and “Administrator”)
Jobs, Job Applications, Companies and Configurations can be managed in a convienient way. The HR / Backoffice employee can also analyze individual job applications and jobs with IBM Watson Services.
