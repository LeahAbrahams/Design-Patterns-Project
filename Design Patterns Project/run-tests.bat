@echo off
echo Downloading JUnit...
curl -L -o junit-platform-console-standalone-1.9.3.jar https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.3/junit-platform-console-standalone-1.9.3.jar

echo.
echo Compiling source files...
javac -d out src\main\java\models\*.java src\main\java\Clone\*.java src\main\java\Condition\*.java src\main\java\DataOperations\*.java src\main\java\Logger\*.java src\main\java\Validation\*.java src\main\java\Iteration\*.java src\main\java\builder\*.java

echo.
echo Compiling test files...
javac -cp "out;junit-platform-console-standalone-1.9.3.jar" -d out src\test\java\*.java

echo.
echo Running tests...
java -jar junit-platform-console-standalone-1.9.3.jar --class-path out --scan-class-path

pause
