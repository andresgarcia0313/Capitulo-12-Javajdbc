docker run --name mi_base_datos_mysql \
-e MYSQL_ROOT_PASSWORD=mi_contraseña \
-e MYSQL_DATABASE=mi_base_datos \
-e MYSQL_USER=mi_usuario \
-e MYSQL_PASSWORD=mi_contraseña_usuario \
-p 3306:3306 \
-v mi_volumen_mysql:/var/lib/mysql \
-d mysql:latest

GRANT ALL PRIVILEGES ON mi_base_de_datos.* TO 'mi_usuario'@'%' IDENTIFIED BY 'mi_contraseña_usuario';
FLUSH PRIVILEGES;

https://dev.mysql.com/downloads/connector/j/