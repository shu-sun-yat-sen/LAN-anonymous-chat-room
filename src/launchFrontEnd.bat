@echo on

set MY_VAR=Hello, World!

npm list -g --depth=0 | findstr /i "http-server"

if %errorlevel% equ 0 (
start cmd /k "cd FrontEndPackage\SnakeDist && http-server -g -p 8082 --push-state"
cd  FrontEndPackage\MainDist
http-server -g -p 8081 --push-state
) else (
echo http-server not install.
pause
exit
)


