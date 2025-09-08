#!/usr/bin/env sh
# Gradle wrapper (UNIX)
# NOTE: keep this file executable (chmod +x)

prog="$0"
if [ -n "$JAVA_HOME" ]; then
  JAVA_EXE="$JAVA_HOME/bin/java"
else
  JAVA_EXE="java"
fi

# Use bundled wrapper jar
BASEDIR=$(dirname "$0")
CLASSPATH="$BASEDIR/gradle/wrapper/gradle-wrapper.jar"

exec "$JAVA_EXE" -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
