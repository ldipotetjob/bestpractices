## Best Practices - Scala 

### Style Guide 
* comments:
```scala
/** My comment in single line. */
```
### Configuration Libraries 
We are using the lightbend configuration libraries following [Lightbend config spec](https://github.com/lightbend/config/blob/master/HOCON.md)

### Case class with default values 
```scala
case class ClassName (classField: fieldType1 = Default_Value, classField: fieldTypeN = Default_ValueN)
```
### Closeable resources 
 ```scala
 def processCloseable[T <: Closeable, R](resource: T)(block: T => R): R = {
    try { block(resource) }
    finally { resource.close() }
  }
```

### Tracking memory consumption 

```shell
java -XX:NativeMemoryTracking=summary -Xms128M -Xmx512m -XX:+UseG1GC -jar /Users/ldipotet/libsscala/sbt/bin/sbt-launch.jar

run -Dconfig.resource=development.conf
```
ref.: [Configuring Native Memory Tracking](https://www.baeldung.com/native-memory-tracking-in-jvm#nmt)</br>
ref.: [Configuring JVM](https://docs.oracle.com/cd/E15523_01/web.1111/e13814/jvm_tuning.htm#PERFM161) 
