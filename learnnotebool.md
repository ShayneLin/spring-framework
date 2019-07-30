下载源码后一定要先运行：
gradlew cleanIdea :spring-oxm:compileTestJava


官网安装导入的教程：https://github.com/spring-projects/spring-framework/blob/master/import-into-idea.md

1.编译Beans报错：
Build file 'D:\Program Files\spring-framework\spring-beans\spring-beans.gradle' line: 30

处理方法：注释掉对应的三条语句
参考：https://blog.csdn.net/m0_37798534/article/details/79584031



2.spring-core报错，缺失DefaultNamingPolicy类

处理方法：在spring-core里执行：gradle objenesisRepackJar 、    gradle cglibRepackJar
https://blog.csdn.net/kzadmxz/article/details/93985597

3.AnnotationBeanConfigurerAspect 找不到符号 
处理方法：将spring-aspects项目编译器修改，Use compiler设置为Ajc
https://www.youyoustudio.com/2019/03/21/109.html