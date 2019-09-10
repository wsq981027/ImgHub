Jdk1.8，项目使用IDEA开发。数据库使用Mysql(5.1.53)。

Sql文件在根目录的Sql文件夹下，导入即可。

运行前需要在application.properties配置文件中将spring.datasource.url修改为你自己的数据库连接地址，再修改下web.upload-path的路径(你本地的项目磁盘路径)就可以了。 

前台功能都在前缀为Test的文件中，后台功能都在前缀为Admin的文件中
