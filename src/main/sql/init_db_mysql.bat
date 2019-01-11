REM open with text editor to edit in eclipse
REM open with system editor to launch in eclipse

cd "%~dp0"
set MYSQL_HOME=C:\Program Files\MySQL\MySQL Workbench 8.0 CE
"%MYSQL_HOME%/mysql" -u root -p < init_db.sql
pause