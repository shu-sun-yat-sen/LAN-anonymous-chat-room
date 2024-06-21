cd server2
java target.classes.com.example.demo1.DemoApplication
timeout /t 5 /nobreak
cd ../../../../..

rem 新建一个终端窗口并在其中执行命令
start cmd /k "cd client-side-application && npm run serve"
