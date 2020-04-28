# Scala json exercise
This README provides the outline for implementing your own json library.
You get some help from [spray-json](https://github.com/spray/spray-json) for some small things like stringifying
the json to the commonly known format (e.g. `{"userId": 1, "userName": "harry"}`).

## Part 1 - The model
Let's start by modeling a JSON data type. If you need help remembering what the json data type consists of, check 
[here](https://en.wikipedia.org/wiki/JSON). 

1. Start of by implementing and appropriately extending the following model
   * `sealed trait JsonValue`
   
   You can put the file wherever you like, but it might make sense to put it inside the `json` package.

2. Now that you have your JSON model, it would be helpful if you can visualize it nicely. For this, we can get a bit of 
help from the `spray-json` library. Implement the `myJson2SprayJson(json: JsonValue): JsValue` inside 
[MyJsonPrinter](src/main/scala/json/MyJsonPrinter.scala). After you've implemented it you can update your `JsonValue` trait
like 
     
    ```scala
    sealed trait JsonValue {
      override def toString: String = MyJsonPrinter(this)
    }
    ```  

   Now you can nicely stringify any json value you have like `jsonArrayOfNumbers.toString -> [1,2,3]`

## Part 2 - The codecs
1. We're already quite far now. We have a way to model JSON values in a typesafe way inside our codebase and we can nicely
stringify complex json structures. The stringified version can then be used to transfer the json data over the network.
However, as you might have noticed, it's quite cumbersome to create our json data from scala types. Therefore, it makes
sense to introduce a generic interface that would allow the conversion between scala types and our json model 
(and from our json model to scala types). Think a bit how such an interface should look like. You can implement it or 
check the suggested interface below.

<details>
<summary>Example Codec</summary>

```scala
trait JsonCodec[T] {
    def read(json: JsonValue): T
    def write(t: T): JsonValue
}
```
</details> 

2. Now that we have an interface, we can define concrete codec instances for the different scala types that we want to convert.
I'd suggest that you start with some basic types (e.g. `String`,`Int`,`Boolean`,`List`). 

3. We also need a way to deal with json null values. In scala we usually work with the `Option` type in these cases. 
Try to implement such a codec for one or two of the basic types. After your implementation you might already notice that
you're using a lot of the same code. Try to refactor the solution to reuse as much code as possible. 

