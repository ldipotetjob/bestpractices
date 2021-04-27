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

### Config Resources 
 ```scala
 /** FailureTrait: Custom model error*/
  def getConfig[T <: Config,R](config:T)(block: T=>R): Either[FailureTrait,R] = {
    Try(block(config)) match {
      case Success(expectedValue) => Right(expectedValue)
      case  Failure(fail) => 	Left(ConfigurationError(
        "conf_error",s"Error reading configuration info. Details: ${fail.getMessage}",fail)
      )
    }
  }
```

### Traversing Monads [Either]

```scala
\\ example 
def returnEither(value: String): Either[NumberFormatException, Int] =
  {
    try {
      Right(value.toInt)
    } catch {
      case ex: NumberFormatException => Left(ex)
    }
  }
```
```scala
returnEither("34").map(x=>x*3) \\ val res7: scala.util.Either[NumberFormatException,Int] = Right(102)

returnEither("uu").map(x=>x*3) \\ val res6: scala.util.Either[NumberFormatException,Int] = Left(java.lang.NumberFormatException: For input string: "uu")
```
### Tracking memory consumption 

```shell
java -XX:NativeMemoryTracking=summary -Xms128M -Xmx512m -XX:+UseG1GC -jar /Users/ldipotet/libsscala/sbt/bin/sbt-launch.jar

run -Dconfig.resource=development.conf
```
ref.: [Configuring Native Memory Tracking](https://www.baeldung.com/native-memory-tracking-in-jvm#nmt)</br>
ref.: [Configuring JVM](https://docs.oracle.com/cd/E15523_01/web.1111/e13814/jvm_tuning.htm#PERFM161) 

### LightBend Products 

#### PlayFramework 

* Java Option on Play Framework memory default values **-Xms128M** and **-Xmx512m** </br>
  ref: [Play Framework Default Memory Configuration](https://github.com/playframework/playframework/blob/master/documentation/manual/working/commonGuide/production/ProductionConfiguration.md#jvm-configuration)
* If your are creating your disribution via Sbt Native Packager you can implement your own Conf following the [Sbt Native Packager Java Option Configuration](https://www.scala-sbt.org/sbt-native-packager/archetypes/java_app/customize.html#via-build-sbt)
</br></br>
There is a trade-off between our memory consumption, the reserved memory an what we really need in our peak of the memory consumption.
