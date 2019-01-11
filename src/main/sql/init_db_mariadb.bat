REM open with text editor to edit in eclipse
REM open with system editor to launch in eclipse

REM pour ne pas entrer en conflit avec mysql 3306 
REM (partie de WAMP , MySQL Workbench 8.0 CE)
REM MariaDB a été configuré sur le port 3307 (password "root" for user "root")

cd "%~dp0"
set MARIADB_HOME=C:\Program Files\MariaDB 10.3
"%MARIADB_HOME%/bin/mysql" --port=3307 -u root -p < init_db.sql
pause