# Best Practices - Scala 

## Style Guide 
* comments:
```
/** My comment in single line. */
```
## Configuration Libraries 
We are using the lightbend configuration libraries following [Lightbend config spec](https://github.com/lightbend/config/blob/master/HOCON.md)

## Case class with default values 
```
case class ClassName (classField: fieldType1 = Default_Value, classField: fieldTypeN = Default_ValueN)
```
