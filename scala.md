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
