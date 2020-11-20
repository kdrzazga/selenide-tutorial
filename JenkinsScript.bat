REM 3 separate steps of Build procedure

REM STEP 1
IF EXIST "webautomation-tutorial" (
  ECHO Directory webautomation-tutorial exists, deleting
  RMDIR "webautomation-tutorial" /S /Q
) ELSE (
  ECHO Directory webautomation-tutorial does not exist, cloning...
)
git clone https://github.com/kdrzazga/webautomation-tutorial.git
cd webautomation-tutorial
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_261
gradle clean

REM STEP 2
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_261
gradle build

REM STEP 3
SET ALLURE_HOME=C:\Users\kdrzazga\scoop\apps\allure\current
SET JAVA_HOME=C:\Program Files\Java\jdk1.8.0_261
C:\allure-2.13.6\bin\allure generate
echo Done
