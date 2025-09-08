@echo off
rem Gradle wrapper (Windows)
setlocal
set DIRNAME=%~dp0
set CLASSPATH=%DIRNAME%gradle/wrapper/gradle-wrapper.jar

if defined JAVA_HOME (
  set JAVA_EXE=%JAVA_HOME%\bin\java
) else (
  set JAVA_EXE=java
)

"%JAVA_EXE%" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
