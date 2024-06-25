@echo on

echo Starting client-side application...

npm list -g --depth=0 | findstr /i "http-server"

if %errorlevel% equ 0 (
start cmd /k "cd FrontEndPackage\SnakeDist && http-server -g -p 8082 --push-state"
start cmd /k "cd FrontEndPackage\MainDist && http-server -g -p 8081 --push-state"
) else (
echo http-server not install.
pause
exit
)

if %errorlevel% neq 0 (
    echo Failed to start client-side application!
    exit /b %errorlevel%
)

timeout /t 5 /nobreak

echo Starting server-side application...
cd server2/
java -jar demo1-0.0.1-SNAPSHOT.jar

if %errorlevel% neq 0 (
    echo Failed to start server-side application!
    exit /b %errorlevel%
)

echo Both applications started successfully.
pause